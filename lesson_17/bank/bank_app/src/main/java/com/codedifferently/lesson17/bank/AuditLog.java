package com.codedifferently.lesson17.bank;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * Maintains a set of records for each transaction performed on bank accounts.
 */
public class AuditLog {
  private Set<String> audits;

  /**
   * Constructs a new AuditLog with an empty set of audit records.
   */
  public AuditLog() {
    audits = new HashSet<>();
  }

  /**
   * Logs a financial transaction for the specified account.
   * Contains the account number, action performed, transaction amount,
   * and the resulting account balance.
   *
   * @param account The account of the transaction
   * @param action The type of action performed (e.g., "DEPOSIT", "WITHDRAW")
   * @param amount The amount used in the transaction
   */
  public void log(Account account, String action, double amount) {
    String audit =
        "Account: "
            + account.getAccountNumber()
            + " | Action: "
            + action
            + " | Amount: "
            + amount
            + " | New Balance: "
            + account.getBalance();
    audits.add(audit);
  }

  public Set<String> getAudits() {
    return audits;
  }

  /**
   * Returns a string representation of all audit records.
   * Each audit entry is separated by a newline character.
   * 
   * @return A formatted string containing all audit entries, each on a separate line
   */
  @Override
  public String toString() {
    String auditString = "";
    for (String audit : audits) {
      auditString += audit + "\n";
    }
    return auditString;
  }
}
