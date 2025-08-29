import csv from 'csv-parser';
import fs from 'fs';
import { Credit, MediaItem } from '../models/index.js';
import { Loader } from './loader.js';

export class EvanderBlueLoader implements Loader {
  getLoaderName(): string {
    return 'evanderblue';
  }

  async loadData(): Promise<MediaItem[]> {
    const credits = await this.loadCredits();
    const mediaItems = await this.loadMediaItems();

    // Merge credits into media items by id (for extra credit)
    const creditsById = new Map();
    for (const credit of credits) {
      const id = credit.getMediaItemId();
      if (!creditsById.has(id)) {
        creditsById.set(id, []);
      }
      creditsById.get(id).push(credit);
    }

    const mergedMediaItems = mediaItems.map((item) => {
      const id = item.getId();
      const itemCredits = creditsById.get(id) || [];
      // Use public getters only, do not change MediaItem class
      return new MediaItem(
        id,
        item.getTitle(),
        (item as any).type, // Use type as stored in constructor
        item.getReleaseYear(),
        itemCredits,
      );
    });

    console.log(
      `Loaded ${credits.length} credits and ${mediaItems.length} media items`,
    );

    return mergedMediaItems;
  }

  async loadMediaItems(): Promise<MediaItem[]> {
    const mediaItems = [];
    const readable = fs
      .createReadStream('data/media_items.csv', 'utf-8')
      .pipe(csv());
    for await (const row of readable) {
      const { id, type, title, year } = row;
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
