package com.codedifferently.lesson17.bank;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents an audit log entry for banking transactions. Follows Single Responsibility Principle -
 * only handles transaction logging.
 */
public class AuditLog {

  /** Represents a single audit entry. */
  public static class AuditEntry {
    private final LocalDateTime timestamp;
    private final String accountNumber;
    private final String transactionType;
    private final double amount;
    private final String description;

    /**
     * Creates a new audit entry.
     *
     * @param accountNumber The account number involved in the transaction.
     * @param transactionType The type of transaction (DEPOSIT, WITHDRAWAL, etc.).
     * @param amount The transaction amount.
     * @param description Additional details about the transaction.
     */
    public AuditEntry(
        String accountNumber, String transactionType, double amount, String description) {
      this.timestamp = LocalDateTime.now();
      this.accountNumber = accountNumber;
      this.transactionType = transactionType;
      this.amount = amount;
      this.description = description;
    }

    public LocalDateTime getTimestamp() {
      return timestamp;
    }

    public String getAccountNumber() {
      return accountNumber;
    }

    public String getTransactionType() {
      return transactionType;
    }

    public double getAmount() {
      return amount;
    }

    public String getDescription() {
      return description;
    }

    @Override
    public String toString() {
      return String.format(
          "[%s] %s - Account: %s, Amount: $%.2f - %s",
          timestamp, transactionType, accountNumber, amount, description);
    }
  }

  private final List<AuditEntry> entries = new ArrayList<>();

  /**
   * Logs a transaction.
   *
   * @param accountNumber The account number.
   * @param transactionType The transaction type.
   * @param amount The amount.
   * @param description The description.
   */
  public void logTransaction(
      String accountNumber, String transactionType, double amount, String description) {
    entries.add(new AuditEntry(accountNumber, transactionType, amount, description));
  }

  /**
   * Gets all audit entries.
   *
   * @return List of all audit entries.
   */
  public List<AuditEntry> getAuditEntries() {
    return new ArrayList<>(entries); // Return copy for immutability
  }

  /**
   * Gets audit entries for a specific account.
   *
   * @param accountNumber The account number to filter by.
   * @return List of audit entries for the account.
   */
  public List<AuditEntry> getAuditEntriesForAccount(String accountNumber) {
    return entries.stream()
        .filter(entry -> entry.getAccountNumber().equals(accountNumber))
        .toList();
  }
}
