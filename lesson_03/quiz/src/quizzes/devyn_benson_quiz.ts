import {
  AnswerChoice,
  MultipleChoiceQuizQuestion,
  QuizQuestion,
  QuizQuestionProvider,
} from 'codedifferently-instructional';

export class DevynBensonQuiz implements QuizQuestionProvider {
  getProviderName(): string {
    return 'devynbenson';
  }

  makeQuizQuestions(): QuizQuestion[] {
    return [
      DevynBensonQuiz.makeQuestion0(),
      DevynBensonQuiz.makeQuestion1(),
      DevynBensonQuiz.makeQuestion2(),
      DevynBensonQuiz.makeQuestion3(),
      DevynBensonQuiz.makeQuestion4(),
      DevynBensonQuiz.makeQuestion5(),
      DevynBensonQuiz.makeQuestion6(),
      DevynBensonQuiz.makeQuestion7(),
      DevynBensonQuiz.makeQuestion8(),
      DevynBensonQuiz.makeQuestion9(),
    ];
  }

  private static makeQuestion0(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      0,
      'What kind of dog does Mr. Mays have?',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, 'A Weiner Dog'],
        [AnswerChoice.B, 'Yorkshire Terrier'],
        [AnswerChoice.C, 'A Golden Retriever'],
        [AnswerChoice.D, 'Schnauzer'],
      ]),
      AnswerChoice.UNANSWERED,
    ); // Replace `UNANSWERED` with the correct answer.
  }

  private static makeQuestion1(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      1,
      'Answer the blank, which is false? : Mr. Mays is very _____.',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, 'Tall'],
        [AnswerChoice.B, 'Handsome'],
        [AnswerChoice.C, 'Smart'],
        [AnswerChoice.D, 'Evil'],
      ]),
      AnswerChoice.UNANSWERED,
    ); // Replace `UNANSWERED` with the correct answer.
  }

  private static makeQuestion2(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      2,
      'What is apart of the 6 steps?',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, 'Versatility'],
        [AnswerChoice.B, 'Adapt'],
        [AnswerChoice.C, 'Do Not Communicate'],
        [AnswerChoice.D, 'Ask Questions'],
      ]),
      AnswerChoice.UNANSWERED,
    ); // Replace `UNANSWERED` with the correct answer.
  }

  private static makeQuestion3(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      3,
      'What color is the sky?',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, 'Blue'],
        [AnswerChoice.B, 'Violet'],
        [AnswerChoice.C, 'Yellow'],
        [AnswerChoice.D, 'Red'],
      ]),
      AnswerChoice.UNANSWERED,
    ); // Replace `UNANSWERED` with the correct answer.
  }
  private static makeQuestion4(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      4,
      'How many students are in Cohort 25.2?',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, '24'],
        [AnswerChoice.B, '28'],
        [AnswerChoice.C, '27'],
        [AnswerChoice.D, '23'],
      ]),
      AnswerChoice.UNANSWERED,
    ); // Replace `UNANSWERED` with the correct answer.
  }
  private static makeQuestion5(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      5,
      'What did Mr. Mays eat from the food truck on 08/13/25?',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, 'Cheeseteak'],
        [AnswerChoice.B, 'Tacos'],
        [AnswerChoice.C, 'Chicken Katsu'],
        [AnswerChoice.D, 'Loaded Fries w/ brisket'],
      ]),
      AnswerChoice.UNANSWERED,
    ); // Replace `UNANSWERED` with the correct answer.
  }
  private static makeQuestion6(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      6,
      'Who did Devyn introduce for the first tech talk?',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, 'Sadiq Hook'],
        [AnswerChoice.B, 'Terrance Bowman'],
        [AnswerChoice.C, 'Tarik Hook '],
        [AnswerChoice.D, 'Tariq Hook'],
      ]),
      AnswerChoice.UNANSWERED,
    ); // Replace `UNANSWERED` with the correct answer.
  }

  private static makeQuestion7(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      7,
      'How should you brush your teeth?',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, 'With no brush'],
        [AnswerChoice.B, 'With a Brush, Toothpaste, and Water'],
        [AnswerChoice.C, 'With Water and a Brush'],
        [AnswerChoice.D, 'With a Brush'],
      ]),
      AnswerChoice.UNANSWERED,
    ); // Replace `UNANSWERED` with the correct answer.
  }

  private static makeQuestion8(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      8,
      'What is Mr. Mays\'nickname?' ,
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, 'Money Mays'],
        [AnswerChoice.B, 'Ant Man'],
        [AnswerChoice.C, 'Compton Google'],
        [AnswerChoice.D, 'Zoogler'],
      ]),
      AnswerChoice.UNANSWERED,
    ); // Replace `UNANSWERED` with the correct answer.
  }

   private static makeQuestion9(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      9,
      'What team does Jordan Eldrige ',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, 'Commanders'],
        [AnswerChoice.B, 'Pitsburgh'],
        [AnswerChoice.C, 'Cowboys (aka Cowgirls)'],
        [AnswerChoice.D, 'Eagles'],
      ]),
      AnswerChoice.UNANSWERED,
    ); // Replace `UNANSWERED` with the correct answer.
  }
}