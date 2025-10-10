package com.codedifferently.lesson25.library.exceptions;

public class MediaItemCheckedOutException extends RuntimeException {

  public MediaItemCheckedOutException(String message) {
    super(message);
  }
}
