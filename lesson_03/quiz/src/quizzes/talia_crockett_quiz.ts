import {
  AnswerChoice,
  MultipleChoiceQuizQuestion,
  QuizQuestion,
  QuizQuestionProvider,
} from 'codedifferently-instructional';

export class TaliaCrockettQuiz implements QuizQuestionProvider {
  getProviderName(): string {
    return 'taliacrockett';
  }

  makeQuizQuestions(): QuizQuestion[] {
    return [
      TaliaCrockettQuiz.makeQuestion0(),
      TaliaCrockettQuiz.makeQuestion1(),
      TaliaCrockettQuiz.makeQuestion2(),
    ];
  }

  private static makeQuestion0(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      0,
      'What is an HTML element?',
      new Map<AnswerChoice, string>([
        [
          AnswerChoice.A,
          'The smallest building block of a web page, defined by a start tag, content, and an end tag.',
        ],
        [
          AnswerChoice.B,
          'A tool used to store images and videos on the internet.',
        ],
        [AnswerChoice.C, 'A type of programming loop used in JavaScript.'],
        [AnswerChoice.D, 'A style rule written in CSS.'],
      ]),
      AnswerChoice.UNANSWERED,
    ); // Replace `UNANSWERED` with the correct answer.
  }

  private static makeQuestion1(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      1,
      'What does HTML stand for?',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, 'Hyper Text Markup Language'],
        [AnswerChoice.B, 'High Technology Modern Language'],
        [AnswerChoice.C, 'Home Tool Markup Language'],
        [AnswerChoice.D, 'Hyperlink and Text Management Language'],
      ]),
      AnswerChoice.UNANSWERED,
    ); // Replace `UNANSWERED` with the correct answer.
  }

  private static makeQuestion2(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      2,
      'What is the correct HTML element for inserting a line break?',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, '<break>'],
        [AnswerChoice.B, '<lb>'],
        [AnswerChoice.C, '<br>'],
        [AnswerChoice.D, '<b>'],
      ]),
      AnswerChoice.UNANSWERED,
    ); // Replace `UNANSWERED` with the correct answer.
  }
}
