package com.codedifferently.lesson17.bank;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.codedifferently.lesson17.bank.exceptions.InsufficientFundsException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SavingsAccountTest {

  private SavingsAccount classSTest;
  private Set<Customer> owners;

  @BeforeEach
  void setSUp() {
    owners = new HashSet<>();
    owners.add(new Customer(UUID.randomUUID(), "Jay Doe"));
    owners.add(new Customer(UUID.randomUUID(), "Joy Smith"));
    classSTest = new SavingsAccount("123456789", owners, 100.0);
  }

  @Test
  void getAccountNumber() {
    assertEquals("123456789", classSTest.getAccountNumber());
  }

  @Test
  void getOwners() {
    assertEquals(owners, classSTest.getOwners());
  }

  @Test
  void deposit() {
    classSTest.deposit(50.0);
    assertEquals(150.0, classSTest.getBalance());
  }

  @Test
  void deposit_withNegativeAmount() {
    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> classSTest.deposit(-50.0));
  }

  @Test
  void withdraw() {
    classSTest.withdraw(50.0);
    assertEquals(50.0, classSTest.getBalance());
  }

  @Test
  void withdraw_withNegativeAmount() {
    assertThatExceptionOfType(IllegalStateException.class)
        .isThrownBy(() -> classSTest.withdraw(-50.0))
        .withMessage("Withdrawal amount must be positive");
  }

  @Test
  void withdraw_withInsufficientBalance() {
    assertThatExceptionOfType(InsufficientFundsException.class)
        .isThrownBy(() -> classSTest.withdraw(150.0))
        .withMessage("Account does not have enough funds for withdrawal");
  }

  @Test
  void getBalance() {
    assertEquals(100.0, classSTest.getBalance());
  }

  @Test
  void closeAccount_withPositiveBalance() {
    assertThatExceptionOfType(IllegalStateException.class)
        .isThrownBy(() -> classSTest.closeAccount());
  }

  @Test
  void isClosed() {
    assertFalse(classSTest.isClosed());
    classSTest.withdraw(100);
    classSTest.closeAccount();
    assertTrue(classSTest.isClosed());
  }

  @Test
  void equals() {
    SavingsAccount otherAccount = new SavingsAccount("123456789", owners, 200.0);
    assertEquals(classSTest, otherAccount);
  }

  @Test
  void hashCodeTest() {
    SavingsAccount otherAccount = new SavingsAccount("123456789", owners, 200.0);
    assertEquals(classSTest.hashCode(), otherAccount.hashCode());
  }

  @Test
  void toStringTest() {
    String expected = "SavingsAccount{accountNumber='123456789', balance=100.0, isActive=true}";
    assertEquals(expected, classSTest.toString());
  }
}
