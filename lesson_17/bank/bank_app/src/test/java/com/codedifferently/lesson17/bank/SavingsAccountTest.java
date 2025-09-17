package com.codedifferently.lesson17.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.codedifferently.lesson17.bank.exceptions.InsufficientFundsException;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Test cases for SavingsAccount class. */
class SavingsAccountTest {

  private SavingsAccount savingsAccount;
  private Customer customer;

  @BeforeEach
  void setUp() {
    customer = new Customer(UUID.randomUUID(), "John Doe");
    savingsAccount = new SavingsAccount("SAV001", Set.of(customer), 1000.0);
  }

  @Test
  void testConstructor() {
    assertEquals("SAV001", savingsAccount.getAccountNumber());
    assertEquals(1000.0, savingsAccount.getBalance());
    assertTrue(savingsAccount.getOwners().contains(customer));
  }

  @Test
  void testDeposit() {
    savingsAccount.deposit(500.0);
    assertEquals(1500.0, savingsAccount.getBalance());
  }

  @Test
  void testWithdraw() throws InsufficientFundsException {
    savingsAccount.withdraw(300.0);
    assertEquals(700.0, savingsAccount.getBalance());
  }

  @Test
  void testWithdrawInsufficientFunds() {
    assertThrows(
        InsufficientFundsException.class,
        () -> savingsAccount.withdraw(1500.0),
        "Should throw InsufficientFundsException when withdrawal amount exceeds balance");
  }

  @Test
  void testWithdrawNegativeAmount() {
    assertThrows(
        IllegalArgumentException.class,
        () -> savingsAccount.withdraw(-100.0),
        "Should throw IllegalArgumentException for negative withdrawal amount");
  }

  @Test
  void testDepositNegativeAmount() {
    assertThrows(
        IllegalArgumentException.class,
        () -> savingsAccount.deposit(-100.0),
        "Should throw IllegalArgumentException for negative deposit amount");
  }

  @Test
  void testGetBalance() {
    assertEquals(1000.0, savingsAccount.getBalance());
  }

  @Test
  void testGetOwners() {
    Set<Customer> owners = savingsAccount.getOwners();
    assertEquals(1, owners.size());
    assertTrue(owners.contains(customer));
  }

  @Test
  void testGetAccountNumber() {
    assertEquals("SAV001", savingsAccount.getAccountNumber());
  }

  @Test
  void testMultipleCustomers() {
    Customer customer2 = new Customer(UUID.randomUUID(), "Jane Smith");
    SavingsAccount jointAccount = new SavingsAccount("SAV002", Set.of(customer, customer2), 2000.0);

    assertEquals(2, jointAccount.getOwners().size());
    assertTrue(jointAccount.getOwners().contains(customer));
    assertTrue(jointAccount.getOwners().contains(customer2));
  }

  @Test
  void testAccountImplementsInterface() {
    assertTrue(savingsAccount instanceof Account);
  }

  @Test
  void testZeroBalanceAccount() {
    SavingsAccount zeroAccount = new SavingsAccount("SAV003", Set.of(customer), 0.0);
    assertEquals(0.0, zeroAccount.getBalance());

    assertThrows(
        InsufficientFundsException.class,
        () -> zeroAccount.withdraw(1.0),
        "Should not allow withdrawal from zero balance account");
  }
}
