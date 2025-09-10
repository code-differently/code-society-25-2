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

import { describe, expect, it, jest } from '@jest/globals';
import { Test, TestingModule } from '@nestjs/testing';
import { AppModule } from './app.module.js';
import { MediaCollectionApp } from './cli/media_collection_app.js';
import { Loader } from './loaders/loader.js';
import { Loaders } from './loaders/loaders.module.js';
import { MediaCollection } from './models/media_collection.js';

// NOTE: These integration tests use a real CsvLoader and a real CSV file as fixture data.
// Make sure you have a test CSV file at 'test/fixtures/media.csv' with a few rows of valid data.
// Example CSV:
// id,title,type,releaseYear,credits
// 1,Test Movie,movie,2020,"Actor A;Actor B"
// 2,Test Show,tv_show,2021,"Actor C"

const IGNORE_LOADER = 'anthonymays';

describe('MediaCollectionApp', () => {
  let loaders: Loader[];
  let moduleFixture: TestingModule;

  beforeAll(async () => {
    moduleFixture = await Test.createTestingModule({
      imports: [AppModule],
    }).compile();

    loaders = moduleFixture.get(Loaders);
    loaders = loaders.filter(
      (loader) => loader.getLoaderName() !== IGNORE_LOADER,
    ); // Remove the anthonymays loader to avoid issues
  });

  /**
   * Test: Exits immediately from the main menu.
   * Mocks user input to select EXIT, verifies that the collection is loaded and printed.
   */
  it('should run through the main menu and exit (EXIT command)', async () => {
    const app = new MediaCollectionApp(loaders);
    const loadDataSpy = jest.spyOn(loaders[0], 'loadData');
    jest
      .spyOn(app as any, 'getLoaderFromCommandLine')
      .mockReturnValue(loaders[0].getLoaderName());
    jest.spyOn(app as any, 'promptForCommand').mockResolvedValue(1);
    const printSpy = jest
      .spyOn(app as any, 'printMediaCollection')
      .mockImplementation(() => {});
    await app.run();
    expect(printSpy).toHaveBeenCalled();
    expect(loadDataSpy).toHaveBeenCalled();
  });

  /**
   * Test: Runs the SEARCH flow, ensuring all private methods are called.
   * Simulates a search command, mocks search criteria, and verifies search and print methods.
   */
  it('should run SEARCH flow and call all private methods', async () => {
    const app = new MediaCollectionApp(loaders);
    const loaderCollection = await loaders[0].loadData();
    const realCollection = new MediaCollection();
    loaderCollection.forEach((item) => realCollection.addItem(item));
    const searchSpy = jest.spyOn(realCollection, 'search');
    jest
      .spyOn(app as any, 'getLoaderFromCommandLine')
      .mockReturnValue(loaders[0].getLoaderName());
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
    jest
      .spyOn(app as any, 'loadCollectionUsingLoader')
      .mockResolvedValue(realCollection);
    await app.run();
    expect(printMediaCollection).toHaveBeenCalled();
    expect(printSearchResults).toHaveBeenCalled();
    expect(searchSpy).toHaveBeenCalledWith({ title: 'A' });
    expect(promptForCommand).toHaveBeenCalledTimes(2);
  });

  /**
   * Test: Handles an unknown search command gracefully.
   * Simulates an invalid search command and verifies that search and print methods are called with undefined.
   */
  it('should handle unknown search command gracefully', async () => {
    const app = new MediaCollectionApp(loaders);
    const loaderCollection = await loaders[0].loadData();
    const realCollection = new MediaCollection();
    loaderCollection.forEach((item) => realCollection.addItem(item));
    const searchSpy = jest.spyOn(realCollection, 'search');
    jest
      .spyOn(app as any, 'getLoaderFromCommandLine')
      .mockReturnValue(loaders[0].getLoaderName());
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
    jest
      .spyOn(app as any, 'loadCollectionUsingLoader')
      .mockResolvedValue(realCollection);
    await app.run();
    expect(printMediaCollection).toHaveBeenCalled();
    expect(printSearchResults).toHaveBeenCalled();
    expect(searchSpy).toHaveBeenCalledWith(undefined);
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
  it('should print media collection info', async () => {
    const app = new MediaCollectionApp(loaders);
    const loaderCollection = await loaders[0].loadData();
    const realCollection = new MediaCollection();
    loaderCollection.forEach((item) => realCollection.addItem(item));
    const logSpy = jest.spyOn(console, 'log').mockImplementation(() => {});
    (app as any).printMediaCollection(realCollection);
    expect(logSpy).toHaveBeenCalledWith(
      'Number of items: ' + loaderCollection?.length,
    );
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
  it('should print search results with results', async () => {
    const app = new MediaCollectionApp(loaders);
    const logSpy = jest.spyOn(console, 'log').mockImplementation(() => {});
    const loaderCollection = await loaders[0].loadData();
    const realCollection = new MediaCollection();
    loaderCollection.forEach((item) => realCollection.addItem(item));
    const resultSet = new Set(loaderCollection?.slice(0, 1)); // Use first item as result
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
    jest
      .spyOn(app as any, 'getLoaderFromCommandLine')
      .mockReturnValue(loaders[0].getLoaderName());
    jest
      .spyOn(app as any, 'promptForCommand')
      .mockResolvedValueOnce(99) // UNKNOWN
      .mockResolvedValueOnce(1); // EXIT
    jest.spyOn(app as any, 'printMediaCollection').mockImplementation(() => {});
    const logSpy = jest.spyOn(console, 'log').mockImplementation(() => {});
    const loaderCollection = await loaders[0].loadData();
    const realCollection = new MediaCollection();
    loaderCollection.forEach((item) => realCollection.addItem(item));
    jest.spyOn(app as any, 'loadCollectionUsingLoader').mockResolvedValue({
      getInfo: () => ({ getItems: () => loaderCollection }),
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
