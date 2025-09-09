# Media Collection App – Integration & Unit Testing

This project is a TypeScript CLI application for managing a media collection, designed to demonstrate **Test-Driven Development (TDD)** and **integration testing** practices. It is based on the Lesson 10 `libraries` app, extended for Lesson 15 with a focus on robust testing and code coverage.

## Features

- **MediaCollectionApp**: CLI for loading, searching, and displaying media items from various loaders (e.g., CSV).
- **Loader System**: Easily extensible to support different data sources.
- **Test Suite**: Comprehensive unit and integration tests using Jest and TypeScript.
- **Code Coverage**: Configured to measure and report code coverage, with a focus on the CLI and loader integration.

## Test Functionality

### What the Tests Cover

- **Integration Tests**:  
  - Ensure `MediaCollectionApp` can load data using a mock CSV loader.
  - Simulate CLI flows: main menu, search, and exit.
  - Test error handling and edge cases (invalid commands, exceptions in input parsing).
- **Branch Coverage**:  
  - All branches in CLI logic, including `catch` blocks, `default` cases in `switch`, and error paths.
  - Tests for invalid loader names, invalid search commands, and exceptions thrown in user input.
- **Mocking**:  
  - All loaders are mocked to isolate the CLI logic and ensure tests are deterministic.
  - User input is simulated using mock scanner objects.

### Example Test Cases

- Running the app and immediately exiting.
- Running a search and verifying all private methods are called.
- Handling unknown or invalid commands and search commands.
- Forcing exceptions in user input to test error handling.
- Ensuring only the mock CSV loader is used in tests.

## How to Run the Tests

1. **Install dependencies:**
   ```sh
   npm install
   ```

2. **Run all tests with coverage:**
   ```sh
   npm test
   ```

3. **Run only the Media Collection App tests with coverage:**
   ```sh
   npm run test:media-collection
   ```

   This will only run `src/media_collection_app.test.ts` and show coverage for the CLI app.

## Code Coverage

- **Target Coverage:**  
  - The project aims for at least **70% branch coverage** (as required by the stretch assignment).
  - The CLI app (`media_collection_app.ts`) typically achieves **85%+** coverage for statements, branches, and functions with the provided tests.
- **How to View Coverage Report:**  
  - After running the tests, open the HTML report in `coverage/lcov-report/index.html` to see detailed coverage metrics.
  - Only the CLI app and its tests are included in the `test:media-collection` script coverage.

## Customizing Coverage

If you want to limit coverage to only the CLI app, the following script is used in `package.json`:

```json
"test:media-collection": "jest --coverage --collectCoverageFrom=src/cli/media_collection_app.ts src/media_collection_app.test.ts"
```

## Notes

- All loaders in tests are mocked; no real CSV or other file I/O is performed.
- To test additional loaders, add them to the `loaders` array in your test setup.
- The test suite is designed to be run in a Node.js/TypeScript environment with Jest.

## Troubleshooting

- If you see coverage for files you are not testing, ensure your `collectCoverageFrom` setting is correct.
- If TypeScript complains about type errors in tests that intentionally break types (to test error handling), use `// @ts-expect-error` above the relevant line.

