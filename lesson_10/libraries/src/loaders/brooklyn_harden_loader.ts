import csv from 'csv-parser';
import fs from 'fs';
import { Credit, MediaItem, MediaType } from '../models/index.js';
import { Loader } from './loader.js';

export class BrooklynHardenLoader implements Loader {
  getLoaderName(): string {
    return 'brooklynharden';
  }

  async loadData(): Promise<MediaItem[]> {
    const credits = await this.loadCredits();
    const mediaItems = await this.loadMediaItems();
    credits.forEach(credit => {
      const media_item = mediaItems.find(media_item => media_item.getId() === credit.getMediaItemId());
      if(media_item) {
        media_item.addCredit(credit);
      
    }
        //compare ids of credit id and media item id
    
    })


    console.log(
      `Loaded ${credits.length} credits and ${mediaItems.length} media items`,
    );

    return [...mediaItems.values()];
  }

  async loadMediaItems(): Promise<MediaItem[]> {
    // TODO: Implement this method.
    const results = [];
    const read = fs
      .createReadStream('data/media_items.csv', 'utf-8')
      .pipe(csv());
    for await (const row of read) {
      const { id, type, title, year } = row;
       const mediaType = type as MediaType;
       results.push(new MediaItem(id, title, mediaType, parseInt(year), []));
      
    }
    return results;
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

// const csv = require('csv-parser');
// const fs = require('fs');
// const results = [];

// fs.createReadStream('data.csv')
//   .pipe(csv())
//   .on('data', (data) => results.push(data))
//   .on('end', () => {
//     console.log(results);
//
//   });
