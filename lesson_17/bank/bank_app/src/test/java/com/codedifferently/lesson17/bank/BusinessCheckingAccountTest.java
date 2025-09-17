package com.codedifferently.lesson17.bank;

import static org.assertj.core.api.Assertions.assertThat;
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

class BusinessCheckingAccountTest {

  private BusinessCheckingAccount classUnderTest;
  private Set<Customer> businessOwners;
  private BusinessCustomer businessCustomer;
  private Customer personalCustomer;

  @BeforeEach
  void setUp() {
    businessCustomer = new BusinessCustomer(UUID.randomUUID(), "ACME Corp", "123-45-6789");
    personalCustomer = new Customer(UUID.randomUUID(), "John Doe");
    businessOwners = new HashSet<>();
    businessOwners.add(businessCustomer);
    businessOwners.add(personalCustomer);
    classUnderTest = new BusinessCheckingAccount("123456789", businessOwners, 1000.0);
  }

  @Test
  void getAccountNumber() {
    assertEquals("123456789", classUnderTest.getAccountNumber());
  }

  @Test
  void getOwners() {
    assertEquals(businessOwners, classUnderTest.getOwners());
  }

  @Test
  void deposit() {
    classUnderTest.deposit(500.0);
    assertEquals(1500.0, classUnderTest.getBalance());
  }

  @Test
  void withdraw() {
    classUnderTest.withdraw(300.0);
    assertEquals(700.0, classUnderTest.getBalance());
  }

  @Test
  void getBalance() {
    assertEquals(1000.0, classUnderTest.getBalance());
  }

  @Test
  void constructorWithOnlyBusinessOwner() {
    // Arrange
    Set<Customer> singleBusinessOwner = Set.of(businessCustomer);

    // Act & Assert - Should not throw exception
    BusinessCheckingAccount account =
        new BusinessCheckingAccount("555555555", singleBusinessOwner, 500.0);
    assertThat(account.getOwners()).containsOnly(businessCustomer);
  }

  @Test
  void constructorWithoutBusinessOwner_ThrowsException() {
    // Arrange
    Customer customer1 = new Customer(UUID.randomUUID(), "Alice Smith");
    Customer customer2 = new Customer(UUID.randomUUID(), "Bob Johnson");
    Set<Customer> nonBusinessOwners = Set.of(customer1, customer2);

    // Act & Assert
    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> new BusinessCheckingAccount("666666666", nonBusinessOwners, 100.0))
        .withMessage("Business checking account requires at least one business owner");
  }

  @Test
  void constructorWithEmptyOwners_ThrowsException() {
    // Arrange
    Set<Customer> emptyOwners = Set.of();

    // Act & Assert
    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> new BusinessCheckingAccount("777777777", emptyOwners, 100.0))
        .withMessage("Business checking account requires at least one business owner");
  }

  @Test
  void deposit_withNegativeAmount() {
    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> classUnderTest.deposit(-50.0));
  }

  @Test
  void withdraw_withNegativeAmount() {
    assertThatExceptionOfType(IllegalStateException.class)
        .isThrownBy(() -> classUnderTest.withdraw(-50.0))
        .withMessage("Withdrawal amount must be positive");
  }

  @Test
  void withdraw_withInsufficientBalance() {
    assertThatExceptionOfType(InsufficientFundsException.class)
        .isThrownBy(() -> classUnderTest.withdraw(1500.0))
        .withMessage("Account does not have enough funds for withdrawal");
  }

  @Test
  void closeAccount_withPositiveBalance() {
    assertThatExceptionOfType(IllegalStateException.class)
        .isThrownBy(() -> classUnderTest.closeAccount());
  }

  @Test
  void isClosed() {
    assertFalse(classUnderTest.isClosed());
    classUnderTest.withdraw(1000.0);
    classUnderTest.closeAccount();
    assertTrue(classUnderTest.isClosed());
  }

  @Test
  void equals() {
    BusinessCheckingAccount otherAccount =
        new BusinessCheckingAccount("123456789", businessOwners, 2000.0);
    assertEquals(classUnderTest, otherAccount);
  }

  @Test
  void hashCodeTest() {
    BusinessCheckingAccount otherAccount =
        new BusinessCheckingAccount("123456789", businessOwners, 2000.0);
    assertEquals(classUnderTest.hashCode(), otherAccount.hashCode());
  }

  @Test
  void toStringTest() {
    String expected =
        "BusinessCheckingAccount{accountNumber='123456789', balance=1000.0, isActive=true}";
    assertEquals(expected, classUnderTest.toString());
  }
}
