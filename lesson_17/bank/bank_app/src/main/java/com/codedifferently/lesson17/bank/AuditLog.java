package com.codedifferently.lesson17.bank;

import java.util.Set;
import java.util.HashSet;

public class AuditLog {
  private Set<String> audits;

  public AuditLog() {
    audits = new HashSet<>();
  }

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

  public String toString() {
    String auditString = "";
    for (String audit : audits) {
      auditString += audit + "\n";
    }
    return auditString;
  }
}
