import csv from 'csv-parser';
import fs from 'fs';
import { Credit, MediaItem, MediaType, Role } from '../models/index.js';
import { Loader } from './loader.js';

export class DeanWalstonLoader implements Loader {
  getLoaderName(): string {
    return 'deanwalston';
  }

  async loadData(): Promise<MediaItem[]> {
    const credits = await this.loadCredits();
    const mediaItems = await this.loadMediaItems();

    console.log(
      `Loaded ${credits.length} credits and ${mediaItems.length} media items`,
    );

    // Combine credits with media items
    const mediaItemsMap = new Map<string, MediaItem>();
    
    // Create a map of media items by ID for easy lookup
    mediaItems.forEach(item => {
      mediaItemsMap.set(item.getId(), item);
    });

    // Associate credits with their corresponding media items
    credits.forEach(credit => {
      const mediaItem = mediaItemsMap.get(credit.getMediaItemId());
      if (mediaItem) {
        mediaItem.addCredit(credit);
      }
    });

    return [...mediaItemsMap.values()];
  }

  async loadMediaItems(): Promise<MediaItem[]> {
    const mediaItems = [];
    const readable = fs
      .createReadStream('data/media_items.csv', 'utf-8')
      .pipe(csv());
    for await (const row of readable) {
      const { id, title, type, year } = row;
      // Parse the type as MediaType and year as number
      const mediaType = type as MediaType;
      const releaseYear = parseInt(year);
      // Create MediaItem with empty credits initially (they'll be added later)
      mediaItems.push(new MediaItem(id, title, mediaType, releaseYear, []));
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
      // Parse the role as Role enum
      const roleEnum = role as Role;
      credits.push(new Credit(media_item_id, name, roleEnum));
    }
    return credits;
  }
}
