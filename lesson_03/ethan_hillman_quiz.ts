import {
  AnswerChoice,
  MultipleChoiceQuizQuestion,
  QuizQuestion,
  QuizQuestionProvider,
} from 'codedifferently-instructional';

export class EthanHillmanQuiz implements QuizQuestionProvider {
  getProviderName(): string {
    return 'ethanhillman';
  }

  makeQuizQuestions(): QuizQuestion[] {
    return [
      EthanHillmanQuiz.makeQuestion0(),
      EthanHillmanQuiz.makeQuestion1(),
      EthanHillmanQuiz.makeQuestion2(),
      EthanHillmanQuiz.makeQuestion3(),
    ];
  }

  private static makeQuestion0(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      0,
      'What do 1/s and 0/s stand for in a binary signal?',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, '1s are on, 0s are off'],
        [AnswerChoice.B, '1s are off, 0s are on'],
        [AnswerChoice.C, '1s are high, 0s are low'],
        [AnswerChoice.D, '1s are low, 0s are high'],
      ]),
      AnswerChoice.UNANSWERED, 
      // Replace `UNANSWERED` with the correct answer.
    );
  }

  private static makeQuestion1(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      1,
      'What does CPU stand for?',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, 'Content Processing Unit'],
        [AnswerChoice.B, 'Control Processing Unit'],
        [AnswerChoice.C, 'Central Processing Unit'],
        [AnswerChoice.D, 'Computer Processing Unit'],
      ]),
      AnswerChoice.UNANSWERED,
      // Replace `UNANSWERED` with the correct answer.
    );
  }

  private static makeQuestion2(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      2,
      'What does RAM stand for?',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, 'Raging Angry Man'],
        [AnswerChoice.B, 'Random Access Memory'],
        [AnswerChoice.C, 'Read Access Memory'],
        [AnswerChoice.D, 'Random Access Machine'],
      ]),
      AnswerChoice.UNANSWERED,
    ); // Replace `UNANSWERED` with the correct answer.
  }

  private static makeQuestion3(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      3,
      'What/s the purpose of the motherboard?',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, 'To store data'],
        [AnswerChoice.B, 'To run the operating system'],
        [AnswerChoice.C, 'Process data'],
        [AnswerChoice.D, 'To connect all components of the computer'],
      ]),
      AnswerChoice.UNANSWERED,
    ); // Replace `UNANSWERED` with the correct answer.
  }
}
