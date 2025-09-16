package com.codedifferently.lesson17.bank;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import com.codedifferently.lesson17.bank.exceptions.InsufficientFundsException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SavingsAccountTest {

  private SavingsAccount classUnderTest;
  private Set<Customer> owners;

  @BeforeEach
  void setUp() {
    owners = new HashSet<>();
    owners.add(new Customer(UUID.randomUUID(), "John Doe"));
    owners.add(new Customer(UUID.randomUUID(), "Jane Smith"));
    classUnderTest = new SavingsAccount("123456789", owners, 100.0);
  }

  @Test
  void getAccountNumber() {
    assertThat(classUnderTest.getAccountNumber()).isEqualTo("123456789");
  }

  @Test
  void getOwners() {
    assertThat(classUnderTest.getOwners()).isEqualTo(owners);
  }

  @Test
  void depositFundsIncreasesBalance() {
    classUnderTest.deposit(50.0);
    assertThat(classUnderTest.getBalance()).isEqualTo(150.0);
  }

  @Test
  void depositNegativeAmountThrowsException() {
    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> classUnderTest.deposit(-50.0));
  }

  @Test
  void withdrawFundsDecreasesBalance() {
    classUnderTest.withdraw(50.0);
    assertThat(classUnderTest.getBalance()).isEqualTo(50.0);
  }

  @Test
  void withdrawTooMuchThrowsException() {
    assertThatExceptionOfType(InsufficientFundsException.class)
        .isThrownBy(() -> classUnderTest.withdraw(150.0));
  }

  @Test
  void canWriteChecksReturnsFalse() {
    assertThat(classUnderTest.canWriteChecks()).isFalse();
  }

  @Test
  void isClosedInitiallyFalse() {
    assertThat(classUnderTest.isClosed()).isFalse();
  }

  @Test
  void closeAccountWithZeroBalanceWorks() {
    classUnderTest.withdraw(100.0);
    classUnderTest.closeAccount();
    assertThat(classUnderTest.isClosed()).isTrue();
  }

  @Test
  void closingAccountWithPositiveBalanceThrowsException() {
    assertThatExceptionOfType(IllegalStateException.class)
        .isThrownBy(() -> classUnderTest.closeAccount());
  }
}
