package com.codedifferently.lesson17.bank.exceptions;

public class SavingsException extends RuntimeException {
  public SavingsException(String message) {
    super(message);
  }
}