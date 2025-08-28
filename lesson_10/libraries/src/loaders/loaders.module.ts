import { Module } from '@nestjs/common';
import { AnthonyMaysLoader } from './anthony_mays_loader.js';
import { JaydenEllisLoader } from './jayden_ellis_loader.js';
   
export const Loaders = Symbol.for('Loaders');

const LOADER_PROVIDERS = [
  AnthonyMaysLoader,
  JaydenEllisLoader
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
