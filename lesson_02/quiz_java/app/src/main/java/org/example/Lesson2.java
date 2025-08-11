package org.example;

import java.util.HashMap;
import java.util.Map;

import com.codedifferently.instructional.quiz.AnswerChoice;
import com.codedifferently.instructional.quiz.MultipleChoiceQuizQuestion;
import com.codedifferently.instructional.quiz.QuizPrinter;
import com.codedifferently.instructional.quiz.QuizQuestion;
/**
 * Lesson 2: Quiz on Git, IDEs, and Terminal Commands.
 */
 public class Lesson2 {
  public static void main(String[] args) {
    QuizQuestion[] quizQuestions = makeQuizQuestions();
    if (!quizQuestions == null) throw new RuntimeException("Quiz questions cannot be null");
    QuizPrinter printer = new QuizPrinter();
    printer.printQuiz(quizQuestions);
  }

  public static QuizQuestion[] makeQuizQuestions() {
    return new QuizQuestion[] {
      makeQuestion0(),
      makeQuestion1(),
      makeQuestion2(),
      makeQuestion3(),
      makeQuestion4(),
      makeQuestion5(),
      makeQuestion6(),
      makeQuestion7(),
      makeQuestion8(),
      makeQuestion9(),
      makeQuestion10()
    };
  }

  private static makeQuestion0() {
    Map<AnswerChoice, String> choices = new HashMap<>();
    choices.put(AnswerChoice.A, "To make backups of files");
    choices.put(AnswerChoice.B, "To keep a record of changes over time");
    choices.put(AnswerChoice.C, "To delete unnecessary files");
    choices.put(AnswerChoice.D, "To run code more efficiently");
    return new MultipleChoiceQuizQuestion(
      0,
      "What is the main purpose of version control?",
      choices,
      AnswerChoice.B // Replace `UNANSWERED` with the correct answer.
    );
  }

  private static makeQuestion1(): QuizQuestion {
     Map<AnswerChoice, String> choices = new HashMap<>();
      choices.put(AnswerChoice.A, "A duplicate copy of a repository that you own and modify");
      choices.put(AnswerChoice.B, "A temporary backup of the code");
      choices.put(AnswerChoice.C, "A tool for merging branches");
      choices.put(AnswerChoice.D, "A way to delete a repository");
    return new MultipleChoiceQuizQuestion(
      1,
      "What is a fork in Git?",
      choices,
      AnswerChoice.A // Replace `UNANSWERED` with the correct answer.
    );
  }

  private static makeQuestion2(): QuizQuestion {
    Map<AnswerChoice, String> choices = new HashMap<>();
    choices.put(AnswerChoice.A, "Pull the latest changes");
    choices.put(AnswerChoice.B, "Commit changes locally");
    choices.put(AnswerChoice.C, "Push changes to the server");
    choices.put(AnswerChoice.D, "Write code directly in GitHub");
    return new MultipleChoiceQuizQuestion(
      2,
      "Which of the following is NOT part of the basic Git workflow?",
      choices,
      AnswerChoice.D // Replace `UNANSWERED` with the correct answer.
    );
  }

  private static makeQuestion3(): QuizQuestion {
    Map<AnswerChoice, String> choices = new HashMap<>();
    choices.put(AnswerChoice.A, "git commit");
    choices.put(AnswerChoice.B, "git merge");
    choices.put(AnswerChoice.C, "git branch");
    choices.put(AnswerChoice.D, "git pull");
    return new MultipleChoiceQuizQuestion(
      3,
      "What command is used to combine changes from different branches?",
      choices,
      AnswerChoice.B // Replace `UNANSWERED` with the correct answer.
    );
  }

  private static makeQuestion4(): QuizQuestion {
    Map<AnswerChoice, String> choices = new HashMap<>();
    choices.put(AnswerChoice.A, "Eclipse");
    choices.put(AnswerChoice.B, "IntelliJ IDEA");
    choices.put(AnswerChoice.C, "NetBeans");
    choices.put(AnswerChoice.D, "VS Code");
    return new MultipleChoiceQuizQuestion(
      4,
      "Which IDE is being used in the class?",
      choices,
      AnswerChoice.D // Replace `UNANSWERED` with the correct answer.
    );
  }

  private static makeQuestion5(): QuizQuestion {
    Map<AnswerChoice, String> choices = new HashMap<>();
    choices.put(AnswerChoice.A, "Extensions");
    choices.put(AnswerChoice.B, "Debugger");
    choices.put(AnswerChoice.C, "Dev Containers");
    choices.put(AnswerChoice.D, "Source Control");
    return new MultipleChoiceQuizQuestion(
      5,
      "What feature allows developers to work from the same pre-configured environment in VS Code?",
      choices,
      AnswerChoice.C // Replace `UNANSWERED` with the correct answer.
    );
  }

  private static makeQuestion6(): QuizQuestion {
    Map<AnswerChoice, String> choices = new HashMap<>();
    choices.put(AnswerChoice.A, "Editing and refactoring code");
    choices.put(AnswerChoice.B, "Browsing code");
    choices.put(AnswerChoice.C, "Playing music");
    choices.put(AnswerChoice.D, "Managing source control");
    return new MultipleChoiceQuizQuestion(
      6,
      "What is NOT a reason for using an IDE?",
      choices,
      AnswerChoice.C // Replace `UNANSWERED` with the correct answer.
    );
  }

  private static makeQuestion7(): QuizQuestion {
    Map<AnswerChoice, String> choices = new HashMap<>();
    choices.put(AnswerChoice.A, "pwd");
    choices.put(AnswerChoice.B, "ls");
    choices.put(AnswerChoice.C, "cd");
    choices.put(AnswerChoice.D, "mkdir");
    return new MultipleChoiceQuizQuestion(
      7,
      "What is the command to list files in the current directory?",
      choices,
      AnswerChoice.B // Replace `UNANSWERED` with the correct answer.
    );
  }

  private static makeQuestion8(): QuizQuestion {
    Map<AnswerChoice, String> choices = new HashMap<>();
    choices.put(AnswerChoice.A, "pwd");
    choices.put(AnswerChoice.B, "ls");
    choices.put(AnswerChoice.C, "cd");
    choices.put(AnswerChoice.D, "mkdir");
    return new MultipleChoiceQuizQuestion(
      8,
      "Which command is used to change directories in the terminal?",
      choices,
      AnswerChoice.C // Replace `UNANSWERED` with the correct answer.
    );
  }

  private static makeQuestion9(): QuizQuestion {
    Map<AnswerChoice, String> choices = new HashMap<>();
    choices.put(AnswerChoice.A, "Change file or directory permissions");
    choices.put(AnswerChoice.B, "List files in a directory");
    choices.put(AnswerChoice.C, "Remove a file or directory");
    choices.put(AnswerChoice.D, "Copy a file or directory");
    return new MultipleChoiceQuizQuestion(
      9,
      "What does the command `chmod` do?",
      choices,
      AnswerChoice.A // Replace `UNANSWERED` with the correct answer.
    );
  }

  private static makeQuestion10(): QuizQuestion {
    Map<AnswerChoice, String> choices = new HashMap<>();
    choices.put(AnswerChoice.A, "⌘ + Shift + T");
    choices.put(AnswerChoice.B, '⌘ + Spacebar, then type "terminal"');
    choices.put(AnswerChoice.C, "⌘ + Q");
    choices.put(AnswerChoice.D, '⌘ + S, then type "terminal"');
    return new MultipleChoiceQuizQuestion(
      10,
      "What is the shortcut for getting to the Mac terminal?",
      choices,
      AnswerChoice.B // Replace `UNANSWERED` with the correct answer.
    );
  }
}