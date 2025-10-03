package com.codedifferently.lesson23.library.exceptions;

public class MediaItemCheckedOutException extends RuntimeException {

  public MediaItemCheckedOutException(String message) {
    super(message);
  }
}
