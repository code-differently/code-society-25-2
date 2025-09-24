package com.codedifferently.lesson17.bank.exceptions;

/** Thrown when an account lacks sufficient balance for a withdrawal. */
public class InsufficientFundsException extends RuntimeException {
  public InsufficientFundsException(String message) {
    super(message);
  }
}
