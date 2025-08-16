import { Module } from '@nestjs/common';
import { DanielsonAdjocyQuiz } from './danielson_adjocys_quiz.js';
import { AnotherQuiz } from './another_quiz.js';
import { AnthonyMaysQuiz } from './anthony_mays_quiz.js';
import { DanielBoyceQuiz } from './daniel_boyce_quiz.js';
import { NicoleJacksonQuiz } from './nicole_jackson_quiz.js';
import { MattieWeathersbyQuiz } from './mweathersby_quiz.js';
import { KerryFergusonQuiz } from './kerry_ferguson_quiz.js';
import { BenjaminScottQuiz } from './benjamin_scott_quiz.js';
import { BrooklynHardenQuiz } from './brooklyn_harden_quiz.js';
import { TaliaCrockettQuiz } from './talia_crockett_quiz.js';
import { TrinitieJacksonQuiz } from './trinitie_jackson_quiz.js';
import { TyranRicesQuiz } from './tyran_rices_quiz.js';
import { DevynBensonQuiz } from './devyn_benson_quiz.js';
import { MarthaOQuiz } from './martha_o_quiz.js';
import { LindaQuinoaQuiz } from './linda_quinoa_quiz.js';
import { DeanWalstonQuiz } from './dean_walston_quiz.js';
import { EvanderBlueQuiz } from './evander_blue_quiz.js';

export const Quizzes = Symbol.for('Quizzes');

// Add your quiz provider here.
const QUIZ_PROVIDERS = [
  AnthonyMaysQuiz,
  NicoleJacksonQuiz,
  TrinitieJacksonQuiz,
  BrooklynHardenQuiz,
  DevynBensonQuiz,
  TyranRicesQuiz,
  AnotherQuiz,
  MattieWeathersbyQuiz,
  BenjaminScottQuiz,
  DanielsonAdjocyQuiz,
  DanielBoyceQuiz,
  LindaQuinoaQuiz,
  TaliaCrockettQuiz,
  DeanWalstonQuiz,
  KerryFergusonQuiz,
  MarthaOQuiz,
  EvanderBlueQuiz
];

@Module({
  providers: [
    ...QUIZ_PROVIDERS,
    {
      provide: Quizzes,
      useFactory: (...args) => [...args],
      inject: QUIZ_PROVIDERS,
    },
  ],
})
export class QuizzesModule {}
