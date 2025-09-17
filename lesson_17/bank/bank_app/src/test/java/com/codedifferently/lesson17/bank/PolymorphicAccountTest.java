package com.codedifferently.lesson17.bank;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PolymorphicAccountTest {

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

    bankAtm.addAccount(checkingAccount);
    bankAtm.addAccount(savingsAccount);
  }

  @Test
  void testFindAccountsByCustomerIdReturnsPolymorphicAccounts() {
    // Act
    Set<Account> accounts = bankAtm.findAccountsByCustomerId(customer.getId());

    // Assert - Should return both checking and savings accounts as Account interface
    assertThat(accounts).hasSize(2);
    assertThat(accounts).containsExactlyInAnyOrder(checkingAccount, savingsAccount);

    // Verify they are polymorphic - can be treated as Account interface
    for (Account account : accounts) {
      assertThat(account.getAccountNumber()).isNotEmpty();
      assertThat(account.getBalance()).isGreaterThanOrEqualTo(0);
      assertThat(account.getOwners()).contains(customer);
    }
  }

  @Test
  void testPolymorphicAccountOperations() {
    // Get accounts polymorphically
    Set<Account> accounts = bankAtm.findAccountsByCustomerId(customer.getId());

    // Find savings account polymorphically
    Account savingsAsAccount =
        accounts.stream()
            .filter(acc -> acc.getAccountNumber().equals("SAV001"))
            .findFirst()
            .orElseThrow();

    // Find checking account polymorphically
    Account checkingAsAccount =
        accounts.stream()
            .filter(acc -> acc.getAccountNumber().equals("CHK001"))
            .findFirst()
            .orElseThrow();

    // Both can be operated on through Account interface
    double initialSavingsBalance = savingsAsAccount.getBalance();
    double initialCheckingBalance = checkingAsAccount.getBalance();

    savingsAsAccount.deposit(100.0);
    checkingAsAccount.deposit(200.0);

    assertThat(savingsAsAccount.getBalance()).isEqualTo(initialSavingsBalance + 100.0);
    assertThat(checkingAsAccount.getBalance()).isEqualTo(initialCheckingBalance + 200.0);
  }

  @Test
  void testCustomerCanHoldMultipleAccountTypes() {
    // Verify customer can hold both account types
    Set<Account> customerAccounts = customer.getAccounts();

    assertThat(customerAccounts).hasSize(2);

    boolean hasChecking = customerAccounts.stream().anyMatch(acc -> acc instanceof CheckingAccount);
    boolean hasSavings = customerAccounts.stream().anyMatch(acc -> acc instanceof SavingsAccount);

    assertThat(hasChecking).isTrue();
    assertThat(hasSavings).isTrue();
  }
}
