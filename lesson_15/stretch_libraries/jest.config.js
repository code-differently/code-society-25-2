/** @type {import('jest').Config} */
module.exports = {
  preset: 'ts-jest',
  testEnvironment: 'node',
  roots: ['<rootDir>/src'],
  testMatch: ['**/__tests__/**/*.ts', '**/?(*.)+(spec|test).ts'],
  moduleNameMapper: {
    '^@/(.*)$': '<rootDir>/libraries/src/$1',
  },
  collectCoverageFrom: [
    'src/**/*.{js,ts}',
    'libraries/src/**/*.{js,ts}',
    '!src/**/*.d.ts',
    '!libraries/src/**/*.d.ts',
    '!src/**/index.ts',
    '!libraries/src/**/index.ts'
  ],
  coverageDirectory: 'coverage',
  coverageReporters: ['text', 'lcov', 'html'],
  coverageThreshold: {
    global: {
      branches: 70,
      functions: 70,
      lines: 70,
      statements: 70
    }
  }
};
