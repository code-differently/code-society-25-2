import {
  AnswerChoice,
  MultipleChoiceQuizQuestion,
  QuizQuestion,
  QuizQuestionProvider,
} from 'codedifferently-instructional';

export class JoneemckellarQuiz implements QuizQuestionProvider {
  getProviderName(): string {
    return 'joneemckellar_quiz_provider';
  }
  makeQuizQuestions(): QuizQuestion[] {
    return [
      JoneemckellarQuiz.makeQuestion0(),
      JoneemckellarQuiz.makeQuestion1(),
      JoneemckellarQuiz.makeQuestion2(),
    ];
  }