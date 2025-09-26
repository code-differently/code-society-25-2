package com.codedifferently.lesson17.bank;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MoneyOrderTest {

  private CheckingAccount account1;
  private CheckingAccount account2;
  private MoneyOrder classUnderTest;

  @BeforeEach
  void setUp() {
    account1 = new CheckingAccount("123456789", null, 100.0);
    account2 = new CheckingAccount("987654321", null, 200.0);
  }

  @Test
  void testCreate() {
    // Act
    classUnderTest = new MoneyOrder("123456789", 50.0, account1, account2, "USD");

    // Assert
    assertThat(account1.getBalance()).isEqualTo(50.0);
    assertThat(account2.getBalance()).isEqualTo(250.0);
  }

  @Test
  void testCreate2() {
    // Act
    classUnderTest = new MoneyOrder("123456789", 100.0, account2, account1, "USD");

    // Assert
    assertThat(account1.getBalance()).isEqualTo(200.0);
    assertThat(account2.getBalance()).isEqualTo(100.0);
  }

  @Test
  void testConstructor_CantCreateWithNegativeAmount() {
    // Act & Assert
    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> new MoneyOrder("123456789", -50.0, account1, account2, "USD"))
        .withMessage("Amount must be positive");
  }

  @Test
  void testHashCode() {
    // Arrange
    MoneyOrder classUnderTest = new MoneyOrder("123456789", 50.0, account1, account2, "USD");
    MoneyOrder otherOrder = new MoneyOrder("123456789", 50.0, account1, account2, "USD");

    // Assert
    assertThat(classUnderTest.hashCode()).isEqualTo(otherOrder.hashCode());
  }

  @Test
  void testEquals() {
    // Arrange
    MoneyOrder classUnderTest = new MoneyOrder("123456789", 50.0, account1, account2, "USD");
    Check otherCheck = new Check("123456789", 25.0, account1);
    Check differentCheck = new Check("987654321", 100.0, account1);

    // Assert
    assertThat(classUnderTest.equals(otherCheck)).isTrue();
    assertThat(classUnderTest.equals(differentCheck)).isFalse();
  }

  @Test
  void testToString() {
    // Arrange
    classUnderTest = new MoneyOrder("123456789", 50.0, account1, account2, "USD");
    // Assert
    assertThat(classUnderTest.toString())
        .isEqualTo(
            "MoneyOrder{orderNumber='123456789', amount=50.0, fromAccount=123456789, toAccount=987654321}");
  }
}
