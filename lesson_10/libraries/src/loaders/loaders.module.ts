import { Module } from '@nestjs/common';
import { AnthonyMaysLoader } from './anthony_mays_loader.js';
import { BenjaminScottLoader } from './benjamin_scott_loader.js';
import { BrooklynHardenLoader } from './brooklyn_harden_loader.js';
import { DanielBoyceLoader } from './daniel_boyce_loader.js';
import { DeanWalstonLoader } from './dean_walston_loader.js';
import { KerryFergusonLoader } from './kerry_ferguson_loader.js';
import { JaizelcespedesLoader } from './jaizelcespedes_loader.js';
import { JaredEdgeLoader } from './jared_edge_loader.js';
import { JoneemckellarLoader } from './joneemckellar_loader.js';
import { LindaQuinoaLoader } from './linda_quinoa_loader.js';
import { MattieWeathersbyLoader } from './mattie_weathersby_loader.js';
import { NicoleJacksonLoader } from './nicole_jackson_loader.js';
import { TrinitieJacksonLoader } from './trinitie_jackson_loader.js';
import { TyranRiceLoader } from './tyranricejr_loader.js';

export const Loaders = Symbol.for('Loaders');

const LOADER_PROVIDERS = [
  AnthonyMaysLoader,
  BrooklynHardenLoader,
  BenjaminScottLoader,
  DanielBoyceLoader,
  DeanWalstonLoader,
  JaizelcespedesLoader,
  JaredEdgeLoader,
  JoneemckellarLoader,
  KerryFergusonLoader,
  LindaQuinoaLoader,  
  MattieWeathersbyLoader,
  NicoleJacksonLoader,
  TrinitieJacksonLoader,
  TyranRiceLoader,
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
