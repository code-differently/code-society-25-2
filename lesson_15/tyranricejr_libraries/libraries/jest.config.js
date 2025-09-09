/** @type {import('ts-jest').JestConfigWithTsJest} **/
export default {
  testEnvironment: 'node',
  transform: {
    '^.+.tsx?$': ['ts-jest', { useESM: true }],
  },
  moduleNameMapper: {
    '^(\\.\\.?\\/.+)\\.js$': '$1',
  },
  extensionsToTreatAsEsm: ['.ts'],
  preset: 'ts-jest', // Use ts-jest for TypeScript support
  collectCoverage: true, // Enable coverage collection
  collectCoverageFrom: [
    // Specify files to include in coverage
    'src/**/*.ts',
  ],
  coverageReporters: ['json', 'lcov', 'text', 'clover'], // Output formats
  coverageDirectory: 'coverage', // Directory for coverage reports
  testPathIgnorePatterns: ['/node_modules/', '/build/'],

  coverageThreshold: {
    global: {
      branches: 70,
      functions: 70,
      lines: 70,
      statements: 70,
    },
  },
};
