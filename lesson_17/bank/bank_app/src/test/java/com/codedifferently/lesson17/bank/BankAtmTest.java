package com.codedifferently.lesson17.bank;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.codedifferently.lesson17.bank.exceptions.AccountNotFoundException;
import com.codedifferently.lesson17.bank.exceptions.CheckVoidedException;

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
    // Arrange
    Customer customer3 = new Customer(UUID.randomUUID(), "Alice Johnson");
    CheckingAccount account3 = new CheckingAccount("555555555", Set.of(customer3), 300.0);
    customer3.addAccount(account3);

    // Act
    classUnderTest.addAccount(account3);

    // Assert
    Set<CheckingAccount> accounts = classUnderTest.findAccountsByCustomerId(customer3.getId());
    assertThat(accounts).containsOnly(account3);
  }

  @Test
  void testFindAccountsByCustomerId() {
    // Act
    Set<CheckingAccount> accounts = classUnderTest.findAccountsByCustomerId(customer1.getId());

    // Assert
    assertThat(accounts).containsOnly(account1, account2);
  }

  @Test
  void testDepositFunds() {
    // Act
    classUnderTest.depositFunds(account1.getAccountNumber(), 50.0);

    // Assert
    assertThat(account1.getBalance()).isEqualTo(150.0);
    
    // Verify audit log
    var auditEntries = classUnderTest.getAuditLog().getEntriesForAccount(account1.getAccountNumber());
    assertThat(auditEntries).hasSize(1);
    assertThat(auditEntries.get(0).getType()).isEqualTo(AuditLog.TransactionType.CREDIT);
    assertThat(auditEntries.get(0).getAmount()).isEqualTo(50.0);
    assertThat(auditEntries.get(0).getDescription()).isEqualTo("Cash deposit");
  }

  @Test
  void testDepositFunds_Check() {
    // Arrange
    Check check = new Check("987654321", 100.0, account1);

    // Act
    classUnderTest.depositFunds("987654321", check);

    // Assert
    assertThat(account1.getBalance()).isEqualTo(0);
    assertThat(account2.getBalance()).isEqualTo(300.0);
    
    // Verify audit log
    var auditEntries = classUnderTest.getAuditLog().getEntriesForAccount("987654321");
    assertThat(auditEntries).hasSize(1);
    assertThat(auditEntries.get(0).getType()).isEqualTo(AuditLog.TransactionType.CREDIT);
    assertThat(auditEntries.get(0).getAmount()).isEqualTo(100.0);
    assertThat(auditEntries.get(0).getDescription()).isEqualTo("Check deposit - Check #987654321");
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
    
    // Verify audit log
    var auditEntries = classUnderTest.getAuditLog().getEntriesForAccount(account2.getAccountNumber());
    assertThat(auditEntries).hasSize(1);
    assertThat(auditEntries.get(0).getType()).isEqualTo(AuditLog.TransactionType.DEBIT);
    assertThat(auditEntries.get(0).getAmount()).isEqualTo(50.0);
    assertThat(auditEntries.get(0).getDescription()).isEqualTo("Cash withdrawal");
  }

  @Test
  void testWithdrawFunds_AccountNotFound() {
    String nonExistingAccountNumber = "999999999";

    // Act & Assert
    assertThatExceptionOfType(AccountNotFoundException.class)
        .isThrownBy(() -> classUnderTest.withdrawFunds(nonExistingAccountNumber, 50.0))
        .withMessage("Account not found");
  }
  
  @Test
  void testWithdrawFunds_InsufficientFunds() {
    // Act & Assert
    assertThatExceptionOfType(RuntimeException.class)
        .isThrownBy(() -> classUnderTest.withdrawFunds(account1.getAccountNumber(), 200.0))
        .withMessageContaining("Insufficient funds for withdrawal");
  }
  
  @Test
  void testDepositFunds_AccountNotFound() {
    String nonExistingAccountNumber = "999999999";

    // Act & Assert
    assertThatExceptionOfType(AccountNotFoundException.class)
        .isThrownBy(() -> classUnderTest.depositFunds(nonExistingAccountNumber, 50.0))
        .withMessage("Account not found");
  }
  
  @Test
  void testFindAccountsByCustomerId_NonExistentCustomer() {
    // Arrange
    UUID nonExistentCustomerId = UUID.randomUUID();
    
    // Act
    Set<CheckingAccount> accounts = classUnderTest.findAccountsByCustomerId(nonExistentCustomerId);

    // Assert
    assertThat(accounts).isEmpty();
  }
  
  @Test
  void testAuditLog_MultipleTransactions() {
    // Act
    classUnderTest.depositFunds(account1.getAccountNumber(), 25.0);
    classUnderTest.withdrawFunds(account1.getAccountNumber(), 10.0);
    classUnderTest.depositFunds(account1.getAccountNumber(), 15.0);
    
    // Assert
    var auditEntries = classUnderTest.getAuditLog().getEntriesForAccount(account1.getAccountNumber());
    assertThat(auditEntries).hasSize(3);
    assertThat(auditEntries.get(0).getType()).isEqualTo(AuditLog.TransactionType.CREDIT);
    assertThat(auditEntries.get(1).getType()).isEqualTo(AuditLog.TransactionType.DEBIT);
    assertThat(auditEntries.get(2).getType()).isEqualTo(AuditLog.TransactionType.CREDIT);
    
    // Verify all audit entries exist
    var allEntries = classUnderTest.getAuditLog().getEntries();
    assertThat(allEntries).hasSize(3);
  }
  
  @Test
  void testAddSavingsAccount() {
    // Arrange
    Customer customer3 = new Customer(UUID.randomUUID(), "Alice Johnson");
    SavingsAccount savingsAccount = new SavingsAccount("SAV123456789", Set.of(customer3), 300.0);
    customer3.addAccount(savingsAccount);

    // Act
    classUnderTest.addAccount(savingsAccount);

    // Assert - verify deposits work on savings accounts
    classUnderTest.depositFunds("SAV123456789", 50.0);
    assertThat(savingsAccount.getBalance()).isEqualTo(350.0);
  }
  
  @Test
  void testSavingsAccount_CashDeposit() {
    // Arrange
    Customer customer3 = new Customer(UUID.randomUUID(), "Alice Johnson");
    SavingsAccount savingsAccount = new SavingsAccount("SAV123456789", Set.of(customer3), 300.0);
    customer3.addAccount(savingsAccount);
    classUnderTest.addAccount(savingsAccount);

    // Act
    classUnderTest.depositFunds("SAV123456789", 100.0);

    // Assert
    assertThat(savingsAccount.getBalance()).isEqualTo(400.0);
    
    // Verify audit log
    var auditEntries = classUnderTest.getAuditLog().getEntriesForAccount("SAV123456789");
    assertThat(auditEntries).hasSize(1);
    assertThat(auditEntries.get(0).getType()).isEqualTo(AuditLog.TransactionType.CREDIT);
    assertThat(auditEntries.get(0).getDescription()).isEqualTo("Cash deposit");
  }
  
  @Test
  void testSavingsAccount_CashWithdrawal() {
    // Arrange
    Customer customer3 = new Customer(UUID.randomUUID(), "Alice Johnson");
    SavingsAccount savingsAccount = new SavingsAccount("SAV123456789", Set.of(customer3), 300.0);
    customer3.addAccount(savingsAccount);
    classUnderTest.addAccount(savingsAccount);

    // Act
    classUnderTest.withdrawFunds("SAV123456789", 50.0);

    // Assert
    assertThat(savingsAccount.getBalance()).isEqualTo(250.0);
    
    // Verify audit log
    var auditEntries = classUnderTest.getAuditLog().getEntriesForAccount("SAV123456789");
    assertThat(auditEntries).hasSize(1);
    assertThat(auditEntries.get(0).getType()).isEqualTo(AuditLog.TransactionType.DEBIT);
    assertThat(auditEntries.get(0).getDescription()).isEqualTo("Cash withdrawal");
  }
  
  @Test
  void testSavingsAccount_CheckDepositNotAllowed() {
    // Arrange
    Customer customer3 = new Customer(UUID.randomUUID(), "Alice Johnson");
    SavingsAccount savingsAccount = new SavingsAccount("SAV123456789", Set.of(customer3), 300.0);
    customer3.addAccount(savingsAccount);
    classUnderTest.addAccount(savingsAccount);
    Check check = new Check("12345", 100.0, account1);

    // Act & Assert
    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> classUnderTest.depositFunds("SAV123456789", check))
        .withMessage("Savings accounts do not support check deposits");
  }
}
