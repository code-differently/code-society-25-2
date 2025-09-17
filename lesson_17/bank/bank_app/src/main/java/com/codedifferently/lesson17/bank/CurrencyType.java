package com.codedifferently.lesson17.bank;

/** Enumeration of supported currency types for banking operations. */
public enum CurrencyType {
  USD("United States Dollar", 1.0),
  EUR("Euro", 1.1),
  GBP("British Pound", 1.25),
  CAD("Canadian Dollar", 0.75),
  JPY("Japanese Yen", 0.007),
  CNY("Chinese Yuan", 0.14),
  AUD("Australian Dollar", 0.67),
  CHF("Swiss Franc", 1.05),
  SEK("Swedish Krona", 0.095),
  NOK("Norwegian Krone", 0.092);

  private final String fullName;
  private final double exchangeRateToUSD;

  /**
   * Creates a currency type with its exchange rate to USD.
   *
   * @param fullName The full name of the currency.
   * @param exchangeRateToUSD The exchange rate to convert to USD.
   */
  CurrencyType(String fullName, double exchangeRateToUSD) {
    this.fullName = fullName;
    this.exchangeRateToUSD = exchangeRateToUSD;
  }

  /**
   * Gets the full name of the currency.
   *
   * @return The full name.
   */
  public String getFullName() {
    return fullName;
  }

  /**
   * Gets the exchange rate to USD.
   *
   * @return The exchange rate.
   */
  public double getExchangeRateToUSD() {
    return exchangeRateToUSD;
  }
}
