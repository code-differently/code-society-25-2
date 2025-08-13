import {
  AnswerChoice,
  MultipleChoiceQuizQuestion,
  QuizQuestion,
  QuizQuestionProvider,
} from 'codedifferently-instructional';

export class BrooklynHardenQuiz implements QuizQuestionProvider {
  getProviderName(): string {
    return 'brooklynharden';
  }

  makeQuizQuestions(): QuizQuestion[] {
    return [
      BrooklynHardenQuiz.makeQuestion0(),
      BrooklynHardenQuiz.makeQuestion1(),
      BrooklynHardenQuiz.makeQuestion2(),
    ];
  }

  private static makeQuestion0(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      0,
      "What does 'git checkout -b (your-branch-name)' do?",
      new Map<AnswerChoice, string>([
        [
          AnswerChoice.A,
          'Deletes the current branch and switches to a new one',
        ],
        [AnswerChoice.B, 'Creates a new branch and merges it immediately'],
        [
          AnswerChoice.C,
          'Creates a new branch, while also switching to that current branch just made',
        ],
        [
          AnswerChoice.D,
          'Switches to an existing branch without creating a new one',
        ],
      ]),
      AnswerChoice.UNANSWERED,
    ); // Replace `UNANSWERED` with the correct answer.
  }

  private static makeQuestion1(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      1,
      'Why Do We Branch?',
      new Map<AnswerChoice, string>([
        [
          AnswerChoice.A,
          'To delete unused features, to simplify project structure, and to consolidate all tasks',
        ],
        [
          AnswerChoice.B,
          'To merge all features into one branch, to finalize changes permanently, and to streamline the main project',
        ],
        [
          AnswerChoice.C,
          'To implement major updates directly, to skip testing, and to ignore project stability',
        ],
        [
          AnswerChoice.D,
          'To work on features or fix without affecting others, to test or deploy changes safely, and to experiment without breaking the main project',
        ],
      ]),
      AnswerChoice.UNANSWERED,
    ); // Replace `UNANSWERED` with the correct answer.
  }

  private static makeQuestion2(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      2,
      'A version control system(VCS) that helps keep track of changes made to files, directories, and folders. It helps you save versions, create branches to try new ideas, and its fast and great for teams',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, 'Git'],
        [AnswerChoice.B, 'VS Code'],
        [AnswerChoice.C, 'GitHub'],
        [AnswerChoice.D, 'XCODE'],
      ]),
      AnswerChoice.UNANSWERED,
    ); // Replace `UNANSWERED` with the correct answer.
  }
}
