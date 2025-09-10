import { beforeEach, describe, expect, it } from '@jest/globals';
import { BenjaminScottLoader } from '../loaders/benjamin_scott_loader.js';
import { JaizelcespedesLoader } from '../loaders/jaizelcespedes_loader.js';
import { JoneemckellarLoader } from '../loaders/joneemckellar_loader.js';
import { MediaCollection } from '../models/media_collection.js';
import { MediaItem } from '../models/media_item.js';

describe('MediaCollectionApp Integration Tests', () => {
  let benjaminScottLoader: BenjaminScottLoader;
  let jaizelLoader: JaizelcespedesLoader;
  let joneeLoader: JoneemckellarLoader;
  let collection: MediaCollection;
  let items: MediaItem[];

  beforeEach(async () => {
    benjaminScottLoader = new BenjaminScottLoader();
    jaizelLoader = new JaizelcespedesLoader();
    joneeLoader = new JoneemckellarLoader();

    // Load items from BenjaminScott loader
    items = await benjaminScottLoader.loadData();
    collection = new MediaCollection();
    for (const item of items) {
      collection.addItem(item);
    }
  });

  it('loads all properties correctly', async () => {
    expect(items.length).toBe(200);

    // Verify each item has the required properties
    items.forEach((item: MediaItem) => {
      expect(item.getId()).toBeDefined();
      expect(item.getTitle()).toBeDefined();
      expect(item.getType()).toBeDefined();
      expect(item.getReleaseYear()).toBeGreaterThan(1900);
      expect(Array.isArray(item.getCredits())).toBe(true);
    });

    // Verify type counts
    const typeCount: Record<string, number> = {};
    items.forEach((item: MediaItem) => {
      const type = item.getType();
      typeCount[type] = (typeCount[type] || 0) + 1;
    });

    expect(typeCount).toEqual({
      movie: 68,
      tv_show: 66,
      documentary: 66,
    });
  });

  it('verifies credits are properly loaded', async () => {
    // Check that at least some items have credits
    const itemsWithCredits = items.filter(
      (item: MediaItem) => item.getCredits().length > 0,
    );
    expect(itemsWithCredits.length).toBeGreaterThan(0);

    // Verify credit properties
    itemsWithCredits.forEach((item: MediaItem) => {
      item.getCredits().forEach((credit) => {
        expect(credit.getMediaItemId()).toBe(item.getId());
        expect(credit.getName()).toBeDefined();
        expect(credit.getRole()).toBeDefined();
      });
    });
  });

  it('verifies search functionality with various criteria', () => {
    const testItem = Array.from(collection.search())[0];
    const testTitle = testItem.getTitle().toLowerCase();
    const testYear = testItem.getReleaseYear();
    const testType = testItem.getType();

    // Test individual criteria
    let searchResults = collection.search({ title: testTitle });
    expect(searchResults.size).toBeGreaterThan(0);

    searchResults = collection.search({ releaseYear: testYear });
    expect(searchResults.size).toBeGreaterThan(0);

    searchResults = collection.search({ type: testType });
    expect(searchResults.size).toBeGreaterThan(0);

    // Test combinations of criteria
    searchResults = collection.search({
      title: testTitle,
      releaseYear: testYear,
    });
    expect(searchResults.size).toBeGreaterThan(0);

    searchResults = collection.search({ title: testTitle, type: testType });
    expect(searchResults.size).toBeGreaterThan(0);

    searchResults = collection.search({
      releaseYear: testYear,
      type: testType,
    });
    expect(searchResults.size).toBeGreaterThan(0);

    searchResults = collection.search({
      title: testTitle,
      releaseYear: testYear,
      type: testType,
    });
    expect(searchResults.size).toBeGreaterThan(0);

    // Test no matches
    searchResults = collection.search({ title: 'ThisTitleShouldNotExist' });
    expect(searchResults.size).toBe(0);

    searchResults = collection.search({ releaseYear: 1800 });
    expect(searchResults.size).toBe(0);

    searchResults = collection.search({ type: 'non-existent-type' });
    expect(searchResults.size).toBe(0);

    // Test one matching criteria and one non-matching
    searchResults = collection.search({
      title: testTitle,
      releaseYear: 1800,
    });
    expect(searchResults.size).toBe(0);
  });

  it('verifies edge cases in search functionality', () => {
    // Test with empty strings
    let searchResults = collection.search({ title: '' });
    expect(searchResults.size).toBe(200);

    searchResults = collection.search({ creditName: '' });
    expect(searchResults.size).toBe(200);

    // Test with undefined values (should be converted to empty string)
    searchResults = collection.search({ title: undefined });
    expect(searchResults.size).toBe(200);

    searchResults = collection.search({ creditName: undefined });
    expect(searchResults.size).toBe(200);
  });

  it('verifies credit search functionality with combinations', () => {
    const itemWithCredits = Array.from(collection.search()).find(
      (item) => item.getCredits().length > 0,
    );
    if (itemWithCredits) {
      const credit = itemWithCredits.getCredits()[0];
      const creditName = credit.getName();
      const testTitle = itemWithCredits.getTitle();
      const testYear = itemWithCredits.getReleaseYear();
      const testType = itemWithCredits.getType();

      // Test credit name with other criteria
      let searchResults = collection.search({
        creditName,
        title: testTitle,
      });
      expect(searchResults.size).toBeGreaterThan(0);

      searchResults = collection.search({
        creditName,
        releaseYear: testYear,
      });
      expect(searchResults.size).toBeGreaterThan(0);

      searchResults = collection.search({
        creditName,
        type: testType,
      });
      expect(searchResults.size).toBeGreaterThan(0);

      // Test partial credit name
      const partialName = creditName.substring(0, 3);
      searchResults = collection.search({ creditName: partialName });
      expect(searchResults.size).toBeGreaterThan(0);

      // Test with mixed case credit name
      searchResults = collection.search({
        creditName: creditName.toUpperCase(),
      });
      expect(searchResults.size).toBeGreaterThan(0);
    }

    // Test with non-existent credit name
    const searchResults = collection.search({
      creditName: 'ThisNameShouldNotExist',
    });
    expect(searchResults.size).toBe(0);
  });

  it('verifies loader name is correct', () => {
    expect(benjaminScottLoader.getLoaderName()).toBe('benjaminscott');
  });

  it('verifies multiple loaders functionality', async () => {
    // Load from Jaizelcespedes loader
    const jaizelItems = await jaizelLoader.loadData();
    expect(jaizelItems.length).toBeGreaterThan(0);
    expect(jaizelLoader.getLoaderName()).toBe('jaizelcespedes');

    // Load from Joneemckellar loader
    const joneeItems = await joneeLoader.loadData();
    expect(joneeItems.length).toBeGreaterThan(0);
    expect(joneeLoader.getLoaderName()).toBe('joneemckellar');
  });

  it('verifies MediaCollection getInfo functionality', () => {
    const info = collection.getInfo();
    const items = info.getItems();

    // Verify that getItems returns the correct type and content
    expect(Array.isArray(items)).toBe(true);
    expect(items.length).toBe(200);

    // Verify each item is a tuple of [string, MediaItem]
    items.forEach(([id, item]) => {
      expect(typeof id).toBe('string');
      expect(item instanceof MediaItem).toBe(true);
      expect(id).toBe(item.getId());
    });
  });
});
