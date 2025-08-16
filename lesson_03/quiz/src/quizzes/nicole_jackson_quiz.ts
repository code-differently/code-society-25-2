import {
  AnswerChoice,
  MultipleChoiceQuizQuestion,
  QuizQuestion,
  QuizQuestionProvider,
} from 'codedifferently-instructional';

export class NicoleJacksonQuiz implements QuizQuestionProvider {
  getProviderName(): string {
    return 'nicolejackson';
  }

  makeQuizQuestions(): QuizQuestion[] {
    return [
      NicoleJacksonQuiz.makeQuestion0(),
      NicoleJacksonQuiz.makeQuestion1(),
      NicoleJacksonQuiz.makeQuestion2(),
    ];
  }

  private static makeQuestion0(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      0,
      'Which of the following MAC OS Terminal commands will allow you to list directories?',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, 'pwd'],
        [AnswerChoice.B, 'ls'],
        [AnswerChoice.C, 'cd ..'],
        [AnswerChoice.D, 'mkdir'],
      ]),
      AnswerChoice.UNANSWERED,
    ); // Replace `UNANSWERED` with the correct answer.
  }

  private static makeQuestion1(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      1,
      'What is a CPU?',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, 'Central Printing Unit'],
        [AnswerChoice.B, 'Central Processing Unit'],
        [AnswerChoice.C, 'Computer Programming Unit'],
        [AnswerChoice.D, 'Coding Protocol Unit'],
      ]),
      AnswerChoice.UNANSWERED,
    ); // Provide an answer.
  }
   private static makeQuestion2(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      2,
      'Using boolean logic, which of the following would not represent a true value?',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, 'yes'],
        [AnswerChoice.B, 'probable'],
        [AnswerChoice.C, 'true'],
        [AnswerChoice.D, '1'],
      ]),
      AnswerChoice.UNANSWERED,
    ); // Provide an answer.
  

  
}
}
