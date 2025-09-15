package com.codedifferently.lesson17.bank;

import static org.junit.jupiter.api.Assertions.*;

import com.codedifferently.lesson17.bank.exceptions.InsufficientFundsException;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.Test;

/** Tests for the SavingsAccount class. */
public class SavingsAccountTest {

  @Test
  public void testGetAccountNumber() {
    // Given
    Customer customer1 = new Customer(UUID.randomUUID(), "John Doe");
    SavingsAccount account = new SavingsAccount("123456", Set.of(customer1), 100.0);

    // When
    String accountNumber = account.getAccountNumber();

    // Then
    assertEquals("123456", accountNumber);
  }

  @Test
  public void testGetOwners() {
    // Given
    Customer customer1 = new Customer(UUID.randomUUID(), "John Doe");
    Set<Customer> owners = Set.of(customer1);
    SavingsAccount account = new SavingsAccount("123456", owners, 100.0);

    // When
    Set<Customer> result = account.getOwners();

    // Then
    assertEquals(owners, result);
  }

  @Test
  public void testDeposit() {
    // Given
    Customer customer1 = new Customer(UUID.randomUUID(), "John Doe");
    SavingsAccount account = new SavingsAccount("123456", Set.of(customer1), 100.0);

    // When
    account.deposit(50.0);

    // Then
    assertEquals(150.0, account.getBalance());
  }

  @Test
  public void testDeposit_withNegativeAmount() {
    // Given
    Customer customer1 = new Customer(UUID.randomUUID(), "John Doe");
    SavingsAccount account = new SavingsAccount("123456", Set.of(customer1), 100.0);

    // When & Then
    assertThrows(IllegalArgumentException.class, () -> account.deposit(-10.0));
  }

  @Test
  public void testWithdraw() throws InsufficientFundsException {
    // Given
    Customer customer1 = new Customer(UUID.randomUUID(), "John Doe");
    SavingsAccount account = new SavingsAccount("123456", Set.of(customer1), 100.0);

    // When
    account.withdraw(25.0);

    // Then
    assertEquals(75.0, account.getBalance());
  }

  @Test
  public void testWithdraw_withInsufficientBalance() {
    // Given
    Customer customer1 = new Customer(UUID.randomUUID(), "John Doe");
    SavingsAccount account = new SavingsAccount("123456", Set.of(customer1), 100.0);

    // When & Then
    assertThrows(InsufficientFundsException.class, () -> account.withdraw(150.0));
  }

  @Test
  public void testWithdraw_withNegativeAmount() {
    // Given
    Customer customer1 = new Customer(UUID.randomUUID(), "John Doe");
    SavingsAccount account = new SavingsAccount("123456", Set.of(customer1), 100.0);

    // When & Then
    assertThrows(IllegalStateException.class, () -> account.withdraw(-10.0));
  }

  @Test
  public void testGetBalance() {
    // Given
    Customer customer1 = new Customer(UUID.randomUUID(), "John Doe");
    SavingsAccount account = new SavingsAccount("123456", Set.of(customer1), 100.0);

    // When
    double balance = account.getBalance();

    // Then
    assertEquals(100.0, balance);
  }

  @Test
  public void testCloseAccount_withPositiveBalance() {
    // Given
    Customer customer1 = new Customer(UUID.randomUUID(), "John Doe");
    SavingsAccount account = new SavingsAccount("123456", Set.of(customer1), 100.0);

    // When & Then
    assertThrows(IllegalStateException.class, () -> account.closeAccount());
  }

  @Test
  public void testIsClosed() {
    // Given
    Customer customer1 = new Customer(UUID.randomUUID(), "John Doe");
    SavingsAccount account = new SavingsAccount("123456", Set.of(customer1), 0.0);

    // When
    account.closeAccount();

    // Then
    assertTrue(account.isClosed());
  }

  @Test
  public void testEquals() {
    // Given
    Customer customer1 = new Customer(UUID.randomUUID(), "John Doe");
    SavingsAccount account1 = new SavingsAccount("123456", Set.of(customer1), 100.0);
    SavingsAccount account2 = new SavingsAccount("123456", Set.of(customer1), 200.0);
    SavingsAccount account3 = new SavingsAccount("789012", Set.of(customer1), 100.0);

    // When & Then
    assertEquals(account1, account2);
    assertNotEquals(account1, account3);
  }

  @Test
  public void testHashCode() {
    // Given
    Customer customer1 = new Customer(UUID.randomUUID(), "John Doe");
    SavingsAccount account1 = new SavingsAccount("123456", Set.of(customer1), 100.0);
    SavingsAccount account2 = new SavingsAccount("123456", Set.of(customer1), 200.0);

    // When & Then
    assertEquals(account1.hashCode(), account2.hashCode());
  }

  @Test
  public void testToString() {
    // Given
    Customer customer1 = new Customer(UUID.randomUUID(), "John Doe");
    SavingsAccount account = new SavingsAccount("123456", Set.of(customer1), 100.0);

    // When
    String result = account.toString();

    // Then
    assertTrue(result.contains("SavingsAccount"));
    assertTrue(result.contains("123456"));
    assertTrue(result.contains("100.0"));
  }
}
