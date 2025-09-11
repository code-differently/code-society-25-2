// Jest setup file to configure global mocks and utilities
import { afterEach, beforeEach, expect, jest } from '@jest/globals';

// Setup global Jest utilities
if (typeof globalThis !== 'undefined') {
  globalThis.jest = jest;
}

// Extend Jest matchers if needed
expect.extend({
  // Add any custom matchers here
});

// Setup console mock to avoid cluttering output during tests
beforeEach(() => {
  jest.spyOn(console, 'log').mockImplementation(() => {
    // Silenced for testing
  });
});

afterEach(() => {
  jest.restoreAllMocks();
});
