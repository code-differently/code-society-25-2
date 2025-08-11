package com.codedifferently.lesson02;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.codedifferently.instructional.quiz.AnswerChoice;
import com.codedifferently.instructional.quiz.QuizQuestion;

public class Lesson2Test {

    private SimpleQuizConfig quizConfig;
    private List<QuizQuestion> quizQuestions;

    private static final int EXPECTED_NUMBER_OF_QUESTIONS = 11;

    @BeforeEach
    void setUp() {
        quizConfig = new SimpleQuizConfig("quiz.yaml");
        getQuestions();
    }

    private void getQuestions() {
        List<QuizQuestion> originalQuestions = Lesson2.makeQuizQuestions();
        quizQuestions = new java.util.ArrayList<>(originalQuestions);
        quizQuestions.sort((a, b) -> Integer.compare(a.getQuestionNumber(), b.getQuestionNumber()));
    }

    @Test
    void checkQuizQuestions_areAssembledCorrectly() {
        // Expect the right number of questions.
        assertEquals(EXPECTED_NUMBER_OF_QUESTIONS, quizQuestions.size());

        // Expect questions to be numbered correctly.
        for (int i = 0; i < quizQuestions.size(); i++) {
            assertEquals(i, quizQuestions.get(i).getQuestionNumber());
        }
    }

    @Test
    void checkQuizQuestions_promptsAreUnique() {
        Set<String> questionPrompts = new HashSet<>();
        for (QuizQuestion question : quizQuestions) {
            questionPrompts.add(question.getQuestionPrompt());
        }
        assertEquals(EXPECTED_NUMBER_OF_QUESTIONS, questionPrompts.size());
    }

    @Test
    void checkQuestions_answeredCorrectly() {
        assertEquals(quizQuestions.size(), quizConfig.size("default"));

        for (QuizQuestion question : quizQuestions) {
            String actualAnswer = question.getAnswer();

            // Check that the question was answered.
            assertNotEquals(AnswerChoice.UNANSWERED.toString(), actualAnswer,
                    "Question " + question.getQuestionNumber() + " was not answered");

            // Check that the answer is correct.
            assertTrue(
                    quizConfig.checkAnswer("default", question.getQuestionNumber(), actualAnswer),
                    "Question " + question.getQuestionNumber() + " was answered incorrectly. "
                    + "Expected answer does not match provided answer: " + actualAnswer
            );
        }
    }
}
