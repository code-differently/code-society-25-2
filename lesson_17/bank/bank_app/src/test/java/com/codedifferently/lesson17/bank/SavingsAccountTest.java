package com.codedifferently.lesson17.bank;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SavingsAccountTest {

  private SavingsAccount classUnderTest;

  @BeforeEach
  void setUp() {
    classUnderTest = new SavingsAccount("987654321", 200.0);
  }

  @Test
  void writeCheckThrowsException() {
    assertThatExceptionOfType(UnsupportedOperationException.class)
        .isThrownBy(() -> classUnderTest.writeCheck(50.0))
        .withMessage("Cannot write checks from a savings account");
  }

  @Test
  void testSavingsAccountCreation() {
    assertEquals("987654321", classUnderTest.getAccountNumber());
    assertEquals(200.0, classUnderTest.getBalance());
  }
}
