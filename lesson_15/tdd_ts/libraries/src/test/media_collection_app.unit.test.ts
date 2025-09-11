import {
  afterEach,
  beforeEach,
  describe,
  expect,
  it,
  jest,
} from '@jest/globals';
import { MediaCollectionApp } from '../cli/media_collection_app.js';
import { Scanner } from '../cli/scanner.js';
import { Loader } from '../loaders/loader.js';
import { Credit } from '../models/credit.js';
import { MediaCollection } from '../models/media_collection.js';
import { MediaItem } from '../models/media_item.js';
import { MediaType } from '../models/media_type.js';
import { Role } from '../models/role.js';
import { SearchCriteria } from '../models/search_criteria.js';

// Type interface for accessing private methods
interface MediaCollectionAppTestAccess {
  loadCollectionUsingLoader: (
    loaderName: string | null,
  ) => Promise<MediaCollection>;
  printMediaCollection: (collection: MediaCollection) => void;
  printMenu: () => void;
  printSearchMenu: () => void;
  getSearchCriteria: (scanner: Scanner, command: number) => Promise<unknown>;
  getLoaderFromCommandLine: () => string | null;
  promptForCommand: (scanner: Scanner) => Promise<number>;
  promptForSearchCommand: (scanner: Scanner) => Promise<number>;
  printSearchResults: (results: ReadonlySet<MediaItem>) => void;
}

// Mock commander
jest.mock('commander', () => ({
  program: {
    option: jest.fn(),
    parse: jest.fn(),
    opts: jest.fn(() => ({ loader: 'test-loader' })),
  },
}));

describe('MediaCollectionApp Unit Tests', () => {
  let app: MediaCollectionApp;
  let mockLoaders: Loader[];
  let mockLoader: Loader;
  let consoleLogSpy: jest.SpiedFunction<typeof console.log>;

  beforeEach(() => {
    // Create mock loader with proper data
    mockLoader = {
      getLoaderName: jest.fn(() => 'test-loader'),
      loadData: jest.fn(async () => [
        new MediaItem('1', 'Test Movie', MediaType.Movie, 2020, [
          new Credit('1', 'Actor One', Role.Actor),
          new Credit('1', 'Director One', Role.Director),
        ]),
        new MediaItem('2', 'Another Movie', MediaType.Movie, 2021, [
          new Credit('2', 'Actor Two', Role.Actor),
        ]),
      ]),
    };

    mockLoaders = [mockLoader];
    app = new MediaCollectionApp(mockLoaders);

    // Mock console.log to avoid cluttering test output
    consoleLogSpy = jest.spyOn(console, 'log').mockImplementation(() => {
      // Intentionally empty to silence console output
    });
  });

  afterEach(() => {
    jest.restoreAllMocks();
  });

  describe('loadCollectionUsingLoader', () => {
    it('should load collection with valid loader name', async () => {
      const collection = await (
        app as unknown as MediaCollectionAppTestAccess
      ).loadCollectionUsingLoader('test-loader');

      expect(collection).toBeInstanceOf(MediaCollection);
      expect(mockLoader.loadData).toHaveBeenCalledTimes(1);
      expect(collection.getInfo().getItems().length).toBe(2);
    });

    it('should return empty collection with invalid loader name', async () => {
      const collection = await (
        app as unknown as MediaCollectionAppTestAccess
      ).loadCollectionUsingLoader('invalid-loader');

      expect(collection).toBeInstanceOf(MediaCollection);
      expect(collection.getInfo().getItems().length).toBe(0);
    });

    it('should return empty collection with null loader name', async () => {
      const collection = await (
        app as unknown as MediaCollectionAppTestAccess
      ).loadCollectionUsingLoader(null);

      expect(collection).toBeInstanceOf(MediaCollection);
      expect(collection.getInfo().getItems().length).toBe(0);
    });
  });

  describe('printMediaCollection', () => {
    it('should print collection info with correct item count', () => {
      const collection = new MediaCollection();
      collection.addItem(new MediaItem('1', 'Test', MediaType.Movie, 2020, []));
      collection.addItem(
        new MediaItem('2', 'Test2', MediaType.TVShow, 2021, []),
      );

      (app as any).printMediaCollection(collection);

      expect(consoleLogSpy).toHaveBeenCalledWith('Number of items: 2');
      expect(consoleLogSpy).toHaveBeenCalledWith(
        '========================================',
      );
    });

    it('should print collection info for empty collection', () => {
      const collection = new MediaCollection();

      (app as any).printMediaCollection(collection);

      expect(consoleLogSpy).toHaveBeenCalledWith('Number of items: 0');
    });
  });

  describe('printMenu', () => {
    it('should print main menu options', () => {
      (app as any).printMenu();

      expect(consoleLogSpy).toHaveBeenCalledWith(
        '\nEnter the number of the desired command:',
      );
      expect(consoleLogSpy).toHaveBeenCalledWith('1) << EXIT');
      expect(consoleLogSpy).toHaveBeenCalledWith('2) SEARCH');
    });
  });

  describe('printSearchMenu', () => {
    it('should print search menu options', () => {
      (app as any).printSearchMenu();

      expect(consoleLogSpy).toHaveBeenCalledWith(
        '\nEnter the number of the desired search criteria:',
      );
      expect(consoleLogSpy).toHaveBeenCalledWith('1) << RETURN');
      expect(consoleLogSpy).toHaveBeenCalledWith('2) TITLE');
      expect(consoleLogSpy).toHaveBeenCalledWith('3) AUTHOR');
      expect(consoleLogSpy).toHaveBeenCalledWith('4) TYPE');
    });
  });

  describe('getSearchCriteria', () => {
    let mockScanner: Scanner;
    let mockPrompt: jest.Mock;

    beforeEach(() => {
      mockPrompt = jest.fn();
      mockScanner = {
        prompt: mockPrompt,
      } as unknown as Scanner;
    });

    it('should return title criteria for TITLE command', async () => {
      mockPrompt.mockResolvedValue('Test Movie');

      const criteria = await (
        app as unknown as MediaCollectionAppTestAccess
      ).getSearchCriteria(mockScanner, 2); // TITLE = 2

      expect(criteria).toEqual({ title: 'Test Movie' });
      expect(mockPrompt).toHaveBeenCalledWith('title');
    });

    it('should return release year criteria for RELEASE_YEAR command', async () => {
      mockPrompt.mockResolvedValue('2020');

      const criteria = await (app as any).getSearchCriteria(mockScanner, 3); // RELEASE_YEAR = 3

      expect(criteria).toEqual({ releaseYear: 2020 });
      expect(mockPrompt).toHaveBeenCalledWith('year');
    });

    it('should return credit name criteria for CAST_NAME command', async () => {
      mockPrompt.mockResolvedValue('Actor Name');

      const criteria = await (app as any).getSearchCriteria(mockScanner, 4); // CAST_NAME = 4

      expect(criteria).toEqual({ creditName: 'Actor Name' });
      expect(mockPrompt).toHaveBeenCalledWith('type');
    });

    it('should return undefined for invalid command', async () => {
      const criteria = await (app as any).getSearchCriteria(mockScanner, 99);

      expect(criteria).toBeUndefined();
      expect(consoleLogSpy).toHaveBeenCalledWith('Invalid search command: 99');
    });
  });

  describe('printSearchResults', () => {
    it('should print results when items are found', () => {
      const items = new Set([
        new MediaItem('1', 'Test Movie', MediaType.Movie, 2020, []),
        new MediaItem('2', 'Another Movie', MediaType.TVShow, 2021, []),
      ]);

      (app as any).printSearchResults(items);

      expect(consoleLogSpy).toHaveBeenCalledWith('Search results:\n');
      expect(consoleLogSpy).toHaveBeenCalledWith('ID: 1');
      expect(consoleLogSpy).toHaveBeenCalledWith('TITLE: Test Movie');
      expect(consoleLogSpy).toHaveBeenCalledWith('TYPE: MOVIE');
      expect(consoleLogSpy).toHaveBeenCalledWith('ID: 2');
      expect(consoleLogSpy).toHaveBeenCalledWith('TITLE: Another Movie');
      expect(consoleLogSpy).toHaveBeenCalledWith('TYPE: TV');
      expect(consoleLogSpy).toHaveBeenCalledWith('Found 2 result(s).\n');
    });

    it('should print no results message when no items are found', () => {
      const items = new Set<MediaItem>();

      (app as any).printSearchResults(items);

      expect(consoleLogSpy).toHaveBeenCalledWith('No results found.');
      expect(consoleLogSpy).not.toHaveBeenCalledWith('Search results:\n');
    });
  });

  describe('getLoaderFromCommandLine', () => {
    it('should return loader name from command line options', () => {
      const loaderName = (app as any).getLoaderFromCommandLine();

      expect(loaderName).toBe('test-loader');
    });
  });

  describe('promptForCommand', () => {
    let mockScanner: Scanner;

    beforeEach(() => {
      mockScanner = {
        prompt: jest.fn(),
      } as unknown as Scanner;
    });

    it('should return valid command', async () => {
      (mockScanner.prompt as jest.Mock).mockResolvedValue('1');

      const command = await (app as any).promptForCommand(mockScanner);

      expect(command).toBe(1);
      expect(mockScanner.prompt).toHaveBeenCalledWith('command');
    });

    it('should retry on invalid input and then accept valid input', async () => {
      (mockScanner.prompt as jest.Mock)
        .mockResolvedValueOnce('invalid')
        .mockResolvedValueOnce('2');

      const command = await (app as any).promptForCommand(mockScanner);

      expect(command).toBe(2);
      expect(mockScanner.prompt).toHaveBeenCalledTimes(2);
    });
  });

  describe('promptForSearchCommand', () => {
    let mockScanner: Scanner;

    beforeEach(() => {
      mockScanner = {
        prompt: jest.fn(),
      } as unknown as Scanner;
    });

    it('should return valid search command', async () => {
      (mockScanner.prompt as jest.Mock).mockResolvedValue('2');

      const command = await (app as any).promptForSearchCommand(mockScanner);

      expect(command).toBe(2);
      expect(mockScanner.prompt).toHaveBeenCalledWith('search');
    });

    it('should retry on invalid input', async () => {
      (mockScanner.prompt as jest.Mock)
        .mockResolvedValueOnce('invalid')
        .mockResolvedValueOnce('3');

      const command = await (app as any).promptForSearchCommand(mockScanner);

      expect(command).toBe(3);
      expect(mockScanner.prompt).toHaveBeenCalledTimes(2);
    });
  });
});
