import {
  AnswerChoice,
  MultipleChoiceQuizQuestion,
  QuizQuestion,
  QuizQuestionProvider,
} from 'codedifferently-instructional';

export class DanielsonAdjocyQuiz implements QuizQuestionProvider {
  getProviderName(): string {
    return 'danielsonadjocy';
  }

  makeQuizQuestions(): QuizQuestion[] {
    return [
      DanielsonAdjocyQuiz.makeQuestion0(),
      DanielsonAdjocyQuiz.makeQuestion1(),
      DanielsonAdjocyQuiz.makeQuestion2(),
    ];
  }

  private static makeQuestion0(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      0,
      'What should go first in a pc frame?',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, 'CPU'],
        [AnswerChoice.B, 'GPU'],
        [
          AnswerChoice.C,
          'Motherboard',
        ],
        [AnswerChoice.D, 'Power supply'],
      ]),
      AnswerChoice.UNANSWERED,
    ); // Replace `UNANSWERED` with the correct answer.
  }

  private static makeQuestion1(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      1,
      'True or False: Data in a computer is represented by Binary, Decimal, and Hexadecimal',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, 'True'],
        [AnswerChoice.B, 'False'],
      ]),
      AnswerChoice.UNANSWERED,
    ); // Replace `UNANSWERED` with the correct answer.
  }

  private static makeQuestion2(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      2,
      'What is the brain of the computer?',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, 'CPU'],
        [AnswerChoice.B, 'GPU'],
        [AnswerChoice.C, 'Motherboard'],
        [AnswerChoice.D, 'Power supply'],
      ]),
      AnswerChoice.UNANSWERED,
    ); // Replace `UNANSWERED` with the correct answer.
  }
}
