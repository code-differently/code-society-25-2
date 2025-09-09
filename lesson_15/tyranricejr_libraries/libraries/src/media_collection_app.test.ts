/**
 * Integration and unit tests for MediaCollectionApp.
 *
 * These tests use Jest and TypeScript to verify the CLI logic, error handling,
 * and integration with mock loaders. All user input and loader data is mocked,
 * ensuring deterministic and isolated tests.
 *
 * Test Coverage:
 * - Main menu and exit flow
 * - Search flow and all private methods
 * - Handling of unknown/invalid commands and search commands
 * - Error handling for exceptions in user input (catch blocks)
 * - Default and edge cases for loader selection and search criteria
 * - Output and menu rendering
 *
 * Usage:
 *   npm run test:media-collection
 *   # or
 *   jest --coverage src/media_collection_app.test.ts
 *
 * For more info, see the project README.md.
 */

import { beforeEach, describe, expect, it, jest } from '@jest/globals';
import { MediaCollectionApp } from './cli/media_collection_app.js';
import { Loader } from './loaders/loader.js';
import { MediaCollection } from './models/media_collection.js';
import { MediaItem } from './models/media_item.js';

// Helper: create a mock Loader
function createMockLoader(name: string, items: MediaItem[] = []) {
  return {
    getLoaderName: jest.fn(() => name),
    loadData: jest.fn(async () => items),
  } as unknown as Loader;
}

// Helper: create a mock MediaItem
function createMockMediaItem(
  id: number,
  title = 'Test',
  type = 'movie',
  year = 2020,
) {
  return {
    getId: jest.fn(() => id),
    getTitle: jest.fn(() => title),
    getType: jest.fn(() => type),
    getReleaseYear: jest.fn(() => year),
    getCredits: jest.fn(() => []),
  } as unknown as MediaItem;
}

// Helper: mock Scanner to simulate user input
class MockScanner {
  private prompts: string[];
  constructor(prompts: string[]) {
    this.prompts = prompts;
  }
  async prompt() {
    return this.prompts.shift() ?? '';
  }
}

describe('MediaCollectionApp', () => {
  let loaders: Loader[];
  let items: MediaItem[];

  beforeEach(() => {
    items = [
      createMockMediaItem(1, 'A', 'movie', 2020),
      createMockMediaItem(2, 'B', 'tv_show', 2021),
    ];
    loaders = [createMockLoader('csv', items)];
  });

  /**
   * Test: Exits immediately from the main menu.
   * Mocks user input to select EXIT, verifies that the collection is loaded and printed.
   */
  it('should run through the main menu and exit (EXIT command)', async () => {
    const app = new MediaCollectionApp(loaders);
    jest.spyOn(app as any, 'getLoaderFromCommandLine').mockReturnValue('csv');
    jest.spyOn(app as any, 'promptForCommand').mockResolvedValue(1);
    const printSpy = jest
      .spyOn(app as any, 'printMediaCollection')
      .mockImplementation(() => {});
    await app.run();
    expect(printSpy).toHaveBeenCalled();
    expect((app as any).loaders[0].loadData).toHaveBeenCalled();
  });

  /**
   * Test: Runs the SEARCH flow, ensuring all private methods are called.
   * Simulates a search command, mocks search criteria, and verifies search and print methods.
   */
  it('should run SEARCH flow and call all private methods', async () => {
    const app = new MediaCollectionApp(loaders);
    jest.spyOn(app as any, 'getLoaderFromCommandLine').mockReturnValue('csv');
    const promptForCommand = jest
      .spyOn(app as any, 'promptForCommand')
      .mockResolvedValueOnce(2) // SEARCH
      .mockResolvedValueOnce(1); // EXIT
    jest.spyOn(app as any, 'promptForSearchCommand').mockResolvedValue(2); // TITLE
    jest
      .spyOn(app as any, 'getSearchCriteria')
      .mockResolvedValue({ title: 'A' });
    const printMediaCollection = jest
      .spyOn(app as any, 'printMediaCollection')
      .mockImplementation(() => {});
    const printSearchResults = jest
      .spyOn(app as any, 'printSearchResults')
      .mockImplementation(() => {});
    const mockCollection = {
      getInfo: () => ({ getItems: () => items }),
      search: jest.fn(() => new Set([items[0]])),
    } as unknown as MediaCollection;
    jest
      .spyOn(app as any, 'loadCollectionUsingLoader')
      .mockResolvedValue(mockCollection);
    await app.run();
    expect(printMediaCollection).toHaveBeenCalled();
    expect(printSearchResults).toHaveBeenCalled();
    expect(mockCollection.search).toHaveBeenCalledWith({ title: 'A' });
    expect(promptForCommand).toHaveBeenCalledTimes(2);
  });

  /**
   * Test: Handles an unknown search command gracefully.
   * Simulates an invalid search command and verifies that search and print methods are called with undefined.
   */
  it('should handle unknown search command gracefully', async () => {
    const app = new MediaCollectionApp(loaders);
    jest.spyOn(app as any, 'getLoaderFromCommandLine').mockReturnValue('csv');
    jest
      .spyOn(app as any, 'promptForCommand')
      .mockResolvedValueOnce(2)
      .mockResolvedValueOnce(1);
    jest.spyOn(app as any, 'promptForSearchCommand').mockResolvedValue(99); // UNKNOWN
    jest.spyOn(app as any, 'getSearchCriteria').mockResolvedValue(undefined);
    const printMediaCollection = jest
      .spyOn(app as any, 'printMediaCollection')
      .mockImplementation(() => {});
    const printSearchResults = jest
      .spyOn(app as any, 'printSearchResults')
      .mockImplementation(() => {});
    const mockCollection = {
      getInfo: () => ({ getItems: () => items }),
      search: jest.fn(() => new Set()),
    } as unknown as MediaCollection;
    jest
      .spyOn(app as any, 'loadCollectionUsingLoader')
      .mockResolvedValue(mockCollection);
    await app.run();
    expect(printMediaCollection).toHaveBeenCalled();
    expect(printSearchResults).toHaveBeenCalled();
    expect(mockCollection.search).toHaveBeenCalledWith(undefined);
  });

  /**
   * Test: Verifies that the main and search menus are printed correctly.
   */
  it('should print correct menu and search menu', () => {
    const app = new MediaCollectionApp(loaders);
    const menuSpy = jest.spyOn(console, 'log').mockImplementation(() => {});
    (app as any).printMenu();
    (app as any).printSearchMenu();
    expect(menuSpy).toHaveBeenCalledWith(
      '\nEnter the number of the desired command:',
    );
    expect(menuSpy).toHaveBeenCalledWith('1) << EXIT');
    expect(menuSpy).toHaveBeenCalledWith('2) SEARCH');
    expect(menuSpy).toHaveBeenCalledWith(
      '\nEnter the number of the desired search criteria:',
    );
    expect(menuSpy).toHaveBeenCalledWith('1) << RETURN');
    expect(menuSpy).toHaveBeenCalledWith('2) TITLE');
    expect(menuSpy).toHaveBeenCalledWith('3) AUTHOR');
    expect(menuSpy).toHaveBeenCalledWith('4) TYPE');
    menuSpy.mockRestore();
  });

  /**
   * Test: Verifies that the media collection info is printed with the correct number of items.
   */
  it('should print media collection info', () => {
    const app = new MediaCollectionApp(loaders);
    const collection = {
      getInfo: () => ({
        getItems: () => items,
      }),
    } as unknown as MediaCollection;
    const logSpy = jest.spyOn(console, 'log').mockImplementation(() => {});
    (app as any).printMediaCollection(collection);
    expect(logSpy).toHaveBeenCalledWith('Number of items: ' + items.length);
    logSpy.mockRestore();
  });

  /**
   * Test: Verifies that printing search results with no results still logs output.
   */
  it('should print search results with no results', () => {
    const app = new MediaCollectionApp(loaders);
    const logSpy = jest.spyOn(console, 'log').mockImplementation(() => {});
    (app as any).printSearchResults(new Set());
    expect(logSpy).toHaveBeenCalled();
    logSpy.mockRestore();
  });

  /**
   * Test: Verifies that printing search results with results logs output.
   */
  it('should print search results with results', () => {
    const app = new MediaCollectionApp(loaders);
    const logSpy = jest.spyOn(console, 'log').mockImplementation(() => {});
    const resultSet = new Set([createMockMediaItem(1, 'A', 'movie', 2020)]);
    (app as any).printSearchResults(resultSet);
    expect(logSpy).toHaveBeenCalled();
    logSpy.mockRestore();
  });

  /**
   * Test: Ensures that loading with a null loaderName returns an empty collection.
   */
  it('should return empty collection if loaderName is null', async () => {
    const app = new MediaCollectionApp(loaders);
    const collection = await (app as any).loadCollectionUsingLoader(null);
    expect(collection.getInfo().getItems()).toEqual([]);
  });

  /**
   * Test: Handles the default case in the main run loop (unknown command).
   * Simulates an unknown command, then exit, and verifies the correct log output.
   */
  it('should handle default case in run loop', async () => {
    const app = new MediaCollectionApp(loaders);
    jest.spyOn(app as any, 'getLoaderFromCommandLine').mockReturnValue('csv');
    jest
      .spyOn(app as any, 'promptForCommand')
      .mockResolvedValueOnce(99) // UNKNOWN
      .mockResolvedValueOnce(1); // EXIT
    jest.spyOn(app as any, 'printMediaCollection').mockImplementation(() => {});
    const logSpy = jest.spyOn(console, 'log').mockImplementation(() => {});
    jest.spyOn(app as any, 'loadCollectionUsingLoader').mockResolvedValue({
      getInfo: () => ({ getItems: () => items }),
    });
    await app.run();
    expect(logSpy).toHaveBeenCalledWith('\nNot ready yet, coming soon!');
    logSpy.mockRestore();
  });

  /**
   * Test: Forces the catch block in promptForSearchCommand by making .trim() throw.
   * Verifies that the error is logged and the loop continues until a valid command is returned.
   */
  it('should log error when promptForSearchCommand catch block is triggered', async () => {
    const app = new MediaCollectionApp(loaders);
    const scanner = {
      prompt: jest
        .fn()
        // @ts-expect-error: purposely returning an object to trigger catch block
        .mockResolvedValueOnce({
          trim: () => {
            throw new Error('fail');
          },
        })
        // @ts-expect-error: purposely returning an object to trigger catch block
        .mockResolvedValueOnce('1'), // RETURN
    };
    const printSearchMenuSpy = jest
      .spyOn(app as any, 'printSearchMenu')
      .mockImplementation(() => {});
    const logSpy = jest.spyOn(console, 'log').mockImplementation(() => {});
    const result = await (app as any).promptForSearchCommand(scanner);
    expect(result).toBe(1); // Should eventually return the valid command
    expect(logSpy).toHaveBeenCalledWith(
      expect.stringContaining('Invalid command:'),
    );
    printSearchMenuSpy.mockRestore();
    logSpy.mockRestore();
  });

  /**
   * Test: Covers the default branch in getSearchCriteria.
   * Calls getSearchCriteria with an invalid command and verifies the error log.
   */
  it('should log error when getSearchCriteria is called with an invalid command', async () => {
    const app = new MediaCollectionApp(loaders);
    const scanner = {
      prompt: jest.fn(),
    };
    const logSpy = jest.spyOn(console, 'log').mockImplementation(() => {});
    const result = await (app as any).getSearchCriteria(scanner, 999);
    expect(result).toBeUndefined();
    expect(logSpy).toHaveBeenCalledWith('Invalid search command: 999');
    logSpy.mockRestore();
  });

  /**
   * Test: Forces the catch block in promptForCommand by making .trim() throw.
   * Verifies that the error is logged and the loop continues until a valid command is returned.
   */
  it('should log error when promptForCommand catch block is triggered', async () => {
    const app = new MediaCollectionApp(loaders);
    const scanner = {
      prompt: jest
        .fn()
        // @ts-expect-error: purposely returning an object to trigger catch block
        .mockResolvedValueOnce({
          trim: () => {
            throw new Error('fail');
          },
        })
        // @ts-expect-error: purposely returning an object to trigger catch block
        .mockResolvedValueOnce('1'), // EXIT
    };
    const printMenuSpy = jest
      .spyOn(app as any, 'printMenu')
      .mockImplementation(() => {});
    const logSpy = jest.spyOn(console, 'log').mockImplementation(() => {});
    const result = await (app as any).promptForCommand(scanner);
    expect(result).toBe(1); // Should eventually return the valid command
    expect(logSpy).toHaveBeenCalledWith(
      expect.stringContaining('Invalid command:'),
    );
    printMenuSpy.mockRestore();
    logSpy.mockRestore();
  });
});
