package com.codedifferently.lesson17.bank;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/** Keeps track of all financial transactions for auditing purposes. */
public class AuditLog {

  private final List<TransactionRecord> transactions = new ArrayList<>();

  /**
   * Records a debit transaction.
   *
   * @param accountNumber The account number.
   * @param amount The amount debited.
   * @param description The description of the transaction.
   */
  public void recordDebit(String accountNumber, double amount, String description) {
    transactions.add(
        new TransactionRecord(
            accountNumber, amount, TransactionType.DEBIT, description, LocalDateTime.now()));
  }

  /**
   * Records a credit transaction.
   *
   * @param accountNumber The account number.
   * @param amount The amount credited.
   * @param description The description of the transaction.
   */
  public void recordCredit(String accountNumber, double amount, String description) {
    transactions.add(
        new TransactionRecord(
            accountNumber, amount, TransactionType.CREDIT, description, LocalDateTime.now()));
  }

  public List<TransactionRecord> getAllTransactions() {
    return new ArrayList<>(transactions);
  }

  public List<TransactionRecord> getTransactionsForAccount(String accountNumber) {
    return transactions.stream()
        .filter(record -> record.getAccountNumber().equals(accountNumber))
        .toList();
  }

  /**
   * Represents a transaction record.
   */
  public static class TransactionRecord {
    private final String accountNumber;
    private final double amount;
    private final TransactionType type;
    private final String description;
    private final LocalDateTime timestamp;

    public TransactionRecord(String accountNumber, double amount, TransactionType type, 
                           String description, LocalDateTime timestamp) {
      this.accountNumber = accountNumber;
      this.amount = amount;
      this.type = type;
      this.description = description;
      this.timestamp = timestamp;
    }

    public String getAccountNumber() { return accountNumber; }
    public double getAmount() { return amount; }
    public TransactionType getType() { return type; }
    public String getDescription() { return description; }
    public LocalDateTime getTimestamp() { return timestamp; }

    @Override
    public String toString() {
      return "TransactionRecord{" +
          "accountNumber='" + accountNumber + '\'' +
          ", amount=" + amount +
          ", type=" + type +
          ", description='" + description + '\'' +
          ", timestamp=" + timestamp +
          '}';
    }
  }

  /**
   * Enumeration of transaction types.
   */
  public enum TransactionType {
    DEBIT, CREDIT
  }
}
