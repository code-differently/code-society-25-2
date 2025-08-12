import { Module } from '@nestjs/common';
import { AnotherQuiz } from './another_quiz.js';
import { TrinitieJacksonQuiz } from './trinitie_jackson_quiz.js';
export const Quizzes = Symbol.for('Quizzes');

// Add your quiz provider here.

const QUIZ_PROVIDERS = [
  TrinitieJacksonQuiz,
  AnotherQuiz,

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
