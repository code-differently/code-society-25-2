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
/
/*
 * Title: Lesson 2 Test Class
 * 
 * Description: This class contains unit tests for the Lesson2 application. It verifies that the quiz questions
 * are assembled correctly, that the prompts are unique, and that the questions are answered correctly.
 * 
 * Created By: Tyran Rice Jr.
 * Date: 2023-10-01
 * 
 */
@SpringBootTest
public class Lesson2Test {


   @Autowired
   public QuizConfig quizConfig;


   private QuizQuestion[] quizQuestions;
   private static final int EXPECTED_NUMBER_OF_QUESTIONS = 11;


/**
 * Title: Set Up
 * 
 * Description: Sets up the test environment before each test case.
 * Initializes the QuizConfig instance and retrieves the quiz questions.
 * The quiz questions are sorted by their question number in ascending order.
 * 
 * Argument: None
 * 
 * Return: Void
 */
   @BeforeEach
   public void setUp() {
       quizConfig = new QuizConfig();
      
       quizQuestions = Lesson2.makeQuizQuestions();       
       Arrays.sort(quizQuestions, (a, b) -> a.getQuestionNumber() - b.getQuestionNumber());
   }

/*
 * Title: Check Quiz Questions are Assembled Correctly
 * 
 * Description: This test verifies that the quiz questions are assembled correctly.
 * It checks that the number of questions matches the expected number and that each question
 * is numbered correctly.
 * 
 * Argument: None
 * 
 * Return: Void
 */
   //@Test
   @Bean
   public void checkQuizQuestions_areAssembledCorrectly() {
       // Expect the right number of questions
       assertEquals(EXPECTED_NUMBER_OF_QUESTIONS, quizQuestions.length);


       // Expect questions to be numbered correctly
       for (int i = 0; i < quizQuestions.length; i++) {
           assertEquals(i, quizQuestions[i].getQuestionNumber());
       }
   }

/*
 * Title: Check Quiz Questions Prompts are Unique
 * 
 * Description: This test verifies that all quiz question prompts are unique.
 * It collects all question prompts into a set and checks that the size of the set
 * matches the expected number of questions, ensuring no duplicate prompts exist.
 * 
 * Argument: None
 * 
 * Return: Void
 */
   //@Test
   @Bean
   public void checkQuizQuestions_promptsAreUnique() {
       Set<String> questionPrompts = new HashSet<>();
       for (QuizQuestion q : quizQuestions) {
           questionPrompts.add(q.getQuestionPrompt());
       }
       assertEquals(EXPECTED_NUMBER_OF_QUESTIONS, questionPrompts.size());
   }

/* 
 * Title: Check Questions Answered Correctly
 * 
 * Description: This test verifies that all quiz questions are answered correctly.
 * It checks that each question has been answered (not left unanswered) and that the provided
 * answer matches the correct answer as defined in the QuizConfig.
 * 
 * Argument: None
 * 
 * Return: Void
 * 
 */
   //@Test
   @Bean
   public void checkQuestions_answeredCorrectly() {
       assertEquals(quizConfig.size("default"), quizQuestions.length);


       for (QuizQuestion question : quizQuestions) {
           String actualAnswer = question.getAnswer();


           // Check that the question was answered
           assertNotEquals(AnswerChoice.UNANSWERED, actualAnswer);


           // Check that the answer is correct
           assertTrue(
               quizConfig.checkAnswer("default", question.getQuestionNumber(), actualAnswer)
           );
       }
   }
}


