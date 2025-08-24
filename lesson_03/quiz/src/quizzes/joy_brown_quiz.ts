    import {
  AnswerChoice,
  MultipleChoiceQuizQuestion,
  QuizQuestion,
  QuizQuestionProvider,
} from 'codedifferently-instructional';


export class JoyBrownQuiz implements QuizQuestionProvider {
  getProviderName(): string {
    return 'joybrown';
  }

  makeQuizQuestions(): QuizQuestion[] {
    return [
      JoyBrownQuiz.makeQuestion0(),
      JoyBrownQuiz.makeQuestion1(),
      JoyBrownQuiz.makeQuestion2(),
      
    ];
  }

  private static makeQuestion0(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      0,
      'What is a pull?',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, 'A download.'],
        [AnswerChoice.B, 'A upload.'],
        [
          AnswerChoice.C,
          'Saving a file.',
        ],
        [AnswerChoice.D, 'A request to merge your changes into another branch.'],
      ]),
      AnswerChoice.UNANSWERED,
    ); // Replace `UNANSWERED` with the correct answer.
  }

  private static makeQuestion1(): QuizQuestion {
    return new QuizQuestion(
      1,
      'Git is an online cloud for coding, Yes or No',
      'No',
    ); // Provide an answer.
  }

  private static makeQuestion2(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      2,
      'Which command makes a new directory',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, 'mkdir'],
        [AnswerChoice.B, 'cp'],
        [
          AnswerChoice.C,
          'rm',
        ],
        [AnswerChoice.D, 'mv'],
      ]),
      AnswerChoice.UNANSWERED,
    ); // Replace `UNANSWERED` with the correct answer.
  }
}