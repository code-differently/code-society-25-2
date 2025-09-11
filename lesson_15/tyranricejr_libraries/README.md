# Media Collection App – Integration & Unit Testing

This project is a TypeScript CLI application for managing a media collection, designed to demonstrate **Test-Driven Development (TDD)** and **integration testing** practices. It is based on the Lesson 10 `libraries` app, extended for Lesson 15 with a focus on robust testing and code coverage.

## Features

- **MediaCollectionApp**: CLI for loading, searching, and displaying media items from various loaders.
- **Loader System**: Easily extensible to support different data sources.
- **Test Suite**: Comprehensive unit and integration tests using Jest and TypeScript.
- **Code Coverage**: Configured to measure and report code coverage, with a focus on the CLI and loader integration.

## Test Functionality

## Test Functionality

### Test Suite Structure

This project includes two types of tests for comprehensive coverage:

1. **Integration Tests** (`media_collection_app_integration.test.ts`):  
   - Uses real child processes to spawn the CLI application
   - Tests end-to-end workflows with actual CSV data
   - Verifies complete integration between components
   - Simulates real user interactions through stdin/stdout

2. **Unit Tests** (`media_collection_app.unit.test.ts`):  
   - Tests individual methods of the MediaCollectionApp class in isolation
   - Uses mocks and spies for comprehensive code coverage
   - Covers all private and public methods
   - Tests error handling and edge cases

### What the Tests Cover

- **Integration Tests**:  
  - CLI flows: main menu, search, and exit using real processes
  - Menu display verification through actual CLI output
  - Search workflows with real CSV data loading
  - Collection info display through real CLI execution

- **Unit Tests**:
  - All private methods: loadCollectionUsingLoader, printMediaCollection, getLoaderFromCommandLine
  - Menu printing methods: printMenu, printSearchMenu
  - Search functionality: getSearchCriteria, printSearchResults, doSearch
  - User input handling: promptForCommand, promptForSearchCommand
  - Error handling and invalid input scenarios

### Example Test Cases

- **Integration**: Running the app with real CLI and verifying output contains expected menus and responses
- **Unit**: Testing loadCollectionUsingLoader with valid/invalid loader names
- **Unit**: Testing promptForCommand with invalid then valid input to verify error handling
- **Integration**: Testing complete search workflows from menu selection to results display

## How to Run the Tests

1. **Install dependencies:**
   ```sh
   npm install
   ```

2. **Run all tests with coverage:**
   ```sh
   npm test
   ```

3. **Run specific test suites:**

   **Integration tests only:**
   ```sh
   npm run test:media-collection-integration
   ```

   **Unit tests only:**
   ```sh
   npm run test:media-collection-unit
   ```

   **Both integration and unit tests:**
   ```sh
   npm run test:media-collection-all
   ```

   These commands run the respective test files and show coverage for the CLI app.

## Code Coverage

- **Target Coverage:**  
  - The project aims for at least **70% branch coverage** (as required by the stretch assignment).
  - The CLI app (`media_collection_app.ts`) typically achieves **85%+** coverage for statements, branches, and functions with the provided tests.
- **How to View Coverage Report:**  
  - After running the tests, open the HTML report in `coverage/lcov-report/index.html` to see detailed coverage metrics.
  - Only the CLI app and its tests are included in the `test:media-collection` script coverage.

## Customizing Coverage

The following scripts are available in `package.json` for different testing scenarios:

```json
{
  "test:media-collection-integration": "jest --coverage --collectCoverageFrom=src/cli/media_collection_app.ts src/media_collection_app_integration.test.ts",
  "test:media-collection-unit": "jest --coverage --collectCoverageFrom=src/cli/media_collection_app.ts src/media_collection_app.unit.test.ts",
  "test:media-collection-all": "jest --coverage --collectCoverageFrom=src/cli/media_collection_app.ts src/media_collection_app_integration.test.ts src/media_collection_app.unit.test.ts"
}
```

- **Integration tests** provide real-world validation but may have lower code coverage
- **Unit tests** provide comprehensive code coverage for all methods and branches
- **Combined tests** give the best of both worlds: real-world validation + comprehensive coverage

## Notes

- **Integration tests** use real loaders; gather loaders from classmates inside 'loaders' subdirectory.
- **Unit tests** use mocked dependencies for isolated testing.
- The test suite is designed to be run in a Node.js/TypeScript environment with Jest.
- Integration tests may take longer to run as they spawn real processes.

## Troubleshooting

- If you see coverage for files you are not testing, ensure your `collectCoverageFrom` setting is correct.
- If TypeScript complains about type errors in tests that intentionally break types (to test error handling), use `// @ts-expect-error` above the relevant line.
- For integration test timeouts, increase the timeout values in the test files (currently set to 20 seconds).
- If integration tests fail, ensure your CSV data file exists and has valid data for the tyranricejr loader.

