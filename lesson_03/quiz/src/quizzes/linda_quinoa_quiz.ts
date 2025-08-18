import {
  AnswerChoice,
  MultipleChoiceQuizQuestion,
  QuizQuestion,
  QuizQuestionProvider,
} from 'codedifferently-instructional';

export class LindaQuinoaQuiz implements QuizQuestionProvider {
  getProviderName(): string {
    return 'lindaquinoa';
  }

  makeQuizQuestions(): QuizQuestion[] {
    return [
      LindaQuinoaQuiz.makeQuestion0(),
      LindaQuinoaQuiz.makeQuestion1(),
      LindaQuinoaQuiz.makeQuestion2(),
    ];
  }

  private static makeQuestion0(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      0,
      'Which HTML element is best suited for defining the mainheading of a page for both search engines and screen readers?',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, '<title>'],
        [AnswerChoice.B, '<header'],
        [AnswerChoice.C, '<h1>'],
        [AnswerChoice.D, '<head>'],
      ]),
      AnswerChoice.UNANSWERED,
    ); // Replace `UNANSWERED` with the correct answer.
  }

  private static makeQuestion1(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      1,
      'When two CSS rules target the same element, which factor has the highest priority in determining which style is applied?',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, 'The order the CSS files are linked in the HTML'],
        [AnswerChoice.B, 'Specificity of the selectors'],
        [AnswerChoice.C, 'The font-family value'],
        [AnswerChoice.D, 'The number of lines of CSS code in the stylesheet'],
      ]),
      AnswerChoice.UNANSWERED,
    ); // Replace `UNANSWERED` with the correct answer.
  }

  private static makeQuestion2(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      2,
      'What is the main purpose of creating a new branch in Git before starting a feature?',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, 'To automatically merge changes into the main branch'],
        [AnswerChoice.B, 'To experiment without affecting the main codebase'],
        [AnswerChoice.C, 'To delete the main branch safely'],
        [AnswerChoice.D, 'To reduce the size of the repository'],
      ]),
      AnswerChoice.UNANSWERED,
    ); // Replace `UNANSWERED` with the correct answer.
  }
}
