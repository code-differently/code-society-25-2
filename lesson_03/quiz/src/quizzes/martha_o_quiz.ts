import {
  AnswerChoice,
  MultipleChoiceQuizQuestion,
  QuizQuestion,
  QuizQuestionProvider,
} from 'codedifferently-instructional';

export class MarthaOQuiz implements QuizQuestionProvider {
  getProviderName(): string {
    return 'marthao';
  }

  makeQuizQuestions(): QuizQuestion[] {
    return [
      MarthaOQuiz.makeQuestion0(),
      MarthaOQuiz.makeQuestion1(),
      MarthaOQuiz.makeQuestion2(),
    ];
  }

  private static makeQuestion0(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      0,
      'Which of these commands brings in changes from a remote repo AND merges them into the current branch?',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, 'git add'],
        [AnswerChoice.B, 'git fetch'],
        [AnswerChoice.C, 'git pull'],
        [AnswerChoice.D, 'git merge'],
      ]),
      AnswerChoice.UNANSWERED,
    ); // Replace `UNANSWERED` with the correct answer.
  }

  private static makeQuestion1(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      1,
      'Who is considered the first computer programmer?',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, 'Mike Tyson'],
        [AnswerChoice.B, 'Ada Lovelace'],
        [AnswerChoice.C, 'Steve Jobs'],
        [AnswerChoice.D, 'Mickey Mouse'],
      ]),
      AnswerChoice.UNANSWERED,
    ); // Replace `UNANSWERED` with the correct answer.
  }

  private static makeQuestion2(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      2,
      'Which of the following is NOT something GPUs are typically used for?',
      new Map<AnswerChoice, string>([
        [
          AnswerChoice.A,
          'Converting AC from a wall outlet into DC for computer components.',
        ],
        [AnswerChoice.B, 'Rendering high-resolution graphics for video games.'],
        [
          AnswerChoice.C,
          'Handling complex algorithms for machine learning and training AI.',
        ],
        [
          AnswerChoice.D,
          'Solving complex math problems for cryptocurrency mining.',
        ],
      ]),
      AnswerChoice.UNANSWERED,
    ); // Replace `UNANSWERED` with the correct answer.
  }
}
