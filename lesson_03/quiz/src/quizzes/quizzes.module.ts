import { Module } from '@nestjs/common';
import { AnotherQuiz } from './another_quiz.js';
import { AnthonyMaysQuiz } from './anthony_mays_quiz.js';
import { TyranRicesQuiz } from './tyran_rices_quiz.js';
import { BrooklynHardenQuiz } from './brooklyn_harden_quiz.js';
export const Quizzes = Symbol.for('Quizzes');

// Add your quiz provider here.

const QUIZ_PROVIDERS = [AnthonyMaysQuiz, AnotherQuiz, TyranRicesQuiz, BrooklynHardenQuiz];

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
