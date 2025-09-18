package com.codedifferently.lesson17.bank;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CheckTest {

  private CheckingAccount source;
  private CheckingAccount target;

  @BeforeEach
  void setUp() {
    // Using null for owners to match the existing constructor used in the repo
    source = new CheckingAccount("ACC-1", null, 100.0);
    target = new CheckingAccount("ACC-2", null, 200.0);
  }

  @Test
  void depositFunds_withValidCheck_movesMoneyFromSourceToTarget() {
    // Arrange
    Check check = new Check(source, 50.0);

    // Act
    check.depositFunds(target);

    // Assert
    assertThat(source.getBalance()).isEqualTo(50.0); // 100 - 50
    assertThat(target.getBalance()).isEqualTo(250.0); // 200 + 50
  }

  @Test
  void constructor_rejectsNonPositiveAmount() {
    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> new Check(source, -50.0))
        .withMessage("Check amount must be positive");
  }

  @Test
  void moneyOrder_debitsImmediately_thenDepositOnlyCreditsTarget() {
    // Arrange (initial balances: source 100, target 200)
    MoneyOrder mo = new MoneyOrder(source, 40.0);

    // Immediately debited on creation
    assertThat(source.getBalance()).isEqualTo(60.0);

    // Act: deposit prepaid instrument
    mo.depositFunds(target);

    // Assert: source unchanged; target credited once
    assertThat(source.getBalance()).isEqualTo(60.0);
    assertThat(target.getBalance()).isEqualTo(240.0);
  }
}
