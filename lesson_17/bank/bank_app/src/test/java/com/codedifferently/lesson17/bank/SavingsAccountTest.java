package com.codedifferently.lesson17.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SavingsAccountTest {

  private SavingsAccount classUnderTest;
  private Set<Customer> customers;
  private String testAccountNumber;
  private Double initialBalance;

  @BeforeEach
  void setUp() {
    customers = new HashSet<>();
    customers.add(new Customer(UUID.randomUUID(), "John Doe"));
    customers.add(new Customer(UUID.randomUUID(), "Jane Smith"));
    testAccountNumber = "SAV-123456789";
    initialBalance = 500.0;
    classUnderTest = new SavingsAccount(testAccountNumber, customers, initialBalance);
  }

  @Test
  void constructor_createsAccountWithCorrectProperties() {
    // Assert
    assertEquals(testAccountNumber, classUnderTest.getAccountNumber());
    assertEquals(customers, classUnderTest.getCustomers());
    assertEquals(initialBalance, classUnderTest.getBalance());
    assertFalse(classUnderTest.isClosed());
  }

  @Test
  void getAccountNumber_returnsCorrectAccountNumber() {
    // Act & Assert
    assertEquals(testAccountNumber, classUnderTest.getAccountNumber());
  }

  @Test
  void getCustomers_returnsCorrectCustomers() {
    // Act & Assert
    assertEquals(customers, classUnderTest.getCustomers());
  }

  @Test
  void getBalance_returnsCorrectBalance() {
    // Act & Assert
    assertEquals(initialBalance, classUnderTest.getBalance());
  }

  @Test
  void deposit_increasesBalance() {
    // Arrange
    Double depositAmount = 100.0;

    // Act
    classUnderTest.deposit(depositAmount);

    // Assert
    assertEquals(initialBalance + depositAmount, classUnderTest.getBalance());
  }

  @Test
  void deposit_withNegativeAmount_doesNotChangeBalance() {
    // Arrange
    Double originalBalance = classUnderTest.getBalance();

    // Act
    classUnderTest.deposit(-50.0);

    // Assert
    assertEquals(originalBalance, classUnderTest.getBalance());
  }

  @Test
  void deposit_withZeroAmount_doesNotChangeBalance() {
    // Arrange
    Double originalBalance = classUnderTest.getBalance();

    // Act
    classUnderTest.deposit(0.0);

    // Assert
    assertEquals(originalBalance, classUnderTest.getBalance());
  }

  @Test
  void withdraw_decreasesBalance() {
    // Arrange
    Double withdrawAmount = 100.0;

    // Act
    classUnderTest.withdraw(withdrawAmount);

    // Assert
    assertEquals(initialBalance - withdrawAmount, classUnderTest.getBalance());
  }

  @Test
  void withdraw_withAmountGreaterThanBalance_doesNotChangeBalance() {
    // Arrange
    Double originalBalance = classUnderTest.getBalance();
    Double excessiveAmount = originalBalance + 100.0;

    // Act
    classUnderTest.withdraw(excessiveAmount);

    // Assert
    assertEquals(originalBalance, classUnderTest.getBalance());
  }

  @Test
  void withdraw_withNegativeAmount_doesNotChangeBalance() {
    // Arrange
    Double originalBalance = classUnderTest.getBalance();

    // Act
    classUnderTest.withdraw(-50.0);

    // Assert
    assertEquals(originalBalance, classUnderTest.getBalance());
  }

  @Test
  void close_closesAccount() {
    // Act
    classUnderTest.close();

    // Assert
    assertTrue(classUnderTest.isClosed());
  }

  @Test
  void deposit_toClosedAccount_doesNotChangeBalance() {
    // Arrange
    classUnderTest.close();
    Double originalBalance = classUnderTest.getBalance();

    // Act
    classUnderTest.deposit(100.0);

    // Assert
    assertEquals(originalBalance, classUnderTest.getBalance());
  }

  @Test
  void withdraw_fromClosedAccount_doesNotChangeBalance() {
    // Arrange
    classUnderTest.close();
    Double originalBalance = classUnderTest.getBalance();

    // Act
    classUnderTest.withdraw(100.0);

    // Assert
    assertEquals(originalBalance, classUnderTest.getBalance());
  }

  @Test
  void toString_returnsCorrectFormat() {
    // Act
    String result = classUnderTest.toString();

    // Assert
    String expected =
        "SavingsAccount{accountNumber='"
            + testAccountNumber
            + "', balance="
            + initialBalance
            + ", isActive=true}";
    assertEquals(expected, result);
  }

  @Test
  void toString_forClosedAccount_showsInactive() {
    // Arrange
    classUnderTest.close();

    // Act
    String result = classUnderTest.toString();

    // Assert
    String expected =
        "SavingsAccount{accountNumber='"
            + testAccountNumber
            + "', balance="
            + initialBalance
            + ", isActive=false}";
    assertEquals(expected, result);
  }

  @Test
  void isClosed_initiallyReturnsFalse() {
    // Act & Assert
    assertFalse(classUnderTest.isClosed());
  }

  @Test
  void isClosed_afterClosing_returnsTrue() {
    // Arrange
    classUnderTest.close();

    // Act & Assert
    assertTrue(classUnderTest.isClosed());
  }
}
