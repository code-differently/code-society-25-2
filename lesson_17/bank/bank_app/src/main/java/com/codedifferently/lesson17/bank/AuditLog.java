package com.codedifferently.lesson17.bank;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Audit log for tracking all banking transactions. Records debits and credits to accounts for
 * compliance and traceability.
 */
public class AuditLog {

  private final List<AuditEntry> entries = new ArrayList<>();

  /**
   * Records a debit transaction.
   *
   * @param accountNumber The account number.
   * @param amount The amount debited.
   * @param description Description of the transaction.
   */
  public void recordDebit(String accountNumber, double amount, String description) {
    entries.add(new AuditEntry(accountNumber, TransactionType.DEBIT, amount, description));
  }

  /**
   * Records a credit transaction.
   *
   * @param accountNumber The account number.
   * @param amount The amount credited.
   * @param description Description of the transaction.
   */
  public void recordCredit(String accountNumber, double amount, String description) {
    entries.add(new AuditEntry(accountNumber, TransactionType.CREDIT, amount, description));
  }

  /**
   * Gets all audit entries.
   *
   * @return List of all audit entries.
   */
  public List<AuditEntry> getAllEntries() {
    return new ArrayList<>(entries);
  }

  /**
   * Gets audit entries for a specific account.
   *
   * @param accountNumber The account number to filter by.
   * @return List of audit entries for the specified account.
   */
  public List<AuditEntry> getEntriesForAccount(String accountNumber) {
    return entries.stream()
        .filter(entry -> entry.getAccountNumber().equals(accountNumber))
        .toList();
  }

  /**
   * Gets the total number of recorded transactions.
   *
   * @return The total number of transactions.
   */
  public int getTransactionCount() {
    return entries.size();
  }

  /** Represents a single audit entry. */
  public static class AuditEntry {
    private final String accountNumber;
    private final TransactionType type;
    private final double amount;
    private final String description;
    private final LocalDateTime timestamp;

    /**
     * Creates a new audit entry.
     *
     * @param accountNumber The account number.
     * @param type The transaction type.
     * @param amount The transaction amount.
     * @param description The transaction description.
     */
    public AuditEntry(
        String accountNumber, TransactionType type, double amount, String description) {
      this.accountNumber = accountNumber;
      this.type = type;
      this.amount = amount;
      this.description = description;
      this.timestamp = LocalDateTime.now();
    }

    /**
     * Gets the account number.
     *
     * @return The account number.
     */
    public String getAccountNumber() {
      return accountNumber;
    }

    /**
     * Gets the transaction type.
     *
     * @return The transaction type.
     */
    public TransactionType getType() {
      return type;
    }

    /**
     * Gets the transaction amount.
     *
     * @return The transaction amount.
     */
    public double getAmount() {
      return amount;
    }

    /**
     * Gets the transaction description.
     *
     * @return The transaction description.
     */
    public String getDescription() {
      return description;
    }

    /**
     * Gets the transaction timestamp.
     *
     * @return The transaction timestamp.
     */
    public LocalDateTime getTimestamp() {
      return timestamp;
    }

    @Override
    public String toString() {
      return String.format(
          "[%s] %s %s: %.2f - %s", timestamp, accountNumber, type, amount, description);
    }
  }

  /** Enumeration for transaction types. */
  public enum TransactionType {
    CREDIT,
    DEBIT
  }
}
