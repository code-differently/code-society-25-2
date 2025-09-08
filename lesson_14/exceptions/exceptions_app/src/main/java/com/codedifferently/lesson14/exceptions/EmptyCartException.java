package com.codedifferently.lesson14.exceptions;

/** Exception thrown when attempting to checkout an empty cart */
public class EmptyCartException extends Exception {
  public EmptyCartException() {
    super("Cannot checkout an empty cart. Please add items before proceeding.");
  }
}
