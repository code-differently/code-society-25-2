import csv from 'csv-parser';
import fs from 'fs';
import { Credit, MediaItem, MediaType } from '../models/index.js';
import { Loader } from './loader.js';

export class TobyEvansLoader implements Loader {
  getLoaderName(): string {
    return 'tobyevans';
  }

  async loadData(): Promise<MediaItem[]> {
    const credits = await this.loadCredits();
    const mediaItems = await this.loadMediaItems();

    // Extra credit: attach credits to each media item (no non-null assertion)
    const creditsByMediaId = new Map<string, Credit[]>();
    for (const c of credits) {
      const id = c.getMediaItemId();
      const list = creditsByMediaId.get(id) ?? [];
      list.push(c);
      creditsByMediaId.set(id, list);
    }
    for (const item of mediaItems) {
      for (const c of creditsByMediaId.get(item.getId()) ?? []) {
        item.addCredit(c);
      }
    }

    console.log(
      `Loaded ${credits.length} credits and ${mediaItems.length} media items`,
    );

    return mediaItems;
  }

  async loadMediaItems(): Promise<MediaItem[]> {
    const mediaItems: MediaItem[] = [];
    const counts: Record<'movie' | 'documentary' | 'tv_show', number> = {
      movie: 0,
      documentary: 0,
      tv_show: 0,
    };

    const readable = fs
      .createReadStream('data/media_items.csv', 'utf-8')
      .pipe(csv());

    for await (const row of readable) {
      const { id, type, title, year } = row;
      const mediaType = type as MediaType;
      mediaItems.push(
        new MediaItem(id, title, mediaType, parseInt(year, 10), []),
      );

      // count by type
      const kind = type as 'movie' | 'documentary' | 'tv_show';
      if (kind in counts) counts[kind] += 1;
    }

    // validate counts
    const expected = { total: 200, movie: 68, documentary: 66, tv_show: 66 };
    const total = mediaItems.length;
    if (
      total !== expected.total ||
      counts.movie !== expected.movie ||
      counts.documentary !== expected.documentary ||
      counts.tv_show !== expected.tv_show
    ) {
      throw new Error(
        `Media items invariant failed: expected ${JSON.stringify(
          expected,
        )}; got total=${total}, counts=${JSON.stringify(counts)}`,
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
