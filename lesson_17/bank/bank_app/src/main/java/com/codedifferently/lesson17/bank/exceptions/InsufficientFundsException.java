package com.codedifferently.lesson17.bank.exceptions;

/** Exception thrown when insufficient funds are available for a withdrawal. */
public class InsufficientFundsException extends Exception {

  /**
   * Creates a new InsufficientFundsException with the specified message.
   *
   * @param message The exception message.
   */
  public InsufficientFundsException(String message) {
    super(message);
  }

  /**
   * Creates a new InsufficientFundsException with the specified message and cause.
   *
   * @param message The exception message.
   * @param cause The cause of the exception.
   */
  public InsufficientFundsException(String message, Throwable cause) {
    super(message, cause);
  }
}
