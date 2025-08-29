import csv from 'csv-parser';
import fs from 'fs';
import { MediaItem } from '../models/media_item.js';
import { Loader } from './loader.js';
import { Credit } from '../models/credit.js';

export class DevynBensonLoader implements Loader {
  getLoaderName(): string {
    return 'devynbenson';
  }

  async loadData(): Promise<MediaItem[]> {
    const credits = await this.loadCredits();
    const mediaItems = await this.loadMediaItems();
    for (let i = 0; i < credits.length; i++) {
      for (let j = 0; j < mediaItems.length; j++){
        const medItId = mediaItems[i].getId();
        const credItId = credits[j].getMediaItemId();
        if (credItId === medItId) {
          mediaItems[i].addCredit(credits[j]);
        }
      }
    }
    

      console.log(
        `Loaded ${credits.length} credits and ${mediaItems.length} media items`,
      );

    return [...mediaItems.values()];
  }

  async loadMediaItems(): Promise<MediaItem[]> {
    const mediaItems = [];
    const readable = fs
      .createReadStream('data/media_items.csv', 'utf-8')
      .pipe(csv());
       
    for await (const row of readable) {
        const { id, title, type, year} = row;
        mediaItems.push(new MediaItem(id, title, type, year, [])); 
        // look into the clas objexts contstructor (one of these types is not needed)
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
