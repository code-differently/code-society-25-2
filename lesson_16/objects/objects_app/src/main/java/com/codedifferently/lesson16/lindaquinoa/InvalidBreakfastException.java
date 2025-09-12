package com.codedifferently.lesson16.lindaquinoa;

/** Custom exception for invalid breakfast food operations */
public class InvalidBreakfastException extends Exception {

  public InvalidBreakfastException(String message) {
    super(message);
  }

  public InvalidBreakfastException(String message, Throwable cause) {
    super(message, cause);
  }
}

