package com.codedifferently.lesson17.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AuditLogTest {

  private AuditLog auditLog;

  @BeforeEach
  void setUp() {
    auditLog = new AuditLog();
  }

  @Test
  void testRecordCredit() {
    // Act
    auditLog.recordCredit("123456789", 100.0, "Deposit");

    // Assert
    List<AuditLog.TransactionRecord> transactions = auditLog.getAllTransactions();
    assertEquals(1, transactions.size());

    AuditLog.TransactionRecord record = transactions.get(0);
    assertEquals("123456789", record.getAccountNumber());
    assertEquals(100.0, record.getAmount());
    assertEquals(AuditLog.TransactionType.CREDIT, record.getType());
    assertEquals("Deposit", record.getDescription());
    assertTrue(record.getTimestamp() != null);
  }

  @Test
  void testRecordDebit() {
    // Act
    auditLog.recordDebit("123456789", 50.0, "Withdrawal");

    // Assert
    List<AuditLog.TransactionRecord> transactions = auditLog.getAllTransactions();
    assertEquals(1, transactions.size());

    AuditLog.TransactionRecord record = transactions.get(0);
    assertEquals("123456789", record.getAccountNumber());
    assertEquals(50.0, record.getAmount());
    assertEquals(AuditLog.TransactionType.DEBIT, record.getType());
    assertEquals("Withdrawal", record.getDescription());
  }

  @Test
  void testGetTransactionsForAccount() {
    // Arrange
    auditLog.recordCredit("123456789", 100.0, "Deposit");
    auditLog.recordDebit("123456789", 50.0, "Withdrawal");
    auditLog.recordCredit("987654321", 200.0, "Another deposit");

    // Act
    List<AuditLog.TransactionRecord> accountTransactions =
        auditLog.getTransactionsForAccount("123456789");

    // Assert
    assertEquals(2, accountTransactions.size());
    assertEquals("123456789", accountTransactions.get(0).getAccountNumber());
    assertEquals("123456789", accountTransactions.get(1).getAccountNumber());
  }

  @Test
  void testGetAllTransactions() {
    // Arrange
    auditLog.recordCredit("123456789", 100.0, "Deposit");
    auditLog.recordDebit("987654321", 50.0, "Withdrawal");

    // Act
    List<AuditLog.TransactionRecord> allTransactions = auditLog.getAllTransactions();

    // Assert
    assertEquals(2, allTransactions.size());
  }

  @Test
  void testTransactionRecordToString() {
    // Arrange
    auditLog.recordCredit("123456789", 100.0, "Test deposit");

    // Act
    AuditLog.TransactionRecord record = auditLog.getAllTransactions().get(0);
    String result = record.toString();

    // Assert
    assertTrue(result.contains("TransactionRecord{"));
    assertTrue(result.contains("accountNumber='123456789'"));
    assertTrue(result.contains("amount=100.0"));
    assertTrue(result.contains("type=CREDIT"));
    assertTrue(result.contains("description='Test deposit'"));
    assertTrue(result.contains("timestamp="));
  }
}
