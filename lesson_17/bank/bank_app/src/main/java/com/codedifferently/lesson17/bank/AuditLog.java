package com.codedifferently.lesson17.bank;

import java.util.ArrayList;
import java.util.List;

/** Maintains an audit trail of all banking transactions for traceability. */
public class AuditLog {
  private final List<String> entries = new ArrayList<>();

  /**
   * Adds an entry to the audit log.
   *
   * @param entry The transaction entry to log.
   */
  public void log(String entry) {
    entries.add(entry);
  }

  /**
   * Gets a copy of all audit log entries.
   *
   * @return A list of all logged entries.
   */
  public List<String> getEntries() {
    return new ArrayList<>(entries);
  }
}
