package com.codedifferently.lesson17.bank;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.Test;

public class SavingsAccountTest {
  @Test
  void depositAndWithdrawWork() {
    // Arrange
    Customer customer = new Customer(UUID.randomUUID(), "John Doe", false);
    SavingsAccount account = new SavingsAccount("123456789", Set.of(customer), 100.0);

    // Act
    account.deposit(50.0);
    account.withdraw(25.0);

    // Assert
    assertThat(account.getBalance()).isEqualTo(125.0);
  }

  @Test
  void multipleTransactionsWorkCorrectly() {
    // Arrange
    Customer customer = new Customer(UUID.randomUUID(), "John Doe", false);
    SavingsAccount account = new SavingsAccount("123456789", Set.of(customer), 1000.0);

    // Act
    account.deposit(500.0); // Balance should be 1500
    account.withdraw(200.0); // Balance should be 1300
    account.deposit(700.0); // Balance should be 2000
    account.withdraw(500.0); // Balance should be 1500

    // Assert
    assertThat(account.getBalance()).isEqualTo(1500.0);
    assertThat(account.getAccountNumber()).isEqualTo("123456789");
    assertThat(account.getOwners()).containsExactly(customer);
  }
}
