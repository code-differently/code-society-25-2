package com.codedifferently.lesson17.bank;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import com.codedifferently.lesson17.bank.exceptions.InsufficientFundsException;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.Test;

public class SavingsAccountTest {
  @Test
  void depositAndWithdrawWork() {
    Customer customer = new Customer(UUID.randomUUID(), "John Doe", false);
    SavingsAccount account = new SavingsAccount("123456789", Set.of(customer), 100.0);

    account.deposit(50.0);
    account.withdraw(25.0);

    assertThat(account.getBalance()).isEqualTo(125.0);
  }

  @Test
  void multipleTransactionsWorkCorrectly() {
    Customer customer = new Customer(UUID.randomUUID(), "John Doe", false);
    SavingsAccount account = new SavingsAccount("123456789", Set.of(customer), 1000.0);

    account.deposit(500.0);
    account.withdraw(200.0);
    account.deposit(700.0);
    account.withdraw(500.0);

    assertThat(account.getBalance()).isEqualTo(1500.0);
    assertThat(account.getAccountNumber()).isEqualTo("123456789");
    assertThat(account.getOwners()).containsExactly(customer);
  }

  @Test
  void throwsExceptionWhenWithdrawingTooMuch() {
    Customer customer = new Customer(UUID.randomUUID(), "John Doe", false);
    SavingsAccount account = new SavingsAccount("123456789", Set.of(customer), 100.0);

    assertThatExceptionOfType(InsufficientFundsException.class)
        .isThrownBy(() -> account.withdraw(150.0))
        .withMessage("Account does not have enough funds for withdrawal");

    assertThat(account.getBalance()).isEqualTo(100.0);
  }
}
