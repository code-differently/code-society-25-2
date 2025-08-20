import {
  AnswerChoice,
  MultipleChoiceQuizQuestion,
  QuizQuestion,
  QuizQuestionProvider,
} from 'codedifferently-instructional';

export class DeanWalstonQuiz implements QuizQuestionProvider {
  getProviderName(): string {
    return 'dean_walston_25_2';
  }

  makeQuizQuestions(): QuizQuestion[] {
    return [
      DeanWalstonQuiz.makeQuestion0(),
      DeanWalstonQuiz.makeQuestion1(),
      DeanWalstonQuiz.makeQuestion2(),
      DeanWalstonQuiz.makeQuestion3(),
    ];
  }

  private static makeQuestion0(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      0,
      'What is a programming language?',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, 'A way to communicate with computers'],
        [AnswerChoice.B, 'A formal language for writing instructions'],
        [AnswerChoice.C, 'A set of rules for creating software'],
        [AnswerChoice.D, 'All of the above'],
      ]),
      AnswerChoice.UNANSWERED, // Replace `UNANSWERED` with the correct answer.
    );
  }

  private static makeQuestion1(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      1,
      'What is source code?',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, 'The final program that users can run'],
        [
          AnswerChoice.B,
          'The human-readable instructions written in a programming language',
        ],
        [AnswerChoice.C, 'The binary code that computers execute'],
        [AnswerChoice.D, 'The documentation for a program'],
      ]),
      AnswerChoice.UNANSWERED, // Replace `UNANSWERED` with the correct answer.
    );
  }

  private static makeQuestion2(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      2,
      'Which Terminal command is used to display the current working directory path?',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, 'cd'],
        [AnswerChoice.B, 'ls -l'],
        [AnswerChoice.C, 'pwd'],
        [AnswerChoice.D, 'echo $PATH'],
      ]),
      AnswerChoice.UNANSWERED, // Replace `UNANSWERED` with the correct answer.
    );
  }

  private static makeQuestion3(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      3,
      'Which Git command is used to create a new branch and switch to it?',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, 'git branch'],
        [AnswerChoice.B, 'git checkout -b'],
        [AnswerChoice.C, 'git switch'],
        [AnswerChoice.D, 'git new'],
      ]),
      AnswerChoice.UNANSWERED, // Replace `UNANSWERED` with the correct answer.
    );
  }
}
