import { Module } from '@nestjs/common';
import { AnthonyMaysLoader } from './anthony_mays_loader.js';
import { TobyEvansLoader } from './toby_evans_loader.js';

export const Loaders = Symbol.for('Loaders');

const LOADER_PROVIDERS = [AnthonyMaysLoader, TobyEvansLoader];

@Module({
  providers: [
    ...LOADER_PROVIDERS,
    {
      provide: Loaders,
      useFactory: (...args: any[]) => [...args],
      inject: LOADER_PROVIDERS,
    },
  ],
  exports: [Loaders],
})
export class LoadersModule {}
