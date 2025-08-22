import {
  AnswerChoice,
  MultipleChoiceQuizQuestion,
  QuizQuestion,
  QuizQuestionProvider,
} from 'codedifferently-instructional';

export class CalvinRobinsonQuiz implements QuizQuestionProvider {
  getProviderName(): string {
    return 'calvinrobinson';
  }

  makeQuizQuestions(): QuizQuestion[] {
    return [
      CalvinRobinsonQuiz.makeQuestion0(),
      CalvinRobinsonQuiz.makeQuestion1(),
      CalvinRobinsonQuiz.makeQuestion2(),
    ];
  }

  private static makeQuestion0(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      0,
      'Why do PCs overheat?',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, 'Using a wireless mouse and keyboard'],
        [AnswerChoice.B, 'Excessive use of solid-state drives'],
        [AnswerChoice.C, 'Poor ventilation and airflow inside the case'],
        [AnswerChoice.D, 'Running low-resolution graphics settings'],
      ]),
      AnswerChoice.UNANSWERED,
    ); // Replace `UNANSWERED` with the correct answer.
  }

  private static makeQuestion1(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      1,
      'Which answer best describes GitHub?',
      new Map<AnswerChoice, string>([
        [
          AnswerChoice.A,
          'A cloud-based spreadsheet tool for financial analysis',
        ],
        [
          AnswerChoice.B,
          'A social media platform for developers to share memes',
        ],
        [
          AnswerChoice.C,
          'A web-based platform for hosting and collaborating on Git repositories',
        ],
        [
          AnswerChoice.D,
          'A database management system for storing large datasets',
        ],
      ]),
      AnswerChoice.UNANSWERED,
    ); // Replace `UNANSWERED` with the correct answer.
  }

  private static makeQuestion2(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      2,
      'What is PR in software development?',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, 'Public Relations strategy for tech companies'],
        [AnswerChoice.B, 'Pull Request used to propose changes in a codebase'],
        [AnswerChoice.C, 'Page Refresh triggered by user interaction'],
        [AnswerChoice.D, 'Program Runtime measured during execution'],
      ]),
      AnswerChoice.UNANSWERED,
    ); // Replace `UNANSWERED` with the correct answer.
  }
}
