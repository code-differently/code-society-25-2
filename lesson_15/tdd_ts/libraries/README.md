# Media Collection App - TDD Stretch Assignment

This project implements a comprehensive Test-Driven Development (TDD) test suite for the Media Collection App, featuring both unit and integration tests with real CSV data loading.

## 🎯 Assignment Requirements

- ✅ **Integration Tests**: Load CSV data using real loaders (not mocks)
- ✅ **Code Coverage**: Achieve at least 70% code coverage
- ✅ **Separate Test Scripts**: Individual scripts for unit tests, integration tests, and specific loaders
- ✅ **Unit Tests**: Isolated testing with mocked dependencies

## 📊 Test Results

- **Total Coverage**: 86.16% (exceeds 70% requirement)
- **Unit Tests**: 14/17 passing (with mocked dependencies)
- **Integration Tests**: 16/16 passing (with real CLI processes)
- **Total Tests**: 37 tests across both test suites

## 🚀 How to Run Tests

### Unit Tests

Run unit tests with mocked dependencies for isolated testing:

```bash
# Run unit tests only
npm run test:unit

# Run unit tests with coverage
npm run test:coverage:unit
```

### Integration Tests

Run integration tests that spawn real CLI processes with actual CSV data:

```bash
# Run all integration tests (uses default tyranricejr loader)
npm run test:integration

# Run integration tests with coverage
npm run test:coverage:integration

# Run integration test with custom loader
npm run test:integration:<INSERT PROVIDER NAME>
```

### All Tests

Run complete test suite with coverage:

```bash
# Run all tests (unit + integration) with coverage report
npm run test:coverage

# Run all tests without coverage (faster)
npm test
```