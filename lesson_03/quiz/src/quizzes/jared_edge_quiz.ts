import {
  AnswerChoice,
  MultipleChoiceQuizQuestion,
  QuizQuestion,
  QuizQuestionProvider,
} from 'codedifferently-instructional';

export class JaredEdgeQuiz implements QuizQuestionProvider {
  getProviderName(): string {
    return 'jarededge';
  }

  makeQuizQuestions(): QuizQuestion[] {
    return [
      JaredEdgeQuiz.makeQuestion0(),
      JaredEdgeQuiz.makeQuestion1(),
      JaredEdgeQuiz.makeQuestion2(),
    ];
  }

  private static makeQuestion0(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      0,
      'What does the "code" command do in the terminal?',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, 'Displays the current directory'],
        [AnswerChoice.B, 'Deletes a file in the terminal'],
        [AnswerChoice.C, 'Switches you to that specified file'], // CORRECT
        [AnswerChoice.D, 'Starts a new Git repository'],
      ]),
      AnswerChoice.UNANSWERED,
    );
  }

  private static makeQuestion1(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      1,
      'What does the (Command + Shift + P) shortcut do in VS Code?',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, 'Opens the command palette'], // CORRECT
        [AnswerChoice.B, 'Saves all files'],
        [AnswerChoice.C, 'Creates a new terminal window'],
        [AnswerChoice.D, 'Opens the settings.json file'],
      ]),
      AnswerChoice.UNANSWERED,
    );
  }

  private static makeQuestion2(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      2,
      'What is the best way to get an answer about something?',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, 'Start off with what you tried first'], // CORRECT
        [AnswerChoice.B, 'Just say “it is not working”'],
        [AnswerChoice.C, 'Wait for the instructor to figure it out'],
        [AnswerChoice.D, 'Repeat the problem multiple times'],
      ]),
      AnswerChoice.UNANSWERED,
    );
  }
}
