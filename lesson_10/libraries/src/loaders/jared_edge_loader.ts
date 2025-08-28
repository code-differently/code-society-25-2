import csv from 'csv-parser';
import fs from 'fs';
import { Credit, MediaItem, MediaType } from '../models/index.js';
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

    for (const c of credits) {
      const mediaItemId = this.creditToMediaItemId(c);
      const item = byId.get(mediaItemId);
      if (item) item.addCredit(c);
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
    switch (v) {
      case 'movie':
        return (
          (MediaType as any).Movie ??
          (MediaType as any).MOVIE ??
          ('movie' as unknown as MediaType)
        );
      case 'tv_show':
        return (
          (MediaType as any).TvShow ??
          (MediaType as any).TV_SHOW ??
          ('tv_show' as unknown as MediaType)
        );
      case 'documentary':
        return (
          (MediaType as any).Documentary ??
          (MediaType as any).DOCUMENTARY ??
          ('documentary' as unknown as MediaType)
        );
      default:
        throw new Error(`Unknown media type: ${raw}`);
    }
  }

  async loadCredits(): Promise<Credit[]> {
    const credits: Credit[] = [];
    const readable = fs
      .createReadStream('data/credits.csv', 'utf-8')
      .pipe(csv());
    for await (const row of readable) {
      const { media_item_id, role, name } = row;
      credits.push(new Credit(media_item_id, name, role));
    }
    return credits;
  }

  private creditToMediaItemId(c: Credit): string {
    const id =
      (typeof (c as any).getMediaItemId === 'function' &&
        (c as any).getMediaItemId()) ??
      (c as any).mediaItemId ??
      (c as any).media_item_id;

    if (id == null) {
      throw new Error('Unable to determine media item id from Credit');
    }
    return String(id);
  }
}
