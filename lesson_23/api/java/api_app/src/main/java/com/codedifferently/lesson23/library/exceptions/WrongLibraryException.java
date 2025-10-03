package com.codedifferently.lesson23.library.exceptions;

public class WrongLibraryException extends RuntimeException {

  public WrongLibraryException(String message) {
    super(message);
  }
}
