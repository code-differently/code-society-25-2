package com.codedifferently.lesson17.bank;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

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
  void testAuditLog_AddAccountLogsTransaction() {
    // Arrange
    Customer customer3 = new Customer(UUID.randomUUID(), "Alice Johnson");
    CheckingAccount account3 = new CheckingAccount("555555555", Set.of(customer3), 300.0);
    customer3.addAccount(account3);

    // Act
    classUnderTest.addAccount(account3);

    // Assert
    List<String> transactions = classUnderTest.auditLog.getTransactions(customer3.getId());
    assertThat(transactions).hasSize(1);
    assertThat(transactions.get(0)).isEqualTo("Added account 555555555");
  }

  @Test
  void testAuditLog_DepositFundsLogsTransaction() {
    // Arrange - Create a fresh BankAtm for this test
    BankAtm freshBankAtm = new BankAtm();
    Customer testCustomer = new Customer(UUID.randomUUID(), "Test Customer");
    CheckingAccount testAccount = new CheckingAccount("999999999", Set.of(testCustomer), 100.0);
    testCustomer.addAccount(testAccount);
    freshBankAtm.addAccount(testAccount);

    // Act
    freshBankAtm.depositFunds(testAccount.getAccountNumber(), 75.0);

    // Assert
    List<String> transactions = freshBankAtm.auditLog.getTransactions(testCustomer.getId());
    assertThat(transactions).hasSize(2); // 1 for addAccount, 1 for deposit
    assertThat(transactions.get(0)).isEqualTo("Added account 999999999");
    assertThat(transactions.get(1)).isEqualTo("Deposited 75.0 to account 999999999");
  }

  @Test
  void testAuditLog_DepositCheckLogsTransaction() {
    // Arrange - Create a fresh BankAtm for this test
    BankAtm freshBankAtm = new BankAtm();
    Customer testCustomer = new Customer(UUID.randomUUID(), "Test Customer");
    CheckingAccount testAccount = new CheckingAccount("888888888", Set.of(testCustomer), 100.0);
    testCustomer.addAccount(testAccount);
    freshBankAtm.addAccount(testAccount);

    Check check = new Check("888888888", 50.0, testAccount);

    // Act
    freshBankAtm.depositFunds("888888888", check);

    // Assert
    List<String> transactions = freshBankAtm.auditLog.getTransactions(testCustomer.getId());
    assertThat(transactions).hasSize(2); // 1 for addAccount, 1 for deposit check
    assertThat(transactions.get(0)).isEqualTo("Added account 888888888");
    assertThat(transactions.get(1)).contains("Deposited check of");
    assertThat(transactions.get(1)).contains("to account 888888888");
  }

  @Test
  void testAuditLog_WithdrawFundsLogsTransaction() {
    // Arrange - Create a fresh BankAtm for this test
    BankAtm freshBankAtm = new BankAtm();
    Customer testCustomer = new Customer(UUID.randomUUID(), "Test Customer");
    CheckingAccount testAccount = new CheckingAccount("777777777", Set.of(testCustomer), 200.0);
    testCustomer.addAccount(testAccount);
    freshBankAtm.addAccount(testAccount);

    // Act
    freshBankAtm.withdrawFunds(testAccount.getAccountNumber(), 80.0);

    // Assert
    List<String> transactions = freshBankAtm.auditLog.getTransactions(testCustomer.getId());
    assertThat(transactions).hasSize(2); // 1 for addAccount, 1 for withdraw
    assertThat(transactions.get(0)).isEqualTo("Added account 777777777");
    assertThat(transactions.get(1)).isEqualTo("Withdrew 80.0 from account 777777777");
  }

  @Test
  void testAuditLog_MultipleTransactionsForSameCustomer() {
    // Arrange - Create a fresh BankAtm for this test
    BankAtm freshBankAtm = new BankAtm();
    Customer testCustomer = new Customer(UUID.randomUUID(), "Test Customer");
    CheckingAccount testAccount1 = new CheckingAccount("666666666", Set.of(testCustomer), 200.0);
    CheckingAccount testAccount2 = new CheckingAccount("555555555", Set.of(testCustomer), 300.0);
    testCustomer.addAccount(testAccount1);
    testCustomer.addAccount(testAccount2);
    freshBankAtm.addAccount(testAccount1);
    freshBankAtm.addAccount(testAccount2);

    // Act - Perform multiple transactions for the same customer
    freshBankAtm.depositFunds(testAccount1.getAccountNumber(), 50.0);
    freshBankAtm.withdrawFunds(testAccount1.getAccountNumber(), 25.0);
    freshBankAtm.depositFunds(testAccount2.getAccountNumber(), 100.0);

    // Assert
    List<String> transactions = freshBankAtm.auditLog.getTransactions(testCustomer.getId());
    assertThat(transactions).hasSize(5); // 2 for addAccount + 3 for transactions
    assertThat(transactions.get(0)).isEqualTo("Added account 666666666");
    assertThat(transactions.get(1)).isEqualTo("Added account 555555555");
    assertThat(transactions.get(2)).isEqualTo("Deposited 50.0 to account 666666666");
    assertThat(transactions.get(3)).isEqualTo("Withdrew 25.0 from account 666666666");
    assertThat(transactions.get(4)).isEqualTo("Deposited 100.0 to account 555555555");
  }

  @Test
  void testSavingsAccount_CannotDepositCheck() {
    // Arrange
    Customer customer3 = new Customer(UUID.randomUUID(), "Savings Customer");
    SavingsAccount savingsAccount = new SavingsAccount("444444444", Set.of(customer3), 500.0);
    customer3.addAccount(savingsAccount);
    classUnderTest.addAccount(savingsAccount);

    Check check = new Check("444444444", 100.0, account1);

    // Act & Assert
    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> classUnderTest.depositFunds("444444444", check))
        .withMessage("You can not deposit a check into a savings account");
  }

  @Test
  void testSavingsAccount_CanDepositCash() {
    // Arrange
    Customer customer3 = new Customer(UUID.randomUUID(), "Savings Customer");
    SavingsAccount savingsAccount = new SavingsAccount("333333333", Set.of(customer3), 500.0);
    customer3.addAccount(savingsAccount);
    classUnderTest.addAccount(savingsAccount);

    // Act
    classUnderTest.depositFunds("333333333", 75.0);

    // Assert
    assertThat(savingsAccount.getBalance()).isEqualTo(575.0);
  }
}
