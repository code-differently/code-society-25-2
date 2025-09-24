package com.codedifferently.lesson17.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CustomerTest {

  private Customer customer;
  private UUID customerId;
  private String customerName;

  @BeforeEach
  void setUp() {
    customerId = UUID.randomUUID();
    customerName = "John Doe";
    customer = new Customer(customerId, customerName);
  }

  @Test
  void testConstructor() {
    // Test that constructor properly initializes all fields
    assertEquals(customerId, customer.getId());
    assertEquals(customerName, customer.getName());
    assertTrue(customer.getAccounts().isEmpty());
    assertTrue(customer.getSavingsAccounts().isEmpty());
  }

  @Test
  void testGetId() {
    assertEquals(customerId, customer.getId());
  }

  @Test
  void testGetName() {
    assertEquals(customerName, customer.getName());
  }

  @Test
  void testAddAccount() {
    Set<AccountOwner> owners = new HashSet<>();
    owners.add(customer);
    CheckingAccount account = new CheckingAccount(UUID.randomUUID().toString(), owners, 100.0);

    customer.addAccount(account);

    assertTrue(customer.getAccounts().contains(account));
    assertEquals(1, customer.getAccounts().size());
  }

  @Test
  void testAddSavingsAccount() {
    Set<AccountOwner> owners = new HashSet<>();
    owners.add(customer);
    SavingAccount savingsAccount = new SavingAccount(UUID.randomUUID().toString(), owners, 100.0);

    customer.addSavingsAccount(savingsAccount);

    assertTrue(customer.getSavingsAccounts().contains(savingsAccount));
    assertEquals(1, customer.getSavingsAccounts().size());
  }

  @Test
  void testGetAccounts() {
    Set<CheckingAccount> accounts = customer.getAccounts();
    assertNotNull(accounts);
    assertTrue(accounts.isEmpty());

    // Test that adding an account shows up in getAccounts
    Set<AccountOwner> owners = new HashSet<>();
    owners.add(customer);
    CheckingAccount account = new CheckingAccount(UUID.randomUUID().toString(), owners, 100.0);
    customer.addAccount(account);

    assertEquals(1, customer.getAccounts().size());
    assertTrue(customer.getAccounts().contains(account));
  }

  @Test
  void testGetSavingsAccounts() {
    Set<SavingAccount> savingsAccounts = customer.getSavingsAccounts();
    assertNotNull(savingsAccounts);
    assertTrue(savingsAccounts.isEmpty());

    // Test that adding a savings account shows up in getSavingsAccounts
    Set<AccountOwner> owners = new HashSet<>();
    owners.add(customer);
    SavingAccount savingsAccount = new SavingAccount(UUID.randomUUID().toString(), owners, 100.0);
    customer.addSavingsAccount(savingsAccount);

    assertEquals(1, customer.getSavingsAccounts().size());
    assertTrue(customer.getSavingsAccounts().contains(savingsAccount));
  }

  @Test
  void testAddMultipleAccounts() {
    Set<AccountOwner> owners = new HashSet<>();
    owners.add(customer);

    CheckingAccount account1 = new CheckingAccount(UUID.randomUUID().toString(), owners, 100.0);
    CheckingAccount account2 = new CheckingAccount(UUID.randomUUID().toString(), owners, 200.0);

    customer.addAccount(account1);
    customer.addAccount(account2);

    assertEquals(2, customer.getAccounts().size());
    assertTrue(customer.getAccounts().contains(account1));
    assertTrue(customer.getAccounts().contains(account2));
  }

  @Test
  void testAddMultipleSavingsAccounts() {
    Set<AccountOwner> owners = new HashSet<>();
    owners.add(customer);

    SavingAccount savings1 = new SavingAccount(UUID.randomUUID().toString(), owners, 100.0);
    SavingAccount savings2 = new SavingAccount(UUID.randomUUID().toString(), owners, 200.0);

    customer.addSavingsAccount(savings1);
    customer.addSavingsAccount(savings2);

    assertEquals(2, customer.getSavingsAccounts().size());
    assertTrue(customer.getSavingsAccounts().contains(savings1));
    assertTrue(customer.getSavingsAccounts().contains(savings2));
  }

  @Test
  void testEquals() {
    // Test equality with same id
    Customer sameCustomer = new Customer(customerId, "Different Name");
    assertTrue(customer.equals(sameCustomer));

    // Test inequality with different id
    Customer differentCustomer = new Customer(UUID.randomUUID(), customerName);
    assertFalse(customer.equals(differentCustomer));

    // Test inequality with null
    assertNotEquals(customer, null);

    // Test inequality with different type
    assertNotEquals(customer, "Not a customer");

    // Test reflexivity
    assertTrue(customer.equals(customer));
  }

  @Test
  void testHashCode() {
    assertEquals(customerId.hashCode(), customer.hashCode());

    // Test that equal objects have equal hash codes
    Customer sameCustomer = new Customer(customerId, "Different Name");
    assertEquals(customer.hashCode(), sameCustomer.hashCode());
  }

  @Test
  void testToString() {
    String expected = "Customer{id=" + customerId + ", name='" + customerName + "'}";
    assertEquals(expected, customer.toString());
  }

  @Test
  void testAccountOwnerInterface() {
    // Test that Customer properly implements AccountOwner interface
    assertTrue(customer instanceof AccountOwner);

    // Test interface methods work correctly
    assertEquals(customerId, customer.getId());
    assertEquals(customerName, customer.getName());

    Set<AccountOwner> owners = new HashSet<>();
    owners.add(customer);
    CheckingAccount checkingAccount =
        new CheckingAccount(UUID.randomUUID().toString(), owners, 100.0);
    SavingAccount savingAccount = new SavingAccount(UUID.randomUUID().toString(), owners, 100.0);

    customer.addAccount(checkingAccount);
    customer.addSavingsAccount(savingAccount);

    assertEquals(1, customer.getAccounts().size());
    assertEquals(1, customer.getSavingsAccounts().size());
  }

  @Test
  void testAddSameAccountTwice() {
    Set<AccountOwner> owners = new HashSet<>();
    owners.add(customer);
    CheckingAccount account = new CheckingAccount(UUID.randomUUID().toString(), owners, 100.0);

    customer.addAccount(account);
    customer.addAccount(account); // Add same account again

    // Should only have one account since it's a Set
    assertEquals(1, customer.getAccounts().size());
    assertTrue(customer.getAccounts().contains(account));
  }

  @Test
  void testAddSameSavingsAccountTwice() {
    Set<AccountOwner> owners = new HashSet<>();
    owners.add(customer);
    SavingAccount savingsAccount = new SavingAccount(UUID.randomUUID().toString(), owners, 100.0);

    customer.addSavingsAccount(savingsAccount);
    customer.addSavingsAccount(savingsAccount); // Add same account again

    // Should only have one account since it's a Set
    assertEquals(1, customer.getSavingsAccounts().size());
    assertTrue(customer.getSavingsAccounts().contains(savingsAccount));
  }

  @Test
  void testCustomerWithEmptyName() {
    Customer customerWithEmptyName = new Customer(UUID.randomUUID(), "");
    assertEquals("", customerWithEmptyName.getName());
    assertNotNull(customerWithEmptyName.getId());
  }

  @Test
  void testCustomerWithNullName() {
    Customer customerWithNullName = new Customer(UUID.randomUUID(), null);
    assertNull(customerWithNullName.getName());
    assertNotNull(customerWithNullName.getId());
  }
}
