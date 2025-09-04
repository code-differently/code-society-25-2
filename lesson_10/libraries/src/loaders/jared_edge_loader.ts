import csv from 'csv-parser';
import fs from 'fs';
import { Credit, MediaItem, MediaType, Role } from '../models/index.js';
import { Loader } from './loader.js';

export class JaredEdgeLoader implements Loader {
  getLoaderName(): string {
    return 'jarededge';
  }

  async loadData(): Promise<MediaItem[]> {
    const [items, credits] = await Promise.all([
      this.loadMediaItems(),
      this.loadCredits(),
    ]);

    const byId = new Map(items.map((i) => [i.getId(), i]));

    for (const credit of credits) {
      const mediaItemId = this.creditToMediaItemId(credit);
      const item = byId.get(mediaItemId);
      if (item) item.addCredit(credit);
    }

    return [...byId.values()];
  }

  async loadMediaItems(): Promise<MediaItem[]> {
    const items: MediaItem[] = [];
    const readable = fs
      .createReadStream('data/media_items.csv', 'utf-8')
      .pipe(csv());

    for await (const row of readable) {
      const id = String(row.id).trim();
      const title = String(row.title).trim();
      const releaseYear = Number.parseInt(String(row.year), 10);
      const mediaType = this.toMediaType(String(row.type));

      if (Number.isNaN(releaseYear)) {
        throw new Error(`Invalid year "${row.year}" for id=${id} (${title})`);
      }

      items.push(new MediaItem(id, title, mediaType, releaseYear, []));
    }

    return items;
  }

  private toMediaType(raw: string): MediaType {
    const v = raw.trim().toLowerCase();
    if (v === 'movie') return MediaType.Movie;
    if (v === 'tv_show') return MediaType.TVShow;
    if (v === 'documentary') return MediaType.Documentary;
    throw new Error(`Unknown media type: ${raw}`);
  }

  async loadCredits(): Promise<Credit[]> {
    const credits: Credit[] = [];
    const readable = fs
      .createReadStream('data/credits.csv', 'utf-8')
      .pipe(csv());
    for await (const row of readable) {
      const mediaItemId = String(row.media_item_id).trim();
      const name = String(row.name).trim();
      const role = this.toRole(String(row.role));
      credits.push(new Credit(mediaItemId, name, role));
    }
    return credits;
  }

  private toRole(raw: string): Role {
    const normalized = raw.trim();
    const values = Object.values(Role) as string[];

    const match = values.find(
      (v) => v === normalized || v.toLowerCase() === normalized.toLowerCase(),
    );
    if (match) return match as Role;

    const map = Role as unknown as Record<string, Role>;
    const key = Object.keys(map).find(
      (k) => k.toLowerCase() === normalized.toLowerCase(),
    );
    if (key) return map[key];

    throw new Error(`Unknown role: ${raw}`);
  }

  private creditToMediaItemId(c: Credit): string {
    return c.getMediaItemId();
  }
}
