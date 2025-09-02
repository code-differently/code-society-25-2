import csv from 'csv-parser';
import * as fs from 'fs';
import * as path from 'path';
import { Credit, MediaItem, MediaType } from '../models/index.js';
import { Loader } from './loader.js';

export class TrishtanhusserLoader implements Loader {
  getLoaderName(): string {
    return 'trishtanhusser';
  }

  async loadData(): Promise<MediaItem[]> {
    const credits = await this.loadCredits();
    const mediaItems = await this.loadMediaItems();

   
    const mediaItemsMap = new Map<string, MediaItem>();
    mediaItems.forEach((item) => {
      mediaItemsMap.set(item.getId(), item);
    });

    credits.forEach((credit) => {
      const mediaItem = mediaItemsMap.get(credit.getMediaItemId());
      if (mediaItem) {
        mediaItem.addCredit(credit);
      }
    });

    console.log(
      `Loaded ${credits.length} credits and ${mediaItems.length} media items`,
    );

    return [...mediaItemsMap.values()];
  }

  async loadMediaItems(): Promise<MediaItem[]> {
    const mediaItems: MediaItem[] = [];
    const filePath = path.join(process.cwd(), 'data', 'media_items.csv');
    const readable = fs.createReadStream(filePath, 'utf-8').pipe(csv());

    for await (const row of readable) {
      const { id, type, title, year } = row;

      
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
          console.warn(`Unknown media type: ${type}, defaulting to Movie`);
          mediaType = MediaType.Movie;
      }

      const mediaItem = new MediaItem(
        id,
        title,
        mediaType,
        parseInt(year, 10),
        [],
      );

      mediaItems.push(mediaItem);
    }

    return mediaItems;
  }

  async loadCredits(): Promise<Credit[]> {
    const credits: Credit[] = [];
    const filePath = path.join(process.cwd(), 'data', 'credits.csv');
    const readable = fs.createReadStream(filePath, 'utf-8').pipe(csv());
    for await (const row of readable) {
      const { media_item_id, role, name } = row;
      credits.push(new Credit(media_item_id, name, role));
    }
    return credits;
  }
}
