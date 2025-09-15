package com.codedifferently.lesson17.bank;

import static org.junit.jupiter.api.Assertions.*;

import com.codedifferently.lesson17.bank.exceptions.CheckVoidedException;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.Test;

/** Tests for the MoneyOrder class. */
public class MoneyOrderTest {

  @Test
  public void testConstructor_WithdrawsImmediately() {
    // Given
    Customer customer1 = new Customer(UUID.randomUUID(), "John Doe");
    CheckingAccount sourceAccount = new CheckingAccount("123456", Set.of(customer1), 1000.0);

    // When
    MoneyOrder moneyOrder = new MoneyOrder("MO001", 100.0, sourceAccount);

    // Then
    assertEquals(900.0, sourceAccount.getBalance()); // Funds withdrawn immediately
    assertFalse(moneyOrder.getIsUsed());
  }

  @Test
  public void testConstructor_CantCreateMoneyOrderWithNegativeAmount() {
    // Given
    Customer customer1 = new Customer(UUID.randomUUID(), "John Doe");
    CheckingAccount sourceAccount = new CheckingAccount("123456", Set.of(customer1), 1000.0);

    // When & Then
    assertThrows(
        IllegalArgumentException.class, () -> new MoneyOrder("MO001", -100.0, sourceAccount));
  }

  @Test
  public void testDepositFunds() {
    // Given
    Customer customer1 = new Customer(UUID.randomUUID(), "John Doe");
    Customer customer2 = new Customer(UUID.randomUUID(), "Jane Smith");
    CheckingAccount sourceAccount = new CheckingAccount("123456", Set.of(customer1), 1000.0);
    CheckingAccount targetAccount = new CheckingAccount("789012", Set.of(customer2), 500.0);
    MoneyOrder moneyOrder = new MoneyOrder("MO001", 100.0, sourceAccount);

    // When
    moneyOrder.depositFunds(targetAccount);

    // Then
    assertEquals(600.0, targetAccount.getBalance());
    assertEquals(900.0, sourceAccount.getBalance());
    assertTrue(moneyOrder.getIsUsed());
  }

  @Test
  public void testDepositFunds_MoneyOrderUsed() {
    // Given
    Customer customer1 = new Customer(UUID.randomUUID(), "John Doe");
    Customer customer2 = new Customer(UUID.randomUUID(), "Jane Smith");
    CheckingAccount sourceAccount = new CheckingAccount("123456", Set.of(customer1), 1000.0);
    CheckingAccount targetAccount = new CheckingAccount("789012", Set.of(customer2), 500.0);
    MoneyOrder moneyOrder = new MoneyOrder("MO001", 100.0, sourceAccount);

    // Use the money order first
    moneyOrder.depositFunds(targetAccount);

    // When & Then
    assertThrows(CheckVoidedException.class, () -> moneyOrder.depositFunds(targetAccount));
  }

  @Test
  public void testEquals() {
    // Given
    Customer customer1 = new Customer(UUID.randomUUID(), "John Doe");
    CheckingAccount sourceAccount = new CheckingAccount("123456", Set.of(customer1), 1000.0);
    MoneyOrder moneyOrder1 = new MoneyOrder("MO001", 100.0, sourceAccount);
    MoneyOrder moneyOrder2 = new MoneyOrder("MO001", 200.0, sourceAccount);
    MoneyOrder moneyOrder3 = new MoneyOrder("MO002", 100.0, sourceAccount);

    // When & Then
    assertEquals(moneyOrder1, moneyOrder2);
    assertNotEquals(moneyOrder1, moneyOrder3);
  }

  @Test
  public void testHashCode() {
    // Given
    Customer customer1 = new Customer(UUID.randomUUID(), "John Doe");
    CheckingAccount sourceAccount = new CheckingAccount("123456", Set.of(customer1), 1000.0);
    MoneyOrder moneyOrder1 = new MoneyOrder("MO001", 100.0, sourceAccount);
    MoneyOrder moneyOrder2 = new MoneyOrder("MO001", 200.0, sourceAccount);

    // When & Then
    assertEquals(moneyOrder1.hashCode(), moneyOrder2.hashCode());
  }

  @Test
  public void testToString() {
    // Given
    Customer customer1 = new Customer(UUID.randomUUID(), "John Doe");
    CheckingAccount sourceAccount = new CheckingAccount("123456", Set.of(customer1), 1000.0);
    MoneyOrder moneyOrder = new MoneyOrder("MO001", 100.0, sourceAccount);

    // When
    String result = moneyOrder.toString();

    // Then
    assertTrue(result.contains("MoneyOrder"));
    assertTrue(result.contains("MO001"));
    assertTrue(result.contains("100.0"));
  }
}
