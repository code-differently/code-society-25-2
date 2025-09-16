package com.codedifferently.lesson17.bank;

import java.time.Instant;

final class TransactionEntry {
  private final Instant timestamp;
  private final String accountNumber;
  private final double amount;
  private final TransactionType type;
  private final String note;

  TransactionEntry(
      Instant timestamp, String accountNumber, double amount, TransactionType type, String note) {
    this.timestamp = timestamp;
    this.accountNumber = accountNumber;
    this.amount = amount;
    this.type = type;
    this.note = note;
  }

  public Instant getTimestamp() {
    return timestamp;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public double getAmount() {
    return amount;
  }

  public TransactionType getType() {
    return type;
  }

  public String getNote() {
    return note;
  }
}
