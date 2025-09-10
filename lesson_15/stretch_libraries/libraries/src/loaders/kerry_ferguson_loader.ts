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

    // Group credits by media item id
    const creditsByMediaId = new Map<string, Credit[]>();
    for (const credit of credits) {
      const mediaItemId = credit.getMediaItemId();
      const mediaCredits = creditsByMediaId.get(mediaItemId) || [];
      mediaCredits.push(credit);
      creditsByMediaId.set(mediaItemId, mediaCredits);
    }

    // Associate credits with media items
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
    // Read the entire CSV file and split into lines
    const lines = fs
      .readFileSync('data/media_items.csv', 'utf-8')
      .split('\n')
      .filter((line) => line.trim() !== ''); // Remove empty lines

    // Skip header row (first line contains column names)
    const dataLines = lines.slice(1);

    // Parse each line and create MediaItem objects
    return dataLines.map((line) => {
      // Split CSV line by commas and destructure the values
      const [id, type, title, , year] = line.split(',');
      return new MediaItem(
        id,
        title,
        type as MediaType,
        parseInt(year, 10), // Convert year string to number using base 10
        [], // Empty credits array - credits will be loaded separately
      );
    });
  }

  async loadCredits(): Promise<Credit[]> {
    const credits = [];

    // Create a readable stream from the CSV file and pipe it through csv-parser
    const readable = fs
      .createReadStream('data/credits.csv', 'utf-8')
      .pipe(csv()); // csv-parser automatically handles CSV parsing and headers

    // Iterate through each row asynchronously
    for await (const row of readable) {
      // Destructure the CSV columns from each row object
      const { media_item_id, role, name } = row;
      credits.push(new Credit(media_item_id, name, role));
    }
    return credits;
  }
}
