import {
  AnswerChoice,
  MultipleChoiceQuizQuestion,
  QuizQuestion,
  QuizQuestionProvider,
} from 'codedifferently-instructional';

export class WayleomVargasQuiz implements QuizQuestionProvider {
  getProviderName(): string {
    return 'wayleomvargas';
  }

  makeQuizQuestions(): QuizQuestion[] {
    return [
      WayleomVargasQuiz.makeQuestion0(),
      WayleomVargasQuiz.makeQuestion1(),
      WayleomVargasQuiz.makeQuestion2(),
    ];
  }

  private static makeQuestion0(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      0,
      'Where does the CPU load software from?',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, 'Hard Drive'],
        [AnswerChoice.B, 'RAM'],
        [AnswerChoice.C, 'GPU'],
        [AnswerChoice.D, 'Peripherals'],
      ]),
      AnswerChoice.UNANSWERED,
    ); // Replace `UNANSWERED` with the correct answer.
  }

  private static makeQuestion1(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      1,
      'Where does the motherboard get the energy to power the computer?',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, 'CPU'],
        [AnswerChoice.B, 'RAM'],
        [AnswerChoice.C, 'Power Supply'],
        [AnswerChoice.D, 'Mouse'],
      ]),
      AnswerChoice.UNANSWERED,
    ); // Replace `UNANSWERED` with the correct answer.
  }

  private static makeQuestion2(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      2,
      'What git commands should you use to push your changes to the remote repository?',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, 'Git clone, Git init, Git push'],
        [AnswerChoice.B, 'Git pull, Git merge, Git push'],
        [AnswerChoice.C, 'Git status, Git log, Git push'],
        [AnswerChoice.D, 'Git add, Git commit, Git push'],
      ]),
      AnswerChoice.UNANSWERED,
    ); // Replace `UNANSWERED` with the correct answer.
  }
}
