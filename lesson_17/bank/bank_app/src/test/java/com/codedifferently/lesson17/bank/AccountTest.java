package com.codedifferently.lesson17.bank;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AccountTest {

  private Account account;
  private Customer customer;

  @BeforeEach
  void setUp() {
    customer = new Customer(UUID.randomUUID(), "John Doe");
    account = new CheckingAccount("123456789", Set.of(customer), 100.0);
  }

  @Test
  void testAccountInterface_deposit() {
    // Act
    account.deposit(50.0);

    // Assert
    assertThat(account.getBalance()).isEqualTo(150.0);
  }

  @Test
  void testAccountInterface_withdraw() throws Exception {
    // Act
    account.withdraw(25.0);

    // Assert
    assertThat(account.getBalance()).isEqualTo(75.0);
  }

  @Test
  void testAccountInterface_getAccountNumber() {
    // Assert
    assertThat(account.getAccountNumber()).isEqualTo("123456789");
  }

  @Test
  void testAccountInterface_getOwners() {
    // Assert
    assertThat(account.getOwners()).containsOnly(customer);
  }

  @Test
  void testAccountInterface_closeAccount() throws Exception {
    // Arrange
    account.withdraw(100.0); // Withdraw all funds

    // Act
    account.closeAccount();

    // Assert
    assertThat(account.isClosed()).isTrue();
  }
}
