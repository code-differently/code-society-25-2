package com.codedifferently.lesson17.bank;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/** Represents an audit log for recording transactions. */
public class AuditLog {

  private final List<String> logs = new ArrayList<>();

  /**
   * Logs a deposit transaction.
   *
   * @param accountNumber The account number.
   * @param amount The amount deposited.
   */
  public void logDeposit(String accountNumber, double amount) {
    String logEntry =
        String.format(
            "[%s] Deposited $%.2f to account %s", LocalDateTime.now(), amount, accountNumber);
    logs.add(logEntry);
  }

  /**
   * Logs a withdrawal transaction.
   *
   * @param accountNumber The account number.
   * @param amount The amount withdrawn.
   */
  public void logWithdrawal(String accountNumber, double amount) {
    String logEntry =
        String.format(
            "[%s] Withdrew $%.2f from account %s", LocalDateTime.now(), amount, accountNumber);
    logs.add(logEntry);
  }

  /**
   * Gets all log entries.
   *
   * @return The list of log entries.
   */
  public List<String> getLogs() {
    return new ArrayList<>(logs);
  }

  @Override
  public String toString() {
    return "AuditLog{" + "logs=" + logs + '}';
  }
}
