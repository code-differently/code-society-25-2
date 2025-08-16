import {
  AnswerChoice,
  MultipleChoiceQuizQuestion,
  QuizQuestion,
  QuizQuestionProvider,
} from 'codedifferently-instructional';

export class KerryFergusonQuiz implements QuizQuestionProvider {
  getProviderName(): string {
    return 'kerryferguson';
  }

  makeQuizQuestions(): QuizQuestion[] {
    return [
      KerryFergusonQuiz.makeQuestion0(),
      KerryFergusonQuiz.makeQuestion1(),
      KerryFergusonQuiz.makeQuestion2(),
    ];
  }

  private static makeQuestion0(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      0,
      'What is the command to print the working directory in a terminal?',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, 'pwd'],
        [AnswerChoice.B, 'ls'],
        [AnswerChoice.C, 'dir'],
        [AnswerChoice.D, 'cd'],
      ]),
      AnswerChoice.UNANSWERED,
    ); // Replace `UNANSWERED` with the correct answer (A).
  }

  private static makeQuestion1(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      1,
      'What is the command to make a new file?',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, 'mkdir'],
        [AnswerChoice.B, 'mv'],
        [AnswerChoice.C, 'touch'],
        [AnswerChoice.D, 'grep'],
      ]),
      AnswerChoice.UNANSWERED,
    ); // Replace `UNANSWERED` with the correct answer (C).
  }

  private static makeQuestion2(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      2,
      'What is the special operator to (pipe) feed output of one command to another?',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, '&&'],
        [AnswerChoice.B, '||'],
        [AnswerChoice.C, ';'],
        [AnswerChoice.D, '|'],
      ]),
      AnswerChoice.UNANSWERED,
    ); // Replace `UNANSWERED` with the correct answer (D) - pipe operator chains commands
  }
}
