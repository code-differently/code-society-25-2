package com.codedifferently.lesson17.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Test cases for AuditLog class. */
class AuditLogTest {

  private AuditLog auditLog;

  @BeforeEach
  void setUp() {
    auditLog = new AuditLog();
  }

  @Test
  void testConstructor() {
    assertNotNull(auditLog);
    assertEquals(0, auditLog.getTransactionCount());
  }

  @Test
  void testRecordCredit() {
    auditLog.recordCredit("ACC001", 500.0, "Cash deposit");

    List<AuditLog.AuditEntry> entries = auditLog.getAllEntries();
    assertEquals(1, entries.size());

    AuditLog.AuditEntry entry = entries.get(0);
    assertEquals("ACC001", entry.getAccountNumber());
    assertEquals(AuditLog.TransactionType.CREDIT, entry.getType());
    assertEquals(500.0, entry.getAmount());
    assertEquals("Cash deposit", entry.getDescription());
    assertNotNull(entry.getTimestamp());
  }

  @Test
  void testRecordDebit() {
    auditLog.recordDebit("ACC001", 200.0, "ATM withdrawal");

    List<AuditLog.AuditEntry> entries = auditLog.getAllEntries();
    assertEquals(1, entries.size());

    AuditLog.AuditEntry entry = entries.get(0);
    assertEquals("ACC001", entry.getAccountNumber());
    assertEquals(AuditLog.TransactionType.DEBIT, entry.getType());
    assertEquals(200.0, entry.getAmount());
    assertEquals("ATM withdrawal", entry.getDescription());
    assertNotNull(entry.getTimestamp());
  }

  @Test
  void testMultipleTransactions() {
    auditLog.recordCredit("ACC001", 500.0, "Initial deposit");
    auditLog.recordDebit("ACC001", 100.0, "ATM withdrawal");
    auditLog.recordCredit("ACC002", 1000.0, "Transfer in");

    List<AuditLog.AuditEntry> entries = auditLog.getAllEntries();
    assertEquals(3, entries.size());
    assertEquals(3, auditLog.getTransactionCount());

    // Verify chronological order
    assertTrue(
        entries.get(0).getTimestamp().isBefore(entries.get(1).getTimestamp())
            || entries.get(0).getTimestamp().equals(entries.get(1).getTimestamp()));
    assertTrue(
        entries.get(1).getTimestamp().isBefore(entries.get(2).getTimestamp())
            || entries.get(1).getTimestamp().equals(entries.get(2).getTimestamp()));
  }

  @Test
  void testGetEntriesForAccount() {
    auditLog.recordCredit("ACC001", 500.0, "Deposit");
    auditLog.recordDebit("ACC002", 100.0, "Withdrawal");
    auditLog.recordCredit("ACC001", 200.0, "Another deposit");

    List<AuditLog.AuditEntry> acc001Entries = auditLog.getEntriesForAccount("ACC001");
    assertEquals(2, acc001Entries.size());

    List<AuditLog.AuditEntry> acc002Entries = auditLog.getEntriesForAccount("ACC002");
    assertEquals(1, acc002Entries.size());

    List<AuditLog.AuditEntry> acc003Entries = auditLog.getEntriesForAccount("ACC003");
    assertEquals(0, acc003Entries.size());
  }

  @Test
  void testAuditEntryGetters() {
    LocalDateTime beforeRecording = LocalDateTime.now();
    auditLog.recordCredit("TEST001", 123.45, "Test description");
    LocalDateTime afterRecording = LocalDateTime.now();

    AuditLog.AuditEntry entry = auditLog.getAllEntries().get(0);

    assertEquals("TEST001", entry.getAccountNumber());
    assertEquals(AuditLog.TransactionType.CREDIT, entry.getType());
    assertEquals(123.45, entry.getAmount());
    assertEquals("Test description", entry.getDescription());

    // Verify timestamp is within expected range
    LocalDateTime timestamp = entry.getTimestamp();
    assertTrue(timestamp.isAfter(beforeRecording) || timestamp.equals(beforeRecording));
    assertTrue(timestamp.isBefore(afterRecording) || timestamp.equals(afterRecording));
  }

  @Test
  void testEmptyDescription() {
    auditLog.recordCredit("ACC001", 100.0, "");

    AuditLog.AuditEntry entry = auditLog.getAllEntries().get(0);
    assertEquals("", entry.getDescription());
  }

  @Test
  void testNullDescription() {
    auditLog.recordCredit("ACC001", 100.0, null);

    AuditLog.AuditEntry entry = auditLog.getAllEntries().get(0);
    assertNull(entry.getDescription());
  }

  @Test
  void testZeroAmount() {
    auditLog.recordCredit("ACC001", 0.0, "Balance adjustment");

    AuditLog.AuditEntry entry = auditLog.getAllEntries().get(0);
    assertEquals(0.0, entry.getAmount());
  }

  @Test
  void testNegativeAmount() {
    auditLog.recordDebit("ACC001", -5.0, "Fee reversal");

    AuditLog.AuditEntry entry = auditLog.getAllEntries().get(0);
    assertEquals(-5.0, entry.getAmount());
  }

  @Test
  void testGetAllEntriesReturnsNewList() {
    auditLog.recordCredit("ACC001", 100.0, "Test");

    List<AuditLog.AuditEntry> entries1 = auditLog.getAllEntries();
    List<AuditLog.AuditEntry> entries2 = auditLog.getAllEntries();

    // Should return different list instances
    assertNotSame(entries1, entries2);
    assertEquals(entries1.size(), entries2.size());
  }

  @Test
  void testAuditEntryToString() {
    auditLog.recordDebit("ACC123", 250.75, "Check #1001");

    AuditLog.AuditEntry entry = auditLog.getAllEntries().get(0);
    String entryString = entry.toString();

    assertTrue(entryString.contains("ACC123"));
    assertTrue(entryString.contains("DEBIT"));
    assertTrue(entryString.contains("250.75"));
    assertTrue(entryString.contains("Check #1001"));
  }

  @Test
  void testTransactionTypes() {
    auditLog.recordCredit("ACC001", 100.0, "Credit transaction");
    auditLog.recordDebit("ACC001", 50.0, "Debit transaction");

    List<AuditLog.AuditEntry> entries = auditLog.getAllEntries();

    assertEquals(AuditLog.TransactionType.CREDIT, entries.get(0).getType());
    assertEquals(AuditLog.TransactionType.DEBIT, entries.get(1).getType());
  }

  @Test
  void testLargeNumberOfTransactions() {
    int numTransactions = 1000;

    for (int i = 0; i < numTransactions; i++) {
      if (i % 2 == 0) {
        auditLog.recordCredit("ACC" + (i % 10), i * 1.0, "Transaction " + i);
      } else {
        auditLog.recordDebit("ACC" + (i % 10), i * 1.0, "Transaction " + i);
      }
    }

    assertEquals(numTransactions, auditLog.getTransactionCount());
    assertEquals(numTransactions, auditLog.getAllEntries().size());
  }
}
