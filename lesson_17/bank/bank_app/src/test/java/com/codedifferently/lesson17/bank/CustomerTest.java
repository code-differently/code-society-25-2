package com.codedifferently.lesson17.bank;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CustomerTest {

  private Customer customer;
  private UUID customerId;
  private CheckingAccount account1;
  private CheckingAccount account2;

  @BeforeEach
  void setUp() {
    customerId = UUID.randomUUID();
    customer = new Customer(customerId, "John Doe");
    account1 = new CheckingAccount("123456789", Set.of(customer), 100.0);
    account2 = new CheckingAccount("987654321", Set.of(customer), 200.0);
  }

  @Test
  void testConstructor() {
    // Assert
    assertThat(customer.getId()).isEqualTo(customerId);
    assertThat(customer.getName()).isEqualTo("John Doe");
    assertThat(customer.getAccounts()).isEmpty();
  }

  @Test
  void testGetId() {
    // Assert
    assertThat(customer.getId()).isEqualTo(customerId);
  }

  @Test
  void testGetName() {
    // Assert
    assertThat(customer.getName()).isEqualTo("John Doe");
  }

  @Test
  void testAddAccount() {
    // Act
    customer.addAccount(account1);

    // Assert
    assertThat(customer.getAccounts()).containsOnly(account1);
  }

  @Test
  void testAddMultipleAccounts() {
    // Act
    customer.addAccount(account1);
    customer.addAccount(account2);

    // Assert
    assertThat(customer.getAccounts()).containsOnly(account1, account2);
  }

  @Test
  void testAddSameAccountTwice() {
    // Act
    customer.addAccount(account1);
    customer.addAccount(account1); // Adding same account again

    // Assert - Set should contain only one instance
    assertThat(customer.getAccounts()).hasSize(1);
    assertThat(customer.getAccounts()).containsOnly(account1);
  }

  @Test
  void testGetAccounts_ReturnsCopy() {
    // Arrange
    customer.addAccount(account1);

    // Act
    Set<CheckingAccount> accounts = customer.getAccounts();
    accounts.clear(); // Try to modify the returned set

    // Assert - Customer's accounts should not be affected
    assertThat(customer.getAccounts()).containsOnly(account1);
  }

  @Test
  void testEquals() {
    // Arrange
    Customer sameIdCustomer = new Customer(customerId, "Jane Smith");
    Customer differentIdCustomer = new Customer(UUID.randomUUID(), "John Doe");

    // Assert
    assertThat(customer.equals(sameIdCustomer)).isTrue();
    assertThat(customer.equals(differentIdCustomer)).isFalse();
    assertThat(customer.equals(null)).isFalse();
    assertThat(customer.equals("not a customer")).isFalse();
  }

  @Test
  void testHashCode() {
    // Arrange
    Customer sameIdCustomer = new Customer(customerId, "Jane Smith");
    Customer differentIdCustomer = new Customer(UUID.randomUUID(), "John Doe");

    // Assert
    assertThat(customer.hashCode()).isEqualTo(sameIdCustomer.hashCode());
    assertThat(customer.hashCode()).isNotEqualTo(differentIdCustomer.hashCode());
  }

  @Test
  void testToString() {
    // Act
    String customerString = customer.toString();

    // Assert
    assertThat(customerString).contains(customerId.toString());
    assertThat(customerString).contains("John Doe");
    assertThat(customerString).startsWith("Customer{");
  }

  @Test
  void testAddSavingsAccount() {
    // Arrange
    SavingsAccount savingsAccount = new SavingsAccount("SAV123456789", Set.of(customer), 200.0);

    // Act
    customer.addAccount(savingsAccount);

    // Assert - getAccounts should only return checking accounts
    assertThat(customer.getAccounts()).isEmpty();

    // getAllAccounts should return all account types
    assertThat(customer.getAllAccounts()).hasSize(1);
    assertThat(customer.getAllAccounts()).contains(savingsAccount);
  }

  @Test
  void testMixedAccountTypes() {
    // Arrange
    SavingsAccount savingsAccount = new SavingsAccount("SAV123456789", Set.of(customer), 200.0);

    // Act
    customer.addAccount(account1);
    customer.addAccount(savingsAccount);

    // Assert
    assertThat(customer.getAccounts()).containsOnly(account1);
    assertThat(customer.getAllAccounts()).containsOnly(account1, savingsAccount);
  }
}
