package com.codedifferently.lesson17.bank;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import com.codedifferently.lesson17.bank.exceptions.InsufficientFundsException;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SavingsAccountTest {

  private SavingsAccount savingsAccount;
  private Customer customer1;
  private Customer customer2;

  @BeforeEach
  void setUp() {
    customer1 = new Customer(UUID.randomUUID(), "John Doe");
    customer2 = new Customer(UUID.randomUUID(), "Jane Smith");
    savingsAccount = new SavingsAccount("SAV123456", Set.of(customer1), 1000.0);
  }

  @Test
  @DisplayName("Should create savings account with initial balance")
  void testConstructor() {
    // Assert
    assertThat(savingsAccount.getAccountNumber()).isEqualTo("SAV123456");
    assertThat(savingsAccount.getBalance()).isEqualTo(1000.0);
    assertThat(savingsAccount.getOwners()).containsOnly(customer1);
    assertThat(savingsAccount.isClosed()).isFalse();
  }

  @Test
  @DisplayName("Should create savings account with multiple owners")
  void testConstructorWithMultipleOwners() {
    // Arrange & Act
    SavingsAccount multiOwnerAccount =
        new SavingsAccount("SAV999999", Set.of(customer1, customer2), 2000.0);

    // Assert
    assertThat(multiOwnerAccount.getOwners()).containsExactlyInAnyOrder(customer1, customer2);
    assertThat(multiOwnerAccount.getBalance()).isEqualTo(2000.0);
  }

  @Test
  @DisplayName("Should deposit funds successfully")
  void testDeposit() {
    // Arrange
    double depositAmount = 500.0;
    double expectedBalance = 1500.0;

    // Act
    savingsAccount.deposit(depositAmount);

    // Assert
    assertThat(savingsAccount.getBalance()).isEqualTo(expectedBalance);
  }

  @Test
  @DisplayName("Should handle multiple deposits")
  void testMultipleDeposits() {
    // Act
    savingsAccount.deposit(100.0);
    savingsAccount.deposit(200.0);
    savingsAccount.deposit(50.0);

    // Assert
    assertThat(savingsAccount.getBalance()).isEqualTo(1350.0);
  }

  @Test
  @DisplayName("Should withdraw funds successfully")
  void testWithdraw() {
    // Arrange
    double withdrawAmount = 300.0;
    double expectedBalance = 700.0;

    // Act
    savingsAccount.withdraw(withdrawAmount);

    // Assert
    assertThat(savingsAccount.getBalance()).isEqualTo(expectedBalance);
  }

  @Test
  @DisplayName("Should handle multiple withdrawals")
  void testMultipleWithdrawals() {
    // Act
    savingsAccount.withdraw(100.0);
    savingsAccount.withdraw(200.0);
    savingsAccount.withdraw(50.0);

    // Assert
    assertThat(savingsAccount.getBalance()).isEqualTo(650.0);
  }

  @Test
  @DisplayName("Should throw exception when withdrawing more than available balance")
  void testWithdrawInsufficientFunds() {
    // Act & Assert
    assertThatExceptionOfType(InsufficientFundsException.class)
        .isThrownBy(() -> savingsAccount.withdraw(1500.0))
        .withMessage("Account does not have enough funds for withdrawal");

    // Balance should remain unchanged
    assertThat(savingsAccount.getBalance()).isEqualTo(1000.0);
  }

  @Test
  @DisplayName("Should allow withdrawal of exact balance")
  void testWithdrawExactBalance() {
    // Act
    savingsAccount.withdraw(1000.0);

    // Assert
    assertThat(savingsAccount.getBalance()).isEqualTo(0.0);
  }

  @Test
  @DisplayName("Should close account when balance is zero")
  void testCloseAccountWithZeroBalance() {
    // Arrange
    savingsAccount.withdraw(1000.0); // Make balance zero

    // Act
    savingsAccount.closeAccount();

    // Assert
    assertThat(savingsAccount.isClosed()).isTrue();
  }

  @Test
  @DisplayName("Should throw exception when depositing to closed account")
  void testDepositToClosedAccount() {
    // Arrange
    savingsAccount.withdraw(1000.0); // Make balance zero
    savingsAccount.closeAccount();

    // Act & Assert
    assertThatExceptionOfType(IllegalStateException.class)
        .isThrownBy(() -> savingsAccount.deposit(100.0))
        .withMessage("Cannot deposit to a closed account");
  }

  @Test
  @DisplayName("Should verify savings account does not have check handling methods")
  void testSavingsAccountDoesNotHandleChecks() {
    // Verify that SavingsAccount does not have check-related methods
    // This is a compile-time check - if SavingsAccount had check methods, this test would fail to
    // compile

    // Check that SavingsAccount class does not declare any check-related methods
    boolean hasCheckMethods = false;
    try {
      // Try to find check-related methods - these should not exist
      savingsAccount.getClass().getMethod("depositFunds", Check.class);
      hasCheckMethods = true;
    } catch (NoSuchMethodException e) {
      // Expected - SavingsAccount should not have check deposit methods
    }

    assertThat(hasCheckMethods).isFalse();
  }
}
