package com.codedifferently.lesson17.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BusinessCustomerTest {

  private BusinessCustomer classUnderTest;
  private UUID testId;
  private String testName;

  @BeforeEach
  void setUp() {
    testId = UUID.randomUUID();
    testName = "Test Business Corp";
    classUnderTest = new BusinessCustomer(testId, testName);
  }

  @Test
  void constructor_createsBusinessCustomerWithCorrectProperties() {
    // Assert
    assertEquals(testId, classUnderTest.getId());
    assertEquals(testName, classUnderTest.getName());
    assertNotNull(classUnderTest.getAccounts());
    assertTrue(classUnderTest.getAccounts().isEmpty());
  }

  @Test
  void getId_returnsCorrectId() {
    // Act & Assert
    assertEquals(testId, classUnderTest.getId());
  }

  @Test
  void getName_returnsCorrectName() {
    // Act & Assert
    assertEquals(testName, classUnderTest.getName());
  }

  @Test
  void toString_returnsCorrectFormat() {
    // Act
    String result = classUnderTest.toString();

    // Assert
    String expected = "BusinessCustomer{id=" + testId + ", name='" + testName + "'}";
    assertEquals(expected, result);
  }

  @Test
  void inheritedMethods_workCorrectly() {
    // Test inherited functionality from Customer class
    Account testAccount = new CheckingAccount("TEST-001", new java.util.HashSet<>(), 100.0);

    // Act
    classUnderTest.addAccount(testAccount);

    // Assert
    assertTrue(classUnderTest.getAccounts().contains(testAccount));
    assertEquals(1, classUnderTest.getAccounts().size());
  }

  @Test
  void equals_worksCorrectly() {
    // Arrange
    BusinessCustomer sameIdCustomer = new BusinessCustomer(testId, "Different Name");
    BusinessCustomer differentIdCustomer = new BusinessCustomer(UUID.randomUUID(), testName);

    // Act & Assert
    assertEquals(classUnderTest, sameIdCustomer);
    assertEquals(classUnderTest.hashCode(), sameIdCustomer.hashCode());
    assertTrue(!classUnderTest.equals(differentIdCustomer));
  }
}
