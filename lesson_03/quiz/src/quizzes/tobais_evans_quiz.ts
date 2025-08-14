import {
  AnswerChoice,
  MultipleChoiceQuizQuestion,
  QuizQuestion,
  QuizQuestionProvider,
} from 'codedifferently-instructional';

export class TobaisEvansQuiz implements QuizQuestionProvider {
  getProviderName(): string {
    return 'tobaisevans-quiz';
  }

  makeQuizQuestions(): QuizQuestion[] {
    return [
      TobaisEvansQuiz.makeQuestion0(),
      TobaisEvansQuiz.makeQuestion1(),
      TobaisEvansQuiz.makeQuestion2(),
    ];
  }

  private static makeQuestion0(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      0,
      'Which component executes program instructions?',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, 'Hard drive'],
        [AnswerChoice.B, 'CPU'],
        [AnswerChoice.C, 'GPU'],
        [AnswerChoice.D, 'Network card'],
      ]),
      AnswerChoice.UNANSWERED,
    ); // Replace `UNANSWERED` with the correct answer.
  }

  private static makeQuestion1(): QuizQuestion {
    return new QuizQuestion(
      1,
      'What does RAM provide for a running program?',
      '', // leave unanswered
    );
  }

  private static makeQuestion2(): QuizQuestion {
    return new QuizQuestion(
      2,
      'Name one core responsibility of an operating system.',
      '', // leave unanswered
    );
  }
}
export { TobaisEvansQuiz };
