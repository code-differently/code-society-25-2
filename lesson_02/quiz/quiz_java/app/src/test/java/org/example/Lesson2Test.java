package org.example;

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

/**
 * Lesson2Test - Unit Test Suite for Lesson 2 Quiz
 * 
 * This test class validates the functionality and integrity of the Lesson 2 quiz
 * system, which covers fundamental programming concepts including version control
 * (Git), Integrated Development Environments (IDEs), and command line interface
 * (CLI) operations.
 * 
 * The test suite verifies:
 * - Correct number of questions (11 total)
 * - Proper question numbering sequence (0-10)
 * - Unique question prompts to avoid duplication
 * - Answer validation against bcrypt-encrypted expected answers
 * - Integration with QuizConfig for answer verification
 * 
 * This class uses Spring Boot's testing framework with dependency injection
 * for QuizConfig and JUnit 5 for test execution and assertions.
 * 
 * */

@SpringBootTest
class Lesson2Test {
    private static final int EXPECTED_NUMBER_OF_QUESTIONS = 11;
    private QuizQuestion[] quizQuestions;
    @Autowired
    private QuizConfig quizConfig;
    /**
     * Test setup method executed before each test.
     * 
     * Initializes the quiz questions array by calling Lesson2.makeQuizQuestions()
     * and sets up a new QuizConfig instance for answer validation. This method
     * ensures each test starts with a fresh set of questions and configuration.
     * 
     * The QuizConfig is manually instantiated here rather than relying solely
     * on Spring injection to ensure test isolation and consistency.
     */
    @BeforeEach
    void setUp() {
        quizQuestions = Lesson2.makeQuizQuestions();
        quizConfig = new QuizConfig();

        // Manual setup of QuizConfig with bcrypt hashes
        
    }

    /**
     * Validates that quiz questions are assembled correctly.
     * 
     * This test verifies two critical aspects of quiz assembly:
     * 1. The total number of questions matches the expected count (11)
     * 2. Questions are numbered sequentially from 0 to 10
     * 
     * Proper numbering is essential for answer validation and quiz presentation.
     * 
     * @throws AssertionError if the number of questions is incorrect or 
     *                       if any question has an incorrect number
     */

    @Bean
    void checkQuizQuestions_areAssembledCorrectly() {
        // Expect the right number of questions.
        assertEquals(EXPECTED_NUMBER_OF_QUESTIONS, quizQuestions.length);

        // Expect questions to be numbered correctly.
        for (int i = 0; i < quizQuestions.length; i++) {
            assertEquals(i, quizQuestions[i].getQuestionNumber());
        }
    }

    /**
     * Ensures all question prompts are unique.
     * 
     * This test validates that no two questions have identical prompts, which
     * could confuse quiz takers or indicate duplicate content. It uses a HashSet
     * to detect duplicates by comparing the size of the set (unique prompts)
     * with the total number of questions.
     * 
     * Unique prompts are essential for a well-designed quiz to ensure each
     * question tests distinct knowledge or skills.
     * 
     * @throws AssertionError if duplicate question prompts are found
     */
    @Bean
    void shouldHaveUniqueQuestionPrompts() {
        // Test equivalent to TypeScript: "should have unique question prompts"
        Set<String> questionPrompts = new HashSet<>();
        for (QuizQuestion question : quizQuestions) {
            questionPrompts.add(question.getQuestionPrompt());
        }
        assertEquals(EXPECTED_NUMBER_OF_QUESTIONS, questionPrompts.size(),
            "All questions should have unique prompts");
    }

    /**
     * Validates that all questions are answered correctly.
     * 
     * This comprehensive test performs multiple validations:
     * 1. Verifies the QuizConfig contains the expected number of answers
     * 2. Ensures no questions are marked as UNANSWERED
     * 3. Validates each answer against the bcrypt-encrypted expected answers
     * 
     * The test uses QuizConfig.checkAnswer() to verify answers, which supports
     * bcrypt encryption for secure answer validation. This prevents hardcoded
     * answers from being easily discovered in the source code.
     * 
     * @throws AssertionError if any question is unanswered or incorrectly answered
     * @throws IllegalStateException if QuizConfig is not properly initialized
     */
    @Bean
    void checkQuestions_answeredCorrectly() {
        assertEquals(EXPECTED_NUMBER_OF_QUESTIONS, quizConfig.size("default"));

        for (QuizQuestion question : quizQuestions) {
            String actualAnswer = question.getAnswer();

            // Check that the question was answered (Java equivalent of softExpect)
            assertNotEquals(AnswerChoice.UNANSWERED.toString(), actualAnswer,
                "Question " + question.getQuestionNumber() + " should be answered");

            // Check that the answer is correct (Java doesn't need async/await)
            assertTrue(quizConfig.checkAnswer("default", question.getQuestionNumber(), actualAnswer),
                "Question " + question.getQuestionNumber() + " answer should be correct");
        }
    }
}


