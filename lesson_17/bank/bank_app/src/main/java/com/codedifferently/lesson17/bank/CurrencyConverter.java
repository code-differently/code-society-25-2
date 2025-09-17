package com.codedifferently.lesson17.bank;

/**
 * Currency converter utility that handles conversion between different currencies and USD.
 * Encapsulates currency conversion logic following the Single Responsibility Principle.
 */
public class CurrencyConverter {

  /**
   * Converts an amount from the specified currency to USD.
   *
   * @param amount The amount to convert.
   * @param fromCurrency The source currency type.
   * @return The amount converted to USD.
   * @throws IllegalArgumentException if amount is negative.
   */
  public double convertToUSD(double amount, CurrencyType fromCurrency) {
    if (amount < 0) {
      throw new IllegalArgumentException("Amount cannot be negative");
    }

    if (fromCurrency == CurrencyType.USD) {
      return amount;
    }

    return amount * fromCurrency.getExchangeRateToUSD();
  }

  /**
   * Converts an amount from USD to the specified currency.
   *
   * @param amountUSD The USD amount to convert.
   * @param toCurrency The target currency type.
   * @return The amount converted to the target currency.
   * @throws IllegalArgumentException if amount is negative.
   */
  public double convertFromUSD(double amountUSD, CurrencyType toCurrency) {
    if (amountUSD < 0) {
      throw new IllegalArgumentException("Amount cannot be negative");
    }

    if (toCurrency == CurrencyType.USD) {
      return amountUSD;
    }

    return amountUSD / toCurrency.getExchangeRateToUSD();
  }

  /**
   * Converts an amount between two currencies.
   *
   * @param amount The amount to convert.
   * @param fromCurrency The source currency.
   * @param toCurrency The target currency.
   * @return The converted amount.
   */
  public double convert(double amount, CurrencyType fromCurrency, CurrencyType toCurrency) {
    if (fromCurrency == toCurrency) {
      return amount;
    }

    double usdAmount = convertToUSD(amount, fromCurrency);
    return convertFromUSD(usdAmount, toCurrency);
  }

  /**
   * Formats a currency amount with the appropriate currency symbol.
   *
   * @param amount The amount to format.
   * @param currency The currency type.
   * @return The formatted currency string.
   */
  public String formatAmount(double amount, CurrencyType currency) {
    return String.format("%.2f %s", amount, currency.name());
  }
}
