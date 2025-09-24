package com.codedifferently.lesson17.bank;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class BusinessCheckingAccountTest {

  @Test
  void testBusinessAccountWithBusinessOwnerSucceeds() {
    // Arrange
    BusinessCustomer biz = new BusinessCustomer(UUID.randomUUID(), "Acme Corp");

    // Act
    BusinessCheckingAccount account = new BusinessCheckingAccount("BIZ123", Set.of(biz), 1000.0);

    // Assert
    assertThat(account.getBalance()).isEqualTo(1000.0);
  }

  @Test
  void testBusinessAccountWithoutBusinessOwnerFails() {
    // Arrange
    Customer person = new Customer(UUID.randomUUID(), "John Doe");

    // Act + Assert
    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> new BusinessCheckingAccount("BIZ456", Set.of(person), 500.0))
        .withMessage("BusinessCheckingAccount requires at least one BusinessCustomer as an owner");
  }
}
