package com.codedifferently.lesson17.bank.exceptions;

/** Exception thrown when an account is not found. */
public class AccountNotFoundException extends RuntimeException {

  /**
   * Creates a new AccountNotFoundException with the specified message.
   *
   * @param message The exception message.
   */
  public AccountNotFoundException(String message) {
    super(message);
  }

  /**
   * Creates a new AccountNotFoundException with the specified message and cause.
   *
   * @param message The exception message.
   * @param cause The cause of the exception.
   */
  public AccountNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
