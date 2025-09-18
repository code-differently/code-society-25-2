package com.codedifferently.lesson17.bank.exceptions;

/** Thrown when attempting to use a voided instrument (e.g., a Check or MoneyOrder). */
public class CheckVoidedException extends RuntimeException {
  public CheckVoidedException(String message) {
    super(message);
  }
}
