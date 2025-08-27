import {
  AnswerChoice,
  MultipleChoiceQuizQuestion,
  QuizQuestion,
  QuizQuestionProvider,
} from 'codedifferently-instructional';

export class JaizelQuiz implements QuizQuestionProvider {
  getProviderName(): string {
    return 'jaizel_quiz';
  }

  makeQuizQuestions(): QuizQuestion[] {
    return [
      JaizelQuiz.makeQuestion0(),
      JaizelQuiz.makeQuestion1(),
      JaizelQuiz.makeQuestion2(),
    ];
  }

  private static makeQuestion0(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      0,
      'What is TypeScript?',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, 'A superset of JavaScript that adds static typing'],
        [AnswerChoice.B, 'A completely different language from JavaScript'],
        [AnswerChoice.C, 'A JavaScript runtime environment'],
        [AnswerChoice.D, 'A JavaScript framework like React or Angular'],
      ]),
      AnswerChoice.UNANSWERED,
    );
  }

  private static makeQuestion1(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      1,
      'Which of the following is NOT a valid way to declare a variable in JavaScript?',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, 'var x = 10;'],
        [AnswerChoice.B, 'let x = 10;'],
        [AnswerChoice.C, 'const x = 10;'],
        [AnswerChoice.D, 'static x = 10;'],
      ]),
      AnswerChoice.UNANSWERED,
    );
  }

  private static makeQuestion2(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      2,
      'What is an Interface in TypeScript?',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, 'A way to define a contract for object structure'],
        [AnswerChoice.B, 'A tool for bundling TypeScript code'],
        [AnswerChoice.C, 'A special type of JavaScript class'],
        [AnswerChoice.D, 'A method for importing external modules'],
      ]),
      AnswerChoice.UNANSWERED,
    );
  }
}
