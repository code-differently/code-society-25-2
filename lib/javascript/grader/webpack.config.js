const path = require('path');
const GasPlugin = require('gas-webpack-plugin');
const CopyPlugin = require('copy-webpack-plugin');

module.exports = {
  mode: 'production',
  entry: './src/main.ts',
  output: {
    filename: 'main.js',
    path: path.resolve(__dirname, 'dist'),
    clean: true,
    library: {
      type: 'var',
      name: 'exports'
    },
    globalObject: 'this'
  },
  resolve: {
    extensions: ['.ts', '.js'],
  },
  module: {
    rules: [
      {
        test: /\.ts$/,
        use: 'ts-loader',
        exclude: /node_modules/,
      },
    ],
  },
  plugins: [
    new GasPlugin({
      comment: false,
    }),
    new CopyPlugin({
      patterns: [
        {
          from: 'src/appsscript.json',
          to: 'appsscript.json',
        },
      ],
    }),
  ],
  externals: {
    // Google Apps Script globals
    'SpreadsheetApp': 'SpreadsheetApp',
    'UrlFetchApp': 'UrlFetchApp',
    'PropertiesService': 'PropertiesService',
    'Utilities': 'Utilities',
    'Logger': 'Logger',
    'CacheService': 'CacheService',
    'DriveApp': 'DriveApp',
    'Session': 'Session',
  },
  target: 'web',
  optimization: {
    minimize: false, // Keep readable for debugging
  },
};