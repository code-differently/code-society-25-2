import {
  AnswerChoice,
  MultipleChoiceQuizQuestion,
  QuizQuestion,
  QuizQuestionProvider,
} from 'codedifferently-instructional';

export class EvanderBlueQuiz implements QuizQuestionProvider {
  getProviderName(): string {
    return 'evanderblue';
  }

  makeQuizQuestions(): QuizQuestion[] {
    return [
      EvanderBlueQuiz.makeQuestion0(),
      EvanderBlueQuiz.makeQuestion1(),
      EvanderBlueQuiz.makeQuestion2(),
    ];
  }

  private static makeQuestion0(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      0,
      'What is does the command git push do?',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, 'Uploads your local commits to a remote repository'],
        [
          AnswerChoice.B,
          'Downloads commits from a remote repository to your local machine',
        ],
        [AnswerChoice.C, 'Creates a new branch in your local repository'],
        [
          AnswerChoice.D,
          'Saves your changes to the staging area without committing',
        ],
      ]),
      AnswerChoice.UNANSWERED,
    ); // Replace `UNANSWERED` with the correct answer.
  }

  private static makeQuestion1(): QuizQuestion {
    return new QuizQuestion(
      1,
      'What does RAM stand for?',
      'Random Access Memory',
    ); // Provide an answer.
  }

  private static makeQuestion2(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      2,
      'What does a CPU do?',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, 'Stores long-term data like documents and photos'],
        [AnswerChoice.B, 'Controls the display output to the monitor'],
        [
          AnswerChoice.C,
          'Executes instructions and performs calculations for the computer',
        ],
        [AnswerChoice.D, "Provides power to the computer's components"],
      ]),
      AnswerChoice.UNANSWERED,
    ); // Replace `UNANSWERED` with the correct answer.
  }
}
