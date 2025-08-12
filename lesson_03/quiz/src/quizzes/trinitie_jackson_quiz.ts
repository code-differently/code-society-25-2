import {
  AnswerChoice,
  MultipleChoiceQuizQuestion,
  QuizQuestion,
  QuizQuestionProvider,
} from 'codedifferently-instructional';

export class TrinitieJacksonQuiz implements QuizQuestionProvider {
  getProviderName(): string {
    return 'trinitiejackson';
  }

  makeQuizQuestions(): QuizQuestion[] {
    return [
      TrinitieJacksonQuiz.makeQuestion0(),
      TrinitieJacksonQuiz.makeQuestion1(),
      TrinitieJacksonQuiz.makeQuestion2(),
      TrinitieJacksonQuiz.makeQuestion3(),
    ];
  }

  private static makeQuestion0(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      0,
      'What is another technical term for fork?',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, 'branch'],
        [AnswerChoice.B, 'repository'],
        [AnswerChoice.C, 'pathway'],
        [AnswerChoice.D, 'modifier'],
      ]),
      AnswerChoice.UNANSWERED,
    ); // Replace `UNANSWERED` with the correct answer.
  }

  private static makeQuestion1(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      1,
      'In the desk analogy, what is the CPU?',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, 'motherboard'],
        [AnswerChoice.B, 'power supply'],
        [AnswerChoice.C, 'software'],
        [AnswerChoice.D, 'calculator'],
      ]),
      AnswerChoice.UNANSWERED,
    ); // Replace `UNANSWERED` with the correct answer.
  }

  private static makeQuestion2(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      2,
      'What does the "cat" command do in a terminal?',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, 'copy a file or directory'],
        [AnswerChoice.B, 'dumps contents of a file'],
        [AnswerChoice.C, 'run any command'],
        [AnswerChoice.D, 'view the last several lines of a file'],
      ]),
      AnswerChoice.UNANSWERED,
    ); // Replace `UNANSWERED` with the correct answer.
  }

  private static makeQuestion3(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      3,
      'Which is NOT a void tag in HTML?',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, 'img'],
        [AnswerChoice.B, 'input'],
        [AnswerChoice.C, 'br'],
        [AnswerChoice.D, 'p'],
      ]), 
      AnswerChoice.UNANSWERED,
    ); // Replace `UNANSWERED` with the correct answer.
  }
}
