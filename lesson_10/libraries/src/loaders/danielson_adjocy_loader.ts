import csv from 'csv-parser';
import fs from 'fs';
import { Credit, MediaItem } from '../models/index.js';
import { Loader } from './loader.js';

export class DanielsonAdjocyLoader implements Loader {
  getLoaderName(): string {
    return 'danielsonadjocy';
  }

  async loadData(): Promise<MediaItem[]> {
    const credits = await this.loadCredits();
    const mediaItems = await this.loadMediaItems();

    // Group credits by media_item_id using Map
    const creditsByMediaId = new Map<string, Credit[]>();

    for (const credit of credits) {
      if (!creditsByMediaId.has(credit.getMediaItemId())) {
        creditsByMediaId.set(credit.getMediaItemId(), [credit]);
      } else {
        const arr = creditsByMediaId.get(credit.getMediaItemId());
        if (arr) {
          arr.push(credit);
        }
      }
    }

    // Attach credits to each media item
    for (const item of mediaItems) {
      const itemCredits = creditsByMediaId.get(item.getId());
      if (!itemCredits) {
        continue;
      }
      itemCredits.forEach((credit) => item.addCredit(credit));
    }

    console.log(
      `Loaded ${credits.length} credits and ${mediaItems.length} media items`,
    );

    return [...mediaItems.values()];
  }

  async loadMediaItems(): Promise<MediaItem[]> {
    const items = [];
    const readable = fs
      .createReadStream('data/media_items.csv', 'utf-8')
      .pipe(csv());

    for await (const row of readable) {
      const {id, type, title, genre, year } = row;

      items.push(new MediaItem(id, title, type, year, []));
    }
    return items;
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