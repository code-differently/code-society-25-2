package com.codedifferently.lesson17.bank;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AuditLogTest {

  private AuditLog auditLog;

  @BeforeEach
  void setUp() {
    auditLog = new AuditLog();
  }

  @Test
  void testLogDeposit() {
    auditLog.logDeposit("123456789", 100.0);

    List<Transaction> transactions = auditLog.getAllTransactions();
    assertEquals(1, transactions.size());

    Transaction transaction = transactions.get(0);
    assertEquals("123456789", transaction.accountNumber());
    assertEquals(TransactionType.DEPOSIT, transaction.type());
    assertEquals(100.0, transaction.amount());
    assertThat(transaction.description()).contains("Cash deposit of $100.00");
    assertThat(transaction.transactionId()).startsWith("TXN-");
  }

  @Test
  void testLogCheckDeposit() {
    auditLog.logCheckDeposit("987654321", 250.0, "CHK001");

    List<Transaction> transactions = auditLog.getAllTransactions();
    assertEquals(1, transactions.size());

    Transaction transaction = transactions.get(0);
    assertEquals("987654321", transaction.accountNumber());
    assertEquals(TransactionType.DEPOSIT, transaction.type());
    assertEquals(250.0, transaction.amount());
    assertThat(transaction.description()).contains("Check deposit of $250.00 (Check #CHK001)");
  }

  @Test
  void testLogWithdrawal() {
    auditLog.logWithdrawal("555666777", 75.0);

    List<Transaction> transactions = auditLog.getAllTransactions();
    assertEquals(1, transactions.size());

    Transaction transaction = transactions.get(0);
    assertEquals("555666777", transaction.accountNumber());
    assertEquals(TransactionType.WITHDRAWAL, transaction.type());
    assertEquals(75.0, transaction.amount());
    assertThat(transaction.description()).contains("Cash withdrawal of $75.00");
  }

  @Test
  void testLogTransfer() {
    auditLog.logTransfer("111111111", "222222222", 300.0, "CHK002");

    List<Transaction> transactions = auditLog.getAllTransactions();
    assertEquals(1, transactions.size());

    Transaction transaction = transactions.get(0);
    assertEquals("222222222", transaction.accountNumber()); // Logged to destination account
    assertEquals(TransactionType.TRANSFER, transaction.type());
    assertEquals(300.0, transaction.amount());
    assertThat(transaction.description())
        .contains("Transfer of $300.00 from 111111111 to 222222222 (Check #CHK002)");
  }

  @Test
  void testGetTransactionsForAccount() {
    auditLog.logDeposit("123456789", 100.0);
    auditLog.logWithdrawal("123456789", 50.0);
    auditLog.logDeposit("987654321", 200.0);

    List<Transaction> account1Transactions = auditLog.getTransactionsForAccount("123456789");
    List<Transaction> account2Transactions = auditLog.getTransactionsForAccount("987654321");

    assertEquals(2, account1Transactions.size());
    assertEquals(1, account2Transactions.size());

    // Verify account1 transactions
    assertThat(account1Transactions.get(0).type()).isEqualTo(TransactionType.DEPOSIT);
    assertThat(account1Transactions.get(1).type()).isEqualTo(TransactionType.WITHDRAWAL);

    // Verify account2 transactions
    assertThat(account2Transactions.get(0).type()).isEqualTo(TransactionType.DEPOSIT);
  }

  @Test
  void testGetTransactionCount() {
    assertEquals(0, auditLog.getTransactionCount());

    auditLog.logDeposit("123456789", 100.0);
    assertEquals(1, auditLog.getTransactionCount());

    auditLog.logWithdrawal("123456789", 50.0);
    assertEquals(2, auditLog.getTransactionCount());

    auditLog.logTransfer("111111111", "222222222", 300.0, "CHK003");
    assertEquals(3, auditLog.getTransactionCount());
  }

  @Test
  void testMultipleTransactions() {
    // Create a more complex scenario
    auditLog.logDeposit("ACCT001", 1000.0);
    auditLog.logCheckDeposit("ACCT002", 500.0, "CHK123");
    auditLog.logWithdrawal("ACCT001", 200.0);
    auditLog.logTransfer("ACCT001", "ACCT003", 150.0, "CHK456");

    assertEquals(4, auditLog.getTransactionCount());

    // Check ACCT001 has 2 transactions (deposit and withdrawal)
    List<Transaction> acct001Transactions = auditLog.getTransactionsForAccount("ACCT001");
    assertEquals(2, acct001Transactions.size());

    // Check ACCT002 has 1 transaction (check deposit)
    List<Transaction> acct002Transactions = auditLog.getTransactionsForAccount("ACCT002");
    assertEquals(1, acct002Transactions.size());

    // Check ACCT003 has 1 transaction (transfer destination)
    List<Transaction> acct003Transactions = auditLog.getTransactionsForAccount("ACCT003");
    assertEquals(1, acct003Transactions.size());
    assertEquals(TransactionType.TRANSFER, acct003Transactions.get(0).type());
  }
}
