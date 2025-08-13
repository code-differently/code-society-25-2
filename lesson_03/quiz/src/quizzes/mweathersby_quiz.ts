import {
  AnswerChoice,
  MultipleChoiceQuizQuestion,
  QuizQuestion,
  QuizQuestionProvider,
} from 'codedifferently-instructional';

export class MattieWeathersbyQuiz implements QuizQuestionProvider {
  getProviderName(): string {
    return 'mattieweathersby';
  }

  makeQuizQuestions(): QuizQuestion[] {
    return [
      MattieWeathersbyQuiz.makeQuestion0(),
      MattieWeathersbyQuiz.makeQuestion1(),
      MattieWeathersbyQuiz.makeQuestion2(),
    ];
  }

  private static makeQuestion0(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      0,
      'What command is used for doing a commit?',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, 'git push'],
        [AnswerChoice.B, 'git merge'],
        [AnswerChoice.C, 'git clone'],
        [AnswerChoice.D, 'git commit -m'],
      ]),
      AnswerChoice.UNANSWERED,
    ); // Replace `UNANSWERED` with the correct answer.
  }

  private static makeQuestion1(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      1,
      'What is an IDE?',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, 'editing and refactoring code'],
        [AnswerChoice.B, 'debugging'],
        [AnswerChoice.C, 'managing source control'],
        [AnswerChoice.D, 'all of the above'],
      ]),
      AnswerChoice.UNANSWERED,
    ); // Replace `UNANSWERED` with the correct answer.
  }

  private static makeQuestion2(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      2,
      'What is the main purpose of a fork?',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, 'to make music'],
        [AnswerChoice.B, 'help other developers'],
        [AnswerChoice.C, 'make a copy of someone else project'],
        [AnswerChoice.D, 'to apply for jobs'],
      ]),
      AnswerChoice.UNANSWERED,
    ); // Replace `UNANSWERED` with the correct answer.
  }
}
