package com.codedifferently.lesson16.devynbenson;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Test class for BasketballDemo */
public class BasketballDemoTest {

  private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;

  @BeforeEach
  public void setUpStreams() {
    System.setOut(new PrintStream(outputStream));
  }

  @AfterEach
  public void restoreStreams() {
    System.setOut(originalOut);
  }

  @Test
  public void testMainMethodExecution() {
    // Test that the main method runs without throwing exceptions
    assertDoesNotThrow(
        () -> {
          BasketballDemo.main(new String[] {});
        });

    // Verify that some expected output was produced
    String output = outputStream.toString();
    assert (output.contains("NBA Basketball Demo"));
    assert (output.contains("Spalding"));
    assert (output.contains("Wilson"));
    assert (output.contains("Performance ratings"));
    assert (output.contains("Players Report"));
  }

  @Test
  public void testMainMethodWithArguments() {
    // Test that the main method handles arguments gracefully
    assertDoesNotThrow(
        () -> {
          BasketballDemo.main(new String[] {"arg1", "arg2"});
        });
  }

  @Test
  public void testMainMethodWithNullArguments() {
    // Test that the main method handles null arguments
    assertDoesNotThrow(
        () -> {
          BasketballDemo.main(null);
        });
  }
}
