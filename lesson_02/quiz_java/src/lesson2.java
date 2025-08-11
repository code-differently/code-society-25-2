import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.codedifferently.instructional.quiz.AnswerChoice;
import com.codedifferently.instructional.quiz.MultipleChoiceQuizQuestion;
import com.codedifferently.instructional.quiz.QuizPrinter;
import com.codedifferently.instructional.quiz.QuizQuestion;

public class lesson2 {

    public static ArrayList<QuizQuestion> makeQuizQuestions() {
        ArrayList<QuizQuestion> quizQuestions = new ArrayList<>();
        quizQuestions.add(lesson2.makeQuestion0());
        quizQuestions.add(lesson2.makeQuestion1());
        quizQuestions.add(lesson2.makeQuestion2());
        quizQuestions.add(lesson2.makeQuestion3());
        quizQuestions.add(lesson2.makeQuestion4());
        quizQuestions.add(lesson2.makeQuestion5());
        quizQuestions.add(lesson2.makeQuestion6());
        quizQuestions.add(lesson2.makeQuestion7());
        quizQuestions.add(lesson2.makeQuestion8());
        quizQuestions.add(lesson2.makeQuestion9());
        quizQuestions.add(lesson2.makeQuestion10());
        return quizQuestions;
    }

    public static void main(String[] args) {
        ArrayList<QuizQuestion> quizQuestions = lesson2.makeQuizQuestions();
        if (quizQuestions == null || quizQuestions.isEmpty()) {
            throw new Error("Quiz questions cannot be null or empty");
        }
        final QuizPrinter printer = new QuizPrinter();
        printer.printQuiz(quizQuestions);
    }

    private static QuizQuestion makeQuestion0(){
        Map<AnswerChoice, String> answers = new HashMap();
        answers.put(AnswerChoice.A, "To make backups of files");
        answers.put(AnswerChoice.B, "To keep a record of changes over time");
        answers.put(AnswerChoice.C, "To delete unnecessary files");
        answers.put(AnswerChoice.D, "To run code more efficiently");

        return new MultipleChoiceQuizQuestion(
            0,
            "What is the main purpose of version control?",
            answers,
            AnswerChoice.B);
    }

    private static QuizQuestion makeQuestion1() {
    Map<AnswerChoice, String> answers = new HashMap<>();
    answers.put(AnswerChoice.A, "A duplicate copy of a repository that you own and modify");
    answers.put(AnswerChoice.B, "A temporary backup of the code");
    answers.put(AnswerChoice.C, "A tool for merging branches");
    answers.put(AnswerChoice.D, "A way to delete a repository");

    return new MultipleChoiceQuizQuestion(
        1,
        "What is a fork in Git?",
        answers,
        AnswerChoice.A
    );
}

private static QuizQuestion makeQuestion2() {
    Map<AnswerChoice, String> answers = new HashMap<>();
    answers.put(AnswerChoice.A, "Pull the latest changes");
    answers.put(AnswerChoice.B, "Commit changes locally");
    answers.put(AnswerChoice.C, "Push changes to the server");
    answers.put(AnswerChoice.D, "Write code directly in GitHub");

    return new MultipleChoiceQuizQuestion(
        2,
        "Which of the following is NOT part of the basic Git workflow?",
        answers,
        AnswerChoice.D
    );
}

private static QuizQuestion makeQuestion3() {
    Map<AnswerChoice, String> answers = new HashMap<>();
    answers.put(AnswerChoice.A, "git commit");
    answers.put(AnswerChoice.B, "git merge");
    answers.put(AnswerChoice.C, "git branch");
    answers.put(AnswerChoice.D, "git pull");

    return new MultipleChoiceQuizQuestion(
        3,
        "What command is used to combine changes from different branches?",
        answers,
        AnswerChoice.B
    );
}

private static QuizQuestion makeQuestion4() {
    Map<AnswerChoice, String> answers = new HashMap<>();
    answers.put(AnswerChoice.A, "Eclipse");
    answers.put(AnswerChoice.B, "IntelliJ IDEA");
    answers.put(AnswerChoice.C, "NetBeans");
    answers.put(AnswerChoice.D, "VS Code");

    return new MultipleChoiceQuizQuestion(
        4,
        "Which IDE is being used in the class?",
        answers,
        AnswerChoice.D
    );
}

private static QuizQuestion makeQuestion5() {
    Map<AnswerChoice, String> answers = new HashMap<>();
    answers.put(AnswerChoice.A, "Extensions");
    answers.put(AnswerChoice.B, "Debugger");
    answers.put(AnswerChoice.C, "Dev Containers");
    answers.put(AnswerChoice.D, "Source Control");

    return new MultipleChoiceQuizQuestion(
        5,
        "What feature allows developers to work from the same pre-configured environment in VS Code?",
        answers,
        AnswerChoice.C
    );
}

private static QuizQuestion makeQuestion6() {
    Map<AnswerChoice, String> answers = new HashMap<>();
    answers.put(AnswerChoice.A, "Editing and refactoring code");
    answers.put(AnswerChoice.B, "Browsing code");
    answers.put(AnswerChoice.C, "Playing music");
    answers.put(AnswerChoice.D, "Managing source control");

    return new MultipleChoiceQuizQuestion(
        6,
        "What is NOT a reason for using an IDE?",
        answers,
        AnswerChoice.C
    );
}

private static QuizQuestion makeQuestion7() {
    Map<AnswerChoice, String> answers = new HashMap<>();
    answers.put(AnswerChoice.A, "pwd");
    answers.put(AnswerChoice.B, "ls");
    answers.put(AnswerChoice.C, "cd");
    answers.put(AnswerChoice.D, "mkdir");

    return new MultipleChoiceQuizQuestion(
        7,
        "What is the command to list files in the current directory?",
        answers,
        AnswerChoice.B
    );
}

private static QuizQuestion makeQuestion8() {
    Map<AnswerChoice, String> answers = new HashMap<>();
    answers.put(AnswerChoice.A, "pwd");
    answers.put(AnswerChoice.B, "ls");
    answers.put(AnswerChoice.C, "cd");
    answers.put(AnswerChoice.D, "mkdir");

    return new MultipleChoiceQuizQuestion(
        8,
        "Which command is used to change directories in the terminal?",
        answers,
        AnswerChoice.C
    );
}

private static QuizQuestion makeQuestion9() {
    Map<AnswerChoice, String> answers = new HashMap<>();
    answers.put(AnswerChoice.A, "Change file or directory permissions");
    answers.put(AnswerChoice.B, "List files in a directory");
    answers.put(AnswerChoice.C, "Remove a file or directory");
    answers.put(AnswerChoice.D, "Copy a file or directory");

    return new MultipleChoiceQuizQuestion(
        9,
        "What does the command `chmod` do?",
        answers,
        AnswerChoice.A
    );
}

private static QuizQuestion makeQuestion10() {
    Map<AnswerChoice, String> answers = new HashMap<>();
    answers.put(AnswerChoice.A, "⌘ + Shift + T");
    answers.put(AnswerChoice.B, "⌘ + Spacebar, then type \"terminal\"");
    answers.put(AnswerChoice.C, "⌘ + Q");
    answers.put(AnswerChoice.D, "⌘ + S, then type \"terminal\"");

    return new MultipleChoiceQuizQuestion(
        10,
        "What is the shortcut for getting to the Mac terminal?",
        answers,
        AnswerChoice.B
    );
}

}