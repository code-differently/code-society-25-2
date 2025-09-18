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
    classUnderTest = new SavingsAccount("SAV123456789", owners, 100.0);
  }

  @Test
  void getAccountNumber() {
    assertThat(classUnderTest.getAccountNumber()).isEqualTo("SAV123456789");
  }

  @Test
  void getOwners() {
    assertThat(classUnderTest.getOwners()).isEqualTo(owners);
  }

  @Test
  void deposit() {
    classUnderTest.deposit(50.0);
    assertThat(classUnderTest.getBalance()).isEqualTo(150.0);
  }

  @Test
  void deposit_withNegativeAmount() {
    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> classUnderTest.deposit(-50.0));
  }

  @Test
  void deposit_withZeroAmount() {
    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> classUnderTest.deposit(0.0));
  }

  @Test
  void withdraw() throws InsufficientFundsException {
    classUnderTest.withdraw(50.0);
    assertThat(classUnderTest.getBalance()).isEqualTo(50.0);
  }

  @Test
  void withdraw_withNegativeAmount() {
    assertThatExceptionOfType(IllegalStateException.class)
        .isThrownBy(() -> classUnderTest.withdraw(-50.0))
        .withMessage("Withdrawal amount must be positive");
  }

  @Test
  void withdraw_withZeroAmount() {
    assertThatExceptionOfType(IllegalStateException.class)
        .isThrownBy(() -> classUnderTest.withdraw(0.0))
        .withMessage("Withdrawal amount must be positive");
  }

  @Test
  void withdraw_withInsufficientBalance() {
    assertThatExceptionOfType(InsufficientFundsException.class)
        .isThrownBy(() -> classUnderTest.withdraw(150.0))
        .withMessage("Account does not have enough funds for withdrawal");
  }

  @Test
  void getBalance() {
    assertThat(classUnderTest.getBalance()).isEqualTo(100.0);
  }

  @Test
  void closeAccount_withPositiveBalance() {
    assertThatExceptionOfType(IllegalStateException.class)
        .isThrownBy(() -> classUnderTest.closeAccount());
  }

  @Test
  void isClosed() throws InsufficientFundsException {
    assertThat(classUnderTest.isClosed()).isFalse();
    classUnderTest.withdraw(100);
    classUnderTest.closeAccount();
    assertThat(classUnderTest.isClosed()).isTrue();
  }

  @Test
  void deposit_toClosedAccount() throws InsufficientFundsException {
    // Close the account first
    classUnderTest.withdraw(100.0);
    classUnderTest.closeAccount();
    
    // Try to deposit to closed account
    assertThatExceptionOfType(IllegalStateException.class)
        .isThrownBy(() -> classUnderTest.deposit(50.0))
        .withMessage("Cannot deposit to a closed account");
  }
  
  @Test
  void withdraw_fromClosedAccount() throws InsufficientFundsException {
    // Close the account first
    classUnderTest.withdraw(100.0);
    classUnderTest.closeAccount();
    
    // Try to withdraw from closed account
    assertThatExceptionOfType(IllegalStateException.class)
        .isThrownBy(() -> classUnderTest.withdraw(50.0))
        .withMessage("Cannot withdraw from a closed account");
  }

  @Test
  void equals() {
    SavingsAccount otherAccount = new SavingsAccount("SAV123456789", owners, 200.0);
    assertThat(classUnderTest.equals(otherAccount)).isTrue();
  }

  @Test
  void hashCodeTest() {
    SavingsAccount otherAccount = new SavingsAccount("SAV123456789", owners, 200.0);
    assertThat(classUnderTest.hashCode()).isEqualTo(otherAccount.hashCode());
  }

  @Test
  void toStringTest() {
    String expected = "SavingsAccount{accountNumber='SAV123456789', balance=100.0, isActive=true}";
    assertThat(classUnderTest.toString()).isEqualTo(expected);
  }
}
