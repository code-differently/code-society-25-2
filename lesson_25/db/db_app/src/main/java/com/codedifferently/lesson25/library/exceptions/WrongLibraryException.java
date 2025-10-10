package com.codedifferently.lesson25.library.exceptions;

public class WrongLibraryException extends RuntimeException {

  public WrongLibraryException(String message) {
    super(message);
  }
}
