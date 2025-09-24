package com.codedifferently.lesson17.bank;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class BusinessCheckingAccountTest {

  @Test
  void testBusinessCheckingAccount_WithBusinessOwner() {
    // Arrange
    Set<AccountOwner> owners = new HashSet<>();
    owners.add(new Business(UUID.randomUUID(), "Test Corp", "123-45-6789"));
    owners.add(new Customer(UUID.randomUUID(), "John Doe"));

    // Act
    BusinessCheckingAccount account = new BusinessCheckingAccount("123456789", owners, 1000.0);

    // Assert
    assertEquals("123456789", account.getAccountNumber());
    assertEquals(1000.0, account.getBalance());
    assertEquals(2, account.getOwners().size());
  }

  @Test
  void testBusinessCheckingAccount_WithoutBusinessOwner_ThrowsException() {
    // Arrange
    Set<AccountOwner> owners = new HashSet<>();
    owners.add(new Customer(UUID.randomUUID(), "John Doe"));
    owners.add(new Customer(UUID.randomUUID(), "Jane Smith"));

    // Act & Assert
    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> new BusinessCheckingAccount("123456789", owners, 1000.0))
        .withMessage("Business checking account must have at least one business owner");
  }

  @Test
  void testBusinessCheckingAccount_OnlyBusinessOwner() {
    // Arrange
    Set<AccountOwner> owners = new HashSet<>();
    owners.add(new Business(UUID.randomUUID(), "Solo Business", "987-65-4321"));

    // Act
    BusinessCheckingAccount account = new BusinessCheckingAccount("987654321", owners, 500.0);

    // Assert
    assertEquals("987654321", account.getAccountNumber());
    assertEquals(500.0, account.getBalance());
    assertEquals(1, account.getOwners().size());
    assertTrue(account.getOwners().iterator().next() instanceof Business);
  }
}