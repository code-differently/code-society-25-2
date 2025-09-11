import readline from 'readline';
import { MediaCollectionApp } from '../cli/media_collection_app.js';
import { Scanner } from '../cli/scanner.js';
import { DeanWalstonLoader } from '../loaders/dean_walston_loader.js';
import type { Loader } from '../loaders/loader.js';
import { Credit } from '../models/credit.js';
import { MediaCollection } from '../models/media_collection.js';
import { MediaItem } from '../models/media_item.js';
import { MediaType } from '../models/media_type.js';
import { Role } from '../models/role.js';

describe('MediaCollectionApp Integration', () => {
  let app: MediaCollectionApp;

  beforeEach(() => {
    app = new MediaCollectionApp([new DeanWalstonLoader()]);
  });

  it('should correctly create and access MediaItem model properties', () => {
    const credit = new Credit('10', 'Model Tester', Role.Actor);
    const item = new MediaItem('10', 'Model Test', MediaType.Movie, 2025, [
      credit,
    ]);
    expect(item.getId()).toBe('10');
    expect(item.getTitle()).toBe('Model Test');
    expect(item.getType()).toBe(MediaType.Movie);
    expect(item.getReleaseYear()).toBe(2025);
    expect(item.getCredits()[0].getName()).toBe('Model Tester');
  });

  it('should return empty collection for unknown loader', async () => {
    const collection = await app['loadCollectionUsingLoader']('unknown_loader');
    expect(collection.getInfo().getItems().length).toBe(0);
  });

  it('should return empty collection if no loader name is provided', async () => {
    const collection = await app['loadCollectionUsingLoader'](null);
    expect(collection.getInfo().getItems().length).toBe(0);
  });

  const loaderNames = [
    'deanwalston',
    'anthonymays',
    'benjaminscott',
    'brooklynharden',
    'danielboyce',
    'jaizelcespedes',
    'jarededge',
    'joneemckellar',
    'kerryferguson',
    'lindaquinoa',
    'mattieweathersby',
    'nicolejackson',
    'trinitiejackson',
    'tyranricejr',
  ];

  loaderNames.forEach((loaderName) => {
    it(`should load CSV data using ${loaderName} loader`, async () => {
      const credit = new Credit('20', 'Loader Tester', Role.Producer);
      const item = new MediaItem(
        '20',
        `Test for ${loaderName}`,
        MediaType.TVShow,
        2024,
        [credit],
      );

      const mockLoader: Loader = {
        getLoaderName: () => loaderName,
        loadData: async () => [item],
      };

      const appWithLoader = new MediaCollectionApp([mockLoader]);
      const collection =
        await appWithLoader['loadCollectionUsingLoader'](loaderName);

      expect(collection.getInfo().getItems().length).toBe(1);
      expect(collection.getInfo().getItems()[0][1].getTitle()).toBe(
        `Test for ${loaderName}`,
      );
    });
  });

  it('should handle special media types in a loader', async () => {
    const mockCredit = new Credit('4', 'Sam Example', Role.Writer);
    const mockItem = new MediaItem(
      '4',
      'Unit Test Documentary',
      MediaType.Documentary,
      2023,
      [mockCredit],
    );

    const mockLoader: Loader = {
      getLoaderName: () => 'anthonymays',
      loadData: async () => [mockItem],
    };

    const appWithLoader = new MediaCollectionApp([mockLoader]);
    const collection =
      await appWithLoader['loadCollectionUsingLoader']('anthonymays');

    expect(collection.getInfo().getItems().length).toBe(1);
    expect(collection.getInfo().getItems()[0][1].getTitle()).toBe(
      'Unit Test Documentary',
    );
    expect(collection.getInfo().getItems()[0][1].getType()).toBe(
      MediaType.Documentary,
    );
  });
  describe('Coverage Tests', () => {
    it('should test MediaCollection core methods', () => {
      const collection = new MediaCollection();

      const credit = new Credit('1', 'Test Actor', Role.Actor);
      const item = new MediaItem('1', 'Test Movie', MediaType.Movie, 2020, [
        credit,
      ]);

      collection.addItem(item);
      expect(collection.getInfo().getItems().length).toBe(1);
      expect(collection.removeItem('1')).toBe(true);
      expect(collection.removeItem('nonexistent')).toBe(false);

      collection.addItem(item);

      expect(collection.search({ title: 'Test' }).size).toBe(1);
      expect(collection.search({ releaseYear: 2020 }).size).toBe(1);
      expect(collection.search({ type: MediaType.Movie }).size).toBe(1);
      expect(collection.search({ creditName: 'Actor' }).size).toBe(1);
    });

    describe('Scanner Tests', () => {
      it('should create a Scanner instance', () => {
        const mockReadline = {
          question: (prompt: string, callback: (answer: string) => void) => {
            callback('test input');
          },
        };

        const scanner = new Scanner(
          mockReadline as unknown as readline.Interface,
        );
        expect(scanner).toBeInstanceOf(Scanner);
      });

      it('should prompt user and return input', async () => {
        const mockReadline = {
          question: (prompt: string, callback: (answer: string) => void) => {
            expect(prompt).toBe('test prompt> ');
            callback('test answer');
          },
        };

        const scanner = new Scanner(
          mockReadline as unknown as readline.Interface,
        );
        const result = await scanner.prompt('test prompt');

        expect(result).toBe('test answer');
      });

      it('should use empty string as default message', async () => {
        const mockReadline = {
          question: (prompt: string, callback: (answer: string) => void) => {
            expect(prompt).toBe('> '); // Empty string plus >
            callback('default answer');
          },
        };

        const scanner = new Scanner(
          mockReadline as unknown as readline.Interface,
        );
        const result = await scanner.prompt();

        expect(result).toBe('default answer');
      });
    });
    it('should execute printMediaCollection without errors', () => {
      const collection = new MediaCollection();
      collection.addItem(
        new MediaItem('1', 'Test Item', MediaType.Movie, 2023, []),
      );
      const app = new MediaCollectionApp([]);

      expect(() => {
        app['printMediaCollection'](collection);
      }).not.toThrow();
    });
  });
});
