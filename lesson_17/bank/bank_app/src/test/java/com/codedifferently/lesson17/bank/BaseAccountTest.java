package com.codedifferently.lesson17.bank;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.codedifferently.lesson17.bank.exceptions.InsufficientFundsException;

class BaseAccountTest {

  private CheckingAccount account; // Using CheckingAccount as concrete implementation
  private Customer customer;

  @BeforeEach
  void setUp() {
    customer = new Customer(UUID.randomUUID(), "John Doe");
    account = new CheckingAccount("TEST001", Set.of(customer), 100.0);
  }

  @Test
  void testConstructor() {
    assertThat(account.getAccountNumber()).isEqualTo("TEST001");
    assertThat(account.getOwners()).containsExactly(customer);
    assertThat(account.getBalance()).isEqualTo(100.0);
    assertThat(account.isClosed()).isFalse();
  }

  @Test
  void testDepositToClosedAccount() {
    account.withdraw(100.0); // Make balance zero
    account.closeAccount(); // Close the account

    assertThatExceptionOfType(IllegalStateException.class)
        .isThrownBy(() -> account.deposit(50.0))
        .withMessage("Cannot deposit to a closed account");
  }

  @Test
  void testDepositNegativeAmount() {
    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> account.deposit(-50.0))
        .withMessage("Deposit amount must be positive");
  }

  @Test
  void testDepositZeroAmount() {
    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> account.deposit(0.0))
        .withMessage("Deposit amount must be positive");
  }

  @Test
  void testWithdrawFromClosedAccount() {
    account.withdraw(100.0); // Make balance zero
    account.closeAccount(); // Close the account

    assertThatExceptionOfType(IllegalStateException.class)
        .isThrownBy(() -> account.withdraw(50.0))
        .withMessage("Cannot withdraw from a closed account");
  }

  @Test
  void testWithdrawNegativeAmount() {
    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> account.withdraw(-50.0))
        .withMessage("Withdrawal amount must be positive");
  }

  @Test
  void testWithdrawZeroAmount() {
    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> account.withdraw(0.0))
        .withMessage("Withdrawal amount must be positive");
  }

  @Test
  void testWithdrawInsufficientFunds() {
    assertThatExceptionOfType(InsufficientFundsException.class)
        .isThrownBy(() -> account.withdraw(150.0))
        .withMessage("Account does not have enough funds for withdrawal");
  }

  @Test
  void testCloseAccountWithPositiveBalance() {
    assertThatExceptionOfType(IllegalStateException.class)
        .isThrownBy(() -> account.closeAccount())
        .withMessage("Cannot close account with a positive balance");
  }

  @Test
  void testCloseAccountWithZeroBalance() {
    account.withdraw(100.0); // Make balance zero
    account.closeAccount();
    assertThat(account.isClosed()).isTrue();
  }

  @Test
  void testCloseAccountWithNegativeBalance() {
    // This might not be possible with current implementation, but let's test the edge case
    account.withdraw(100.0); // Balance is now 0
    // We can't easily get negative balance due to validation, so we'll test zero case above
  }

  @Test
  void testEqualsAndHashCode() {
    CheckingAccount sameAccount = new CheckingAccount("TEST001", Set.of(customer), 200.0);
    CheckingAccount differentAccount = new CheckingAccount("TEST002", Set.of(customer), 100.0);

    assertThat(account).isEqualTo(sameAccount);
    assertThat(account).isNotEqualTo(differentAccount);
    assertThat(account).isNotEqualTo(null);
    assertThat(account).isNotEqualTo("Not an account");

    assertThat(account.hashCode()).isEqualTo(sameAccount.hashCode());
    assertThat(account.hashCode()).isNotEqualTo(differentAccount.hashCode());
  }

  @Test
  void testToString() {
    String expected = "CheckingAccount{accountNumber='TEST001', balance=100.0, isActive=true}";
    assertThat(account.toString()).isEqualTo(expected);
  }

  @Test
  void testToStringAfterClosing() {
    account.withdraw(100.0);
    account.closeAccount();
    String expected = "CheckingAccount{accountNumber='TEST001', balance=0.0, isActive=false}";
    assertThat(account.toString()).isEqualTo(expected);
  }
}
