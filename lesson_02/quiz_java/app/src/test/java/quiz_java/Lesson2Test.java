package quiz_java;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.codedifferently.instructional.quiz.*;
import java.util.List;

public class Lesson2Test {

    @Test
    public void testMakeQuizQuestionsReturnsCorrectNumberOfQuestions() {
        List<QuizQuestion> questions = Lesson2.makeQuizQuestions();
        assertEquals(11, questions.size());
    }

    @Test
    public void testQuestionNumbersAreCorrect() {
        List<QuizQuestion> questions = Lesson2.makeQuizQuestions();
        for (int i = 0; i < questions.size(); i++) {
            assertEquals(i, questions.get(i).getQuestionNumber());
        }
    }

    @Test
    public void testAllQuestionsAreMultipleChoice() {
        List<QuizQuestion> questions = Lesson2.makeQuizQuestions();
        for (QuizQuestion question : questions) {
            assertTrue(question instanceof MultipleChoiceQuizQuestion);
        }
    }

    @Test
    public void testQuestionZeroAnswer() {
        List<QuizQuestion> questions = Lesson2.makeQuizQuestions();
        QuizQuestion question0 = questions.get(0);
        assertEquals("What is the main purpose of version control?", question0.getQuestionPrompt());
        assertEquals("B", question0.getAnswer());
    }

    @Test
    public void testQuestionOneAnswer() {
        List<QuizQuestion> questions = Lesson2.makeQuizQuestions();
        QuizQuestion question1 = questions.get(1);
        assertEquals("What is a fork in Git?", question1.getQuestionPrompt());
        assertEquals("A", question1.getAnswer());
    }

    @Test
    public void testQuestionTwoAnswer() {
        List<QuizQuestion> questions = Lesson2.makeQuizQuestions();
        QuizQuestion question2 = questions.get(2);
        assertEquals("Which of the following is NOT part of the basic Git workflow?", question2.getQuestionPrompt());
        assertEquals("D", question2.getAnswer());
    }

    @Test
    public void testRunDoesNotThrowException() {
        assertDoesNotThrow(() -> {
            new Lesson2().run();
        });
    }
}
