import {
  AnswerChoice,
  MultipleChoiceQuizQuestion,
  QuizQuestion,
  QuizQuestionProvider,
} from 'codedifferently-instructional';

export class TobaisEvansQuiz implements QuizQuestionProvider {
  getProviderName(): string {
    return 'tobaisevans';
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
      'Which programming language is known for running in the browser?',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, 'Python'],
        [AnswerChoice.B, 'JavaScript'],
        [AnswerChoice.C, 'C++'],
        [AnswerChoice.D, 'Java'],
      ]),
      AnswerChoice.UNANSWERED, // Replace with correct answer later
    );
  }

  private static makeQuestion1(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      1,
      'In Git, what does "commit" mean?',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, 'Delete changes permanently'],
        [AnswerChoice.B, 'Save a snapshot of your project history'],
        [AnswerChoice.C, 'Create a new branch'],
        [AnswerChoice.D, 'Send changes to the internet'],
      ]),
      AnswerChoice.UNANSWERED, // Replace with correct answer later
    );
  }

  private static makeQuestion2(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      2,
      'Which HTML tag is used for the largest heading?',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, '<h6>'],
        [AnswerChoice.B, '<head>'],
        [AnswerChoice.C, '<h1>'],
        [AnswerChoice.D, '<title>'],
      ]),
      AnswerChoice.UNANSWERED, // Replace with correct answer later
    );
  }
}
