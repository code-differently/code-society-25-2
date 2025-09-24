package com.codedifferently.lesson17.bank;

import java.util.ArrayList;

public class AuditLog {
  private ArrayList<String> log;

  /** Constructor that creates a new AuditLog */
  public AuditLog() {
    this.log = new ArrayList<>();
  }

  /**
   * This adds audit entry to the log
   *
   * @param audit The audit entry to add to the log
   */
  public void addToList(String audit) {
    log.add(audit);
  }

  /**
   * Gets a copy of all audit log entries
   *
   * @return A list of all audit log entries
   */
  public ArrayList<String> getLogEntries() {
    return new ArrayList<>(log);
  }

  /**
   * Gets the number of entries in the audit log
   *
   * @return The number of audit log entries
   */
  public int getLogSize() {
    return log.size();
  }
}
