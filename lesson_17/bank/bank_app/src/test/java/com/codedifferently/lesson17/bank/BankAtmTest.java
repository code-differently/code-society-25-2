package com.codedifferently.lesson17.bank;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import com.codedifferently.lesson17.bank.exceptions.AccountNotFoundException;
import com.codedifferently.lesson17.bank.exceptions.CheckVoidedException;
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
    // Arrange
    Customer customer3 = new Customer(UUID.randomUUID(), "Alice Johnson");
    CheckingAccount account3 = new CheckingAccount("555555555", Set.of(customer3), 300.0);
    customer3.addAccount(account3);

    // Act
    classUnderTest.addAccount(account3);

    // Assert
    Set<Account> accounts = classUnderTest.findAccountsByCustomerId(customer3.getId());
    assertThat(accounts).containsOnly(account3);
  }

  @Test
  void testFindAccountsByCustomerId() {
    // Act
    Set<Account> accounts = classUnderTest.findAccountsByCustomerId(customer1.getId());

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
  void testDepositFunds_AccountNotFound() {
    String nonExistingAccountNumber = "999999999";

    // Act & Assert
    assertThatExceptionOfType(AccountNotFoundException.class)
        .isThrownBy(() -> classUnderTest.depositFunds(nonExistingAccountNumber, 50.0))
        .withMessage("Account not found");
  }

  @Test
  void testDepositFunds_Check_AccountNotFound() {
    Check check = new Check("123456789", 100.0, account1);

    // Act
    assertThatExceptionOfType(AccountNotFoundException.class)
        .isThrownBy(() -> classUnderTest.depositFunds("999999999", check))
        .withMessage("Account not found");
  }

  @Test
  void testFindAccountsByCustomerId_CustomerNotFound() {
    UUID nonExistingCustomerId = UUID.randomUUID();

    // Act
    Set<Account> accounts = classUnderTest.findAccountsByCustomerId(nonExistingCustomerId);

    // Assert
    assertThat(accounts).isEmpty();
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

  // ========== SAVINGS ACCOUNT INTEGRATION TESTS ==========

  @Test
  void testAddSavingsAccount() {
    // Arrange
    Customer customer3 = new Customer(UUID.randomUUID(), "Alice Johnson");
    SavingsAccount savingsAccount = new SavingsAccount("SAV-123456", Set.of(customer3), 1000.0);
    customer3.addAccount(savingsAccount);

    // Act
    classUnderTest.addAccount(savingsAccount);

    // Assert
    Set<Account> accounts = classUnderTest.findAccountsByCustomerId(customer3.getId());
    assertThat(accounts).containsOnly(savingsAccount);
  }

  @Test
  void testDepositFunds_SavingsAccount() {
    // Arrange
    Customer customer3 = new Customer(UUID.randomUUID(), "Alice Johnson");
    SavingsAccount savingsAccount = new SavingsAccount("SAV-123456", Set.of(customer3), 1000.0);
    customer3.addAccount(savingsAccount);
    classUnderTest.addAccount(savingsAccount);

    // Act
    classUnderTest.depositFunds("SAV-123456", 250.0);

    // Assert
    assertThat(savingsAccount.getBalance()).isEqualTo(1250.0);
  }

  @Test
  void testWithdrawFunds_SavingsAccount() {
    // Arrange
    Customer customer3 = new Customer(UUID.randomUUID(), "Alice Johnson");
    SavingsAccount savingsAccount = new SavingsAccount("SAV-123456", Set.of(customer3), 1000.0);
    customer3.addAccount(savingsAccount);
    classUnderTest.addAccount(savingsAccount);

    // Act
    classUnderTest.withdrawFunds("SAV-123456", 150.0);

    // Assert
    assertThat(savingsAccount.getBalance()).isEqualTo(850.0);
  }

  @Test
  void testDepositFunds_CheckToSavingsAccount() {
    // Arrange - Create source checking account and target savings account
    Customer sourceCustomer = new Customer(UUID.randomUUID(), "Source Customer");
    CheckingAccount sourceAccount =
        new CheckingAccount("CHK-SOURCE", Set.of(sourceCustomer), 500.0);

    Customer targetCustomer = new Customer(UUID.randomUUID(), "Target Customer");
    SavingsAccount targetSavingsAccount =
        new SavingsAccount("SAV-TARGET", Set.of(targetCustomer), 1000.0);

    sourceCustomer.addAccount(sourceAccount);
    targetCustomer.addAccount(targetSavingsAccount);
    classUnderTest.addAccount(sourceAccount);
    classUnderTest.addAccount(targetSavingsAccount);

    Check check = new Check("SAV-TARGET", 200.0, sourceAccount);

    // Act
    classUnderTest.depositFunds("SAV-TARGET", check);

    // Assert
    assertThat(sourceAccount.getBalance()).isEqualTo(300.0); // 500 - 200
    assertThat(targetSavingsAccount.getBalance()).isEqualTo(1200.0); // 1000 + 200
  }

  // ========== BUSINESS CHECKING ACCOUNT INTEGRATION TESTS ==========

  @Test
  void testAddBusinessCheckingAccount() {
    // Arrange
    BusinessCustomer businessCustomer = new BusinessCustomer(UUID.randomUUID(), "ABC Corp");
    BusinessCheckingAccount businessAccount =
        new BusinessCheckingAccount("BIZ-123456", "ABC Corp", businessCustomer);
    businessCustomer.addAccount(businessAccount);

    // Act
    classUnderTest.addAccount(businessAccount);

    // Assert
    Set<Account> accounts = classUnderTest.findAccountsByCustomerId(businessCustomer.getId());
    assertThat(accounts).containsOnly(businessAccount);
  }

  @Test
  void testDepositFunds_BusinessCheckingAccount() {
    // Arrange
    BusinessCustomer businessCustomer = new BusinessCustomer(UUID.randomUUID(), "ABC Corp");
    BusinessCheckingAccount businessAccount =
        new BusinessCheckingAccount("BIZ-123456", "ABC Corp", businessCustomer);
    businessCustomer.addAccount(businessAccount);
    classUnderTest.addAccount(businessAccount);

    // Act
    classUnderTest.depositFunds("BIZ-123456", 5000.0);

    // Assert
    assertThat(businessAccount.getBalance()).isEqualTo(5000.0);
  }

  @Test
  void testWithdrawFunds_BusinessCheckingAccount() {
    // Arrange
    BusinessCustomer businessCustomer = new BusinessCustomer(UUID.randomUUID(), "ABC Corp");
    BusinessCheckingAccount businessAccount =
        new BusinessCheckingAccount("BIZ-123456", "ABC Corp", businessCustomer);
    businessAccount.deposit(10000.0); // Add initial funds
    businessCustomer.addAccount(businessAccount);
    classUnderTest.addAccount(businessAccount);

    // Act
    classUnderTest.withdrawFunds("BIZ-123456", 2500.0);

    // Assert
    assertThat(businessAccount.getBalance()).isEqualTo(7500.0);
  }

  @Test
  void testDepositFunds_CheckToBusinessCheckingAccount() {
    // Arrange - Create source checking account and target business account
    Customer sourceCustomer = new Customer(UUID.randomUUID(), "Individual Customer");
    CheckingAccount sourceAccount =
        new CheckingAccount("CHK-INDIVIDUAL", Set.of(sourceCustomer), 3000.0);

    BusinessCustomer businessCustomer = new BusinessCustomer(UUID.randomUUID(), "XYZ Corp");
    BusinessCheckingAccount businessAccount =
        new BusinessCheckingAccount("BIZ-XYZ", "XYZ Corp", businessCustomer);
    businessAccount.deposit(5000.0); // Initial business account balance

    sourceCustomer.addAccount(sourceAccount);
    businessCustomer.addAccount(businessAccount);
    classUnderTest.addAccount(sourceAccount);
    classUnderTest.addAccount(businessAccount);

    Check check = new Check("BIZ-XYZ", 1500.0, sourceAccount);

    // Act
    classUnderTest.depositFunds("BIZ-XYZ", check);

    // Assert
    assertThat(sourceAccount.getBalance()).isEqualTo(1500.0); // 3000 - 1500
    assertThat(businessAccount.getBalance()).isEqualTo(6500.0); // 5000 + 1500
  }

  @Test
  void testDepositFunds_CheckFromBusinessAccount() {
    // Arrange - Business account writing check to individual account
    BusinessCustomer businessCustomer = new BusinessCustomer(UUID.randomUUID(), "Business Corp");
    BusinessCheckingAccount businessAccount =
        new BusinessCheckingAccount("BIZ-CORP", "Business Corp", businessCustomer);
    businessAccount.deposit(10000.0); // Initial business account balance

    Customer individualCustomer = new Customer(UUID.randomUUID(), "John Individual");
    CheckingAccount individualAccount =
        new CheckingAccount("CHK-INDIVIDUAL", Set.of(individualCustomer), 500.0);

    businessCustomer.addAccount(businessAccount);
    individualCustomer.addAccount(individualAccount);
    classUnderTest.addAccount(businessAccount);
    classUnderTest.addAccount(individualAccount);

    Check businessCheck = new Check("CHK-INDIVIDUAL", 2000.0, businessAccount);

    // Act
    classUnderTest.depositFunds("CHK-INDIVIDUAL", businessCheck);

    // Assert
    assertThat(businessAccount.getBalance()).isEqualTo(8000.0); // 10000 - 2000
    assertThat(individualAccount.getBalance()).isEqualTo(2500.0); // 500 + 2000
  }

  // ========== MIXED ACCOUNT TYPE INTEGRATION TESTS ==========

  @Test
  void testFindAccountsByCustomerId_WithMultipleAccountTypes() {
    // Arrange - Customer with both savings and checking accounts
    Customer customer = new Customer(UUID.randomUUID(), "Multi Account Customer");
    CheckingAccount checkingAccount = new CheckingAccount("CHK-MULTI", Set.of(customer), 1000.0);
    SavingsAccount savingsAccount = new SavingsAccount("SAV-MULTI", Set.of(customer), 5000.0);

    customer.addAccount(checkingAccount);
    customer.addAccount(savingsAccount);
    classUnderTest.addAccount(checkingAccount);
    classUnderTest.addAccount(savingsAccount);

    // Act
    Set<Account> accounts = classUnderTest.findAccountsByCustomerId(customer.getId());

    // Assert
    assertThat(accounts).containsOnly(checkingAccount, savingsAccount);
    assertThat(accounts).hasSize(2);
  }

  @Test
  void testTransferBetweenDifferentAccountTypes() {
    // Arrange - Transfer from checking to savings using check
    Customer customer = new Customer(UUID.randomUUID(), "Transfer Customer");
    CheckingAccount checkingAccount = new CheckingAccount("CHK-TRANSFER", Set.of(customer), 2000.0);
    SavingsAccount savingsAccount = new SavingsAccount("SAV-TRANSFER", Set.of(customer), 3000.0);

    customer.addAccount(checkingAccount);
    customer.addAccount(savingsAccount);
    classUnderTest.addAccount(checkingAccount);
    classUnderTest.addAccount(savingsAccount);

    Check transferCheck = new Check("SAV-TRANSFER", 800.0, checkingAccount);

    // Act
    classUnderTest.depositFunds("SAV-TRANSFER", transferCheck);

    // Assert
    assertThat(checkingAccount.getBalance()).isEqualTo(1200.0); // 2000 - 800
    assertThat(savingsAccount.getBalance()).isEqualTo(3800.0); // 3000 + 800
  }
}
