import csv from 'csv-parser';
import fs from 'fs';
import { Credit, MediaItem } from '../models/index.js';
import { Loader } from './loader.js';

export class JoneemckellarLoader implements Loader {
  getLoaderName(): string {
    return 'joneemckellar';
  }

  async loadData(): Promise<MediaItem[]> {
    const credits = await this.loadCredits();
    const mediaItems = await this.loadMediaItems(credits);

    console.log(
      `Loaded ${credits.length} credits and ${mediaItems.length} media items`,
    );

    return mediaItems;
  }

  // Pass all credits to associate with each media item
  async loadMediaItems(credits: Credit[]): Promise<MediaItem[]> {
    const mediaItems: MediaItem[] = []; // Declare the array
    const readable = fs
      .createReadStream('data/media_items.csv', 'utf-8')
      .pipe(csv());

    for await (const row of readable) {
      const { id, title, type, release_year } = row;

      // Filter credits for this specific media item
      const itemCredits = credits.filter((c) => c.getMediaItemId() === id);

      // Handle missing or empty release_year values
      const parsedReleaseYear =
        release_year && release_year.trim() !== ''
          ? parseInt(release_year, 10)
          : 0;

      mediaItems.push(
        new MediaItem(id, title, type, parsedReleaseYear, itemCredits),
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
