import {
  AnswerChoice,
  MultipleChoiceQuizQuestion,
  QuizQuestion,
  QuizQuestionProvider,
} from 'codedifferently-instructional';

export class BenjaminScottQuiz implements QuizQuestionProvider {
  getProviderName(): string {
    return 'benjaminscott';
  }

  makeQuizQuestions(): QuizQuestion[] {
    return [
      BenjaminScottQuiz.makeQuestion0(),
      BenjaminScottQuiz.makeQuestion1(),
      BenjaminScottQuiz.makeQuestion2(),
    ];
  }

  private static makeQuestion0(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      0,
      'What is the command for making a new file?',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, 'feel'],
        [AnswerChoice.B, 'grab'],
        [AnswerChoice.C, 'touch'],
        [AnswerChoice.D, 'poke'],
      ]),
      AnswerChoice.UNANSWERED,
    ); // Replace `UNANSWERED` with the correct answer.
  }

  private static makeQuestion1(): QuizQuestion {
    return new QuizQuestion(
      1,
      'What does "CPU" stand for?',
      'Central Processing Unit',
    ); // Provide an answer.
  }

  private static makeQuestion2(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      2,
      'What special operator chains commands together?',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, '&&'],
        [AnswerChoice.B, '--'],
        [AnswerChoice.C, '%$#'],
        [AnswerChoice.D, '**'],
      ]),
      AnswerChoice.UNANSWERED,
    ); // Replace `UNANSWERED` with the correct answer.
  }
}
