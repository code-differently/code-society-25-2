import csv from 'csv-parser';
import fs from 'fs';
import { Credit, MediaItem, MediaType } from '../models/index.js';
import { Loader } from './loader.js';

export class CalvinRobinsonLoader implements Loader {
  getLoaderName(): string {
    return 'calvinrobinson';
  }

  async loadData(): Promise<MediaItem[]> {
    const credits = await this.loadCredits();
    const mediaItems = await this.loadMediaItems();

    // Build a map from media item ID to MediaItem object
    const mediaItemMap = new Map<string, MediaItem>();
    for (const item of mediaItems) {
      mediaItemMap.set(item.getId(), item);
    }

    // Associate credits with their corresponding media items
    for (const credit of credits) {
      const mediaItem = mediaItemMap.get(credit.getMediaItemId());
      if (mediaItem) {
        mediaItem.addCredit(credit);
      }
    }

    console.log(
      `Loaded ${credits.length} credits and ${mediaItems.length} media items`,
    );

    return [...mediaItemMap.values()];
  }

  async loadMediaItems(): Promise<MediaItem[]> {
    const mediaItems: MediaItem[] = [];

    return new Promise((resolve, reject) => {
      const readable = fs
        .createReadStream('data/media_items.csv', 'utf-8')
        .pipe(csv());

      readable.on('data', (row) => {
        const { id, type, title, year } = row;
        const mediaType = type as MediaType;
        mediaItems.push(
          new MediaItem(id, title, mediaType, parseInt(year), []),
        );
      });

      readable.on('end', () => {
        resolve(mediaItems);
      });

      readable.on('error', (error) => {
        reject(error);
      });
    });
  }

  async loadCredits(): Promise<Credit[]> {
    const credits: Credit[] = [];

    return new Promise((resolve, reject) => {
      const readable = fs
        .createReadStream('data/credits.csv', 'utf-8')
        .pipe(csv());

      readable.on('data', (row) => {
        const { media_item_id, role, name } = row;
        credits.push(new Credit(media_item_id, name, role));
      });

      readable.on('end', () => {
        resolve(credits);
      });

      readable.on('error', (error) => {
        reject(error);
      });
    });
  }
}
