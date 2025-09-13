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
  collectCoverage: true,
  coverageDirectory: 'coverage',
  coverageThreshold: {
    global: {
      branches: 70,
      functions: 70,
      lines: 70,
      statements: 70,
    },
  },

  testMatch: ['**/?(*.)+(spec|test).[jt]s?(x)', '!**/integration/**'],
};
