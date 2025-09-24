package com.codedifferently.lesson17.bank;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CurrencyConverterTest {

  private CurrencyConverter classUnderTest;

  @BeforeEach
  void setUp() {
    classUnderTest = new CurrencyConverter();
  }

  @Test
  void convertToUsd_USD() {
    // Act
    double result = classUnderTest.convertToUsd(100.0, Currency.USD);

    // Assert
    assertThat(result).isEqualTo(100.0);
  }

  @Test
  void convertToUsd_EUR() {
    // Act
    double result = classUnderTest.convertToUsd(100.0, Currency.EUR);

    // Assert
    assertThat(result).isEqualTo(108.0); // 100 * 1.08
  }

  @Test
  void convertToUsd_GBP() {
    // Act
    double result = classUnderTest.convertToUsd(100.0, Currency.GBP);

    // Assert
    assertThat(result).isEqualTo(127.0); // 100 * 1.27
  }

  @Test
  void convertToUsd_JPY() {
    // Act
    double result = classUnderTest.convertToUsd(1000.0, Currency.JPY);

    // Assert
    assertThat(result).isEqualTo(6.7); // 1000 * 0.0067
  }

  @Test
  void convertToUsd_CAD() {
    // Act
    double result = classUnderTest.convertToUsd(100.0, Currency.CAD);

    // Assert
    assertThat(result).isEqualTo(74.0); // 100 * 0.74
  }

  @Test
  void convertToUsd_NegativeAmount() {
    // Act & Assert
    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> classUnderTest.convertToUsd(-50.0, Currency.USD))
        .withMessage("Amount cannot be negative");
  }

  @Test
  void convertToUsd_NullCurrency() {
    // Act & Assert
    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> classUnderTest.convertToUsd(100.0, null))
        .withMessage("Currency cannot be null");
  }

  @Test
  void convertToUsd_ZeroAmount() {
    // Act
    double result = classUnderTest.convertToUsd(0.0, Currency.EUR);

    // Assert
    assertThat(result).isEqualTo(0.0);
  }

  @Test
  void getExchangeRate_USD() {
    // Act
    Double rate = classUnderTest.getExchangeRate(Currency.USD);

    // Assert
    assertThat(rate).isEqualTo(1.0);
  }

  @Test
  void getExchangeRate_EUR() {
    // Act
    Double rate = classUnderTest.getExchangeRate(Currency.EUR);

    // Assert
    assertThat(rate).isEqualTo(1.08);
  }

  @Test
  void isCurrencySupported_SupportedCurrency() {
    // Act & Assert
    assertThat(classUnderTest.isCurrencySupported(Currency.USD)).isTrue();
    assertThat(classUnderTest.isCurrencySupported(Currency.EUR)).isTrue();
    assertThat(classUnderTest.isCurrencySupported(Currency.GBP)).isTrue();
    assertThat(classUnderTest.isCurrencySupported(Currency.JPY)).isTrue();
    assertThat(classUnderTest.isCurrencySupported(Currency.CAD)).isTrue();
    assertThat(classUnderTest.isCurrencySupported(Currency.AUD)).isTrue();
    assertThat(classUnderTest.isCurrencySupported(Currency.CHF)).isTrue();
    assertThat(classUnderTest.isCurrencySupported(Currency.CNY)).isTrue();
  }

  @Test
  void testAllCurrenciesHaveRates() {
    // Test that all enum values have corresponding exchange rates
    for (Currency currency : Currency.values()) {
      assertThat(classUnderTest.isCurrencySupported(currency))
          .as("Currency %s should be supported", currency)
          .isTrue();

      assertThat(classUnderTest.getExchangeRate(currency))
          .as("Currency %s should have an exchange rate", currency)
          .isNotNull()
          .isPositive();
    }
  }
}
