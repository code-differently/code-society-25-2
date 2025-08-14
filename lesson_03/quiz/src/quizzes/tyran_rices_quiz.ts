import {
  AnswerChoice,
  MultipleChoiceQuizQuestion,
  QuizQuestion,
  QuizQuestionProvider,
} from 'codedifferently-instructional';

export class TyranRicesQuiz implements QuizQuestionProvider {
  getProviderName(): string {
    return 'tyranrices';
  }

  makeQuizQuestions(): QuizQuestion[] {
    return [
      TyranRicesQuiz.makeQuestion0(),
      TyranRicesQuiz.makeQuestion1(),
      TyranRicesQuiz.makeQuestion2(),
    ];
  }

  private static makeQuestion0(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      0,
      'What keyword do you use for introducing a new feature in comment name conventions?',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, 'chore:'],
        [AnswerChoice.B, 'doc:'],
        [AnswerChoice.C, 'feat:'],
        [AnswerChoice.D, 'fix:'],
      ]),
      AnswerChoice.UNANSWERED,
    ); // Replace `UNANSWERED` with the correct answer.
  }

  private static makeQuestion1(): QuizQuestion {
    return new QuizQuestion(
      1,
      'What does GPU stand for?',
      'Graphics Processing Unit.',
    ); // Provide an answer.
  }

  private static makeQuestion2(): QuizQuestion {
    return new QuizQuestion(
      2,
      'What does PR stand for in the context of software development?',
      'Pull Request.',
    ); // Provide an answer.
  }
}
