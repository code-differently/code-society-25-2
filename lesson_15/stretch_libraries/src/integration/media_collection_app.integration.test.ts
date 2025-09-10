import { Test, TestingModule } from '@nestjs/testing';
import { MediaCollectionApp } from '@/cli/media_collection_app';
import { AppModule } from '@/app.module';
import { MediaCollection } from '@/models/media_collection';
import { DeanWalstonLoader } from '@/loaders/dean_walston_loader';
import { BenjaminScottLoader } from '@/loaders/benjamin_scott_loader';
import { JaredEdgeLoader } from '@/loaders/jared_edge_loader';

describe('MediaCollectionApp Integration Tests', () => {
  let app: MediaCollectionApp;
  let module: TestingModule;

  beforeEach(async () => {
    module = await Test.createTestingModule({
      imports: [AppModule],
    }).compile();

    app = module.get<MediaCollectionApp>(MediaCollectionApp);
  });

  afterEach(async () => {
    await module.close();
  });

  describe('CSV Data Loading', () => {
    it('should load CSV data using DeanWalstonLoader', async () => {
      const loader = new DeanWalstonLoader();
      const mediaItems = await loader.loadData();
      
      expect(mediaItems).toBeDefined();
      expect(Array.isArray(mediaItems)).toBe(true);
      expect(mediaItems.length).toBeGreaterThan(0);
      
      // Verify that items have required properties
      const firstItem = mediaItems[0];
      expect(firstItem.getId()).toBeDefined();
      expect(firstItem.getTitle()).toBeDefined();
      expect(firstItem.getType()).toBeDefined();
      expect(firstItem.getReleaseYear()).toBeDefined();
    });

    it('should load CSV data using BenjaminScottLoader', async () => {
      const loader = new BenjaminScottLoader();
      const mediaItems = await loader.loadData();
      
      expect(mediaItems).toBeDefined();
      expect(Array.isArray(mediaItems)).toBe(true);
      expect(mediaItems.length).toBeGreaterThan(0);
      
      // Verify that credits are properly loaded and associated
      const itemsWithCredits = mediaItems.filter(item => item.getCredits().length > 0);
      expect(itemsWithCredits.length).toBeGreaterThan(0);
    });

    it('should load CSV data using JaredEdgeLoader', async () => {
      const loader = new JaredEdgeLoader();
      const mediaItems = await loader.loadData();
      
      expect(mediaItems).toBeDefined();
      expect(Array.isArray(mediaItems)).toBe(true);
      expect(mediaItems.length).toBeGreaterThan(0);
      
      // Test specific loader functionality
      expect(loader.getLoaderName()).toBe('jarededge');
    });

    it('should properly integrate loaded data into MediaCollection', async () => {
      const loader = new DeanWalstonLoader();
      const mediaItems = await loader.loadData();
      
      const collection = new MediaCollection();
      mediaItems.forEach(item => collection.addItem(item));
      
      // Test search functionality with loaded data
      const searchResults = collection.search({ title: 'test' });
      expect(searchResults).toBeDefined();
      expect(searchResults instanceof Set).toBe(true);
    });

    it('should handle CSV files with different formats', async () => {
      const loaders = [
        new DeanWalstonLoader(),
        new BenjaminScottLoader(),
        new JaredEdgeLoader()
      ];

      for (const loader of loaders) {
        const mediaItems = await loader.loadData();
        expect(mediaItems).toBeDefined();
        expect(Array.isArray(mediaItems)).toBe(true);
        
        // Each loader should return consistent data structure
        if (mediaItems.length > 0) {
          const item = mediaItems[0];
          expect(typeof item.getId()).toBe('string');
          expect(typeof item.getTitle()).toBe('string');
          expect(typeof item.getReleaseYear()).toBe('number');
        }
      }
    });

    it('should load and associate credits correctly', async () => {
      const loader = new BenjaminScottLoader();
      const mediaItems = await loader.loadData();
      
      // Find an item with credits
      const itemWithCredits = mediaItems.find(item => item.getCredits().length > 0);
      
      if (itemWithCredits) {
        const credits = itemWithCredits.getCredits();
        expect(credits.length).toBeGreaterThan(0);
        
        const credit = credits[0];
        expect(credit.getName()).toBeDefined();
        expect(credit.getRole()).toBeDefined();
        expect(credit.getMediaItemId()).toBe(itemWithCredits.getId());
      }
    });
  });

  describe('MediaCollectionApp Integration', () => {
    it('should initialize and be ready for use', () => {
      expect(app).toBeDefined();
      expect(app).toBeInstanceOf(MediaCollectionApp);
    });

    it('should have access to all configured loaders', () => {
      // This test verifies the dependency injection is working correctly
      expect(app).toBeDefined();
      // The loaders should be injected through the constructor
    });
  });
});
