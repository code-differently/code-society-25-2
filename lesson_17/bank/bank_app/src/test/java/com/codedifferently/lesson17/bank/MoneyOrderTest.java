package com.codedifferently.lesson17.bank;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MoneyOrderTest {

  private CheckingAccount sourceAccount;
  private CheckingAccount destinationAccount;

  @BeforeEach
  void setUp() {
    Customer customer = new Customer(UUID.randomUUID(), "John Doe");
    sourceAccount = new CheckingAccount("123456789", Set.of(customer), 500.0);
    destinationAccount = new CheckingAccount("987654321", Set.of(customer), 200.0);
  }

  @Test
  void testCreateMoneyOrder_ImmediateWithdrawal() {
    // Act
    MoneyOrder moneyOrder = new MoneyOrder("MO123", 100.0, sourceAccount);

    // Assert
    assertEquals("MO123", moneyOrder.getMoneyOrderNumber());
    assertEquals(100.0, moneyOrder.getAmount());
    assertEquals("123456789", moneyOrder.getSourceAccountNumber());
    assertFalse(moneyOrder.isRedeemed());
    assertEquals(400.0, sourceAccount.getBalance()); // 500 - 100
  }

  @Test
  void testCreateMoneyOrder_NegativeAmount() {
    // Act & Assert
    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> new MoneyOrder("MO123", -50.0, sourceAccount))
        .withMessage("Money order amount must be positive");
  }

  @Test
  void testDepositFunds() {
    // Arrange
    MoneyOrder moneyOrder = new MoneyOrder("MO123", 100.0, sourceAccount);

    // Act
    moneyOrder.depositFunds(destinationAccount);

    // Assert
    assertEquals(300.0, destinationAccount.getBalance()); // 200 + 100
    assertTrue(moneyOrder.isRedeemed());
  }

  @Test
  void testDepositFunds_AlreadyRedeemed() {
    // Arrange
    MoneyOrder moneyOrder = new MoneyOrder("MO123", 100.0, sourceAccount);
    moneyOrder.depositFunds(destinationAccount);

    // Act & Assert
    assertThatExceptionOfType(IllegalStateException.class)
        .isThrownBy(() -> moneyOrder.depositFunds(destinationAccount))
        .withMessage("Money order has already been redeemed");
  }

  @Test
  void testEquals() {
    // Arrange
    MoneyOrder moneyOrder1 = new MoneyOrder("MO123", 100.0, sourceAccount);
    MoneyOrder moneyOrder2 = new MoneyOrder("MO123", 150.0, destinationAccount);
    MoneyOrder moneyOrder3 = new MoneyOrder("MO456", 100.0, sourceAccount);

    // Assert
    assertEquals(moneyOrder1, moneyOrder2); // Same money order number
    assertThat(moneyOrder1).isNotEqualTo(moneyOrder3); // Different money order number
  }

  @Test
  void testHashCode() {
    // Arrange
    MoneyOrder moneyOrder1 = new MoneyOrder("MO123", 100.0, sourceAccount);
    MoneyOrder moneyOrder2 = new MoneyOrder("MO123", 150.0, destinationAccount);

    // Assert
    assertEquals(moneyOrder1.hashCode(), moneyOrder2.hashCode());
  }

  @Test
  void testToString() {
    // Arrange
    MoneyOrder moneyOrder = new MoneyOrder("MO123", 100.0, sourceAccount);

    // Act
    String result = moneyOrder.toString();

    // Assert
    String expected =
        "MoneyOrder{moneyOrderNumber='MO123', amount=100.0, sourceAccountNumber='123456789', isRedeemed=false}";
    assertEquals(expected, result);
  }
}
