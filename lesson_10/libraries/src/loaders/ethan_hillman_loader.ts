import csv from 'csv-parser';
import fs from 'fs';
import { MediaItem } from '../models/media_item.js';
import { Loader } from './loader.js';

export class EthanHillmanLoader implements Loader {
  getLoaderName(): string {
    return 'ethanhillman';
  }

  async loadData(): Promise<MediaItem[]> {
    const mediaItems = await this.loadMediaItems();

    console.log(`Loaded ${mediaItems.length} media items`);

    return [...mediaItems.values()];
  }

  async loadMediaItems(): Promise<MediaItem[]> {
    const mediaItems = [];
    const readable = fs
      .createReadStream('data/media_items.csv', 'utf-8')
      .pipe(csv());
    for await (const row of readable) {
      const { id, title, type, release_year } = row;
      mediaItems.push(
        new MediaItem(id, title, type, parseInt(release_year), []),
      );
    }
    return mediaItems;
  }
}
