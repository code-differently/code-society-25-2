package com.codedifferently.lesson17.bank;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/** Records all account transactions for traceability. */
public class AuditLog {

  private final List<String> entries = new ArrayList<>();

  /** Logs a transaction for a customer. */
  public void logTransaction(UUID customerId, String message) {
    entries.add("Customer " + customerId + ": " + message);
  }

  /** Returns all logged transactions. */
  public List<String> getEntries() {
    return new ArrayList<>(entries); // return a copy to prevent external modification
  }
}
