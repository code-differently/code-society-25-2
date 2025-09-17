package com.codedifferently.lesson17.bank;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CustomerTest {

  private Customer customer;
  private Account account1;
  private Account account2;

  @BeforeEach
  void setUp() {
    customer = new Customer(UUID.randomUUID(), "Jane Doe");
    account1 = new CheckingAccount("222222222", Set.of(customer), 100.0);
    account2 = new SavingsAccount("333333333", Set.of(customer), 500.0);
    customer.addAccount(account1);
    customer.addAccount(account2);
  }

  @Test
  void testGetIdAndName() {
    assertThat(customer.getId()).isNotNull();
    assertThat(customer.getName()).isEqualTo("Jane Doe");
  }

  @Test
  void testGetAccounts() {
    // Act
    Set<Account> accounts = customer.getAccounts();
    // Assert
    assertThat(accounts).containsExactlyInAnyOrder(account1, account2);
  }

  @Test
  void testEqualsAndHashCode() {
    // Arrange
    Customer sameCustomer = new Customer(customer.getId(), "Jane Doe");
    // Act & Assert
    assertThat(customer).isEqualTo(sameCustomer);
    assertThat(customer.hashCode()).isEqualTo(sameCustomer.hashCode());
  }
}
