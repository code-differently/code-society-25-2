package com.codedifferently.lesson17.bank;

/**
 * Utility class for currency conversion.
 */
public final class CurrencyConverter {

  private CurrencyConverter() {
    // Utility class
  }

  public static double convertToUSD(double amount, Currency fromCurrency) {
    if (amount < 0) {
      throw new IllegalArgumentException("Amount must be positive");
    }
    return fromCurrency == Currency.USD ? amount : amount / fromCurrency.getConversionRateToUSD();
  }
}
