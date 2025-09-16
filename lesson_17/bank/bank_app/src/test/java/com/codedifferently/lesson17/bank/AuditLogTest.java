package com.codedifferently.lesson17.bank;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Instant;
import java.util.List;
import org.junit.jupiter.api.Test;

class AuditLogTest {

  @Test
  void record_addsEntriesAndFieldsAreCorrect() {
    AuditLog log = new AuditLog();
    log.record("A-001", 100.0, TransactionType.DEPOSIT_CASH, "cash");
    log.record("A-002", 25.5, TransactionType.WITHDRAWAL, "atm");
    log.record("A-003", 75.0, TransactionType.DEPOSIT_CHECK, "Check");

    List<TransactionEntry> entries = log.getEntries();
    assertEquals(3, entries.size());

    TransactionEntry e1 = entries.get(0);
    assertEquals("A-001", e1.getAccountNumber());
    assertEquals(100.0, e1.getAmount(), 0.0001);
    assertEquals(TransactionType.DEPOSIT_CASH, e1.getType());
    assertEquals("cash", e1.getNote());
    assertNotNull(e1.getTimestamp());
  }

  @Test
  void getEntries_isUnmodifiable() {
    AuditLog log = new AuditLog();
    log.record("A-100", 1.0, TransactionType.DEPOSIT_CASH, "x");
    List<TransactionEntry> view = log.getEntries();
    assertThrows(UnsupportedOperationException.class, () -> view.add(view.get(0)));
  }

  @Test
  void transactionEntry_gettersReturnConstructorValues() {
    Instant ts = Instant.parse("2020-01-01T00:00:00Z");
    TransactionEntry entry =
        new TransactionEntry(ts, "A-XYZ", 9.99, TransactionType.WITHDRAWAL, "atm");
    assertEquals(ts, entry.getTimestamp());
    assertEquals("A-XYZ", entry.getAccountNumber());
    assertEquals(9.99, entry.getAmount(), 0.0001);
    assertEquals(TransactionType.WITHDRAWAL, entry.getType());
    assertEquals("atm", entry.getNote());
  }

  @Test
  void bankAtm_exposesAuditLog() {
    BankAtm atm = new BankAtm();
    assertNotNull(atm.getAuditLog());
  }
}
