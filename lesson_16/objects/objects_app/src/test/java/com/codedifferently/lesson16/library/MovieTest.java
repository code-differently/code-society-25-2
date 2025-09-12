package com.codedifferently.lesson16.library;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MovieTest {

  private Movie testMovie;

  @BeforeEach
  public void setUp() {
    // Create a test movie before each test
    testMovie = new Movie("Inception", "Christopher Nolan", 148, 14.99);
  }

  @Test
  public void testConstructor() {
    // Test 1: Constructor sets values correctly
    assertEquals("Inception", testMovie.getTitle());
    assertEquals("Christopher Nolan", testMovie.getDirector());
    assertEquals(148, testMovie.getDuration());
    assertEquals(14.99, testMovie.getPrice());
    assertTrue(testMovie.isAvailable());
  }

  @Test
  public void testAvailabilityInfo() {
    // Test 2: Conditional expression works correctly
    String info = testMovie.getAvailabilityInfo();
    assertEquals("This movie is available for checkout", info);
  }

  @Test
  public void testAddGenre() throws MovieException {
    // Test 3: Collection usage works correctly
    testMovie.addGenre("Fiction");
    testMovie.addGenre("Classic");

    assertEquals(2, testMovie.getGenres().size());
    assertTrue(testMovie.getGenres().contains("Fiction"));
    assertTrue(testMovie.getGenres().contains("Classic"));
  }

  @Test
  public void testGenreDisplay() throws MovieException {
    // Test 4: Loop functionality works correctly
    testMovie.addGenre("Fiction");
    testMovie.addGenre("Drama");

    String display = testMovie.getGenreDisplay();
    assertEquals("Genres: Fiction, Drama", display);
  }

  @Test
  public void testMovieException() {
    // Test 5: Custom exception works correctly
    Exception exception =
        assertThrows(
            MovieException.class,
            () -> {
              testMovie.addGenre("");
            });

    assertEquals("Genre cannot be null or empty", exception.getMessage());
  }
}
