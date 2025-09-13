package com.codedifferently.lesson16.devynbenson;

/** Custom exception for invalid basketball statistics */
public class InvalidStatException extends Exception {

  public InvalidStatException(String message) {
    super(message);
  }

  public InvalidStatException(String message, Throwable cause) {
    super(message, cause);
  }
}
