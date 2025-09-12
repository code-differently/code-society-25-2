package com.codedifferently.lesson16.kerryferguson;

/** Custom exception for invalid time operations in the Clock class. */
public class InvalidTimeException extends Exception {

  /**
   * Creates a new InvalidTimeException with a message.
   *
   * @param message The error message
   */
  public InvalidTimeException(String message) {
    super(message);
  }
}
