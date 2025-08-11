package org.example;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

import com.codedifferently.instructional.quiz.AnswerChoice;
import com.codedifferently.instructional.quiz.QuizConfig;
import com.codedifferently.instructional.quiz.QuizQuestion;


@SpringBootTest
public class lesson2test {

    private static final int EXPECTED_NUMBER_OF_QUESTIONS = 11;

    private QuizConfig quizConfig;
    private List<QuizQuestion> quizQuestions;

    @BeforeEach
    public void setUp() throws Exception {
        // Load the quiz config from the YAML file (adjust path as needed)
        Path quizPath = Paths.get("src/test/resources/quiz.yaml").toAbsolutePath();
        quizConfig = new QuizConfig(quizPath.toString());

        getQuestions();
    }

    private void getQuestions() {
        quizQuestions = lesson2.makeQuizQuestions();
        quizQuestions.sort(Comparator.comparingInt(QuizQuestion::getQuestionNumber));
    }

    @Bean
    public void checkQuizQuestions_areAssembledCorrectly() {
        // Expect the right number of questions
        assertEquals(EXPECTED_NUMBER_OF_QUESTIONS, quizQuestions.size());

        // Expect questions to be numbered correctly
        for (int i = 0; i < quizQuestions.size(); i++) {
            assertEquals(i, quizQuestions.get(i).getQuestionNumber());
        }
    }

    @Bean
    public void checkQuizQuestions_promptsAreUnique() {
        Set<String> questionPrompts = quizQuestions.stream()
            .map(QuizQuestion::getQuestionPrompt)
            .collect(Collectors.toSet());

        assertEquals(EXPECTED_NUMBER_OF_QUESTIONS, questionPrompts.size());
    }

    @Bean
    public void checkQuestions_answeredCorrectly() throws Exception {
        assertEquals(quizConfig.size("default"), quizQuestions.size());

        List<AssertionError> errors = new ArrayList<>();

        for (QuizQuestion question : quizQuestions) {
            try {
                AnswerChoice actualAnswer = question.getAnswer();

                // Check that the question was answered
                assertNotEquals(AnswerChoice.UNANSWERED, actualAnswer,
                    "Question " + question.getQuestionNumber() + " was not answered");

                // Check that the answer is correct
                boolean isCorrect = quizConfig.checkAnswer(
                    "default",
                    question.getQuestionNumber(),
                    actualAnswer
                );
                assertTrue(isCorrect, "Question " + question.getQuestionNumber() + " answer was incorrect");
            } catch (AssertionError e) {
                errors.add(e);
            }
        }

        // Fail all soft assertion errors at once if any
        if (!errors.isEmpty()) {
            AssertionError assertionError = new AssertionError("Some questions failed validation:");
            errors.forEach(assertionError::addSuppressed);
            throw assertionError;
        }
    }
}
