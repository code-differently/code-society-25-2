package com.codedifferently.lesson16.devynbenson;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

/** Test class for InvalidStatException */
public class InvalidStatExceptionTest {

  @Test
  public void testConstructorWithMessage() {
    String message = "Test error message";
    InvalidStatException exception = new InvalidStatException(message);

    assertEquals(message, exception.getMessage());
    assertNull(exception.getCause());
  }

  @Test
  public void testConstructorWithMessageAndCause() {
    String message = "Test error message";
    Throwable cause = new RuntimeException("Root cause");

    InvalidStatException exception = new InvalidStatException(message, cause);

    assertEquals(message, exception.getMessage());
    assertEquals(cause, exception.getCause());
  }

  @Test
  public void testInheritanceFromException() {
    InvalidStatException exception = new InvalidStatException("Test");

    // Verify it's an Exception
    assertEquals(Exception.class, exception.getClass().getSuperclass());
  }
}
