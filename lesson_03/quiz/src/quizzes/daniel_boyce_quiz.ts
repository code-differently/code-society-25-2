import {
  AnswerChoice,
  MultipleChoiceQuizQuestion,
  QuizQuestion,
  QuizQuestionProvider,
} from 'codedifferently-instructional';

export class DanielBoyceQuiz implements QuizQuestionProvider {
  getProviderName(): string {
    return 'danielboyce';
  }

  makeQuizQuestions(): QuizQuestion[] {
    return [DanielBoyceQuiz.makeQuestion0(), DanielBoyceQuiz.makeQuestion1(), DanielBoyceQuiz.makeQuestion2()];
  }

  public static makeQuestion0(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      0,
      'Where is the CPU located in a computer?',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, 'On the screen'],
        [AnswerChoice.B, 'In the power supply unit'],
        [AnswerChoice.C, 'On the motherboard'],
        [AnswerChoice.D, 'The CPU is not a physical component'],
      ]),
      AnswerChoice.UNANSWERED,
    ); // Replace `UNANSWERED` with the correct answer.
  }

  public static makeQuestion1(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      1,
      'What is the puropse of the cpu',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, 'preform calculations'],
        [AnswerChoice.B, 'To store data'],
        [AnswerChoice.C, 'provide power to the computer'],
        [AnswerChoice.D, 'To display information on the screen'],
      ]),
      AnswerChoice.UNANSWERED,
    ); // Replace `UNANSWERED` with the correct answer.
  }

  public static makeQuestion2(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      2,
      'What is the main function of RAM in a computer?',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, 'To store data permanently'],
        [AnswerChoice.B, 'To provide temporary storage for data in use'],
        [AnswerChoice.C, 'To manage power supply'],
        [AnswerChoice.D, 'To connect to the internet'],
      ]),
      AnswerChoice.UNANSWERED,
    ); // Replace `UNANSWERED` with the correct answer.
  }
}
