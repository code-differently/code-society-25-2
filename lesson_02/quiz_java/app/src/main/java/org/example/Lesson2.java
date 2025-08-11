package org.example;


import java.util.Arrays;
import java.util.Map;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.codedifferently.instructional.quiz.AnswerChoice;
import com.codedifferently.instructional.quiz.MultipleChoiceQuizQuestion;
import com.codedifferently.instructional.quiz.QuizPrinter;
import com.codedifferently.instructional.quiz.QuizQuestion;

/*
 * Title: Lesson 2 Quiz Application
 * 
 * Description: This application is designed to create and print a quiz with multiple-choice questions.
 * It includes a set of quiz questions related to version control, Git, IDEs, and terminal commands.
 * 
 * Created By: Tyran Rice Jr.
 * Date: 2023-10-01
 * 
 */
@SpringBootApplication
public class Lesson2 {
    
      /**
       * Title: Main Method
       * 
       * Description: The main method serves as the entry point for the application. It
       * runs Lesson2 by using the `run` method, which initializes the quiz questions
       * 
       * Argument: Command line arguments (not used in this application)
       * 
       * Return: Void
       */
      public static void main(String[] args) {
        System.out.println("Lesson2 app started!");
        new Lesson2().run();
    }

        /**
       * Title: Run
       * 
       * Description: This function initializes the quiz questions and prints them
       * using the QuizPrinter. It ensures that the quiz questions are not null before
       * proceeding to print them. If the quiz questions are null, it throws an error.
       * 
       * Argument: Command line arguments (not used in this application)
       * 
       * Return: Void
       */
    public void run() {
       QuizQuestion[] quizQuestions = makeQuizQuestions();
       if (quizQuestions == null) throw new Error("Quiz questions cannot be null");
       QuizPrinter printer = new QuizPrinter();
       printer.printQuiz(Arrays.asList(quizQuestions));
   }

        /**
       * Title: makeQuizQuestions
       * 
       * Description: This method creates an array of QuizQuestion objects, each representing a 
       * multiple-choice question. The questions cover topics such as version control, Git, IDEs,
       * 
       * Argument: Command line arguments (not used in this application)
       * 
       * Return: An array of QuizQuestion objects, each representing a multiple-choice question.
       */
   public static QuizQuestion[] makeQuizQuestions() {
       return new QuizQuestion[] {
           Lesson2.makeQuestion0(),
           Lesson2.makeQuestion1(),
           Lesson2.makeQuestion2(),
           Lesson2.makeQuestion3(),
           Lesson2.makeQuestion4(),
           Lesson2.makeQuestion5(),
           Lesson2.makeQuestion6(),
           Lesson2.makeQuestion7(),
           Lesson2.makeQuestion8(),
           Lesson2.makeQuestion9(),
           Lesson2.makeQuestion10(),
       };
   }

        /**
       * Title: Make Question 0 - 10
       * 
       * Description: This method creates a QuizQuestion object representing a multiple-choice question.
       * 
       * Argument: Command line arguments (not used in this application)
       * 
       * Return: A QuizQuestion object representing a multiple-choice question.
       */
   private static QuizQuestion makeQuestion0() {
   return new MultipleChoiceQuizQuestion(
     0,
     "What is the main purpose of version control?",
     Map.of(
       AnswerChoice.A, "To make backups of files",
       AnswerChoice.B, "To keep a record of changes over time",
       AnswerChoice.C, "To delete unnecessary files",
       AnswerChoice.D, "To run code more efficiently"
     ),
     AnswerChoice.B // Replace `UNANSWERED` with the correct answer.
   );
 }


 private static QuizQuestion makeQuestion1() {
   return new MultipleChoiceQuizQuestion(
     1,
     "What is a fork in Git?",
     Map.of(
       AnswerChoice.A, "A duplicate copy of a repository that you own and modify",
       AnswerChoice.B, "A temporary backup of the code",
       AnswerChoice.C, "A tool for merging branches",
       AnswerChoice.D, "A way to delete a repository"
     ),
     AnswerChoice.A // Replace `UNANSWERED` with the correct answer.
   );
 }


 private static QuizQuestion makeQuestion2() {
   return new MultipleChoiceQuizQuestion(
     2,
     "Which of the following is NOT part of the basic Git workflow?",
     Map.of(
       AnswerChoice.A, "Pull the latest changes",
       AnswerChoice.B, "Commit changes locally",
       AnswerChoice.C, "Push changes to the server",
       AnswerChoice.D, "Write code directly in GitHub"
     ),
     AnswerChoice.D // Replace `UNANSWERED` with the correct answer.
   );
 }


 private static QuizQuestion makeQuestion3() {
   return new MultipleChoiceQuizQuestion(
     3,
     "What command is used to combine changes from different branches?",
     Map.of(
       AnswerChoice.A, "git commit",
       AnswerChoice.B, "git merge",
       AnswerChoice.C, "git branch",
       AnswerChoice.D, "git pull"
     ),
       AnswerChoice.B // Replace `UNANSWERED` with the correct answer.
   );
 } 


 private static QuizQuestion makeQuestion4() {
   return new MultipleChoiceQuizQuestion(
     4,
     "Which IDE is being used in the class?",
     Map.of(
       AnswerChoice.A, "Eclipse",
       AnswerChoice.B, "IntelliJ IDEA",
       AnswerChoice.C, "NetBeans",
       AnswerChoice.D, "VS Code"
     ),
       AnswerChoice.D // Replace `UNANSWERED` with the correct answer.
   );
}


 private static QuizQuestion makeQuestion5() {
   return new MultipleChoiceQuizQuestion(
     5,
     "What feature allows developers to work from the same pre-configured environment in VS Code?",
     Map.of(
       AnswerChoice.A, "Extensions",
       AnswerChoice.B, "Debugger",
       AnswerChoice.C, "Dev Containers",
       AnswerChoice.D, "Source Control"
     ),
       AnswerChoice.C // Replace `UNANSWERED` with the correct answer.
   );
   }


 private static QuizQuestion makeQuestion6() {
   return new MultipleChoiceQuizQuestion(
     6,
     "What is NOT a reason for using an IDE?",
     Map.of(
       AnswerChoice.A, "Editing and refactoring code",
       AnswerChoice.B, "Browsing code",
       AnswerChoice.C, "Playing music",
       AnswerChoice.D, "Managing source control"
     ),
       AnswerChoice.C // Replace `UNANSWERED` with the correct answer.
   );
   }




 private static QuizQuestion makeQuestion7() {
   return new MultipleChoiceQuizQuestion(
     7,
     "What is the command to list files in the current directory?",
     Map.of(
       AnswerChoice.A, "pwd",
       AnswerChoice.B, "ls",
       AnswerChoice.C, "cd",
       AnswerChoice.D, "mkdir"
     ),
       AnswerChoice.B // Replace `UNANSWERED` with the correct answer.
   );
   }


 private static QuizQuestion makeQuestion8() {
   return new MultipleChoiceQuizQuestion(
     8,
     "Which command is used to change directories in the terminal?",
       Map.of(
       AnswerChoice.A, "pwd",
       AnswerChoice.B, "ls",
       AnswerChoice.C, "cd",
       AnswerChoice.D, "mkdir"
     ),
     AnswerChoice.C // Replace `UNANSWERED` with the correct answer.
   );
 }


 private static QuizQuestion makeQuestion9() {
   return new MultipleChoiceQuizQuestion(
     9,
     "What does the command `chmod` do?",
     Map.of(
       AnswerChoice.A, "Change file or directory permissions",
       AnswerChoice.B, "List files in a directory",
       AnswerChoice.C, "Remove a file or directory",
       AnswerChoice.D, "Copy a file or directory"
     ),
       AnswerChoice.A // Replace `UNANSWERED` with the correct answer.
   );
   }


 private static QuizQuestion makeQuestion10() {
   return new MultipleChoiceQuizQuestion(
     10,
     "What is the shortcut for getting to the Mac terminal?",
     Map.of(
       AnswerChoice.A, "⌘ + Shift + T",
       AnswerChoice.B, "⌘ + Spacebar, then type \"terminal\"",
       AnswerChoice.C, "⌘ + Q",
       AnswerChoice.D, "⌘ + S, then type \"terminal\""
     ),
       AnswerChoice.B // Replace `UNANSWERED` with the correct answer.
   );
 }
}