package com.codedifferently.lesson17.bank;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AuditLogTest {

  private AuditLog auditLog;
  private UUID customerId1;
  private UUID customerId2;

  @BeforeEach
  void setUp() {
    auditLog = new AuditLog();
    customerId1 = UUID.randomUUID();
    customerId2 = UUID.randomUUID();
  }

  @Test
  @DisplayName("Should log transaction for a customer")
  void testLogTransaction() {
    // Given
    String transaction = "Deposit $100";

    // When
    auditLog.logTransaction(customerId1, transaction);

    // Then
    List<String> transactions = auditLog.getTransactions(customerId1);
    assertEquals(1, transactions.size());
    assertEquals(transaction, transactions.get(0));
  }

  @Test
  @DisplayName("Should log multiple transactions for the same customer")
  void testLogMultipleTransactionsForSameCustomer() {
    // Given
    String transaction1 = "Deposit $100";
    String transaction2 = "Withdraw $50";
    String transaction3 = "Transfer $25";

    // When
    auditLog.logTransaction(customerId1, transaction1);
    auditLog.logTransaction(customerId1, transaction2);
    auditLog.logTransaction(customerId1, transaction3);

    // Then
    List<String> transactions = auditLog.getTransactions(customerId1);
    assertEquals(3, transactions.size());
    assertEquals(transaction1, transactions.get(0));
    assertEquals(transaction2, transactions.get(1));
    assertEquals(transaction3, transactions.get(2));
  }

  @Test
  @DisplayName("Should log transactions for different customers separately")
  void testLogTransactionsForDifferentCustomers() {
    // Given
    String transaction1 = "Deposit $100";
    String transaction2 = "Withdraw $50";

    // When
    auditLog.logTransaction(customerId1, transaction1);
    auditLog.logTransaction(customerId2, transaction2);

    // Then
    List<String> customer1Transactions = auditLog.getTransactions(customerId1);
    List<String> customer2Transactions = auditLog.getTransactions(customerId2);

    assertEquals(1, customer1Transactions.size());
    assertEquals(1, customer2Transactions.size());
    assertEquals(transaction1, customer1Transactions.get(0));
    assertEquals(transaction2, customer2Transactions.get(0));
  }

  @Test
  @DisplayName("Should return empty list for customer with no transactions")
  void testGetTransactionsForCustomerWithNoTransactions() {
    // When
    List<String> transactions = auditLog.getTransactions(customerId1);

    // Then
    assertNotNull(transactions);
    assertTrue(transactions.isEmpty());
  }

  @Test
  @DisplayName("Should return empty list for non-existent customer")
  void testGetTransactionsForNonExistentCustomer() {
    // Given
    UUID nonExistentCustomerId = UUID.randomUUID();

    // When
    List<String> transactions = auditLog.getTransactions(nonExistentCustomerId);

    // Then
    assertNotNull(transactions);
    assertTrue(transactions.isEmpty());
  }

  @Test
  @DisplayName("Should return specific transaction by index")
  void testGetTransactionsByNumberValidIndex() {
    // Given
    String transaction1 = "Deposit $100";
    String transaction2 = "Withdraw $50";
    auditLog.logTransaction(customerId1, transaction1);
    auditLog.logTransaction(customerId1, transaction2);

    // When
    String result = auditLog.getTransactionsByNumber(customerId1, 0);

    // Then
    assertEquals(transaction1, result);

    // When
    String result2 = auditLog.getTransactionsByNumber(customerId1, 1);

    // Then
    assertEquals(transaction2, result2);
  }

  @Test
  @DisplayName(
      "Should throw IllegalArgumentException for negative index in getTransactionsByNumber")
  void testGetTransactionsByNumberWithNegativeIndex() {
    // Given
    auditLog.logTransaction(customerId1, "Deposit $100");

    // When & Then
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          auditLog.getTransactionsByNumber(customerId1, -1);
        });
  }

  @Test
  @DisplayName("Should throw IndexOutOfBoundsException for index beyond transactions size")
  void testGetTransactionsByNumberWithIndexOutOfBounds() {
    // Given
    auditLog.logTransaction(customerId1, "Deposit $100");

    // When & Then
    assertThrows(
        IndexOutOfBoundsException.class,
        () -> {
          auditLog.getTransactionsByNumber(customerId1, 1);
        });
  }

  @Test
  @DisplayName("Should throw IndexOutOfBoundsException for customer with no transactions")
  void testGetTransactionsByNumberForCustomerWithNoTransactions() {
    // When & Then
    assertThrows(
        IndexOutOfBoundsException.class,
        () -> {
          auditLog.getTransactionsByNumber(customerId1, 0);
        });
  }

  @Test
  @DisplayName("Should clear all logs")
  void testClearLog() {
    // Given
    auditLog.logTransaction(customerId1, "Deposit $100");
    auditLog.logTransaction(customerId2, "Withdraw $50");

    // When
    auditLog.clearLog();

    // Then
    assertTrue(auditLog.getTransactions(customerId1).isEmpty());
    assertTrue(auditLog.getTransactions(customerId2).isEmpty());
  }

  @Test
  @DisplayName("Should clear log for specific customer only")
  void testClearLogForCustomer() {
    // Given
    auditLog.logTransaction(customerId1, "Deposit $100");
    auditLog.logTransaction(customerId2, "Withdraw $50");

    // When
    auditLog.clearLogForCustomer(customerId1);

    // Then
    assertTrue(auditLog.getTransactions(customerId1).isEmpty());
    assertEquals(1, auditLog.getTransactions(customerId2).size());
    assertEquals("Withdraw $50", auditLog.getTransactions(customerId2).get(0));
  }
}
