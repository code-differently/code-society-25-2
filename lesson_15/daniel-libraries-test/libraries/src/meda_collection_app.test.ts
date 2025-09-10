// __tests__/media_collection_app.int.test.ts
import { Test, TestingModule } from '@nestjs/testing';
import { Loader } from '../src/loaders/loader.js';
import { Loaders } from '../src/loaders/loaders.module.js';
import { AppModule } from './app.module.js';
import { MediaCollectionApp } from './cli/media_collection_app.js';
import { Scanner } from './cli/scanner.js';
import { MediaCollection } from './models/media_collection.js';
import { MediaItem } from './models/media_item.js';
import { MediaType } from './models/media_type.js';
import { Role } from './models/role.js';
import { Credit } from './models/credit.js';

const IGNORE_LOADER = 'anthonymays';

// Mock Scanner class
jest.mock('./cli/scanner.js');
const MockedScanner = Scanner as jest.MockedClass<typeof Scanner>;

describe('MediaCollectionApp (integration)', () => {
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

  beforeEach(() => {
    jest.clearAllMocks();
  });

  it('loads loaders that load all items', async () => {
    for (const loader of loaders) {
      if (loader.getLoaderName() === IGNORE_LOADER) {
        continue;
      }

      const items = await loader.loadData();
      expect(Array.isArray(items)).toBe(true);
      expect(items.length).toBeGreaterThan(0);

      for (const item of items) {
        expect(item.getId()).toBeDefined();
        expect(item.getTitle()).toBeDefined();
        expect(item.getType()).toBeDefined();
      }
    }
  });

  it('should prompt for main command and handle exit', async () => {
    const mockScanner = new MockedScanner({} as any);
    mockScanner.prompt = jest.fn().mockResolvedValue('1'); // EXIT command

    const consoleSpy = jest.spyOn(console, 'log').mockImplementation();

    const command = await app['promptForCommand'](mockScanner);

    expect(command).toBe(1); // MainCommand.EXIT
    expect(mockScanner.prompt).toHaveBeenCalledWith('command');
    expect(consoleSpy).toHaveBeenCalledWith(
      '\nEnter the number of the desired command:',
    );

    consoleSpy.mockRestore();
  });

  it('should prompt for search command and handle return', async () => {
    const mockScanner = new MockedScanner({} as any);
    mockScanner.prompt = jest.fn().mockResolvedValue('1'); // RETURN command

    const consoleSpy = jest.spyOn(console, 'log').mockImplementation();

    const command = await app['promptForSearchCommand'](mockScanner);

    expect(command).toBe(1); // SearchCommand.RETURN
    expect(mockScanner.prompt).toHaveBeenCalledWith('search');
    expect(consoleSpy).toHaveBeenCalledWith(
      '\nEnter the number of the desired search criteria:',
    );

    consoleSpy.mockRestore();
  });

  it('should get search criteria for title', async () => {
    const mockScanner = new MockedScanner({} as any);
    mockScanner.prompt = jest.fn().mockResolvedValue('Test Movie');

    const consoleSpy = jest.spyOn(console, 'log').mockImplementation();

    const criteria = await app['getSearchCriteria'](mockScanner, 2); // SearchCommand.TITLE

    expect(criteria).toEqual({ title: 'Test Movie' });
    expect(mockScanner.prompt).toHaveBeenCalledWith('title');
    expect(consoleSpy).toHaveBeenCalledWith('Enter the title to search for: ');

    consoleSpy.mockRestore();
  });

  it('should get search criteria for release year', async () => {
    const mockScanner = new MockedScanner({} as any);
    mockScanner.prompt = jest.fn().mockResolvedValue('2020');

    const consoleSpy = jest.spyOn(console, 'log').mockImplementation();

    const criteria = await app['getSearchCriteria'](mockScanner, 3); // SearchCommand.RELEASE_YEAR

    expect(criteria).toEqual({ releaseYear: 2020 });
    expect(mockScanner.prompt).toHaveBeenCalledWith('year');
    expect(consoleSpy).toHaveBeenCalledWith(
      'Enter the release year to search for: ',
    );

    consoleSpy.mockRestore();
  });

  it('should get search criteria for cast name', async () => {
    const mockScanner = new MockedScanner({} as any);
    mockScanner.prompt = jest.fn().mockResolvedValue('John Doe');

    const consoleSpy = jest.spyOn(console, 'log').mockImplementation();

    const criteria = await app['getSearchCriteria'](mockScanner, 4); // SearchCommand.CAST_NAME

    expect(criteria).toEqual({ creditName: 'John Doe' });
    expect(mockScanner.prompt).toHaveBeenCalledWith('type');
    expect(consoleSpy).toHaveBeenCalledWith(
      'Enter the cast name to search for: ',
    );

    consoleSpy.mockRestore();
  });


  it('should perform complete search flow', async () => {
    const mockScanner = new MockedScanner({} as any);
    mockScanner.prompt = jest
      .fn()
      .mockResolvedValueOnce('2') // Choose TITLE search
      .mockResolvedValueOnce('Test'); // Enter search term

    const collection = new MediaCollection();
    collection.addItem(
      new MediaItem('1', 'Test Movie', MediaType.Movie, 2020, [new Credit('1','Test Actor',Role.Actor)]),
    );
    collection.addItem(
      new MediaItem('2', 'Another Movie', MediaType.Movie, 2021, [
        new Credit('2', 'Another Actor', Role.Actor),
      ]),
    );

    const consoleSpy = jest.spyOn(console, 'log').mockImplementation();

    await app['doSearch'](mockScanner, collection);

    expect(mockScanner.prompt).toHaveBeenCalledTimes(2);
    expect(consoleSpy).toHaveBeenCalledWith('Search results:\n');

    consoleSpy.mockRestore();
  });

//   it('should handle invalid command input with retry', async () => {
//     const mockScanner = new MockedScanner({} as any);
//     mockScanner.prompt = jest
//       .fn()
//       .mockResolvedValueOnce('invalid') // Invalid input first
//       .mockResolvedValueOnce('1'); // Valid EXIT command

//     const consoleSpy = jest.spyOn(console, 'log').mockImplementation();

//     const command = await app['promptForCommand'](mockScanner);

//     expect(command).toBe(1);
//     expect(mockScanner.prompt).toHaveBeenCalledTimes(2);
//     expect(consoleSpy).toHaveBeenCalledWith(
//       '\nEnter the number of the desired command:\n 1) << EXIT\n 2) SEARCH >>',
//     );

//     consoleSpy.mockRestore();
//   });

  it('should load collection using loader and print info', async () => {
    const validLoader = loaders.find(
      (l) => l.getLoaderName() !== IGNORE_LOADER,
    );
    expect(validLoader).toBeDefined();

    const consoleSpy = jest.spyOn(console, 'log').mockImplementation();

    const collection = await app['loadCollectionUsingLoader'](
      validLoader ? validLoader.getLoaderName() : null,
    );
    app['printMediaCollection'](collection);

    expect(collection).toBeInstanceOf(MediaCollection);
    expect(consoleSpy).toHaveBeenCalledWith(
      'Number of items: ' + collection.getInfo().getItems().length,
    );

    consoleSpy.mockRestore();
  });


});
