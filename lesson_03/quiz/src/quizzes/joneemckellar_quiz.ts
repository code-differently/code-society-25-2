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
    ];
  }

  private static makeQuestion0(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      0,
      'Which conventional commit type should you use to make code cleaner and easier to read?',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, 'fix commit'],
        [AnswerChoice.B, 'refactor commit'],
        [AnswerChoice.C, 'docs commit'],
        [AnswerChoice.D, 'feat commit'],
      ]),
      AnswerChoice.UNANSWERED,
    ); // Replace `UNANSWERED` with the correct answer.
  }

  private static makeQuestion1(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      1,
      'How do you switch between branches in Git using the command line?',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, 'git checkout <branch-name>'],
        [AnswerChoice.B, 'git checkout -b <branch-name>'],
        [AnswerChoice.C, 'git switch -b <branch-name>'],
        [AnswerChoice.D, 'git push origin <branch-name>'],
      ]),
      AnswerChoice.UNANSWERED,
    ); // Replace `UNANSWERED` with the correct answer.
  }

  private static makeQuestion2(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      2,
      'In the computer-body analogy, what part of a computer corresponds to the central nervous system?',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, 'the hard drive'],
        [AnswerChoice.B, 'the central processing unit (CPU)'],
        [AnswerChoice.C, 'the power supply'],
        [AnswerChoice.D, 'the motherboard'],
      ]),
      AnswerChoice.UNANSWERED,
    ); // Replace `UNANSWERED` with the correct answer.
  }
}