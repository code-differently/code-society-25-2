const { createDefaultPreset } = require("ts-jest");

const tsJestTransformCfg = createDefaultPreset().transform;

/** @type {import('ts-jest').JestConfigWithTsJest} **/
module.exports = {
  testEnvironment: "node",
  transform: {
    ...tsJestTransformCfg,
  },
   preset: 'ts-jest',
  testEnvironment: 'node',
  testMatch: ['**/src/**/*_test.ts'],
  transform: {
    '^.+\\.tsx?$': 'ts-jest'
  } 
};