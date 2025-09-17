package com.codedifferently.lesson17.bank;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Handles logging of all banking transactions for audit purposes. This class follows the Single
 * Responsibility Principle - it only handles logging.
 */
public class AuditLog {

  private final List<Transaction> transactions;

  /** Creates a new AuditLog with an empty transaction history. */
  public AuditLog() {
    this.transactions = new ArrayList<>();
  }

  /** Logs a cash deposit transaction. */
  public void logDeposit(String accountNumber, double amount) {
    String transactionId = generateTransactionId();
    String description = String.format("Cash deposit of $%.2f", amount);
    Transaction transaction =
        new Transaction(transactionId, accountNumber, TransactionType.DEPOSIT, amount, description);
    transactions.add(transaction);
  }

  /** Logs a check deposit transaction. */
  public void logCheckDeposit(String accountNumber, double amount, String checkNumber) {
    String transactionId = generateTransactionId();
    String description = String.format("Check deposit of $%.2f (Check #%s)", amount, checkNumber);
    Transaction transaction =
        new Transaction(transactionId, accountNumber, TransactionType.DEPOSIT, amount, description);
    transactions.add(transaction);
  }

  /** Logs a withdrawal transaction. */
  public void logWithdrawal(String accountNumber, double amount) {
    String transactionId = generateTransactionId();
    String description = String.format("Cash withdrawal of $%.2f", amount);
    Transaction transaction =
        new Transaction(
            transactionId, accountNumber, TransactionType.WITHDRAWAL, amount, description);
    transactions.add(transaction);
  }

  /** Logs a transfer transaction (money moving from one account to another via check). */
  public void logTransfer(String fromAccount, String toAccount, double amount, String checkNumber) {
    String transactionId = generateTransactionId();
    String description =
        String.format(
            "Transfer of $%.2f from %s to %s (Check #%s)",
            amount, fromAccount, toAccount, checkNumber);
    Transaction transaction =
        new Transaction(transactionId, toAccount, TransactionType.TRANSFER, amount, description);
    transactions.add(transaction);
  }

  /** Gets all transactions for a specific account. */
  public List<Transaction> getTransactionsForAccount(String accountNumber) {
    return transactions.stream()
        .filter(transaction -> transaction.accountNumber().equals(accountNumber))
        .toList();
  }

  /** Gets all transactions in the system. */
  public List<Transaction> getAllTransactions() {
    return new ArrayList<>(transactions);
  }

  /** Gets the total number of transactions logged. */
  public int getTransactionCount() {
    return transactions.size();
  }

  /** Generates a unique transaction ID. */
  private String generateTransactionId() {
    return "TXN-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
  }
}
