import csv from 'csv-parser';
import fs from 'fs';
import { Credit, MediaItem } from '../models/index.js';
import type { MediaType, Role } from '../models/index.js';
import { Loader } from './loader.js';

/** Map CSV strings to your MediaType union/enum. Adjust cases if needed. */
function toMediaType(raw: unknown): MediaType {
  const v = String(raw ?? '')
    .trim()
    .toLowerCase();
  switch (v) {
    case 'movie':
    case 'film':
      return 'movie' as MediaType;
    case 'tv':
    case 'tv_show':
    case 'show':
    case 'series':
      return 'tv' as MediaType;
    case 'book':
    case 'novel':
      return 'book' as MediaType;
    case 'game':
    case 'videogame':
      return 'game' as MediaType;
    default:
      return 'movie' as MediaType;
  }
}

/** Map CSV strings to your Role union/enum. Adjust cases if needed. */
function toRole(raw: unknown): Role {
  const v = String(raw ?? '')
    .trim()
    .toLowerCase();
  switch (v) {
    case 'actor':
      return 'Actor' as Role;
    case 'director':
      return 'Director' as Role;
    case 'writer':
      return 'Writer' as Role;
    case 'producer':
      return 'Producer' as Role;
    case 'composer':
      return 'Composer' as Role;
    default:
      return 'Actor' as Role;
  }
}

export class JoyBrownLoader implements Loader {
  /** Cache: media_item_id -> credits[] so we can attach to MediaItem */
  private creditsByMediaId: Map<string, Credit[]> = new Map();

  getLoaderName(): string {
    return 'joybrown';
  }

  async loadData(): Promise<MediaItem[]> {
    const credits = await this.loadCredits();

    // Build lookup table for quick attach during media item creation
    this.creditsByMediaId.clear();
    for (const c of credits) {
      // Be resilient to different field names on Credit
      const key = String(
        (c as any).media_item_id ?? (c as any).mediaItemId ?? '',
      ).trim();
      if (!key) continue;
      const arr = this.creditsByMediaId.get(key) ?? [];
      arr.push(c);
      this.creditsByMediaId.set(key, arr);
    }

    const mediaItems = await this.loadMediaItems();

    console.log(
      `Loaded ${credits.length} credits and ${mediaItems.length} media items`,
    );

    return mediaItems;
  }

  async loadMediaItems(): Promise<MediaItem[]> {
    const items: MediaItem[] = [];

    const readable = fs
      .createReadStream('data/media_items.csv', 'utf-8')
      .pipe(csv());

    for await (const row of readable) {
      const id: string = String(row.id ?? row.media_item_id ?? '').trim();
      const title: string = String(row.title ?? row.name ?? '').trim();

      const type: MediaType = toMediaType(
        row.type ?? row.media_type ?? row.format,
      );

      // year must be a number (constructor requires number)
      const yearRaw = row.year ?? row.release_year ?? '';
      const yearParsed = Number.parseInt(String(yearRaw).trim(), 10);
      const year: number = Number.isFinite(yearParsed) ? yearParsed : 0;

      // Grab the credits for this media item id (Iterable<Credit>)
      const creditsForItem: Credit[] = this.creditsByMediaId.get(id) ?? [];

      // IMPORTANT: MediaItem expects the 5th argument to be Iterable<Credit>
      items.push(new MediaItem(id, title, type, year, creditsForItem));
    }

    return items;
  }

  async loadCredits(): Promise<Credit[]> {
    const credits: Credit[] = [];

    const readable = fs
      .createReadStream('data/credits.csv', 'utf-8')
      .pipe(csv());

    for await (const row of readable) {
      const mediaItemId: string = String(row.media_item_id ?? '').trim();
      const name: string = String(row.name ?? '').trim();
      const role: Role = toRole(row.role);

      credits.push(new Credit(mediaItemId, name, role));
    }

    return credits;
  }
}
