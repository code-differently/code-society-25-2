package com.codedifferently.lesson17.bank;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.codedifferently.lesson17.bank.exceptions.InsufficientFundsException;

public class SavingsAccountTest {
  /**
   * Tests that basic deposit and withdraw operations work correctly on a savings account.
   * Verifies that:
   * 1. Initial balance is set correctly
   * 2. Deposit increases the balance
   * 3. Withdraw decreases the balance
   * 4. Final balance is calculated correctly
   */
    @Test
  void depositAndWithdrawWork() {
    Customer customer = new Customer(UUID.randomUUID(), "John Doe", false);
    SavingsAccount account = new SavingsAccount("123456789", Set.of(customer), 100.0);

    account.deposit(50.0);
    account.withdraw(25.0);

    assertThat(account.getBalance()).isEqualTo(125.0);
  }

   /**
   * Tests that multiple transactions can be performed successfully on a savings account.
   * Verifies that:
   * 1. Multiple deposits and withdrawals work in sequence
   * 2. Final balance is calculated correctly after multiple operations
   * 3. Account number remains unchanged throughout transactions
   * 4. Account ownership is maintained correctly
   */
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

  /**
   * Tests that the savings account properly prevents overdrafts.
   * Verifies that:
   * 1. Attempting to withdraw more than the balance throws InsufficientFundsException
   * 2. The exception contains the correct error message
   * 3. The account balance remains unchanged after a failed withdrawal
   */
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
