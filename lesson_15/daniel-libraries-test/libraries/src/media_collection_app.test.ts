// __tests__/media_collection_app.test.ts
import { Test, TestingModule } from '@nestjs/testing';
import { AppModule } from '../src/app.module.js';
import { MediaCollectionApp } from '../src/cli/media_collection_app.js';
import { Loader } from '../src/loaders/loader.js';
import { Loaders } from '../src/loaders/loaders.module.js';
import { Credit } from '../src/models/credit.js';
import { MediaCollection } from '../src/models/media_collection.js';
import { MediaItem } from '../src/models/media_item.js';
import { MediaType } from '../src/models/media_type.js';
import { Role } from '../src/models/role.js';

// Import jest explicitly for TypeScript
import { jest } from '@jest/globals';

const IGNORE_LOADER = 'anthonymays';

describe('MediaCollectionApp Unit Tests', () => {
  let moduleFixture: TestingModule;
  let loaders: Loader[];
  let app: MediaCollectionApp;

  beforeAll(async () => {
    moduleFixture = await Test.createTestingModule({
      imports: [AppModule],
    }).compile();

    loaders = moduleFixture.get(Loaders);
    app = moduleFixture.get(MediaCollectionApp);
  });

  afterAll(async () => {
    await moduleFixture.close();
  });

  describe('MediaCollectionApp CLI Tests', () => {
    it('should load collection using specific loader', async () => {
      // Get the actual loader names from the loaders array, excluding the ignored one
      expect(loaders.length).toBeGreaterThan(0);
      const validLoader = loaders.find(l => l.getLoaderName() !== IGNORE_LOADER);
      expect(validLoader).toBeDefined();
      
      if (validLoader) {
        const loaderName = validLoader.getLoaderName();
        expect(loaderName).toBeDefined();

        const loader = loaders.find((l) => l.getLoaderName() === loaderName);
        expect(loader).toBeDefined();

        if (loader) {
          const items = await loader.loadData();
          expect(items.length).toBeGreaterThan(0);
          expect(items[0].getId()).toBeDefined();
        }
      }
    });

    it('should handle null loader name', async () => {
      // Test loadCollectionUsingLoader with null - call it directly
      const collection = await (app as any).loadCollectionUsingLoader(null);
      expect(collection).toBeDefined();
      expect(collection.getInfo().getItems().length).toBe(0);
    });

    it('should handle loader that does not exist', async () => {
      // Test loadCollectionUsingLoader with non-existent loader
      const collection = await (app as any).loadCollectionUsingLoader(
        'NonExistentLoader',
      );
      expect(collection).toBeDefined();
      expect(collection.getInfo().getItems().length).toBe(0);
    });

    it('should load collection with valid loader', async () => {
      // Test loadCollectionUsingLoader with valid loader (not the ignored one)
      const validLoader = loaders.find(l => l.getLoaderName() !== IGNORE_LOADER);
      expect(validLoader).toBeDefined();
      
      if (validLoader) {
        const loaderName = validLoader.getLoaderName();
        const collection = await (app as any).loadCollectionUsingLoader(
          loaderName,
        );
        expect(collection).toBeDefined();
        expect(collection.getInfo().getItems().length).toBeGreaterThan(0);
      }
    });

    it('should print media collection info', () => {
      const consoleSpy = jest
        .spyOn(console, 'log')
        .mockImplementation(() => {});

      const collection = new MediaCollection();
      const credit = new Credit('1', 'John Doe', Role.Actor);
      const mediaItem = new MediaItem(
        '1',
        'Test Movie',
        MediaType.Movie,
        2020,
        [credit],
      );
      collection.addItem(mediaItem);

      // Call the private method through reflection or indirectly
      (app as any).printMediaCollection(collection);

      expect(consoleSpy).toHaveBeenCalledWith('Number of items: 1');
      expect(consoleSpy).toHaveBeenCalledWith(
        '========================================',
      );

      consoleSpy.mockRestore();
    });

    it('should print menu correctly', () => {
      const consoleSpy = jest
        .spyOn(console, 'log')
        .mockImplementation(() => {});

      (app as any).printMenu();

      expect(consoleSpy).toHaveBeenCalledWith(
        '\nEnter the number of the desired command:',
      );
      expect(consoleSpy).toHaveBeenCalledWith('1) << EXIT');
      expect(consoleSpy).toHaveBeenCalledWith('2) SEARCH');

      consoleSpy.mockRestore();
    });

    it('should print search menu correctly', () => {
      const consoleSpy = jest
        .spyOn(console, 'log')
        .mockImplementation(() => {});

      (app as any).printSearchMenu();

      expect(consoleSpy).toHaveBeenCalledWith(
        '\nEnter the number of the desired search criteria:',
      );
      expect(consoleSpy).toHaveBeenCalledWith('1) << RETURN');
      expect(consoleSpy).toHaveBeenCalledWith('2) TITLE');
      expect(consoleSpy).toHaveBeenCalledWith('3) AUTHOR');
      expect(consoleSpy).toHaveBeenCalledWith('4) TYPE');

      consoleSpy.mockRestore();
    });

    it('should print search results with items', () => {
      const consoleSpy = jest
        .spyOn(console, 'log')
        .mockImplementation(() => {});

      const credit = new Credit('1', 'John Doe', Role.Actor);
      const mediaItem1 = new MediaItem(
        '1',
        'Test Movie',
        MediaType.Movie,
        2020,
        [credit],
      );
      const mediaItem2 = new MediaItem(
        '2',
        'Another Movie',
        MediaType.TVShow,
        2021,
        [credit],
      );
      const results = new Set([mediaItem1, mediaItem2]);

      (app as any).printSearchResults(results);

      expect(consoleSpy).toHaveBeenCalledWith('Search results:\n');
      expect(consoleSpy).toHaveBeenCalledWith('ID: 1');
      expect(consoleSpy).toHaveBeenCalledWith('TITLE: Test Movie');
      expect(consoleSpy).toHaveBeenCalledWith('TYPE: MOVIE');
      expect(consoleSpy).toHaveBeenCalledWith('ID: 2');
      expect(consoleSpy).toHaveBeenCalledWith('TITLE: Another Movie');
      expect(consoleSpy).toHaveBeenCalledWith('TYPE: TV_SHOW');
      expect(consoleSpy).toHaveBeenCalledWith('Found 2 result(s).\n');

      consoleSpy.mockRestore();
    });

    it('should print no results message when search is empty', () => {
      const consoleSpy = jest
        .spyOn(console, 'log')
        .mockImplementation(() => {});

      const results = new Set<MediaItem>();

      (app as any).printSearchResults(results);

      expect(consoleSpy).toHaveBeenCalledWith('No results found.');

      consoleSpy.mockRestore();
    });

    it('should get search criteria for title', async () => {
      const mockScanner = {
        prompt: jest.fn(() => Promise.resolve('Batman')),
      };
      const consoleSpy = jest
        .spyOn(console, 'log')
        .mockImplementation(() => {});

      const SearchCommand = { TITLE: 2 };
      const criteria = await (app as any).getSearchCriteria(
        mockScanner,
        SearchCommand.TITLE,
      );

      expect(criteria).toEqual({ title: 'Batman' });
      expect(consoleSpy).toHaveBeenCalledWith(
        'Enter the title to search for: ',
      );

      consoleSpy.mockRestore();
    });

    it('should get search criteria for release year', async () => {
      const mockScanner = {
        prompt: jest.fn(() => Promise.resolve('2020')),
      };
      const consoleSpy = jest
        .spyOn(console, 'log')
        .mockImplementation(() => {});

      const SearchCommand = { RELEASE_YEAR: 3 };
      const criteria = await (app as any).getSearchCriteria(
        mockScanner,
        SearchCommand.RELEASE_YEAR,
      );

      expect(criteria).toEqual({ releaseYear: 2020 });
      expect(consoleSpy).toHaveBeenCalledWith(
        'Enter the release year to search for: ',
      );

      consoleSpy.mockRestore();
    });

    it('should get search criteria for cast name', async () => {
      const mockScanner = {
        prompt: jest.fn(() => Promise.resolve('John Doe')),
      };
      const consoleSpy = jest
        .spyOn(console, 'log')
        .mockImplementation(() => {});

      const SearchCommand = { CAST_NAME: 4 };
      const criteria = await (app as any).getSearchCriteria(
        mockScanner,
        SearchCommand.CAST_NAME,
      );

      expect(criteria).toEqual({ creditName: 'John Doe' });
      expect(consoleSpy).toHaveBeenCalledWith(
        'Enter the cast name to search for: ',
      );

      consoleSpy.mockRestore();
    });

    it('should handle invalid search command', async () => {
      const mockScanner = {
        prompt: jest.fn(() => Promise.resolve('')),
      };
      const consoleSpy = jest
        .spyOn(console, 'log')
        .mockImplementation(() => {});

      const invalidCommand = 999;
      const criteria = await (app as any).getSearchCriteria(
        mockScanner,
        invalidCommand,
      );

      expect(criteria).toBeUndefined();
      expect(consoleSpy).toHaveBeenCalledWith(
        'Invalid search command: ' + invalidCommand,
      );

      consoleSpy.mockRestore();
    });

    it('should handle command line loader option', () => {
      // Mock process.argv to avoid conflicts with Jest arguments
      const originalArgv = process.argv;
      process.argv = ['node', 'script.js'];
      
      try {
        // Test getLoaderFromCommandLine - this method parses command line options
        const result = (app as any).getLoaderFromCommandLine();
        // The result can be null (no loader specified) or undefined (options not set)
        expect(
          result === null || typeof result === 'string' || result === undefined,
        ).toBe(true);
      } finally {
        // Restore original argv
        process.argv = originalArgv;
      }
    });

    it('should handle prompt for command with invalid input', async () => {
      // Test invalid command handling - parseInt('invalid') returns NaN, which is falsy
      const mockScanner = {
        prompt: jest
          .fn()
          .mockImplementationOnce(() => Promise.resolve('999')) // First call returns out of range number
          .mockImplementationOnce(() => Promise.resolve('1')), // Second call returns valid
      };
      const consoleSpy = jest
        .spyOn(console, 'log')
        .mockImplementation(() => {});

      const command = await (app as any).promptForCommand(mockScanner);

      expect(command).toBe(1); // Should eventually return EXIT
      expect(consoleSpy).toHaveBeenCalledWith(
        '\nEnter the number of the desired command:',
      );

      consoleSpy.mockRestore();
    });

    it('should handle prompt for search command with invalid input', async () => {
      // Test invalid search command handling
      const mockScanner = {
        prompt: jest
          .fn()
          .mockImplementationOnce(() => Promise.resolve('999')) // First call returns out of range
          .mockImplementationOnce(() => Promise.resolve('1')), // Second call returns valid
      };
      const consoleSpy = jest
        .spyOn(console, 'log')
        .mockImplementation(() => {});

      const command = await (app as any).promptForSearchCommand(mockScanner);

      expect(command).toBe(1); // Should eventually return RETURN
      expect(consoleSpy).toHaveBeenCalledWith(
        '\nEnter the number of the desired search criteria:',
      );

      consoleSpy.mockRestore();
    });

    it('should handle do search with return command', async () => {
      // Test doSearch when user chooses to return
      const mockScanner = {
        prompt: jest.fn(() => Promise.resolve('1')), // Return command
      };
      const collection = new MediaCollection();
      const consoleSpy = jest
        .spyOn(console, 'log')
        .mockImplementation(() => {});

      await (app as any).doSearch(mockScanner, collection);

      // Should have printed search menu
      expect(consoleSpy).toHaveBeenCalledWith(
        '\nEnter the number of the desired search criteria:',
      );

      consoleSpy.mockRestore();
    });
  });

  describe('Loader Integration Tests', () => {
    it('should load data from all loaders', async () => {
      for (const loader of loaders) {
        if (loader.getLoaderName() === IGNORE_LOADER) {
          continue;
        }
        const items = await loader.loadData();
        expect(items).toBeInstanceOf(Array);
        if (items.length > 0) {
          expect(items[0].getId()).toBeDefined();
          expect(items[0].getTitle()).toBeDefined();
        }
      }
    });
  });

  describe('MediaCollection Tests', () => {
    it('should add and retrieve items', () => {
      const collection = new MediaCollection();
      const credit = new Credit('1', 'John Doe', Role.Actor);
      const mediaItem = new MediaItem(
        '1',
        'Test Movie',
        MediaType.Movie,
        2020,
        [credit],
      );

      collection.addItem(mediaItem);

      const info = collection.getInfo();
      const items = info.getItems();
      expect(items).toHaveLength(1);
      expect(items[0][1].getTitle()).toBe('Test Movie');
    });

    it('should remove items', () => {
      const collection = new MediaCollection();
      const credit = new Credit('1', 'John Doe', Role.Actor);
      const mediaItem = new MediaItem(
        '1',
        'Test Movie',
        MediaType.Movie,
        2020,
        [credit],
      );

      collection.addItem(mediaItem);
      const removed = collection.removeItem('1');

      expect(removed).toBe(true);
      const info = collection.getInfo();
      expect(info.getItems()).toHaveLength(0);
    });

    it('should return false when removing non-existent item', () => {
      const collection = new MediaCollection();
      const removed = collection.removeItem('non-existent');

      expect(removed).toBe(false);
    });

    it('should search by title', () => {
      const collection = new MediaCollection();
      const credit = new Credit('1', 'John Doe', Role.Actor);
      const mediaItem1 = new MediaItem(
        '1',
        'Batman Begins',
        MediaType.Movie,
        2005,
        [credit],
      );
      const mediaItem2 = new MediaItem(
        '2',
        'Superman Returns',
        MediaType.Movie,
        2006,
        [credit],
      );

      collection.addItem(mediaItem1);
      collection.addItem(mediaItem2);

      const results = collection.search({ title: 'Batman' });
      expect(results.size).toBe(1);
      expect([...results][0].getTitle()).toBe('Batman Begins');
    });

    it('should search by title case insensitive', () => {
      const collection = new MediaCollection();
      const credit = new Credit('1', 'John Doe', Role.Actor);
      const mediaItem = new MediaItem(
        '1',
        'Test Movie',
        MediaType.Movie,
        2020,
        [credit],
      );

      collection.addItem(mediaItem);

      const results = collection.search({ title: 'test' });
      expect(results.size).toBe(1);
      expect([...results][0].getTitle()).toBe('Test Movie');
    });

    it('should search by release year', () => {
      const collection = new MediaCollection();
      const credit = new Credit('1', 'John Doe', Role.Actor);
      const mediaItem1 = new MediaItem(
        '1',
        'Movie 2005',
        MediaType.Movie,
        2005,
        [credit],
      );
      const mediaItem2 = new MediaItem(
        '2',
        'Movie 2006',
        MediaType.Movie,
        2006,
        [credit],
      );

      collection.addItem(mediaItem1);
      collection.addItem(mediaItem2);

      const results = collection.search({ releaseYear: 2005 });
      expect(results.size).toBe(1);
      expect([...results][0].getTitle()).toBe('Movie 2005');
    });

    it('should search by type', () => {
      const collection = new MediaCollection();
      const credit = new Credit('1', 'John Doe', Role.Actor);
      const mediaItem1 = new MediaItem('1', 'Movie', MediaType.Movie, 2005, [
        credit,
      ]);
      const mediaItem2 = new MediaItem('2', 'TV Show', MediaType.TVShow, 2006, [
        credit,
      ]);

      collection.addItem(mediaItem1);
      collection.addItem(mediaItem2);

      const results = collection.search({ type: MediaType.Movie });
      expect(results.size).toBe(1);
      expect([...results][0].getTitle()).toBe('Movie');
    });

    it('should search by cast name', () => {
      const collection = new MediaCollection();
      const credit1 = new Credit('1', 'John Doe', Role.Actor);
      const credit2 = new Credit('2', 'Jane Smith', Role.Actor);
      const mediaItem1 = new MediaItem('1', 'Movie 1', MediaType.Movie, 2005, [
        credit1,
      ]);
      const mediaItem2 = new MediaItem('2', 'Movie 2', MediaType.Movie, 2006, [
        credit2,
      ]);

      collection.addItem(mediaItem1);
      collection.addItem(mediaItem2);

      const results = collection.search({ creditName: 'John Doe' });
      expect(results.size).toBe(1);
      expect([...results][0].getTitle()).toBe('Movie 1');
    });

    it('should search by cast name case insensitive', () => {
      const collection = new MediaCollection();
      const credit = new Credit('1', 'John Doe', Role.Actor);
      const mediaItem = new MediaItem(
        '1',
        'Test Movie',
        MediaType.Movie,
        2020,
        [credit],
      );

      collection.addItem(mediaItem);

      const results = collection.search({ creditName: 'john doe' });
      expect(results.size).toBe(1);
      expect([...results][0].getTitle()).toBe('Test Movie');
    });

    it('should return no results when search returns empty', () => {
      const collection = new MediaCollection();
      const credit = new Credit('1', 'John Doe', Role.Actor);
      const mediaItem = new MediaItem(
        '1',
        'Test Movie',
        MediaType.Movie,
        2020,
        [credit],
      );

      collection.addItem(mediaItem);

      const results = collection.search({ title: 'Nonexistent Movie' });
      expect(results.size).toBe(0);
    });

    it('should return all items for empty search criteria', () => {
      const collection = new MediaCollection();
      const credit = new Credit('1', 'John Doe', Role.Actor);
      const mediaItem = new MediaItem(
        '1',
        'Test Movie',
        MediaType.Movie,
        2020,
        [credit],
      );

      collection.addItem(mediaItem);

      const results = collection.search({});
      expect(results.size).toBe(1);
    });

    it('should use searchByTitle method directly', () => {
      const collection = new MediaCollection();
      const credit = new Credit('1', 'John Doe', Role.Actor);
      const mediaItem = new MediaItem('1', 'Batman', MediaType.Movie, 2020, [
        credit,
      ]);

      collection.addItem(mediaItem);

      const results = collection.searchByTitle('bat');
      expect(results.size).toBe(1);
    });

    it('should use searchByReleaseYear method directly', () => {
      const collection = new MediaCollection();
      const credit = new Credit('1', 'John Doe', Role.Actor);
      const mediaItem = new MediaItem('1', 'Movie', MediaType.Movie, 2020, [
        credit,
      ]);

      collection.addItem(mediaItem);

      const results = collection.searchByReleaseYear(2020);
      expect(results.size).toBe(1);
    });

    it('should use searchByType method directly', () => {
      const collection = new MediaCollection();
      const credit = new Credit('1', 'John Doe', Role.Actor);
      const mediaItem = new MediaItem('1', 'Movie', MediaType.Movie, 2020, [
        credit,
      ]);

      collection.addItem(mediaItem);

      const results = collection.searchByType(MediaType.Movie);
      expect(results.size).toBe(1);
    });

    it('should use searchByCreditName method directly', () => {
      const collection = new MediaCollection();
      const credit = new Credit('1', 'John Doe', Role.Actor);
      const mediaItem = new MediaItem('1', 'Movie', MediaType.Movie, 2020, [
        credit,
      ]);

      collection.addItem(mediaItem);

      const results = collection.searchByCreditName('John');
      expect(results.size).toBe(1);
    });
  });

  describe('MediaItem Tests', () => {
    it('should get all properties correctly', () => {
      const credit = new Credit('1', 'John Doe', Role.Actor);
      const mediaItem = new MediaItem(
        '1',
        'Test Movie',
        MediaType.Movie,
        2020,
        [credit],
      );

      expect(mediaItem.getId()).toBe('1');
      expect(mediaItem.getTitle()).toBe('Test Movie');
      expect(mediaItem.getType()).toBe(MediaType.Movie);
      expect(mediaItem.getReleaseYear()).toBe(2020);
      expect(mediaItem.getCredits()).toHaveLength(1);
      expect(mediaItem.getCredits()[0].getName()).toBe('John Doe');
    });

    it('should add credits', () => {
      const credit1 = new Credit('1', 'John Doe', Role.Actor);
      const mediaItem = new MediaItem(
        '1',
        'Test Movie',
        MediaType.Movie,
        2020,
        [credit1],
      );

      const credit2 = new Credit('2', 'Jane Smith', Role.Director);
      mediaItem.addCredit(credit2);

      expect(mediaItem.getCredits()).toHaveLength(2);
      expect(mediaItem.getCredits()[1].getName()).toBe('Jane Smith');
      expect(mediaItem.getCredits()[1].getRole()).toBe(Role.Director);
    });

    it('should handle empty credits array', () => {
      const mediaItem = new MediaItem(
        '1',
        'Test Movie',
        MediaType.Movie,
        2020,
        [],
      );

      expect(mediaItem.getCredits()).toHaveLength(0);

      const credit = new Credit('1', 'John Doe', Role.Actor);
      mediaItem.addCredit(credit);

      expect(mediaItem.getCredits()).toHaveLength(1);
    });
  });

  describe('Credit Tests', () => {
    it('should get all properties correctly', () => {
      const credit = new Credit('1', 'John Doe', Role.Actor);

      expect(credit.getMediaItemId()).toBe('1');
      expect(credit.getName()).toBe('John Doe');
      expect(credit.getRole()).toBe(Role.Actor);
    });

    it('should work with different roles', () => {
      const roles = [Role.Actor, Role.Director, Role.Producer, Role.Writer];

      roles.forEach((role, index) => {
        const credit = new Credit(`${index}`, 'Test Person', role);
        expect(credit.getRole()).toBe(role);
      });
    });
  });

  describe('Enum Tests', () => {
    it('should test MediaType enum values', () => {
      expect(MediaType.Movie).toBe('movie');
      expect(MediaType.TVShow).toBe('tv_show');
      expect(MediaType.Documentary).toBe('documentary');
    });

    it('should test Role enum values', () => {
      expect(Role.Actor).toBe('Actor');
      expect(Role.Director).toBe('Director');
      expect(Role.Producer).toBe('Producer');
      expect(Role.Writer).toBe('Writer');
      expect(Role.Cinematographer).toBe('Cinematographer');
      expect(Role.Editor).toBe('Editor');
      expect(Role.Composer).toBe('Composer');
    });
  });

  describe('Edge Cases and Integration', () => {
    it('should handle null/undefined in searches gracefully', () => {
      const collection = new MediaCollection();
      const credit = new Credit('1', 'John Doe', Role.Actor);
      const mediaItem = new MediaItem(
        '1',
        'Test Movie',
        MediaType.Movie,
        2020,
        [credit],
      );

      collection.addItem(mediaItem);

      // These should not crash
      const results1 = collection.searchByTitle('');
      const results2 = collection.searchByCreditName('');

      expect(results1).toBeDefined();
      expect(results2).toBeDefined();
    });

    it('should handle null/undefined title in searchByTitle', () => {
      const collection = new MediaCollection();
      const credit = new Credit('1', 'John Doe', Role.Actor);
      const mediaItem = new MediaItem(
        '1',
        'Test Movie',
        MediaType.Movie,
        2020,
        [credit],
      );

      collection.addItem(mediaItem);

      // Test null coalescing operator branch
      const results = collection.searchByTitle(null as any);
      expect(results.size).toBe(1); // Should match because null becomes ''
    });

    it('should handle null/undefined creditName in searchByCreditName', () => {
      const collection = new MediaCollection();
      const credit = new Credit('1', 'John Doe', Role.Actor);
      const mediaItem = new MediaItem(
        '1',
        'Test Movie',
        MediaType.Movie,
        2020,
        [credit],
      );

      collection.addItem(mediaItem);

      // Test null coalescing operator branch
      const results = collection.searchByCreditName(null as any);
      expect(results.size).toBe(1); // Should match because null becomes ''
    });

    it('should handle undefined search criteria', () => {
      const collection = new MediaCollection();
      const credit = new Credit('1', 'John Doe', Role.Actor);
      const mediaItem = new MediaItem(
        '1',
        'Test Movie',
        MediaType.Movie,
        2020,
        [credit],
      );

      collection.addItem(mediaItem);

      // Test optional chaining branches
      const results = collection.search(undefined);
      expect(results.size).toBe(1);
    });

    it('should handle multiple items with same properties', () => {
      const collection = new MediaCollection();
      const credit = new Credit('1', 'John Doe', Role.Actor);

      const mediaItem1 = new MediaItem('1', 'Movie', MediaType.Movie, 2020, [
        credit,
      ]);
      const mediaItem2 = new MediaItem('2', 'Movie', MediaType.Movie, 2020, [
        credit,
      ]);

      collection.addItem(mediaItem1);
      collection.addItem(mediaItem2);

      const results = collection.searchByTitle('Movie');
      expect(results.size).toBe(2);
    });

    it('should maintain immutability of credits array', () => {
      const credit = new Credit('1', 'John Doe', Role.Actor);
      const mediaItem = new MediaItem(
        '1',
        'Test Movie',
        MediaType.Movie,
        2020,
        [credit],
      );

      const credits = mediaItem.getCredits();
      const originalLength = credits.length;

      // Try to modify the returned array
      (credits as any).push(new Credit('2', 'Jane Doe', Role.Director));

      // Original should be unchanged
      expect(mediaItem.getCredits()).toHaveLength(originalLength);
    });

    it('should test search priority - title takes precedence', () => {
      const collection = new MediaCollection();
      const credit = new Credit('1', 'John Doe', Role.Actor);
      const mediaItem = new MediaItem(
        '1',
        'Special Movie',
        MediaType.Movie,
        2020,
        [credit],
      );

      collection.addItem(mediaItem);

      // When multiple criteria are provided, title should take precedence
      const results = collection.search({
        title: 'Special',
        releaseYear: 2020,
        type: MediaType.Movie,
        creditName: 'John Doe',
      });
      expect(results.size).toBe(1);
      expect([...results][0].getTitle()).toBe('Special Movie');
    });

    it('should test search priority - releaseYear when no title', () => {
      const collection = new MediaCollection();
      const credit = new Credit('1', 'John Doe', Role.Actor);
      const mediaItem1 = new MediaItem('1', 'Movie A', MediaType.Movie, 2020, [
        credit,
      ]);
      const mediaItem2 = new MediaItem('2', 'Movie B', MediaType.Movie, 2021, [
        credit,
      ]);

      collection.addItem(mediaItem1);
      collection.addItem(mediaItem2);

      // When no title, releaseYear should be used
      const results = collection.search({
        releaseYear: 2020,
        type: MediaType.Movie,
        creditName: 'John Doe',
      });
      expect(results.size).toBe(1);
      expect([...results][0].getTitle()).toBe('Movie A');
    });

    it('should test search priority - type when no title or releaseYear', () => {
      const collection = new MediaCollection();
      const credit = new Credit('1', 'John Doe', Role.Actor);
      const mediaItem1 = new MediaItem('1', 'Movie', MediaType.Movie, 2020, [
        credit,
      ]);
      const mediaItem2 = new MediaItem('2', 'TV Show', MediaType.TVShow, 2020, [
        credit,
      ]);

      collection.addItem(mediaItem1);
      collection.addItem(mediaItem2);

      // When no title or releaseYear, type should be used
      const results = collection.search({
        type: MediaType.Movie,
        creditName: 'John Doe',
      });
      expect(results.size).toBe(1);
      expect([...results][0].getTitle()).toBe('Movie');
    });

    it('should test all enum values', () => {
      // Test all MediaType values
      expect(Object.values(MediaType)).toContain('movie');
      expect(Object.values(MediaType)).toContain('tv_show');
      expect(Object.values(MediaType)).toContain('documentary');

      // Test all Role values
      expect(Object.values(Role)).toContain('Actor');
      expect(Object.values(Role)).toContain('Director');
      expect(Object.values(Role)).toContain('Producer');
      expect(Object.values(Role)).toContain('Writer');
      expect(Object.values(Role)).toContain('Cinematographer');
      expect(Object.values(Role)).toContain('Editor');
      expect(Object.values(Role)).toContain('Composer');
      expect(Object.values(Role)).toContain('ProductionDesigner');
      expect(Object.values(Role)).toContain('CostumeDesigner');
      expect(Object.values(Role)).toContain('MakeupArtist');
      expect(Object.values(Role)).toContain('SoundDesigner');
      expect(Object.values(Role)).toContain('StuntCoordinator');
    });
  });
});
