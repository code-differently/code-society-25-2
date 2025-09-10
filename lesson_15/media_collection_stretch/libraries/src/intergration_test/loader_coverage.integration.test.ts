import { Test, TestingModule } from '@nestjs/testing';
import { AppModule } from '../app.module.js';
import { MediaCollection } from '../models/media_collection.js';
import { MediaItem } from '../models/media_item.js';
import { MediaType } from '../models/media_type.js';

describe('Loader Coverage Integration Tests', () => {
  let module: TestingModule;
  let mediaCollection: MediaCollection;

  beforeEach(async () => {
    module = await Test.createTestingModule({
      imports: [AppModule],
    }).compile();

    mediaCollection = new MediaCollection();
  });

  afterEach(async () => {
    await module.close();
  });

  describe('Data Loading Simulation', () => {
    it('should handle loading movies data', () => {
      // Simulate loading movie data
      const movieData = [
        { id: '1', title: 'The Matrix', releaseYear: 1999 },
        { id: '2', title: 'The Matrix Reloaded', releaseYear: 2003 },
        { id: '3', title: 'The Matrix Revolutions', releaseYear: 2003 }
      ];

      movieData.forEach(data => {
        const item = new MediaItem(data.id, data.title, MediaType.Movie, data.releaseYear, []);
        mediaCollection.addItem(item);
      });

      const info = mediaCollection.getInfo();
      expect(info.getItems()).toHaveLength(3);
    });

    it('should handle loading TV shows data', () => {
      // Simulate loading TV show data
      const tvShowData = [
        { id: 'tv1', title: 'Breaking Bad', releaseYear: 2008 },
        { id: 'tv2', title: 'Game of Thrones', releaseYear: 2011 },
        { id: 'tv3', title: 'The Office', releaseYear: 2005 }
      ];

      tvShowData.forEach(data => {
        const item = new MediaItem(data.id, data.title, MediaType.TVShow, data.releaseYear, []);
        mediaCollection.addItem(item);
      });

      const info = mediaCollection.getInfo();
      expect(info.getItems()).toHaveLength(3);
    });

    it('should handle loading documentaries data', () => {
      // Simulate loading documentary data
      const documentaryData = [
        { id: 'doc1', title: 'Planet Earth', releaseYear: 2006 },
        { id: 'doc2', title: 'Blue Planet', releaseYear: 2001 },
        { id: 'doc3', title: 'Cosmos', releaseYear: 1980 }
      ];

      documentaryData.forEach(data => {
        const item = new MediaItem(data.id, data.title, MediaType.Documentary, data.releaseYear, []);
        mediaCollection.addItem(item);
      });

      const info = mediaCollection.getInfo();
      expect(info.getItems()).toHaveLength(3);
    });

    it('should handle mixed media types', () => {
      const mixedData = [
        { id: '1', title: 'The Matrix', type: MediaType.Movie, releaseYear: 1999 },
        { id: '2', title: 'Breaking Bad', type: MediaType.TVShow, releaseYear: 2008 },
        { id: '3', title: 'Planet Earth', type: MediaType.Documentary, releaseYear: 2006 }
      ];

      mixedData.forEach(data => {
        const item = new MediaItem(data.id, data.title, data.type, data.releaseYear, []);
        mediaCollection.addItem(item);
      });

      const info = mediaCollection.getInfo();
      expect(info.getItems()).toHaveLength(3);
      
      // Verify different types are present by searching
      const movieResults = mediaCollection.search({ title: 'Matrix' });
      const tvResults = mediaCollection.search({ title: 'Breaking Bad' });
      const docResults = mediaCollection.search({ title: 'Planet Earth' });
      
      expect(movieResults.size).toBe(1);
      expect(tvResults.size).toBe(1);
      expect(docResults.size).toBe(1);
    });

    it('should handle empty data sets', () => {
      // Test with no data loaded
      const info = mediaCollection.getInfo();
      expect(info.getItems()).toHaveLength(0);
    });

    it('should handle items with different release years', () => {
      const items = [
        new MediaItem('1', 'Old Movie', MediaType.Movie, 1950, []),
        new MediaItem('2', 'Recent Movie', MediaType.Movie, 2023, []),
        new MediaItem('3', 'Future Movie', MediaType.Movie, 2025, [])
      ];

      items.forEach(item => mediaCollection.addItem(item));

      const info = mediaCollection.getInfo();
      expect(info.getItems()).toHaveLength(3);
    });

    it('should handle large number of items', () => {
      // Test with a large dataset
      for (let i = 1; i <= 100; i++) {
        const item = new MediaItem(
          `item-${i}`, 
          `Title ${i}`, 
          i % 3 === 0 ? MediaType.Documentary : (i % 2 === 0 ? MediaType.TVShow : MediaType.Movie),
          2000 + (i % 23),
          []
        );
        mediaCollection.addItem(item);
      }

      const info = mediaCollection.getInfo();
      expect(info.getItems()).toHaveLength(100);
    });
  });

  describe('Search Functionality Coverage', () => {
    beforeEach(() => {
      // Add test data for search tests
      const testItems = [
        new MediaItem('1', 'The Matrix', MediaType.Movie, 1999, []),
        new MediaItem('2', 'Matrix Reloaded', MediaType.Movie, 2003, []),
        new MediaItem('3', 'Breaking Bad', MediaType.TVShow, 2008, []),
        new MediaItem('4', 'Planet Earth', MediaType.Documentary, 2006, []),
        new MediaItem('5', 'The Godfather', MediaType.Movie, 1972, []),
        new MediaItem('6', 'Game of Thrones', MediaType.TVShow, 2011, [])
      ];

      testItems.forEach(item => mediaCollection.addItem(item));
    });

    it('should search by exact title match', () => {
      const results = mediaCollection.search({ title: 'The Matrix' });
      expect(results.size).toBe(1);
    });

    it('should search by partial title match', () => {
      const results = mediaCollection.search({ title: 'Matrix' });
      expect(results.size).toBe(2); // Both Matrix movies
    });

    it('should search by release year', () => {
      const results = mediaCollection.search({ releaseYear: 2008 });
      expect(results.size).toBe(1);
    });

    it('should handle case-insensitive title search', () => {
      const results1 = mediaCollection.search({ title: 'matrix' });
      const results2 = mediaCollection.search({ title: 'MATRIX' });
      const results3 = mediaCollection.search({ title: 'MaTrIx' });
      
      expect(results1.size).toBe(2);
      expect(results2.size).toBe(2);
      expect(results3.size).toBe(2);
    });

    it('should handle multiple search criteria', () => {
      // The search method only uses the first criteria found (title in this case)
      const results = mediaCollection.search({ 
        title: 'Matrix',
        releaseYear: 1999 
      });
      expect(results.size).toBe(2); // Both Matrix movies match by title
    });

    it('should return empty results for non-matching search', () => {
      const results = mediaCollection.search({ title: 'Nonexistent Movie' });
      expect(results.size).toBe(0);
    });

    it('should handle empty search criteria', () => {
      const results = mediaCollection.search({});
      expect(results.size).toBe(6); // Returns all items when no criteria
    });

    it('should handle search with only release year', () => {
      const results = mediaCollection.search({ releaseYear: 2003 });
      expect(results.size).toBe(1);
    });

    it('should handle search with very old release year', () => {
      const results = mediaCollection.search({ releaseYear: 1900 });
      expect(results.size).toBe(0);
    });

    it('should handle search with future release year', () => {
      const results = mediaCollection.search({ releaseYear: 2030 });
      expect(results.size).toBe(0);
    });

    it('should handle partial word matches in titles', () => {
      const results = mediaCollection.search({ title: 'God' });
      expect(results.size).toBe(1); // The Godfather
    });

    it('should handle single character searches', () => {
      const results = mediaCollection.search({ title: 'T' });
      // Should match "The Matrix", "The Godfather", but also titles with T in them
      expect(results.size).toBeGreaterThan(0);
    });

    it('should handle searches with special characters', () => {
      // Add an item with special characters
      const specialItem = new MediaItem('special', 'Movie: The "Special" One!', MediaType.Movie, 2020, []);
      mediaCollection.addItem(specialItem);
      
      const results = mediaCollection.search({ title: 'Special' });
      expect(results.size).toBe(1);
    });
  });

  describe('Collection Information Coverage', () => {
    it('should provide accurate count of items', () => {
      expect(mediaCollection.getInfo().getItems()).toHaveLength(0);
      
      mediaCollection.addItem(new MediaItem('1', 'Movie 1', MediaType.Movie, 2020, []));
      expect(mediaCollection.getInfo().getItems()).toHaveLength(1);
      
      mediaCollection.addItem(new MediaItem('2', 'Movie 2', MediaType.Movie, 2021, []));
      expect(mediaCollection.getInfo().getItems()).toHaveLength(2);
    });

    it('should maintain all added items', () => {
      const items = [
        new MediaItem('1', 'Movie 1', MediaType.Movie, 2020, []),
        new MediaItem('2', 'TV Show 1', MediaType.TVShow, 2021, []),
        new MediaItem('3', 'Documentary 1', MediaType.Documentary, 2022, [])
      ];

      items.forEach(item => mediaCollection.addItem(item));
      
      const info = mediaCollection.getInfo();
      const collectionItems = info.getItems();
      
      // Verify by counting and searching
      expect(collectionItems).toHaveLength(3);
      
      const movieResults = mediaCollection.search({ title: 'Movie 1' });
      const tvResults = mediaCollection.search({ title: 'TV Show 1' });
      const docResults = mediaCollection.search({ title: 'Documentary 1' });
      
      expect(movieResults.size).toBe(1);
      expect(tvResults.size).toBe(1);
      expect(docResults.size).toBe(1);
    });

    it('should handle getting info multiple times', () => {
      mediaCollection.addItem(new MediaItem('1', 'Movie 1', MediaType.Movie, 2020, []));
      
      const info1 = mediaCollection.getInfo();
      const info2 = mediaCollection.getInfo();
      
      expect(info1.getItems()).toHaveLength(1);
      expect(info2.getItems()).toHaveLength(1);
    });
  });

  describe('Edge Cases Coverage', () => {
    it('should handle items with empty titles', () => {
      const item = new MediaItem('empty', '', MediaType.Movie, 2020, []);
      mediaCollection.addItem(item);
      
      const info = mediaCollection.getInfo();
      expect(info.getItems()).toHaveLength(1);
      
      // Verify we can search by empty title
      const results = mediaCollection.search({ title: '' });
      expect(results.size).toBe(1);
    });

    it('should handle items with very long titles', () => {
      const longTitle = 'A'.repeat(1000);
      const item = new MediaItem('long', longTitle, MediaType.Movie, 2020, []);
      mediaCollection.addItem(item);
      
      const results = mediaCollection.search({ title: longTitle });
      expect(results.size).toBe(1);
    });

    it('should handle zero release year', () => {
      const item = new MediaItem('zero', 'Ancient Movie', MediaType.Movie, 0, []);
      mediaCollection.addItem(item);
      
      const results = mediaCollection.search({ releaseYear: 0 });
      expect(results.size).toBe(1);
    });

    it('should handle negative release years', () => {
      const item = new MediaItem('negative', 'BC Movie', MediaType.Movie, -100, []);
      mediaCollection.addItem(item);
      
      const results = mediaCollection.search({ releaseYear: -100 });
      expect(results.size).toBe(1);
    });

    it('should handle very large release years', () => {
      const item = new MediaItem('future', 'Far Future Movie', MediaType.Movie, 9999, []);
      mediaCollection.addItem(item);
      
      const results = mediaCollection.search({ releaseYear: 9999 });
      expect(results.size).toBe(1);
    });

    it('should handle items with same ID but different data', () => {
      const item1 = new MediaItem('same', 'Movie 1', MediaType.Movie, 2020, []);
      const item2 = new MediaItem('same', 'Movie 2', MediaType.TVShow, 2021, []);
      
      mediaCollection.addItem(item1);
      mediaCollection.addItem(item2); // This should overwrite item1
      
      const info = mediaCollection.getInfo();
      expect(info.getItems()).toHaveLength(1);
      
      // Verify the second item overwrote the first
      const results = mediaCollection.search({ title: 'Movie 2' });
      expect(results.size).toBe(1);
    });
  });
});
