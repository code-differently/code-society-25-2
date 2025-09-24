package com.codedifferently.lesson17.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BusinessCheckingAccountTest {

  private BusinessCheckingAccount classUnderTest;
  private BusinessCustomer businessCustomer;
  private Customer regularCustomer;
  private String testAccountNumber;
  private String testBusinessName;

  @BeforeEach
  void setUp() {
    testAccountNumber = "BIZ-123456789";
    testBusinessName = "Test Business LLC";
    businessCustomer = new BusinessCustomer(UUID.randomUUID(), "Business Owner");
    regularCustomer = new Customer(UUID.randomUUID(), "Regular Customer");
    classUnderTest = new BusinessCheckingAccount(testAccountNumber, testBusinessName);
  }

  @Test
  void constructor_withAccountNumberAndBusinessName_createsAccountCorrectly() {
    // Assert
    assertEquals(testAccountNumber, classUnderTest.getAccountNumber());
    assertEquals(testBusinessName, classUnderTest.getBusinessName());
    assertEquals(0.0, classUnderTest.getBalance());
    assertFalse(classUnderTest.isClosed());
    assertTrue(classUnderTest.getCustomers().isEmpty());
  }

  @Test
  void constructor_withBusinessCustomer_createsAccountCorrectly() {
    // Arrange & Act
    BusinessCheckingAccount account =
        new BusinessCheckingAccount(testAccountNumber, testBusinessName, businessCustomer);

    // Assert
    assertEquals(testAccountNumber, account.getAccountNumber());
    assertEquals(testBusinessName, account.getBusinessName());
    assertEquals(0.0, account.getBalance());
    assertTrue(account.getCustomers().contains(businessCustomer));
    assertTrue(account.hasBusinessCustomers());
    assertEquals(businessCustomer, account.getBusinessCustomer());
  }

  @Test
  void constructor_withNullBusinessName_throwsException() {
    // Act & Assert
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> new BusinessCheckingAccount(testAccountNumber, null));
    assertEquals("Business name cannot be null or empty", exception.getMessage());
  }

  @Test
  void constructor_withEmptyBusinessName_throwsException() {
    // Act & Assert
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> new BusinessCheckingAccount(testAccountNumber, "   "));
    assertEquals("Business name cannot be null or empty", exception.getMessage());
  }

  @Test
  void constructor_withNullBusinessCustomer_throwsException() {
    // Act & Assert
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> new BusinessCheckingAccount(testAccountNumber, testBusinessName, null));
    assertEquals("Business customer cannot be null", exception.getMessage());
  }

  @Test
  void getBusinessName_returnsCorrectName() {
    // Act & Assert
    assertEquals(testBusinessName, classUnderTest.getBusinessName());
  }

  @Test
  void setBusinessName_updatesNameCorrectly() {
    // Arrange
    String newName = "Updated Business Name";

    // Act
    classUnderTest.setBusinessName(newName);

    // Assert
    assertEquals(newName, classUnderTest.getBusinessName());
  }

  @Test
  void setBusinessName_withNullName_throwsException() {
    // Act & Assert
    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> classUnderTest.setBusinessName(null));
    assertEquals("Business name cannot be null or empty", exception.getMessage());
  }

  @Test
  void setBusinessName_withEmptyName_throwsException() {
    // Act & Assert
    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> classUnderTest.setBusinessName(""));
    assertEquals("Business name cannot be null or empty", exception.getMessage());
  }

  @Test
  void addCustomer_withBusinessCustomer_addsSuccessfully() {
    // Act
    classUnderTest.addCustomer(businessCustomer);

    // Assert
    assertTrue(classUnderTest.getCustomers().contains(businessCustomer));
    assertTrue(classUnderTest.hasBusinessCustomers());
    assertEquals(businessCustomer, classUnderTest.getBusinessCustomer());
  }

  @Test
  void addCustomer_withRegularCustomer_throwsException() {
    // Act & Assert
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class, () -> classUnderTest.addCustomer(regularCustomer));
    assertEquals(
        "Business checking accounts can only have business customers", exception.getMessage());
  }

  @Test
  void hasBusinessCustomers_withNoCustomers_returnsFalse() {
    // Act & Assert
    assertFalse(classUnderTest.hasBusinessCustomers());
  }

  @Test
  void hasBusinessCustomers_withBusinessCustomers_returnsTrue() {
    // Arrange
    classUnderTest.addCustomer(businessCustomer);

    // Act & Assert
    assertTrue(classUnderTest.hasBusinessCustomers());
  }

  @Test
  void getBusinessCustomer_withNoBusinessCustomers_returnsNull() {
    // Act & Assert
    assertNull(classUnderTest.getBusinessCustomer());
  }

  @Test
  void getBusinessCustomer_withBusinessCustomers_returnsFirstBusinessCustomer() {
    // Arrange
    classUnderTest.addCustomer(businessCustomer);

    // Act & Assert
    assertEquals(businessCustomer, classUnderTest.getBusinessCustomer());
  }

  @Test
  void inheritedDepositAndWithdrawFunctionality_worksCorrectly() {
    // Test that inherited functionality still works
    classUnderTest.deposit(100.0);
    assertEquals(100.0, classUnderTest.getBalance());

    classUnderTest.withdraw(50.0);
    assertEquals(50.0, classUnderTest.getBalance());
  }

  @Test
  void toString_returnsCorrectFormat() {
    // Act
    String result = classUnderTest.toString();

    // Assert
    String expected =
        String.format(
            "BusinessCheckingAccount{accountNumber='%s', businessName='%s', balance=%.2f, customers=%d}",
            testAccountNumber, testBusinessName, 0.0, 0);
    assertEquals(expected, result);
  }

  @Test
  void toString_withCustomers_includesCustomerCount() {
    // Arrange
    classUnderTest.addCustomer(businessCustomer);

    // Act
    String result = classUnderTest.toString();

    // Assert
    String expected =
        String.format(
            "BusinessCheckingAccount{accountNumber='%s', businessName='%s', balance=%.2f, customers=%d}",
            testAccountNumber, testBusinessName, 0.0, 1);
    assertEquals(expected, result);
  }
}
