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
      'What command is used to initialize a new Git repository?',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, 'git start'],
        [AnswerChoice.B, 'git create'],
        [AnswerChoice.C, 'git new'],
        [AnswerChoice.D, 'git init'],
      ]),
      AnswerChoice.UNANSWERED,
    ); // Replace `UNANSWERED` with the correct answer.
  }

  private static makeQuestion1(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      1,
      'Which of the following is NOT a basic data type in most programming languages?',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, 'Integer'],
        [AnswerChoice.B, 'String'],
        [AnswerChoice.C, 'Boolean'],
        [AnswerChoice.D, 'Repository'],
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
      'What is the basic unit of computer memory?',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, 'Bit'],
        [AnswerChoice.B, 'Byte'],
        [AnswerChoice.C, 'Word'],
        [AnswerChoice.D, 'Block'],
      ]),
      AnswerChoice.UNANSWERED,
    ); // Replace `UNANSWERED` with the correct answer.
  }
  private static makeQuestion4(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      4,
      'What does CPU stand for?',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, 'Computer Processing Unit'],
        [AnswerChoice.B, 'Central Program Unit'],
        [AnswerChoice.C, 'Core Processing Unit'],
        [AnswerChoice.D, 'Central Processing Unit'],
      ]),
      AnswerChoice.UNANSWERED,
    ); // Replace `UNANSWERED` with the correct answer.
  }
  private static makeQuestion5(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      5,
      'Which Git command is used to create a new branch?',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, 'git new-branch'],
        [AnswerChoice.B, 'git create-branch'],
        [AnswerChoice.C, 'git make-branch'],
        [AnswerChoice.D, 'git branch'],
      ]),
      AnswerChoice.UNANSWERED,
    ); // Replace `UNANSWERED` with the correct answer.
  }
  private static makeQuestion6(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      6,
      'What is the main purpose of GitHub?',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, 'Social networking'],
        [AnswerChoice.B, 'Online shopping'],
        [AnswerChoice.C, 'Video streaming'],
        [AnswerChoice.D, 'Code hosting and version control'],
      ]),
      AnswerChoice.UNANSWERED,
    ); // Replace `UNANSWERED` with the correct answer.
  }

  private static makeQuestion7(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      7,
      'Which component is considered the "brain" of the computer?',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, 'RAM'],
        [AnswerChoice.B, 'CPU'],
        [AnswerChoice.C, 'Hard Drive'],
        [AnswerChoice.D, 'Motherboard'],
      ]),
      AnswerChoice.UNANSWERED,
    ); // Replace `UNANSWERED` with the correct answer.
  }

  private static makeQuestion8(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      8,
      'In programming, what does "debugging" mean?',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, 'Writing new code'],
        [AnswerChoice.B, 'Deleting old files'],
        [AnswerChoice.C, 'Finding and fixing errors in code'],
        [AnswerChoice.D, 'Compiling code'],
      ]),
      AnswerChoice.UNANSWERED,
    ); // Replace `UNANSWERED` with the correct answer.
  }

   private static makeQuestion9(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      9,
      'What does RAM stand for in computer architecture?',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, 'Read Access Memory'],
        [AnswerChoice.B, 'Rapid Application Memory'],
        [AnswerChoice.C, 'Remote Access Module'],
        [AnswerChoice.D, 'Random Access Memory'],
      ]),
      AnswerChoice.UNANSWERED,
    ); // Replace `UNANSWERED` with the correct answer.
  }
}