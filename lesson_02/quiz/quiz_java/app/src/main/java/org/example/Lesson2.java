/**
 * Lesson2 Quiz Generator
 * 
 * This class provides a comprehensive quiz covering fundamental programming concepts
 * including version control (Git), Integrated Development Environments (IDEs),
 * and command line interface (CLI) operations. The quiz consists of 11 multiple-choice
 * questions designed to test understanding of basic development tools and workflows.
 * 
 * Topics covered:
 * - Git version control system and workflows
 * - IDE features and usage (specifically VS Code)
 * - Command line operations and file permissions
 * - Development environment setup
 * 
 */

package org.example;

import java.util.HashMap;
import java.util.Map;

import com.codedifferently.instructional.quiz.AnswerChoice;
import com.codedifferently.instructional.quiz.MultipleChoiceQuizQuestion;
import com.codedifferently.instructional.quiz.QuizQuestion;

public class Lesson2 {
   
 /**
     * Creates Question 0: Version Control Purpose
     * 
     * Tests understanding of the main purpose of version control systems.
     * 
     * @return A MultipleChoiceQuizQuestion about version control fundamentals
     */
    private static QuizQuestion makeQuestion0() {
        Map<AnswerChoice, String> answerOptions = new HashMap<>();
        answerOptions.put(AnswerChoice.A, "To make backups of files");
        answerOptions.put(AnswerChoice.B, "To keep a record of changes over time");
        answerOptions.put(AnswerChoice.C, "To delete unnecessary files");
        answerOptions.put(AnswerChoice.D, "To run code more efficiently");
        
        return new MultipleChoiceQuizQuestion(
            0,
            "What is the main purpose of version control?",
            answerOptions,
            AnswerChoice.B // The correct answer
        );
    }

    /**
     * Creates Question 1: Git Fork Concept
     * 
     * Tests understanding of what a fork means in Git version control.
     * 
     * @return A MultipleChoiceQuizQuestion about Git forking
     */
    private static QuizQuestion makeQuestion1() {
        Map<AnswerChoice, String> answerOptions = new HashMap<>();
        answerOptions.put(AnswerChoice.A, "A duplicate copy of a repository that you own and modify");
        answerOptions.put(AnswerChoice.B, "A temporary backup of the code");
        answerOptions.put(AnswerChoice.C, "A tool for merging branches");
        answerOptions.put(AnswerChoice.D, "A way to delete a repository");
        
        return new MultipleChoiceQuizQuestion(
            1,
            "What is a fork in Git?",
            answerOptions,
            AnswerChoice.A // The correct answer
        );
    }

    /**
     * Creates Question 2: Git Workflow Components
     * 
     * Tests knowledge of what is NOT part of the basic Git workflow.
     * 
     * @return A MultipleChoiceQuizQuestion about Git workflow steps
     */
    private static QuizQuestion makeQuestion2() {
        Map<AnswerChoice, String> answerOptions = new HashMap<>();
        answerOptions.put(AnswerChoice.A, "Pull the latest changes");
        answerOptions.put(AnswerChoice.B, "Commit changes locally");
        answerOptions.put(AnswerChoice.C, "Push changes to the server");
        answerOptions.put(AnswerChoice.D, "Write code directly in GitHub");
        
        return new MultipleChoiceQuizQuestion(
            2,
            "Which of the following is NOT part of the basic Git workflow?",
            answerOptions,
            AnswerChoice.D // The correct answer
        );
    }

    /**
     * Creates Question 3: Git Branch Merging
     * 
     * Tests knowledge of Git commands for combining changes from different branches.
     * 
     * @return A MultipleChoiceQuizQuestion about Git merge commands
     */
    private static QuizQuestion makeQuestion3() {
        Map<AnswerChoice, String> answerOptions = new HashMap<>();
        answerOptions.put(AnswerChoice.A, "git commit");
        answerOptions.put(AnswerChoice.B, "git merge");
        answerOptions.put(AnswerChoice.C, "git branch");
        answerOptions.put(AnswerChoice.D, "git pull");
        
        return new MultipleChoiceQuizQuestion(
            3,
            "What command is used to combine changes from different branches?",
            answerOptions,
            AnswerChoice.B // The correct answer
        );
    }

    /**
     * Creates Question 4: IDE Selection
     * 
     * Tests knowledge of which IDE is being used in the class.
     * 
     * @return A MultipleChoiceQuizQuestion about the class IDE
     */
    private static QuizQuestion makeQuestion4() {
        Map<AnswerChoice, String> answerOptions = new HashMap<>();
        answerOptions.put(AnswerChoice.A, "Eclipse");
        answerOptions.put(AnswerChoice.B, "IntelliJ IDEA");
        answerOptions.put(AnswerChoice.C, "NetBeans");
        answerOptions.put(AnswerChoice.D, "VS Code");
        
        return new MultipleChoiceQuizQuestion(
            4,
            "Which IDE is being used in the class?",
            answerOptions,
            AnswerChoice.D // The correct answer
        );
    }

    /**
     * Creates Question 5: VS Code Dev Containers
     * 
     * Tests understanding of VS Code features for consistent development environments.
     * 
     * @return A MultipleChoiceQuizQuestion about VS Code Dev Containers
     */
    private static QuizQuestion makeQuestion5() {
        Map<AnswerChoice, String> answerOptions = new HashMap<>();
        answerOptions.put(AnswerChoice.A, "Extensions");
        answerOptions.put(AnswerChoice.B, "Debugger");
        answerOptions.put(AnswerChoice.C, "Dev Containers");
        answerOptions.put(AnswerChoice.D, "Source Control");
        
        return new MultipleChoiceQuizQuestion(
            5,
            "What feature allows developers to work from the same pre-configured environment in VS Code?",
            answerOptions,
            AnswerChoice.C // The correct answer
        );
    }

    /**
     * Creates Question 6: IDE Usage Purposes
     * 
     * Tests understanding of what is NOT a typical reason for using an IDE.
     * 
     * @return A MultipleChoiceQuizQuestion about IDE purposes
     */
    private static QuizQuestion makeQuestion6() {
        Map<AnswerChoice, String> answerOptions = new HashMap<>();
        answerOptions.put(AnswerChoice.A, "Editing and refactoring code");
        answerOptions.put(AnswerChoice.B, "Browsing code");
        answerOptions.put(AnswerChoice.C, "Playing music");
        answerOptions.put(AnswerChoice.D, "Managing source control");
        
        return new MultipleChoiceQuizQuestion(
            6,
            "What is NOT a reason for using an IDE?",
            answerOptions,
            AnswerChoice.C // The correct answer
        );
    }

    /**
     * Creates Question 7: Listing Files in CLI
     * 
     * Tests knowledge of the command to list files in the current directory.
     * 
     * @return A MultipleChoiceQuizQuestion about CLI file listing commands
     */
    private static QuizQuestion makeQuestion7() {
        Map<AnswerChoice, String> answerOptions = new HashMap<>();
        answerOptions.put(AnswerChoice.A, "pwd");
        answerOptions.put(AnswerChoice.B, "ls");
        answerOptions.put(AnswerChoice.C, "cd");
        answerOptions.put(AnswerChoice.D, "mkdir");
        
        return new MultipleChoiceQuizQuestion(
            7,
            "What is the command to list files in the current directory?",
            answerOptions,
            AnswerChoice.B // The correct answer
        );
    }
    /**
     * Creates Question 8: Changing Directories in CLI
     * 
     * Tests knowledge of the command to change directories in the terminal.
     * 
     * @return A MultipleChoiceQuizQuestion about CLI directory commands
     */
    private static QuizQuestion makeQuestion8() {
        Map<AnswerChoice, String> answerOptions = new HashMap<>();
        answerOptions.put(AnswerChoice.A, "pwd");
        answerOptions.put(AnswerChoice.B, "ls");
        answerOptions.put(AnswerChoice.C, "cd");
        answerOptions.put(AnswerChoice.D, "mkdir");
        
        return new MultipleChoiceQuizQuestion(
            8,
            "Which command is used to change directories in the terminal?",
            answerOptions,
            AnswerChoice.C // The correct answer
        );
    }
    /**
     * Creates Question 9: File Permissions Command
     * 
     * Tests knowledge of the command used to change file or directory permissions.
     * 
     * @return A MultipleChoiceQuizQuestion about file permissions commands
     */
    private static QuizQuestion makeQuestion9() {
        Map<AnswerChoice, String> answerOptions = new HashMap<>();
        answerOptions.put(AnswerChoice.A, "Change file or directory permissions");
        answerOptions.put(AnswerChoice.B, "List files in a directory");
        answerOptions.put(AnswerChoice.C, "Remove a file or directory");
        answerOptions.put(AnswerChoice.D, "Copy a file or directory");
        
        return new MultipleChoiceQuizQuestion(
            9,
            "What does the command `chmod` do?",
            answerOptions,
            AnswerChoice.A // The correct answer
        );
    }
    /**
     * Creates Question 10: Mac Terminal Shortcut
     * 
     * Tests knowledge of the keyboard shortcut to open the Mac terminal.
     * 
     * @return A MultipleChoiceQuizQuestion about Mac terminal shortcuts
     */
    private static QuizQuestion makeQuestion10() {
        Map<AnswerChoice, String> answerOptions = new HashMap<>();
        answerOptions.put(AnswerChoice.A, "⌘ + Shift + T");
        answerOptions.put(AnswerChoice.B, "⌘ + Spacebar, then type \"terminal\"");
        answerOptions.put(AnswerChoice.C, "⌘ + Q");
        answerOptions.put(AnswerChoice.D, "⌘ + S, then type \"terminal\"");
        
        return new MultipleChoiceQuizQuestion(
            10,
            "What is the shortcut for getting to the Mac terminal?",
            answerOptions,
            AnswerChoice.B // The correct answer
        );
    }
    /**
     * Generates an array of all quiz questions.
     * 
     * @return An array of QuizQuestion objects representing the quiz
     */
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

    /**
     * Displays a quiz question and its answer options.
     * 
     * @param question The QuizQuestion to display
     */
    private static void displayQuestion(QuizQuestion question) {
        System.out.println("Question " + question.getQuestionNumber() + ": " + question.getQuestionPrompt());
        
        if (question instanceof MultipleChoiceQuizQuestion) {
            MultipleChoiceQuizQuestion mcq = (MultipleChoiceQuizQuestion) question;
            System.out.println("A. " + mcq.getAnswerForOption(AnswerChoice.A));
            System.out.println("B. " + mcq.getAnswerForOption(AnswerChoice.B));
            System.out.println("C. " + mcq.getAnswerForOption(AnswerChoice.C));
            System.out.println("D. " + mcq.getAnswerForOption(AnswerChoice.D));
            System.out.println("Correct answer: " + question.getAnswer());
        }
        System.out.println(); // Empty line for spacing
    }
    /**
     * Main method to run the quiz and display all questions.
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("=== Lesson 2 Quiz ===");
        System.out.println();
        
        // Get all quiz questions
        QuizQuestion[] questions = makeQuizQuestions();
        
        // Display all questions
        for (QuizQuestion question : questions) {
            displayQuestion(question);
        }
        
        System.out.println("Quiz completed! Total questions: " + questions.length);
    }
}
