package com.codedifferently.lesson17.bank;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class BusinessCheckingAccountTest {

  @Test
  void testCreateBusinessCheckingAccount_WithBusinessOwner() {
    // Arrange
    BusinessCustomer businessCustomer = new BusinessCustomer(UUID.randomUUID(), "Acme Corp", "REG123");
    Customer regularCustomer = new Customer(UUID.randomUUID(), "John Doe");
    Set<Customer> owners = Set.of(businessCustomer, regularCustomer);

    // Act
    BusinessCheckingAccount account = new BusinessCheckingAccount("BIZ123456789", owners, 1000.0);

    // Assert
    assertEquals("BIZ123456789", account.getAccountNumber());
    assertEquals(1000.0, account.getBalance());
    assertTrue(businessCustomer.isBusiness());
  }

  @Test
  void testCreateBusinessCheckingAccount_WithoutBusinessOwner() {
    // Arrange
    Customer customer1 = new Customer(UUID.randomUUID(), "John Doe");
    Customer customer2 = new Customer(UUID.randomUUID(), "Jane Smith");
    Set<Customer> owners = Set.of(customer1, customer2);

    // Act & Assert
    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> new BusinessCheckingAccount("BIZ123456789", owners, 1000.0))
        .withMessage("Business checking account requires at least one business owner");
  }

  @Test
  void testToString() {
    // Arrange
    BusinessCustomer businessCustomer = new BusinessCustomer(UUID.randomUUID(), "Acme Corp", "REG123");
    Set<Customer> owners = Set.of(businessCustomer);
    BusinessCheckingAccount account = new BusinessCheckingAccount("BIZ123456789", owners, 1000.0);

    // Act
    String result = account.toString();

    // Assert
    String expected = "BusinessCheckingAccount{accountNumber='BIZ123456789', balance=1000.0, isActive=true}";
    assertEquals(expected, result);
  }
}
