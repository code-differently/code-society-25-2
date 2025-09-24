package com.codedifferently.lesson17.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BusinessCheckingAccountTest {

  private Business business;
  private Customer customer;
  private String accountNumber;
  private double initialBalance;

  @BeforeEach
  void setUp() {
    business = new Business(UUID.randomUUID(), "Test Business LLC", "12-3456789");
    customer = new Customer(UUID.randomUUID(), "John Doe");
    accountNumber = "BCA-123456";
    initialBalance = 1000.0;
  }

  @Test
  void testConstructorWithBusinessOwner() {
    Set<AccountOwner> owners = new HashSet<>();
    owners.add(business);

    BusinessCheckingAccount account =
        new BusinessCheckingAccount(accountNumber, owners, initialBalance);

    assertNotNull(account);
    assertEquals(accountNumber, account.getAccountNumber());
    assertEquals(initialBalance, account.getBalance());
    assertFalse(account.isClosed());
    assertTrue(account.getOwners().contains(business));
  }

  @Test
  void testConstructorWithBusinessAndCustomerOwners() {
    Set<AccountOwner> owners = new HashSet<>();
    owners.add(business);
    owners.add(customer);

    BusinessCheckingAccount account =
        new BusinessCheckingAccount(accountNumber, owners, initialBalance);

    assertNotNull(account);
    assertEquals(accountNumber, account.getAccountNumber());
    assertEquals(initialBalance, account.getBalance());
    assertFalse(account.isClosed());
    assertTrue(account.getOwners().contains(business));
    assertTrue(account.getOwners().contains(customer));
  }

  @Test
  void testConstructorWithoutBusinessOwnerThrowsException() {
    Set<AccountOwner> owners = new HashSet<>();
    owners.add(customer); // Only customer, no business

    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> new BusinessCheckingAccount(accountNumber, owners, initialBalance));

    assertEquals(
        "Business checking account must have at least one business owner", exception.getMessage());
  }

  @Test
  void testConstructorWithEmptyOwnersThrowsException() {
    Set<AccountOwner> owners = new HashSet<>(); // Empty set

    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> new BusinessCheckingAccount(accountNumber, owners, initialBalance));

    assertEquals(
        "Business checking account must have at least one business owner", exception.getMessage());
  }

  @Test
  void testInheritsFromCheckingAccount() {
    Set<AccountOwner> owners = new HashSet<>();
    owners.add(business);

    BusinessCheckingAccount account =
        new BusinessCheckingAccount(accountNumber, owners, initialBalance);

    // Test that it inherits CheckingAccount behavior
    assertTrue(account instanceof CheckingAccount);

    // Test deposit functionality inherited from CheckingAccount
    account.deposit(500.0);
    assertEquals(1500.0, account.getBalance());

    // Test withdraw functionality inherited from CheckingAccount
    account.withdraw(200.0);
    assertEquals(1300.0, account.getBalance());
  }

  @Test
  void testToString() {
    Set<AccountOwner> owners = new HashSet<>();
    owners.add(business);

    BusinessCheckingAccount account =
        new BusinessCheckingAccount(accountNumber, owners, initialBalance);

    String expected =
        "BusinessCheckingAccount{accountNumber='"
            + accountNumber
            + "', balance="
            + initialBalance
            + ", isActive=true}";
    assertEquals(expected, account.toString());
  }

  @Test
  void testToStringWhenClosed() {
    Set<AccountOwner> owners = new HashSet<>();
    owners.add(business);

    BusinessCheckingAccount account =
        new BusinessCheckingAccount(accountNumber, owners, initialBalance);
    account.withdraw(initialBalance); // Withdraw all money first
    account.closeAccount();

    String expected =
        "BusinessCheckingAccount{accountNumber='"
            + accountNumber
            + "', balance=0.0, isActive=false}";
    assertEquals(expected, account.toString());
  }

  @Test
  void testBusinessValidationWithMultipleBusinesses() {
    Business business2 = new Business(UUID.randomUUID(), "Another Business", "98-7654321");
    Set<AccountOwner> owners = new HashSet<>();
    owners.add(business);
    owners.add(business2);
    owners.add(customer);

    BusinessCheckingAccount account =
        new BusinessCheckingAccount(accountNumber, owners, initialBalance);

    assertNotNull(account);
    assertEquals(3, account.getOwners().size());
    assertTrue(account.getOwners().contains(business));
    assertTrue(account.getOwners().contains(business2));
    assertTrue(account.getOwners().contains(customer));
  }

  @Test
  void testAccountClosureInherited() {
    Set<AccountOwner> owners = new HashSet<>();
    owners.add(business);

    BusinessCheckingAccount account =
        new BusinessCheckingAccount(accountNumber, owners, initialBalance);

    // Test account closure functionality
    assertFalse(account.isClosed());
    account.withdraw(initialBalance); // Withdraw all money first
    account.closeAccount();
    assertTrue(account.isClosed());

    // Test that closed account throws exception on deposit
    IllegalStateException exception =
        assertThrows(IllegalStateException.class, () -> account.deposit(100.0));
    assertNotNull(exception);
  }

  @Test
  void testAccountNumberAndBalance() {
    Set<AccountOwner> owners = new HashSet<>();
    owners.add(business);

    BusinessCheckingAccount account =
        new BusinessCheckingAccount(accountNumber, owners, initialBalance);

    assertEquals(accountNumber, account.getAccountNumber());
    assertEquals(initialBalance, account.getBalance());

    // Test balance changes
    account.deposit(250.0);
    assertEquals(1250.0, account.getBalance());

    account.withdraw(150.0);
    assertEquals(1100.0, account.getBalance());
  }

  @Test
  void testConstructorWithNegativeBalance() {
    Set<AccountOwner> owners = new HashSet<>();
    owners.add(business);

    // Should allow negative initial balance (overdraft scenarios)
    BusinessCheckingAccount account = new BusinessCheckingAccount(accountNumber, owners, -100.0);

    assertEquals(-100.0, account.getBalance());
  }

  @Test
  void testConstructorWithZeroBalance() {
    Set<AccountOwner> owners = new HashSet<>();
    owners.add(business);

    BusinessCheckingAccount account = new BusinessCheckingAccount(accountNumber, owners, 0.0);

    assertEquals(0.0, account.getBalance());
  }
}
