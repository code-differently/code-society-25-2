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
  preset: 'ts-jest',
  collectCoverage: true, // Enable code coverage collection
  coverageDirectory: 'coverage', // Directory to output coverage reports
  collectCoverageFrom: [
    // Specify files to include in coverage analysis
    'src/**/*.ts',
    // Exclude declaration files
  ],
  // Optional: Configure thresholds for coverage
  coverageThreshold: {
    global: {
      branches: 70,
      functions: 70,
      lines: 70,
      statements: 70,
    },
  },
};
