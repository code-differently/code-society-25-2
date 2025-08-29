import csv from 'csv-parser';
import fs from 'fs';
import { Credit, MediaItem, MediaType } from '../models/index.js';
import { Loader } from './loader.js';

export class TobyEvansLoader implements Loader {
  getLoaderName(): string {
    return 'tobyevans';
  }

  async loadData(): Promise<MediaItem[]> {
    const credits = await this.loadCredits();
    const mediaItems = await this.loadMediaItems();
    console.log(
      `Loaded ${credits.length} credits and ${mediaItems.length} media items`,
    );
    return [...mediaItems.values()];
  }

  async loadMediaItems(): Promise<MediaItem[]> {
    const mediaItems: MediaItem[] = [];
    const readable = fs
      .createReadStream('data/media_items.csv', 'utf-8')
      .pipe(csv());
    for await (const row of readable) {
      const { id, type, title, year } = row;
      const mediaType = type as MediaType;
      mediaItems.push(
        new MediaItem(id, title, mediaType, parseInt(year, 10), []),
      );
    }
    return mediaItems;
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
}
