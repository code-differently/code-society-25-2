package com.codedifferently.lesson17.bank;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.codedifferently.lesson17.bank.exceptions.AccountNotFoundException;
import com.codedifferently.lesson17.bank.exceptions.CheckVoidedException;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BankAtmTest {

  private BankAtm classUnderTest;
  private CheckingAccount account1;
  private CheckingAccount account2;
  private Customer customer1;
  private Customer customer2;

  @BeforeEach
  void setUp() {
    classUnderTest = new BankAtm();
    customer1 = new Customer(UUID.randomUUID(), "John Doe");
    customer2 = new Customer(UUID.randomUUID(), "Jane Smith");
    account1 = new CheckingAccount("123456789", Set.of(customer1), 100.0);
    account2 = new CheckingAccount("987654321", Set.of(customer1, customer2), 200.0);
    customer1.addAccount(account1);
    customer1.addAccount(account2);
    customer2.addAccount(account2);
    classUnderTest.addAccount(account1);
    classUnderTest.addAccount(account2);
  }

  @Test
  void testAddAccount() {
    Customer customer3 = new Customer(UUID.randomUUID(), "Alice Johnson");
    CheckingAccount account3 = new CheckingAccount("555555555", Set.of(customer3), 300.0);
    customer3.addAccount(account3);

    classUnderTest.addAccount(account3);

    Set<Account> accounts = classUnderTest.findAccountsByCustomerId(customer3.getId());
    assertThat(accounts).containsOnly(account3);
  }

  @Test
  void testFindAccountsByCustomerId() {
    Set<Account> accounts = classUnderTest.findAccountsByCustomerId(customer1.getId());

    assertThat(accounts).containsOnly(account1, account2);
  }

  @Test
  void testDepositFunds() {
    classUnderTest.depositFunds(account1.getAccountNumber(), 50.0);

    assertThat(account1.getBalance()).isEqualTo(150.0);
  }

  @Test
  void testDepositFunds_Check() {
    Check check = new Check("987654321", 100.0, account1);

    classUnderTest.depositFunds("987654321", check);

    assertThat(account1.getBalance()).isEqualTo(0);
    assertThat(account2.getBalance()).isEqualTo(300.0);
  }

  @Test
  void testDepositFunds_DoesntDepositCheckTwice() {
    Check check = new Check("987654321", 100.0, account1);

    classUnderTest.depositFunds("987654321", check);

    assertThatExceptionOfType(CheckVoidedException.class)
        .isThrownBy(() -> classUnderTest.depositFunds("987654321", check))
        .withMessage("Check is voided");
  }

  @Test
  void testWithdrawFunds() {
    // Act
    classUnderTest.withdrawFunds(account2.getAccountNumber(), 50.0);

    // Assert
    assertThat(account2.getBalance()).isEqualTo(150.0);
  }

  @Test
  void testWithdrawFunds_AccountNotFound() {
    String nonExistingAccountNumber = "999999999";

    // Act & Assert
    assertThatExceptionOfType(AccountNotFoundException.class)
        .isThrownBy(() -> classUnderTest.withdrawFunds(nonExistingAccountNumber, 50.0))
        .withMessage("Account not found");
  }

  // ========== SavingsAccount Integration Tests ==========

  @Test
  void testAddSavingsAccount() {
    // Arrange
    Customer customer3 = new Customer(UUID.randomUUID(), "Alice Johnson");
    SavingsAccount savingsAccount = new SavingsAccount("555555555", Set.of(customer3), 300.0);
    customer3.addAccount(savingsAccount);

    // Act
    classUnderTest.addAccount(savingsAccount);

    // Assert
    Set<Account> accounts = classUnderTest.findAccountsByCustomerId(customer3.getId());
    assertThat(accounts).containsOnly(savingsAccount);
  }

  @Test
  void testDepositCashToSavingsAccount() {
    // Arrange
    Customer customer3 = new Customer(UUID.randomUUID(), "Alice Johnson");
    SavingsAccount savingsAccount = new SavingsAccount("555555555", Set.of(customer3), 300.0);
    customer3.addAccount(savingsAccount);
    classUnderTest.addAccount(savingsAccount);

    // Act
    classUnderTest.depositFunds(savingsAccount.getAccountNumber(), 150.0);

    // Assert
    assertThat(savingsAccount.getBalance()).isEqualTo(450.0);
  }

  @Test
  void testWithdrawFromSavingsAccount() {
    // Arrange
    Customer customer3 = new Customer(UUID.randomUUID(), "Alice Johnson");
    SavingsAccount savingsAccount = new SavingsAccount("555555555", Set.of(customer3), 300.0);
    customer3.addAccount(savingsAccount);
    classUnderTest.addAccount(savingsAccount);

    // Act
    classUnderTest.withdrawFunds(savingsAccount.getAccountNumber(), 100.0);

    // Assert
    assertThat(savingsAccount.getBalance()).isEqualTo(200.0);
  }

  @Test
  void testDepositCheckToSavingsAccount_ShouldFail() {
    Customer customer3 = new Customer(UUID.randomUUID(), "Alice Johnson");
    SavingsAccount savingsAccount = new SavingsAccount("555555555", Set.of(customer3), 300.0);
    customer3.addAccount(savingsAccount);
    classUnderTest.addAccount(savingsAccount);

    Check check = new Check("123456", 100.0, account1);

    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> classUnderTest.depositFunds(savingsAccount.getAccountNumber(), check))
        .withMessage("Check deposits are only allowed for checking accounts");
  }

  @Test
  void testFindAccountsByCustomerId_WithSavingsAccount() {
    Customer customer3 = new Customer(UUID.randomUUID(), "Alice Johnson");
    SavingsAccount savingsAccount = new SavingsAccount("555555555", Set.of(customer3), 300.0);
    CheckingAccount checkingAccount = new CheckingAccount("666666666", Set.of(customer3), 400.0);
    customer3.addAccount(savingsAccount);
    customer3.addAccount(checkingAccount);
    classUnderTest.addAccount(savingsAccount);
    classUnderTest.addAccount(checkingAccount);

    Set<Account> accounts = classUnderTest.findAccountsByCustomerId(customer3.getId());

    assertThat(accounts).containsOnly(savingsAccount, checkingAccount);
  }

  @Test
  void testDepositCheckToCheckingAccount_StillWorks() {
    Check check = new Check("987654321", 100.0, account1);

    classUnderTest.depositFunds("987654321", check);

    // Check source and destination accounts
    assertThat(account1.getBalance()).isEqualTo(0);
    assertThat(account2.getBalance()).isEqualTo(300.0);
  }

  @Test
  void testDepositFunds_LogsTransaction() {
    classUnderTest.depositFunds(account1.getAccountNumber(), 50.0);

    AuditLog auditLog = classUnderTest.getAuditLog();
    assertEquals(1, auditLog.getTransactionCount());

    List<Transaction> transactions =
        auditLog.getTransactionsForAccount(account1.getAccountNumber());
    assertEquals(1, transactions.size());

    Transaction transaction = transactions.get(0);
    assertEquals(TransactionType.DEPOSIT, transaction.type());
    assertEquals(50.0, transaction.amount());
    assertThat(transaction.description()).contains("Cash deposit of $50.00");
  }

  @Test
  void testWithdrawFunds_LogsTransaction() {
    classUnderTest.withdrawFunds(account2.getAccountNumber(), 75.0);

    AuditLog auditLog = classUnderTest.getAuditLog();
    assertEquals(1, auditLog.getTransactionCount());

    List<Transaction> transactions =
        auditLog.getTransactionsForAccount(account2.getAccountNumber());
    assertEquals(1, transactions.size());

    Transaction transaction = transactions.get(0);
    assertEquals(TransactionType.WITHDRAWAL, transaction.type());
    assertEquals(75.0, transaction.amount());
    assertThat(transaction.description()).contains("Cash withdrawal of $75.00");
  }

  @Test
  void testDepositCheck_LogsTransferTransaction() {
    Check check = new Check("CHK123", 100.0, account1);
    classUnderTest.depositFunds(account2.getAccountNumber(), check);

    AuditLog auditLog = classUnderTest.getAuditLog();
    assertEquals(1, auditLog.getTransactionCount());

    List<Transaction> transactions =
        auditLog.getTransactionsForAccount(account2.getAccountNumber());
    assertEquals(1, transactions.size());

    Transaction transaction = transactions.get(0);
    assertEquals(TransactionType.TRANSFER, transaction.type());
    assertEquals(100.0, transaction.amount());
    assertThat(transaction.description()).contains("Transfer of $100.00");
    assertThat(transaction.description()).contains("CHK123");
    assertThat(transaction.description()).contains(account1.getAccountNumber());
    assertThat(transaction.description()).contains(account2.getAccountNumber());
  }

  @Test
  void testMultipleOperations_AuditLogTracksAll() {
    // Perform multiple operations
    classUnderTest.depositFunds(account1.getAccountNumber(), 100.0);
    classUnderTest.withdrawFunds(account1.getAccountNumber(), 25.0);
    classUnderTest.depositFunds(account2.getAccountNumber(), 200.0);

    Check check = new Check("CHK456", 50.0, account2);
    classUnderTest.depositFunds(account1.getAccountNumber(), check);

    AuditLog auditLog = classUnderTest.getAuditLog();
    assertEquals(4, auditLog.getTransactionCount());

    List<Transaction> account1Transactions =
        auditLog.getTransactionsForAccount(account1.getAccountNumber());
    assertEquals(3, account1Transactions.size());

    List<Transaction> account2Transactions =
        auditLog.getTransactionsForAccount(account2.getAccountNumber());
    assertEquals(1, account2Transactions.size());
    assertEquals(TransactionType.DEPOSIT, account2Transactions.get(0).type());
  }

  @Test
  void testSavingsAccountOperations_AreLogged() {
    Customer customer3 = new Customer(UUID.randomUUID(), "Alice Johnson");
    SavingsAccount savingsAccount = new SavingsAccount("SAVE001", Set.of(customer3), 500.0);
    customer3.addAccount(savingsAccount);
    classUnderTest.addAccount(savingsAccount);

    // Test deposit to savings account
    classUnderTest.depositFunds(savingsAccount.getAccountNumber(), 150.0);

    // Test withdrawal from savings account
    classUnderTest.withdrawFunds(savingsAccount.getAccountNumber(), 75.0);

    AuditLog auditLog = classUnderTest.getAuditLog();
    assertEquals(2, auditLog.getTransactionCount());

    List<Transaction> savingsTransactions =
        auditLog.getTransactionsForAccount(savingsAccount.getAccountNumber());
    assertEquals(2, savingsTransactions.size());

    // Verify deposit transaction
    Transaction depositTransaction = savingsTransactions.get(0);
    assertEquals(TransactionType.DEPOSIT, depositTransaction.type());
    assertEquals(150.0, depositTransaction.amount());

    // Verify withdrawal transaction
    Transaction withdrawalTransaction = savingsTransactions.get(1);
    assertEquals(TransactionType.WITHDRAWAL, withdrawalTransaction.type());
    assertEquals(75.0, withdrawalTransaction.amount());
  }
}
