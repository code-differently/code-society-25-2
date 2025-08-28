import csv from 'csv-parser';
import fs from 'fs';
import { Credit, MediaItem, MediaType } from '../models/index.js';
import { Loader } from './loader.js';

export class TaliaCrockettLoader implements Loader {
  getLoaderName(): string {
    return 'taliacrockett';
  }

  async loadData(): Promise<MediaItem[]> {
    const credits = await this.loadCredits();
    const mediaItems = await this.loadMediaItems();
    const creditsByMediaId = new Map<string, Credit[]>();

    for (const credit of credits) {
      const mediaId = credit.getMediaItemId();
      if (!creditsByMediaId.has(mediaId)) {
        creditsByMediaId.set(mediaId, []);
      }
      creditsByMediaId.get(mediaId)!.push(credit);
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

    return mediaItems;
  }

  async loadMediaItems(): Promise<MediaItem[]> {
    const mediaItems = [];
    const readable = fs
      .createReadStream('data/media_items.csv', 'utf-8')
      .pipe(csv());
    for await (const row of readable) {
      const { id, type, title, genre, year } = row;
      const mediaType = type as MediaType;
      mediaItems.push(new MediaItem(id, title, mediaType, parseInt(year), []));
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
