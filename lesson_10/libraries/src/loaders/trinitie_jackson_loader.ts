import csv from 'csv-parser';
import fs from 'fs';
import { Credit, MediaItem } from '../models/index.js';
import { Loader } from './loader.js';

export class TrinitieJacksonLoader implements Loader {
  getLoaderName(): string {
    return 'trinitiejackson';
  }

  async loadData(): Promise<MediaItem[]> {
    const credits = await this.loadCredits();
    const mediaItems = await this.loadMediaItems();
    const creditsByMediaId = new Map<string, Credit[]>();
    
    for (const credit of credits) {
      const mediaItemId = credit.getMediaItemId();
      const mediaCredits = creditsByMediaId.get(mediaItemId) || [];
      mediaCredits.push(credit);
      creditsByMediaId.set(mediaItemId, mediaCredits);
    }

    for (const mediaItem of mediaItems) {
      const itemCredits = creditsByMediaId.get(mediaItem.getId()) || [];
      for (const credit of itemCredits) {
        mediaItem.addCredit(credit);
      }
    }

    console.log(
      `Loaded ${credits.length} credits and ${mediaItems.length} media items`,
    );

    return [...mediaItems.values()];
  }

  async loadMediaItems(): Promise<MediaItem[]> {
    const mediaItems = [];
    const readable = fs
      .createReadStream('data/media_items.csv', 'utf-8')
      .pipe(csv());
    for await (const row of readable) {
      const { id, type, title, year } = row;
      mediaItems.push(new MediaItem(id, title, type, parseInt(year, 10), []));
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