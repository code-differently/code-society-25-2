import { Module } from '@nestjs/common';
import { DanielsonAdjocyQuiz } from './danielson_adjocys_quiz.js';
import { AnotherQuiz } from './another_quiz.js';
import { AnthonyMaysQuiz } from './anthony_mays_quiz.js';
import { BenjaminScottQuiz } from './benjamin_scott_quiz.js';
import { TrinitieJacksonQuiz } from './trinitie_jackson_quiz.js';
import { TyranRicesQuiz } from './tyran_rices_quiz.js';
import { BrooklynHardenQuiz } from './brooklyn_harden_quiz.js';
export const Quizzes = Symbol.for('Quizzes');

// Add your quiz provider here.




const QUIZ_PROVIDERS = [
  AnthonyMaysQuiz, 
  TrinitieJacksonQuiz, 
  BrooklynHardenQuiz,
  TyranRicesQuiz,
  AnotherQuiz,
  BenjaminScottQuiz,
  DanielsonAdjocyQuiz
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
