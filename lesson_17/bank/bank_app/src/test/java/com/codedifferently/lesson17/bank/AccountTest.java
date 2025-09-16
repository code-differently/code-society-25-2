package com.codedifferently.lesson17.bank;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import com.codedifferently.lesson17.bank.exceptions.InsufficientFundsException;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AccountTest {

  private Account checkingAccount;
  private Customer customer;

  @BeforeEach
  void setUp() {
    customer = new Customer(UUID.randomUUID(), "John Doe");
    checkingAccount = new CheckingAccount("111111111", Set.of(customer), 200.0);
    customer.addAccount(checkingAccount);
  }

  @Test
  void testInitialBalance() {
    assertThat(checkingAccount.getBalance()).isEqualTo(200.0);
  }

  @Test
  void testDeposit() {
    checkingAccount.deposit(50.0);
    assertThat(checkingAccount.getBalance()).isEqualTo(250.0);
  }

  @Test
  void testWithdraw() {
    checkingAccount.withdraw(100.0);
    assertThat(checkingAccount.getBalance()).isEqualTo(100.0);
  }

  @Test
  void testWithdraw_InsufficientFunds() {
    assertThatExceptionOfType(InsufficientFundsException.class)
        .isThrownBy(() -> checkingAccount.withdraw(300.0));
  }

  @Test
  void testEqualsAndHashCode() {
    Account other = new CheckingAccount("111111111", Set.of(customer), 500.0);
    assertThat(checkingAccount).isEqualTo(other);
    assertThat(checkingAccount.hashCode()).isEqualTo(other.hashCode());
  }
}
