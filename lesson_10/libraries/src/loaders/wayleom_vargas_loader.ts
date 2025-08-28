import csv from 'csv-parser';
import fs from 'fs';
import { Credit, MediaItem } from '../models/index.js';
import { Loader } from './loader.js';

export class WayleomVargasLoader implements Loader {
  getLoaderName(): string {
    return 'wayleom_vargas_loader';
  }

  async loadData(): Promise<MediaItem[]> {
    const credits = await this.loadCredits(); // list of credits
    const mediaItems = await this.loadMediaItems(); // list of media items

    // Map media items by their ID for easy lookup
    const mediaItemsMap = new Map<string, MediaItem>();
    mediaItems.forEach((item) => {
      mediaItemsMap.set(item.getId(), item);
    });

    // Load credits into their respective media items
    credits.forEach((credit) => {
      const mediaItem = mediaItemsMap.get(credit.getMediaItemId());
      if (mediaItem) {
        mediaItem.addCredit(credit);
      }
    });

    console.log(
      `Loaded ${credits.length} credits and ${mediaItems.length} media items`,
    );

    return Array.from(mediaItemsMap.values());
  }

  async loadMediaItems(): Promise<MediaItem[]> {
    const mediaItems: MediaItem[] = [];
    const readable = fs
      .createReadStream('data/media_items.csv', 'utf-8')
      .pipe(csv());

    for await (const row of readable) {
      const { id, title, type, year } = row;
      mediaItems.push(new MediaItem(id, title, type, year, []));
    }

    return mediaItems;
  }

  async loadCredits(): Promise<Credit[]> {
    const credits = [];
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
