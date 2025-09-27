package com.codedifferently.lesson17.bank.exceptions;

/** Exception thrown when a check is voided and cannot be deposited. */
public class CheckVoidedException extends RuntimeException {

  /**
   * Creates a new CheckVoidedException with the specified message.
   *
   * @param message The exception message.
   */
  public CheckVoidedException(String message) {
    super(message);
  }

  /**
   * Creates a new CheckVoidedException with the specified message and cause.
   *
   * @param message The exception message.
   * @param cause The cause of the exception.
   */
  public CheckVoidedException(String message, Throwable cause) {
    super(message, cause);
  }
}
