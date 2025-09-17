package com.codedifferently.lesson17.bank;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class BusinessCheckingAccountTest {
  @Test
  void testValidBusinessOwner() {
    Customer business = new Customer(UUID.randomUUID(), "Biz LLC", true);
    BusinessCheckingAccount acct = new BusinessCheckingAccount("BIZ123", Set.of(business), 100.0);
    assertThat(acct.getBalance()).isEqualTo(100.0);
  }

  @Test
  void testInvalidBusinessOwner() {
    Customer person1 = new Customer(UUID.randomUUID(), "Alice");
    Customer person2 = new Customer(UUID.randomUUID(), "Bob");
    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> new BusinessCheckingAccount("BIZ124", Set.of(person1, person2), 100.0))
        .withMessage("BusinessCheckingAccount requires at least one business owner");
  }
}
