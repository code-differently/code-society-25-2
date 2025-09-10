import {
  afterEach,
  beforeEach,
  describe,
  expect,
  it,
  jest,
} from '@jest/globals';
import { Test } from '@nestjs/testing';
import { MediaCollectionApp } from '../cli/media_collection_app.js';
import { Scanner } from '../cli/scanner.js';
import { LoadersModule } from '../loaders/loaders.module.js';
import { MediaCollection } from '../models/media_collection.js';
import { SearchCriteria } from '../models/search_criteria.js';

//npm start with specific loader: npm start -- --loader anthonymays
describe('MediaCollectionApp Integration (NestJS DI)', () => {
  let app: MediaCollectionApp;

  beforeEach(async () => {
    const moduleRef = await Test.createTestingModule({
      imports: [LoadersModule],
      providers: [MediaCollectionApp],
    }).compile();

    app = moduleRef.get<MediaCollectionApp>(MediaCollectionApp);

    // Silence console.log so tests can assert without noisy output
    jest.spyOn(console, 'log').mockImplementation(() => {
      console.log('mocked log');
    });
  });

  afterEach(() => {
    jest.restoreAllMocks();
  });

  it('exits gracefully when EXIT is chosen', async () => {
    const promptSpy = jest.spyOn(
      app as unknown as {
        promptForCommand: (scanner: Scanner) => Promise<number>;
      },
      'promptForCommand',
    );

    promptSpy.mockResolvedValueOnce(1); // EXIT

    await app.run();

    expect(console.log).toHaveBeenCalledWith(
      expect.stringContaining('Number of items:'),
    );
  });

  it('searches by TITLE and returns results', async () => {
    const promptSpy = jest.spyOn(
      app as unknown as {
        promptForCommand: (scanner: Scanner) => Promise<number>;
      },
      'promptForCommand',
    ), [Scanner]>;
    // First call: SEARCH, Second call: EXIT
    promptSpy.mockResolvedValueOnce(2).mockResolvedValueOnce(1);

    const promptSearchSpy = jest.spyOn(
      app as unknown as {
        promptForSearchCommand: (scanner: Scanner) => Promise<number>;
      },
      'promptForSearchCommand',
    ), [Scanner]>;
    promptSearchSpy.mockResolvedValueOnce(2); // TITLE

    const getCriteriaSpy = jest.spyOn(
      app as unknown as {
        getSearchCriteria: (
          scanner: Scanner,
          command: number,
        ) => Promise<SearchCriteria>;
      },
      'getSearchCriteria',
    ), [Scanner, number]>;
    getCriteriaSpy.mockResolvedValueOnce({ title: 'Movie One' });

    await app.run();

    expect(console.log).toHaveBeenCalledWith(
      expect.stringContaining('Search results:'),
    );
    expect(console.log).toHaveBeenCalledWith(
      expect.stringContaining('TITLE: Movie One'),
    );
  });

  it('searches by RELEASE_YEAR and returns results (expects TITLE of matching item)', async () => {
    const promptSpy = jest.spyOn(
      app as unknown as {
        promptForCommand: (scanner: Scanner) => Promise<number>;
      },
      'promptForCommand',
    ), [Scanner]>;
    promptSpy.mockResolvedValueOnce(2).mockResolvedValueOnce(1);

    const promptSearchSpy = jest.spyOn(
      app as unknown as {
        promptForSearchCommand: (scanner: Scanner) => Promise<number>;
      },
      'promptForSearchCommand',
    ), [Scanner]>;
    promptSearchSpy.mockResolvedValueOnce(3); // RELEASE_YEAR

    const getCriteriaSpy = jest.spyOn(
      app as unknown as {
        getSearchCriteria: (
          scanner: Scanner,
          command: number,
        ) => Promise<SearchCriteria>;
      },
      'getSearchCriteria',
    ), [Scanner, number]>;
    getCriteriaSpy.mockResolvedValueOnce({ releaseYear: 2014 });

    await app.run();

    // printSearchResults prints TITLE lines for matched items
    expect(console.log).toHaveBeenCalledWith(expect.stringContaining('TITLE:'));
  });

  it('searches by CAST_NAME and returns results', async () => {
    const promptSpy = jest.spyOn(
      app as unknown as {
        promptForCommand: (scanner: Scanner) => Promise<number>;
      },
      'promptForCommand',
    ), [Scanner]>;
    promptSpy.mockResolvedValueOnce(2).mockResolvedValueOnce(1);

    const promptSearchSpy = jest.spyOn(
      app as unknown as {
        promptForSearchCommand: (scanner: Scanner) => Promise<number>;
      },
      'promptForSearchCommand',
    ), [Scanner]>;
    promptSearchSpy.mockResolvedValueOnce(4); // CAST_NAME

    const getCriteriaSpy = jest.spyOn(
      app as unknown as {
        getSearchCriteria: (
          scanner: Scanner,
          command: number,
        ) => Promise<SearchCriteria>;
      },
      'getSearchCriteria',
    ), [Scanner, number]>;
    // match whatever your loader data contains; example:
    getCriteriaSpy.mockResolvedValueOnce({ creditName: 'Actor A' });

    await app.run();

    expect(console.log).toHaveBeenCalledWith(expect.stringContaining('TITLE:'));
  });

  it('returns empty results for unmatched search', async () => {
    const promptSpy = jest.spyOn(
      app as unknown as {
        promptForCommand: (scanner: Scanner) => Promise<number>;
      },
      'promptForCommand',
    ), [Scanner]>;
    promptSpy.mockResolvedValueOnce(2).mockResolvedValueOnce(1);

    const promptSearchSpy = jest.spyOn(
      app as unknown as {
        promptForSearchCommand: (scanner: Scanner) => Promise<number>;
      },
      'promptForSearchCommand',
    ), [Scanner]>;
    promptSearchSpy.mockResolvedValueOnce(2); // TITLE

    const getCriteriaSpy = jest.spyOn(
      app as unknown as {
        getSearchCriteria: (
          scanner: Scanner,
          command: number,
        ) => Promise<SearchCriteria>;
      },
      'getSearchCriteria',
    ), [Scanner, number]>;
    getCriteriaSpy.mockResolvedValueOnce({ title: 'No Match' });

    await app.run();

    expect(console.log).toHaveBeenCalledWith(
      expect.stringContaining('No results found.'),
    );
  });

  it('handles invalid main command gracefully', async () => {
    const promptSpy = jest.spyOn(
      app as unknown as {
        promptForCommand: (scanner: Scanner) => Promise<number>;
      },
      'promptForCommand',
    ), [Scanner]>;
    promptSpy.mockResolvedValueOnce(99).mockResolvedValueOnce(1);

    await app.run();

    expect(console.log).toHaveBeenCalledWith(
      expect.stringContaining('Not ready yet, coming soon!'),
    );
  });

  it('prints the main and search menus (private helpers)', () => {
    (app as unknown as { printMenu: () => void }).printMenu();
    (app as unknown as { printSearchMenu: () => void }).printSearchMenu();

    expect(console.log).toHaveBeenCalledWith(expect.stringContaining('EXIT'));
    expect(console.log).toHaveBeenCalledWith(expect.stringContaining('SEARCH'));
    expect(console.log).toHaveBeenCalledWith(expect.stringContaining('TITLE'));
  });

  it('prints collection info directly', async () => {
    const loaderAccessor = app as unknown as {
      loadCollectionUsingLoader: (
        loaderName: string,
      ) => Promise<MediaCollection>;
      printMediaCollection: (collection: MediaCollection) => void;
    };

    const collection =
      await loaderAccessor.loadCollectionUsingLoader('anthonymays');
    loaderAccessor.printMediaCollection(collection);

    expect(console.log).toHaveBeenCalledWith(
      expect.stringContaining('Number of items:'),
    );
  });
});
