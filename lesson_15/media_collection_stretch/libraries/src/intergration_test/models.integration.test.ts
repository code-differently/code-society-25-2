import { Test, TestingModule } from '@nestjs/testing';
import { AppModule } from '../app.module.js';
import { MediaCollection } from '../models/media_collection.js';
import { MediaItem } from '../models/media_item.js';
import { Credit } from '../models/credit.js';
import { MediaType } from '../models/media_type.js';
import { Role } from '../models/role.js';

describe('Models Integration Tests', () => {
  let module: TestingModule;

  beforeEach(async () => {
    module = await Test.createTestingModule({
      imports: [AppModule],
    }).compile();
  });

  afterEach(async () => {
    await module.close();
  });

  describe('MediaItem', () => {
    it('should create a media item with all properties', () => {
      const mediaItem = new MediaItem(
        'test-id',
        'Test Title',
        MediaType.Movie,
        2023,
        []
      );

      expect(mediaItem.getId()).toBe('test-id');
      expect(mediaItem.getTitle()).toBe('Test Title');
      expect(mediaItem.getType()).toBe(MediaType.Movie);
      expect(mediaItem.getReleaseYear()).toBe(2023);
    });

    it('should handle credits', () => {
      const mediaItem = new MediaItem(
        'test-id',
        'Test Title',
        MediaType.Movie,
        2023,
        []
      );

      const credit1 = new Credit('test-id', 'Actor 1', Role.Actor);
      const credit2 = new Credit('test-id', 'Director 1', Role.Director);

      mediaItem.addCredit(credit1);
      mediaItem.addCredit(credit2);

      const credits = mediaItem.getCredits();
      expect(credits).toHaveLength(2);
      expect(credits).toContain(credit1);
      expect(credits).toContain(credit2);
    });

    it('should handle multiple credits of same role', () => {
      const mediaItem = new MediaItem(
        'test-id',
        'Test Title',
        MediaType.Movie,
        2023,
        []
      );

      const actor1 = new Credit('test-id', 'Actor 1', Role.Actor);
      const actor2 = new Credit('test-id', 'Actor 2', Role.Actor);
      const actor3 = new Credit('test-id', 'Actor 3', Role.Actor);

      mediaItem.addCredit(actor1);
      mediaItem.addCredit(actor2);
      mediaItem.addCredit(actor3);

      const credits = mediaItem.getCredits();
      expect(credits).toHaveLength(3);
    });

    it('should create media items with different types', () => {
      const movie = new MediaItem('1', 'Movie', MediaType.Movie, 2023, []);
      const tvShow = new MediaItem('2', 'TV Show', MediaType.TVShow, 2023, []);
      const documentary = new MediaItem('3', 'Documentary', MediaType.Documentary, 2023, []);

      expect(movie.getType()).toBe(MediaType.Movie);
      expect(tvShow.getType()).toBe(MediaType.TVShow);
      expect(documentary.getType()).toBe(MediaType.Documentary);
    });
  });

  describe('Credit', () => {
    it('should create credits with different roles', () => {
      const actor = new Credit('item-1', 'John Doe', Role.Actor);
      const director = new Credit('item-1', 'Jane Smith', Role.Director);
      const producer = new Credit('item-1', 'Bob Johnson', Role.Producer);

      expect(actor.getName()).toBe('John Doe');
      expect(actor.getRole()).toBe(Role.Actor);

      expect(director.getName()).toBe('Jane Smith');
      expect(director.getRole()).toBe(Role.Director);

      expect(producer.getName()).toBe('Bob Johnson');
      expect(producer.getRole()).toBe(Role.Producer);
    });

    it('should handle credits with same name different roles', () => {
      const actor = new Credit('item-1', 'Multi Talent', Role.Actor);
      const director = new Credit('item-1', 'Multi Talent', Role.Director);

      expect(actor.getName()).toBe(director.getName());
      expect(actor.getRole()).not.toBe(director.getRole());
    });

    it('should create credits with empty names', () => {
      const credit = new Credit('item-1', '', Role.Actor);
      expect(credit.getName()).toBe('');
      expect(credit.getRole()).toBe(Role.Actor);
    });

    it('should handle media item ID', () => {
      const credit = new Credit('media-123', 'Actor Name', Role.Actor);
      expect(credit.getMediaItemId()).toBe('media-123');
    });
  });

  describe('MediaCollection', () => {
    it('should start empty', () => {
      const collection = new MediaCollection();
      const info = collection.getInfo();
      
      expect(info.getItems()).toHaveLength(0);
    });

    it('should add media items', () => {
      const collection = new MediaCollection();
      const item1 = new MediaItem('1', 'Title 1', MediaType.Movie, 2023, []);
      const item2 = new MediaItem('2', 'Title 2', MediaType.TVShow, 2022, []);

      collection.addItem(item1);
      collection.addItem(item2);

      const info = collection.getInfo();
      const items = info.getItems();
      expect(items).toHaveLength(2);
      
      // Check that both items are in the collection by searching for them
      const results1 = collection.search({ title: 'Title 1' });
      const results2 = collection.search({ title: 'Title 2' });
      expect(results1.size).toBe(1);
      expect(results2.size).toBe(1);
    });

    it('should search by title', () => {
      const collection = new MediaCollection();
      const item1 = new MediaItem('1', 'The Matrix', MediaType.Movie, 1999, []);
      const item2 = new MediaItem('2', 'Matrix Reloaded', MediaType.Movie, 2003, []);
      const item3 = new MediaItem('3', 'Star Wars', MediaType.Movie, 1977, []);

      collection.addItem(item1);
      collection.addItem(item2);
      collection.addItem(item3);

      const results = collection.search({ title: 'Matrix' });
      expect(results.size).toBe(2);
      expect(results.has(item1)).toBe(true);
      expect(results.has(item2)).toBe(true);
      expect(results.has(item3)).toBe(false);
    });

    it('should search by title case-insensitive', () => {
      const collection = new MediaCollection();
      const item = new MediaItem('1', 'The Matrix', MediaType.Movie, 1999, []);
      collection.addItem(item);

      const results1 = collection.search({ title: 'matrix' });
      const results2 = collection.search({ title: 'MATRIX' });
      const results3 = collection.search({ title: 'MaTrIx' });

      expect(results1.size).toBe(1);
      expect(results2.size).toBe(1);
      expect(results3.size).toBe(1);
    });

    it('should search by release year', () => {
      const collection = new MediaCollection();
      const item1 = new MediaItem('1', 'Movie 1999', MediaType.Movie, 1999, []);
      const item2 = new MediaItem('2', 'Movie 2000', MediaType.Movie, 2000, []);
      const item3 = new MediaItem('3', 'Another 1999', MediaType.Movie, 1999, []);

      collection.addItem(item1);
      collection.addItem(item2);
      collection.addItem(item3);

      const results = collection.search({ releaseYear: 1999 });
      expect(results.size).toBe(2);
      expect(results.has(item1)).toBe(true);
      expect(results.has(item3)).toBe(true);
      expect(results.has(item2)).toBe(false);
    });

    it('should search by credit name', () => {
      const collection = new MediaCollection();
      const keanu = new Credit('1', 'Keanu Reeves', Role.Actor);
      const will = new Credit('2', 'Will Smith', Role.Actor);
      const keanu2 = new Credit('3', 'Keanu Reeves', Role.Actor);

      const item1 = new MediaItem('1', 'Movie 1', MediaType.Movie, 1999, [keanu]);
      const item2 = new MediaItem('2', 'Movie 2', MediaType.Movie, 2000, [will]);
      const item3 = new MediaItem('3', 'Movie 3', MediaType.Movie, 2001, [keanu2]);

      collection.addItem(item1);
      collection.addItem(item2);
      collection.addItem(item3);

      const results = collection.search({ creditName: 'Keanu Reeves' });
      expect(results.size).toBe(2);
      expect(results.has(item1)).toBe(true);
      expect(results.has(item3)).toBe(true);
      expect(results.has(item2)).toBe(false);
    });

    it('should search by credit name case-insensitive', () => {
      const collection = new MediaCollection();
      const credit = new Credit('1', 'Keanu Reeves', Role.Actor);
      const item = new MediaItem('1', 'Movie', MediaType.Movie, 1999, [credit]);
      collection.addItem(item);

      const results1 = collection.search({ creditName: 'keanu reeves' });
      const results2 = collection.search({ creditName: 'KEANU REEVES' });
      const results3 = collection.search({ creditName: 'KeAnU rEeVeS' });

      expect(results1.size).toBe(1);
      expect(results2.size).toBe(1);
      expect(results3.size).toBe(1);
    });

    it('should handle empty search results', () => {
      const collection = new MediaCollection();
      const item = new MediaItem('1', 'Movie', MediaType.Movie, 1999, []);
      collection.addItem(item);

      const results = collection.search({ title: 'Nonexistent' });
      expect(results.size).toBe(0);
    });

    it('should handle search with no criteria', () => {
      const collection = new MediaCollection();
      const item = new MediaItem('1', 'Movie', MediaType.Movie, 1999, []);
      collection.addItem(item);

      const results = collection.search({});
      expect(results.size).toBe(1); // Returns all items when no criteria
    });

    it('should handle multiple search criteria (AND logic)', () => {
      const collection = new MediaCollection();
      const keanu1 = new Credit('1', 'Keanu Reeves', Role.Actor);
      const keanu2 = new Credit('2', 'Keanu Reeves', Role.Actor);
      
      const item1 = new MediaItem('1', 'The Matrix', MediaType.Movie, 1999, [keanu1]);
      const item2 = new MediaItem('2', 'Matrix Reloaded', MediaType.Movie, 2003, [keanu2]);

      collection.addItem(item1);
      collection.addItem(item2);

      // Should match both title and credit
      const results = collection.search({ 
        title: 'Matrix', 
        creditName: 'Keanu Reeves' 
      });
      expect(results.size).toBe(2);
    });

    it('should handle search by partial credit name', () => {
      const collection = new MediaCollection();
      const credit = new Credit('1', 'Keanu Reeves', Role.Actor);
      const item = new MediaItem('1', 'Movie', MediaType.Movie, 1999, [credit]);
      collection.addItem(item);

      const results1 = collection.search({ creditName: 'Keanu' });
      const results2 = collection.search({ creditName: 'Reeves' });

      expect(results1.size).toBe(1);
      expect(results2.size).toBe(1);
    });

    it('should handle adding duplicate items with same ID', () => {
      const collection = new MediaCollection();
      const item1 = new MediaItem('1', 'Movie', MediaType.Movie, 1999, []);
      const item2 = new MediaItem('1', 'Different Movie', MediaType.Movie, 2000, []); // Same ID

      collection.addItem(item1);
      collection.addItem(item2); // This will overwrite the first item

      const info = collection.getInfo();
      expect(info.getItems()).toHaveLength(1); // Only one item with same ID
      
      // Verify the second item overwrote the first
      const results = collection.search({ title: 'Different Movie' });
      expect(results.size).toBe(1);
    });
  });

  describe('MediaType and Role enums', () => {
    it('should have all media types available', () => {
      expect(MediaType.Movie).toBeDefined();
      expect(MediaType.TVShow).toBeDefined();
      expect(MediaType.Documentary).toBeDefined();
    });

    it('should have all roles available', () => {
      expect(Role.Actor).toBeDefined();
      expect(Role.Director).toBeDefined();
      expect(Role.Producer).toBeDefined();
      expect(Role.Writer).toBeDefined();
      expect(Role.Cinematographer).toBeDefined();
      expect(Role.Editor).toBeDefined();
      expect(Role.Composer).toBeDefined();
    });

    it('should create items with all media types', () => {
      const types = [MediaType.Movie, MediaType.TVShow, MediaType.Documentary];
      
      types.forEach((type, index) => {
        const item = new MediaItem(`${index}`, `Title ${index}`, type, 2023, []);
        expect(item.getType()).toBe(type);
      });
    });

    it('should create credits with all roles', () => {
      const roles = [Role.Actor, Role.Director, Role.Producer, Role.Writer];
      
      roles.forEach((role, index) => {
        const credit = new Credit('item-1', `Person ${index}`, role);
        expect(credit.getRole()).toBe(role);
      });
    });
  });
});
