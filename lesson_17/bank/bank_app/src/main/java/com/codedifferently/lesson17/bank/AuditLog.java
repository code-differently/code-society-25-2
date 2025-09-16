package com.codedifferently.lesson17.bank;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class AuditLog {
  private final List<TransactionEntry> entries = new ArrayList<>();

  void record(String accountNumber, double amount, TransactionType type, String note) {
    entries.add(new TransactionEntry(Instant.now(), accountNumber, amount, type, note));
  }

  List<TransactionEntry> getEntries() {
    return Collections.unmodifiableList(entries);
  }
}
