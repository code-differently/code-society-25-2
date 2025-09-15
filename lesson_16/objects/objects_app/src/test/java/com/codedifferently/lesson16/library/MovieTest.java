package com.codedifferently.lesson16.library;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
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

  // Additional tests to improve code coverage

  @Test
  public void testGetGenreDisplayEmpty() {
    // Test the empty genres case (loop not executed)
    String display = testMovie.getGenreDisplay();
    assertEquals("No genres assigned", display);
  }

  @Test
  public void testGetGenreDisplaySingleGenre() throws MovieException {
    // Test loop with only one iteration
    testMovie.addGenre("Action");
    String display = testMovie.getGenreDisplay();
    assertEquals("Genres: Action", display);
  }

  @Test
  public void testAddDuplicateGenre() throws MovieException {
    // Test the duplicate check in addGenre
    testMovie.addGenre("Action");
    testMovie.addGenre("Action"); // Should not add duplicate

    assertEquals(1, testMovie.getGenres().size());
    assertTrue(testMovie.getGenres().contains("Action"));
  }

  @Test
  public void testAddGenreWithNullThrowsException() {
    // Test null genre throws exception
    Exception exception = assertThrows(MovieException.class, () -> testMovie.addGenre(null));
    assertEquals("Genre cannot be null or empty", exception.getMessage());
  }

  @Test
  public void testAddGenreWithWhitespaceThrowsException() {
    // Test whitespace-only genre throws exception
    Exception exception = assertThrows(MovieException.class, () -> testMovie.addGenre("   "));
    assertEquals("Genre cannot be null or empty", exception.getMessage());
  }

  @Test
  public void testGetGenresReturnsCopy() throws MovieException {
    // Test that getGenres returns a defensive copy
    testMovie.addGenre("Action");
    List<String> genres1 = testMovie.getGenres();
    List<String> genres2 = testMovie.getGenres();

    // They should contain the same elements but be different objects
    assertEquals(genres1, genres2);
    assertNotSame(genres1, genres2); // Different object references
  }

  @Test
  public void testAllGetterMethods() {
    // Make sure all getters are called at least once
    assertNotNull(testMovie.getTitle());
    assertNotNull(testMovie.getDirector());
    assertTrue(testMovie.getDuration() > 0);
    assertTrue(testMovie.getPrice() > 0);
    assertTrue(testMovie.isAvailable()); // This calls the boolean getter
  }
}
