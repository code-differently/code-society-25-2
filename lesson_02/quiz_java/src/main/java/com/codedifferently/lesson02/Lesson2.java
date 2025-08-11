package com.codedifferently.lesson02;

import java.util.List;
import java.util.Map;

import com.codedifferently.instructional.quiz.AnswerChoice;
import com.codedifferently.instructional.quiz.MultipleChoiceQuizQuestion;
import com.codedifferently.instructional.quiz.QuizPrinter;
import com.codedifferently.instructional.quiz.QuizQuestion;

public class Lesson2 {

    public static void main(String[] args) {
        new Lesson2().run();
    }

    public void run() {
        List<QuizQuestion> quizQuestions = makeQuizQuestions();
        if (quizQuestions == null) {
            throw new IllegalStateException("Quiz questions cannot be null");
        }
        QuizPrinter printer = new QuizPrinter();
        printer.printQuiz(quizQuestions);
    }

    public static List<QuizQuestion> makeQuizQuestions() {
        return List.of(
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
                makeQuestion10());
    }

    private static QuizQuestion makeQuestion0() {
        return new MultipleChoiceQuizQuestion(
                0,
                "What is the main purpose of version control?",
                Map.of(
                        AnswerChoice.A, "To make backups of files",
                        AnswerChoice.B, "To keep a record of changes over time",
                        AnswerChoice.C, "To delete unnecessary files",
                        AnswerChoice.D, "To run code more efficiently"),
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
                        AnswerChoice.D, "A way to delete a repository"),
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
                        AnswerChoice.D, "Write code directly in GitHub"),
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
                        AnswerChoice.D, "git pull"),
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
                        AnswerChoice.D, "VS Code"),
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
                        AnswerChoice.D, "Source Control"),
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
                        AnswerChoice.D, "Managing source control"),
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
                        AnswerChoice.D, "mkdir"),
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
                        AnswerChoice.D, "mkdir"),
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
                        AnswerChoice.D, "Copy a file or directory"),
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
                        AnswerChoice.D, "⌘ + S, then type \"terminal\""),
                AnswerChoice.B // Replace `UNANSWERED` with the correct answer.
        );
    }
}
