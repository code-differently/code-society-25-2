package com.codedifferently.lesson17.bank;

import java.util.Set;
import java.util.UUID;

import javax.security.auth.login.AccountNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.codedifferently.lesson17.bank.exceptions.CheckVoidedException;

class BankAtmTest {

  private BankAtm classUnderTest;
  private CheckingAccount account1;
  private CheckingAccount account2;
  private SavingsAccount savingsAccount;
  private Customer customer1;
  private Customer customer2;

  @BeforeEach
  void setUp() {
    classUnderTest = new BankAtm();
    customer1 = new Customer(UUID.randomUUID(), "John Doe");
    customer2 = new Customer(UUID.randomUUID(), "Jane Smith");
    account1 = new CheckingAccount("123456789", Set.of(customer1), 100.0);
    account2 = new CheckingAccount("987654321", Set.of(customer1, customer2), 200.0);
    savingsAccount = new SavingsAccount("111222333", Set.of(customer2), 500.0);
    customer1.addAccount(account1);
    customer1.addAccount(account2);
    customer2.addAccount(account2);
    classUnderTest.addAccount(account1);
    classUnderTest.addAccount(account2);
    classUnderTest.addAccount(savingsAccount);
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

  @Test
  void testDepositFunds_SavingsAccount() {
    // Act
    classUnderTest.depositFunds(savingsAccount.getAccountNumber(), 100.0);

    // Assert
    assertThat(savingsAccount.getBalance()).isEqualTo(600.0);
  }

  @Test
  void testWithdrawFunds_SavingsAccount() {
    // Act
    classUnderTest.withdrawFunds(savingsAccount.getAccountNumber(), 200.0);

    // Assert
    assertThat(savingsAccount.getBalance()).isEqualTo(300.0);
  }

  @Test
  void testAuditLog_RecordsTransactions() {
    // Act
    classUnderTest.depositFunds(account1.getAccountNumber(), 50.0);
    classUnderTest.withdrawFunds(account2.getAccountNumber(), 25.0);
    classUnderTest.depositFunds(savingsAccount.getAccountNumber(), 75.0);
    classUnderTest.withdrawFunds(savingsAccount.getAccountNumber(), 25.0);

    // Assert
    var entries = classUnderTest.getAuditLog().getEntries();
    assertThat(entries).anyMatch(e -> e.contains("Deposited $50.0 to CheckingAccount"));
    assertThat(entries).anyMatch(e -> e.contains("Withdrew $25.0 from CheckingAccount"));
    assertThat(entries).anyMatch(e -> e.contains("Deposited $75.0 to SavingsAccount"));
    assertThat(entries).anyMatch(e -> e.contains("Withdrew $25.0 from SavingsAccount"));
  }
}
