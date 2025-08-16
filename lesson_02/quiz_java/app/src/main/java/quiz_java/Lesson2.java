package quiz_java;

import com.codedifferently.instructional.quiz.*;
import java.util.*;

public class Lesson2 {
    
    public void run() {
        List<QuizQuestion> quizQuestions = makeQuizQuestions();
        if (quizQuestions == null) {
            throw new RuntimeException("Quiz questions cannot be null");
        }
        QuizPrinter printer = new QuizPrinter();
        printer.printQuiz(quizQuestions);
    }
    
    public static List<QuizQuestion> makeQuizQuestions() {
        List<QuizQuestion> questions = new ArrayList<>();
        questions.add(makeQuestion0());
        questions.add(makeQuestion1());
        questions.add(makeQuestion2());
        questions.add(makeQuestion3());
        questions.add(makeQuestion4());
        questions.add(makeQuestion5());
        questions.add(makeQuestion6());
        questions.add(makeQuestion7());
        questions.add(makeQuestion8());
        questions.add(makeQuestion9());
        questions.add(makeQuestion10());
        return questions;
    }
    
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
            AnswerChoice.B
        );
    }
    
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
            AnswerChoice.A
        );
    }
    
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
            AnswerChoice.D
        );
    }
    
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
            AnswerChoice.B
        );
    }
    
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
            AnswerChoice.D
        );
    }
    
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
            AnswerChoice.C
        );
    }
    
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
            AnswerChoice.C
        );
    }
    
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
            AnswerChoice.B
        );
    }
    
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
            AnswerChoice.C
        );
    }
    
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
            AnswerChoice.A
        );
    }
    
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
            AnswerChoice.B
        );
    }
}
