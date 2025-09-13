import csv from 'csv-parser';
import fs from 'fs';
import { Credit, MediaItem, MediaType } from '../models/index.js';
import { Loader } from './loader.js';

export class JaizelcespedesLoader implements Loader {
  getLoaderName(): string {
    return 'jaizelcespedes';
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
    const mediaItems: MediaItem[] = [];
    const readable = fs
      .createReadStream('data/media_items.csv', 'utf-8')
      .pipe(csv());
    
    for await (const row of readable) {
      const { id, type, title, year } = row;
      
      // Convert the string type to MediaType enum
      let mediaType: MediaType;
      switch (type) {
        case 'movie':
          mediaType = MediaType.Movie;
          break;
        case 'tv_show':
          mediaType = MediaType.TVShow;
          break;
        case 'documentary':
          mediaType = MediaType.Documentary;
          break;
        default:
          console.warn(`Unknown media type: ${type}. Defaulting to Movie.`);
          mediaType = MediaType.Movie;
      }
      
      // Create MediaItem with empty credits array for now
      // Credits will be associated separately in loadData method
      const mediaItem = new MediaItem(
        id,
        title,
        mediaType,
        parseInt(year, 10),
        []
      );
      
      mediaItems.push(mediaItem);
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
