package com.codedifferently.lesson17.bank.exceptions;

public class FundTransferVoidedException extends RuntimeException {

  public FundTransferVoidedException() {}

  public FundTransferVoidedException(String message) {
    super(message);
  }
}
