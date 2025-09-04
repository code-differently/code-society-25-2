import { Module } from '@nestjs/common';
import { AnthonyMaysLoader } from './anthony_mays_loader.js';
import { BenjaminScottLoader } from './benjamin_scott_loader.js';
import { DanielBoyceLoader } from './daniel_boyce_loader.js';
import { DeanWalstonLoader } from './dean_walston_loader.js';
import { JaizelcespedesLoader } from './jaizelcespedes_loader.js';
import { JaredEdgeLoader } from './jared_edge_loader.js';
import { JoneemckellarLoader } from './joneemckellar_loader.js';

export const Loaders = Symbol.for('Loaders');

const LOADER_PROVIDERS = [
  AnthonyMaysLoader,
  BenjaminScottLoader,
  DanielBoyceLoader,
  DeanWalstonLoader,
  JaizelcespedesLoader,
  JaredEdgeLoader,
  JoneemckellarLoader,
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
