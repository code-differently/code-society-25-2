package com.codedifferently.lesson17.bank;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CustomerTest {

  private Customer customer;
  private UUID customerId;
  private CheckingAccount checkingAccount;
  private SavingsAccount savingsAccount;

  @BeforeEach
  void setUp() {
    customerId = UUID.randomUUID();
    customer = new Customer(customerId, "John Doe");
    checkingAccount = new CheckingAccount("CHK001", Set.of(customer), 1000.0);
    savingsAccount = new SavingsAccount("SAV001", Set.of(customer), 500.0);
  }

  @Test
  void testConstructor() {
    assertThat(customer.getId()).isEqualTo(customerId);
    assertThat(customer.getName()).isEqualTo("John Doe");
    assertThat(customer.getAccounts()).isEmpty();
  }

  @Test
  void testAddAccount() {
    customer.addAccount(checkingAccount);
    assertThat(customer.getAccounts()).containsExactly(checkingAccount);
  }

  @Test
  void testAddMultipleAccounts() {
    customer.addAccount(checkingAccount);
    customer.addAccount(savingsAccount);
    assertThat(customer.getAccounts()).containsExactlyInAnyOrder(checkingAccount, savingsAccount);
  }

  @Test
  void testAddSameAccountTwice() {
    customer.addAccount(checkingAccount);
    customer.addAccount(checkingAccount);
    // Set should only contain unique accounts
    assertThat(customer.getAccounts()).hasSize(1);
    assertThat(customer.getAccounts()).containsExactly(checkingAccount);
  }

  @Test
  void testEquals() {
    Customer otherCustomer = new Customer(customerId, "Different Name");
    Customer differentCustomer = new Customer(UUID.randomUUID(), "John Doe");

    assertThat(customer).isEqualTo(otherCustomer); // Same ID
    assertThat(customer).isNotEqualTo(differentCustomer); // Different ID
    assertThat(customer).isNotEqualTo(null);
    assertThat(customer).isNotEqualTo("Not a customer");
  }

  @Test
  void testHashCode() {
    Customer otherCustomer = new Customer(customerId, "Different Name");
    assertThat(customer.hashCode()).isEqualTo(otherCustomer.hashCode());
  }

  @Test
  void testToString() {
    String expected = "Customer{id=" + customerId + ", name='John Doe'}";
    assertThat(customer.toString()).isEqualTo(expected);
  }
}
