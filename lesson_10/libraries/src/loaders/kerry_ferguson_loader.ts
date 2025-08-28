import csv from 'csv-parser';
import fs from 'fs';
import { Credit, MediaItem, MediaType } from '../models/index.js';
import { Loader } from './loader.js';

export class KerryFergusonLoader implements Loader {
  getLoaderName(): string {
    return 'kerryferguson';
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
    const lines = fs
      .readFileSync('data/media_items.csv', 'utf-8')
      .split('\n')
      .filter((line) => line.trim() !== '');

    // Skip header row
    const dataLines = lines.slice(1);

    return dataLines.map((line) => {
      const [id, type, title, , year] = line.split(',');
      return new MediaItem(
        id,
        title,
        type as MediaType,
        parseInt(year, 10),
        [], // Empty credits array - credits will be loaded separately
      );
    });
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
