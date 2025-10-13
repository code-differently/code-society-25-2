package com.codedifferently.lesson17.bank.exceptions;

/**
 * Exception thrown when an illegal operation is attempted on an account.
 * For example, trying to write checks against a SavingsAccount.
 */
public class IllegalOperationException extends RuntimeException {
  
  /**
   * Creates a new IllegalOperationException with the specified message.
   *
   * @param message The error message.
   */
  public IllegalOperationException(String message) {
    super(message);
  }
  
  /**
   * Creates a new IllegalOperationException with the specified message and cause.
   *
   * @param message The error message.
   * @param cause The underlying cause.
   */
  public IllegalOperationException(String message, Throwable cause) {
    super(message, cause);
  }
}
