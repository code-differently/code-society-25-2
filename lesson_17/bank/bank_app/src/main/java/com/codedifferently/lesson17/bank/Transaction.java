package com.codedifferently.lesson17.bank;

import java.time.LocalDateTime;

/**
 * Represents a single bank transaction for audit logging. This is an immutable record that captures
 * all the details of a transaction.
 */
public record Transaction(
    String transactionId,
    String accountNumber,
    TransactionType type,
    double amount,
    LocalDateTime timestamp,
    String description) {

  /** Creates a new Transaction with the current timestamp. */
  public Transaction(
      String transactionId,
      String accountNumber,
      TransactionType type,
      double amount,
      String description) {
    this(transactionId, accountNumber, type, amount, LocalDateTime.now(), description);
  }
}
