# Integration Tests for MediaCollectionApp

This directory contains comprehensive integration tests for the MediaCollectionApp that simulate user interactions through the CLI interface.

## Test Structure

### `media_collection_app.integration.test.ts`

This file contains complete integration tests that cover all user flows:

#### Test Categories:

1. **Load Data and Exit**: Tests the basic functionality of loading data with a specific loader and exiting
2. **Search by Title**: Tests the complete flow of searching for media items by title
3. **Search by Release Year**: Tests searching for media items by release year
4. **Search by Cast Name**: Tests searching for media items by cast member names
5. **Invalid Input Handling**: Tests how the application handles invalid user inputs gracefully
6. **No Loader Specified**: Tests running the application without specifying a data loader
7. **Multiple Search Operations**: Tests performing multiple searches in a single session
8. **Application Lifecycle**: Tests the complete application flow from start to finish

## Key Testing Features

### User Input Simulation
- Uses a `MockInputHelper` class to simulate user keyboard input
- Mocks the `Scanner` class to provide predefined user responses
- Tests various input sequences including valid and invalid inputs

### Console Output Verification
- Captures all console output during test execution
- Verifies that expected menus, prompts, and results are displayed
- Tests that error handling works correctly

### Real Data Integration
- Uses actual CSV data files in the `data/` directory
- Tests with the `anthonymays` loader which loads real credit and media item data
- Verifies that data loading and searching work with actual data

### Error Handling
- Tests invalid menu choices (both main menu and search menu)
- Tests recovery from invalid input
- Ensures the application doesn't crash on unexpected input

## Coverage

The integration tests achieve approximately **30% code coverage**, focusing on:
- CLI interface interactions
- User input processing
- Data loading workflows
- Search functionality
- Error handling paths

## Running the Tests

### Integration Tests Only
```bash
npm run test:integration
```

### All Tests (Unit + Integration)
```bash
npm test  # Runs unit tests only (excludes integration)
npm run test:integration  # Runs integration tests only
```

### Test Configuration

The integration tests are configured to:
- Run separately from unit tests using Jest's `testPathPattern`
- Use real NestJS dependency injection
- Mock console output for verification
- Handle Commander.js command-line parsing conflicts
- Test with actual CSV data loaders

## Test Methodology

These integration tests follow the approach described in the lesson README:
- **Test-Driven Development**: Tests define expected behavior before implementation
- **Real Data Usage**: Tests use actual CSV files rather than mocked data
- **Complete User Flows**: Each test simulates a complete user interaction session
- **Error Scenarios**: Tests include both success and error scenarios
- **Separation of Concerns**: Integration tests run separately from unit tests

## Mocking Strategy

The tests use strategic mocking to isolate the integration concerns:
- **Scanner Input**: Mocked to provide predictable user input sequences
- **Console Output**: Captured to verify application responses
- **Command Line Parsing**: Mocked to avoid Commander.js state conflicts
- **Data Loaders**: Use real loaders but with controlled loader selection

This approach ensures the tests verify the actual user experience while maintaining test reliability and speed.
