import { Test, TestingModule } from '@nestjs/testing';
import { jest } from '@jest/globals';
import { AppModule } from '../app.module.js';
import { MediaCollectionApp } from '../cli/media_collection_app.js';
import { Scanner } from '../cli/scanner.js';

// Helper class to mock user input for integration tests
class MockInputHelper {
  private responses: string[] = [];
  private responseIndex = 0;

  constructor(responses: string[]) {
    this.responses = responses;
  }

  getNextResponse(): string {
    const response = this.responses[this.responseIndex] || '';
    this.responseIndex++;
    return response;
  }

  hasMoreResponses(): boolean {
    return this.responseIndex < this.responses.length;
  }
}

describe('MediaCollectionApp Integration Tests - Complete', () => {
  let app: MediaCollectionApp;
  let module: TestingModule;
  let consoleSpy: ReturnType<typeof jest.spyOn>;
  let consoleOutput: string[];

  beforeEach(async () => {
    // Set up NestJS testing module
    module = await Test.createTestingModule({
      imports: [AppModule],
    }).compile();

    app = module.get<MediaCollectionApp>(MediaCollectionApp);

    // Mock console.log to capture output
    consoleOutput = [];
    consoleSpy = jest.spyOn(console, 'log').mockImplementation((message) => {
      consoleOutput.push(message?.toString() || '');
    });

    // Reset process.argv
    process.argv = ['node', 'test'];
  });

  afterEach(async () => {
    consoleSpy.mockRestore();
    jest.restoreAllMocks();
    await module.close();
  });

  describe('User Flow: Load data and exit immediately', () => {
    it('should load data using a specific loader and display collection info', async () => {
      // Mock the getLoaderFromCommandLine method to return a valid loader
      jest.spyOn(app as any, 'getLoaderFromCommandLine').mockReturnValue('anthonymays');
      
      // Mock user input: just exit (option 1)
      const mockInput = new MockInputHelper(['1']);
      
      jest.spyOn(Scanner.prototype, 'prompt').mockImplementation(async () => {
        return mockInput.getNextResponse();
      });

      await app.run();

      // Verify data was loaded
      expect(consoleOutput).toContain('========================================');
      expect(consoleOutput.some(line => line.includes('Number of items:'))).toBe(true);
      
      // Verify menu was displayed
      expect(consoleOutput.some(line => line.includes('Enter the number of the desired command:'))).toBe(true);
      expect(consoleOutput).toContain('1) << EXIT');
      expect(consoleOutput).toContain('2) SEARCH');
    });
  });

  describe('User Flow: Load data and search by title', () => {
    it('should search for media items by title', async () => {
      jest.spyOn(app as any, 'getLoaderFromCommandLine').mockReturnValue('anthonymays');
      
      // User input sequence: 2 (search) -> 2 (title) -> "Test" -> 1 (return) -> 1 (exit)
      const mockInput = new MockInputHelper(['2', '2', 'Test', '1', '1']);
      
      jest.spyOn(Scanner.prototype, 'prompt').mockImplementation(async () => {
        return mockInput.getNextResponse();
      });

      await app.run();

      // Verify search menu was displayed
      expect(consoleOutput.some(line => line.includes('Enter the number of the desired search criteria:'))).toBe(true);
      expect(consoleOutput).toContain('1) << RETURN');
      expect(consoleOutput).toContain('2) TITLE');
      
      // Verify search prompt
      expect(consoleOutput.some(line => line.includes('Enter the title to search for:'))).toBe(true);
      
      // Verify search results (should show results or "No results found")
      const hasSearchResults = consoleOutput.some(line => 
        line.includes('Search results:') || line.includes('No results found.')
      );
      expect(hasSearchResults).toBe(true);
    });
  });

  describe('User Flow: Load data and search by release year', () => {
    it('should search for media items by release year', async () => {
      jest.spyOn(app as any, 'getLoaderFromCommandLine').mockReturnValue('anthonymays');
      
      // User input: 2 (search) -> 3 (release year) -> "2020" -> 1 (return) -> 1 (exit)
      const mockInput = new MockInputHelper(['2', '3', '2020', '1', '1']);
      
      jest.spyOn(Scanner.prototype, 'prompt').mockImplementation(async () => {
        return mockInput.getNextResponse();
      });

      await app.run();

      // Verify release year search prompt
      expect(consoleOutput.some(line => line.includes('Enter the release year to search for:'))).toBe(true);
    });
  });

  describe('User Flow: Load data and search by cast name', () => {
    it('should search for media items by cast name', async () => {
      jest.spyOn(app as any, 'getLoaderFromCommandLine').mockReturnValue('anthonymays');
      
      // User input: 2 (search) -> 4 (cast name) -> "John" -> 1 (return) -> 1 (exit)
      const mockInput = new MockInputHelper(['2', '4', 'John', '1', '1']);
      
      jest.spyOn(Scanner.prototype, 'prompt').mockImplementation(async () => {
        return mockInput.getNextResponse();
      });

      await app.run();

      // Verify cast name search prompt
      expect(consoleOutput.some(line => line.includes('Enter the cast name to search for:'))).toBe(true);
    });
  });

  describe('User Flow: Invalid input handling', () => {
    it('should handle invalid main menu choices gracefully', async () => {
      jest.spyOn(app as any, 'getLoaderFromCommandLine').mockReturnValue('anthonymays');
      
      // User input: 99 (invalid) -> abc (invalid) -> 1 (exit)
      const mockInput = new MockInputHelper(['99', 'abc', '1']);
      
      jest.spyOn(Scanner.prototype, 'prompt').mockImplementation(async () => {
        return mockInput.getNextResponse();
      });

      await app.run();

      // Should eventually show menu multiple times due to invalid input
      const menuDisplayCount = consoleOutput.filter(line => 
        line.includes('Enter the number of the desired command:')
      ).length;
      expect(menuDisplayCount).toBeGreaterThan(1);
    });

    it('should handle invalid search menu choices gracefully', async () => {
      jest.spyOn(app as any, 'getLoaderFromCommandLine').mockReturnValue('anthonymays');
      
      // User input: 2 (search) -> 99 (invalid search) -> 5 (invalid search) -> 1 (return) -> 1 (exit)
      const mockInput = new MockInputHelper(['2', '99', '5', '1', '1']);
      
      jest.spyOn(Scanner.prototype, 'prompt').mockImplementation(async () => {
        return mockInput.getNextResponse();
      });

      await app.run();

      // Should show search menu multiple times due to invalid input
      const searchMenuDisplayCount = consoleOutput.filter(line => 
        line.includes('Enter the number of the desired search criteria:')
      ).length;
      expect(searchMenuDisplayCount).toBeGreaterThan(1);
    });
  });

  describe('User Flow: No loader specified', () => {
    it('should handle running without a loader', async () => {
      // Don't mock getLoaderFromCommandLine, let it return null
      jest.spyOn(app as any, 'getLoaderFromCommandLine').mockReturnValue(null);
      
      const mockInput = new MockInputHelper(['1']);
      
      jest.spyOn(Scanner.prototype, 'prompt').mockImplementation(async () => {
        return mockInput.getNextResponse();
      });

      await app.run();

      // Should show empty collection (0 items)
      expect(consoleOutput.some(line => line.includes('Number of items: 0'))).toBe(true);
    });
  });

  describe('User Flow: Multiple search operations', () => {
    it('should allow multiple searches in one session', async () => {
      jest.spyOn(app as any, 'getLoaderFromCommandLine').mockReturnValue('anthonymays');
      
      // Complex flow: search by title, then by year, then exit
      const mockInput = new MockInputHelper([
        '2',      // search
        '2',      // title
        'Test',   // search term
        '2',      // search again
        '3',      // release year
        '2020',   // year
        '1',      // exit
      ]);
      
      jest.spyOn(Scanner.prototype, 'prompt').mockImplementation(async () => {
        return mockInput.getNextResponse();
      });

      await app.run();

      // Should show search prompts for both title and release year
      expect(consoleOutput.some(line => line.includes('Enter the title to search for:'))).toBe(true);
      expect(consoleOutput.some(line => line.includes('Enter the release year to search for:'))).toBe(true);
    });
  });

  describe('User Flow: Application lifecycle', () => {
    it('should complete full application lifecycle without errors', async () => {
      jest.spyOn(app as any, 'getLoaderFromCommandLine').mockReturnValue('anthonymays');
      
      const mockInput = new MockInputHelper(['2', '2', 'Matrix', '1', '1']);
      
      jest.spyOn(Scanner.prototype, 'prompt').mockImplementation(async () => {
        return mockInput.getNextResponse();
      });

      // Should not throw any errors
      await expect(app.run()).resolves.not.toThrow();
      
      // Verify the application ran through its complete lifecycle
      expect(consoleOutput.length).toBeGreaterThan(0);
      expect(consoleOutput).toContain('========================================');
    });
  });
});
