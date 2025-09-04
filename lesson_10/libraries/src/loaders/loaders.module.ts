import { Module } from '@nestjs/common';
import { AnthonyMaysLoader } from './anthony_mays_loader.js';
import { BenjaminScottLoader } from './benjamin_scott_loader.js';
import { DanielBoyceLoader } from './daniel_boyce_loader.js';
import { DeanWalstonLoader } from './dean_walston_loader.js';

export const Loaders = Symbol.for('Loaders');

const LOADER_PROVIDERS = [
  AnthonyMaysLoader,
  BenjaminScottLoader,
  DanielBoyceLoader,
  DeanWalstonLoader,
];

@Module({
  providers: [
    ...LOADER_PROVIDERS,
    {
      provide: Loaders,
      useFactory: (...args) => [...args],
      inject: LOADER_PROVIDERS,
    },
  ],
  exports: [Loaders],
})
export class LoadersModule {}
