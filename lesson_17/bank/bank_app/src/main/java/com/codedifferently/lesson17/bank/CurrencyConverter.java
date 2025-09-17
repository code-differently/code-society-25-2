package com.codedifferently.lesson17.bank;

/** Converts an amount in a given currency to USD. */
public interface CurrencyConverter {
  /**
   * Converts the given amount in the given currency to USD.
   *
   * @param amount The amount in the original currency.
   * @param currency The currency type.
   * @return The equivalent amount in USD, rounded to 2 decimals (half-up).
   * @throws UnsupportedOperationException if the currency is not supported.
   */
  double convertToUsd(double amount, Currency currency);
}
