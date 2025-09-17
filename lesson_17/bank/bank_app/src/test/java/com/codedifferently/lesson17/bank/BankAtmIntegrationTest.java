package com.codedifferently.lesson17.bank;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.codedifferently.lesson17.bank.exceptions.AccountNotFoundException;
import com.codedifferently.lesson17.bank.exceptions.InsufficientFundsException;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Integration tests for BankAtm with SavingsAccount and AuditLog. */
class BankAtmIntegrationTest {

  private BankAtm bankAtm;
  private Customer customer;
  private CheckingAccount checkingAccount;
  private SavingsAccount savingsAccount;

  @BeforeEach
  void setUp() {
    bankAtm = new BankAtm();
    customer = new Customer(UUID.randomUUID(), "John Doe");

    checkingAccount = new CheckingAccount("CHK001", Set.of(customer), 1000.0);
    savingsAccount = new SavingsAccount("SAV001", Set.of(customer), 500.0);

    customer.addAccount(checkingAccount);
    customer.addAccount(savingsAccount);

    // Add accounts to the ATM
    bankAtm.addAccount(checkingAccount);
    bankAtm.addAccount(savingsAccount);
  }

  @Test
  void testDepositToSavingsAccount() throws AccountNotFoundException {
    double initialBalance = savingsAccount.getBalance();

    bankAtm.depositFunds("SAV001", 200.0);

    assertEquals(initialBalance + 200.0, savingsAccount.getBalance());

    // Verify audit log contains the deposit
    AuditLog auditLog = bankAtm.getAuditLog();
    List<AuditLog.AuditEntry> entries = auditLog.getEntriesForAccount("SAV001");
    assertEquals(1, entries.size());

    AuditLog.AuditEntry entry = entries.get(0);
    assertEquals("SAV001", entry.getAccountNumber());
    assertEquals(AuditLog.TransactionType.CREDIT, entry.getType());
    assertEquals(200.0, entry.getAmount());
    assertTrue(entry.getDescription().contains("deposit"));
  }

  @Test
  void testWithdrawFromSavingsAccount()
      throws AccountNotFoundException, InsufficientFundsException {
    double initialBalance = savingsAccount.getBalance();

    bankAtm.withdrawFunds("SAV001", 100.0);

    assertEquals(initialBalance - 100.0, savingsAccount.getBalance());

    // Verify audit log contains the withdrawal
    AuditLog auditLog = bankAtm.getAuditLog();
    List<AuditLog.AuditEntry> entries = auditLog.getEntriesForAccount("SAV001");
    assertEquals(1, entries.size());

    AuditLog.AuditEntry entry = entries.get(0);
    assertEquals("SAV001", entry.getAccountNumber());
    assertEquals(AuditLog.TransactionType.DEBIT, entry.getType());
    assertEquals(100.0, entry.getAmount());
    assertTrue(entry.getDescription().contains("withdrawal"));
  }

  @Test
  void testSavingsAccountCannotAcceptCheckDeposits() {
    // SavingsAccount should not accept check deposits

    Check check = new Check("C001", 100.0, checkingAccount);

    // This should work - depositing check to checking account
    assertDoesNotThrow(() -> bankAtm.depositFunds("CHK001", check));

    // This should throw an exception - depositing check to savings account
    Check savingsCheck = new Check("C002", 50.0, checkingAccount);
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> bankAtm.depositFunds("SAV001", savingsCheck),
            "Should not allow check deposits to savings accounts");

    assertThat(exception.getMessage())
        .isEqualTo("Cannot deposit checks to savings accounts. Account type: SavingsAccount");
  }

  @Test
  void testAuditLogTracksAllTransactions() throws Exception {
    // Perform multiple transactions on different accounts
    bankAtm.depositFunds("CHK001", 300.0);
    bankAtm.withdrawFunds("CHK001", 150.0);
    bankAtm.depositFunds("SAV001", 100.0);
    bankAtm.withdrawFunds("SAV001", 50.0);

    AuditLog auditLog = bankAtm.getAuditLog();

    // Should have 4 total transactions
    assertEquals(4, auditLog.getTransactionCount());

    // Check checking account transactions
    List<AuditLog.AuditEntry> checkingEntries = auditLog.getEntriesForAccount("CHK001");
    assertEquals(2, checkingEntries.size());

    // Check savings account transactions
    List<AuditLog.AuditEntry> savingsEntries = auditLog.getEntriesForAccount("SAV001");
    assertEquals(2, savingsEntries.size());

    // Verify transaction types and amounts
    assertEquals(AuditLog.TransactionType.CREDIT, checkingEntries.get(0).getType());
    assertEquals(300.0, checkingEntries.get(0).getAmount());

    assertEquals(AuditLog.TransactionType.DEBIT, checkingEntries.get(1).getType());
    assertEquals(150.0, checkingEntries.get(1).getAmount());
  }

  @Test
  void testPolymorphicAccountHandling() throws AccountNotFoundException {
    // Test that BankAtm can handle both CheckingAccount and SavingsAccount
    // through the Account interface

    double checkingInitial = checkingAccount.getBalance();
    double savingsInitial = savingsAccount.getBalance();

    // Deposit to both account types
    bankAtm.depositFunds("CHK001", 100.0);
    bankAtm.depositFunds("SAV001", 200.0);

    assertEquals(checkingInitial + 100.0, checkingAccount.getBalance());
    assertEquals(savingsInitial + 200.0, savingsAccount.getBalance());

    // Both should be tracked in audit log
    AuditLog auditLog = bankAtm.getAuditLog();
    assertTrue(auditLog.getEntriesForAccount("CHK001").size() > 0);
    assertTrue(auditLog.getEntriesForAccount("SAV001").size() > 0);
  }

  @Test
  void testAccountNotFoundForSavingsAccount() {
    // Test that trying to access a non-existent savings account throws the right exception
    assertThrows(
        AccountNotFoundException.class,
        () -> {
          bankAtm.depositFunds("SAV999", 100.0);
        });

    assertThrows(
        AccountNotFoundException.class,
        () -> {
          bankAtm.withdrawFunds("SAV999", 50.0);
        });
  }

  @Test
  void testInsufficientFundsForSavingsAccount() {
    // Test insufficient funds handling for savings accounts
    assertThrows(
        InsufficientFundsException.class,
        () -> {
          bankAtm.withdrawFunds("SAV001", 1000.0); // More than the 500.0 balance
        });
  }

  @Test
  void testGetBalanceForSavingsAccount() throws AccountNotFoundException {
    // Access balance through the account object since BankAtm doesn't expose getBalance
    assertEquals(500.0, savingsAccount.getBalance());

    // Test that balance changes after transactions
    bankAtm.depositFunds("SAV001", 100.0);
    assertEquals(600.0, savingsAccount.getBalance());

    bankAtm.withdrawFunds("SAV001", 50.0);
    assertEquals(550.0, savingsAccount.getBalance());
  }

  @Test
  void testAuditLogPersistsThroughMultipleSessions() throws Exception {
    // Simulate multiple banking sessions

    // Session 1: Some transactions
    bankAtm.depositFunds("CHK001", 100.0);
    bankAtm.withdrawFunds("SAV001", 25.0);

    int transactionsAfterSession1 = bankAtm.getAuditLog().getTransactionCount();
    assertEquals(2, transactionsAfterSession1);

    // Session 2: More transactions
    bankAtm.depositFunds("SAV001", 75.0);
    bankAtm.withdrawFunds("CHK001", 50.0);

    int transactionsAfterSession2 = bankAtm.getAuditLog().getTransactionCount();
    assertEquals(4, transactionsAfterSession2);

    // All transactions should still be recorded
    List<AuditLog.AuditEntry> allEntries = bankAtm.getAuditLog().getAllEntries();
    assertEquals(4, allEntries.size());
  }

  @Test
  void testCheckDepositWithAuditLogging() {
    // Test that check deposits are properly logged with source account information
    Check check = new Check("C001", 250.0, checkingAccount);

    bankAtm.depositFunds("CHK001", check);

    AuditLog auditLog = bankAtm.getAuditLog();
    List<AuditLog.AuditEntry> entries = auditLog.getEntriesForAccount("CHK001");

    assertEquals(1, entries.size());
    AuditLog.AuditEntry entry = entries.get(0);
    assertEquals(AuditLog.TransactionType.CREDIT, entry.getType());
    assertEquals(250.0, entry.getAmount());
    assertTrue(entry.getDescription().contains("Check deposit"));
    assertTrue(entry.getDescription().contains("CHK001")); // Source account info
  }
}
