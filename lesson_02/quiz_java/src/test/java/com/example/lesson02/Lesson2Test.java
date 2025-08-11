package com.example.lesson02;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.codedifferently.instructional.quiz.QuizQuestion;

public class Lesson2Test {
    @Test
    void answersAreProvided() {
        var questions = Lesson2.quiz();
        assertFalse(questions.isEmpty(), "Questions should not be empty");
        for (QuizQuestion q : questions) {
            assertNotNull(q.getId(), "id is null");
            assertNotNull(q.getPrompt(), "prompt is null");
            assertNotNull(q.getAnswer(), "answer is null");
            assertNotEquals("UNANSWERED", q.getAnswer(), "Found UNANSWERED for " + q.getId());
        }
    }
}
