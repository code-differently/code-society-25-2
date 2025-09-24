package com.codedifferently.lesson17.bank;

import java.util.HashMap;
import java.util.Map;

/**
 * Encapsulates currency conversion logic for converting various currencies to USD.
 *
 * <p>This class provides exchange rate conversion functionality to support multi-currency deposits
 * in the banking system. All conversions are to USD as the base currency for account balance
 * management.
 *
 * <p>Exchange rates are simplified for demonstration purposes and represent approximate values. In
 * a production system, these would be sourced from real-time financial data providers.
 *
 * <p>Usage example:
 *
 * <pre>
 * CurrencyConverter converter = new CurrencyConverter();
 * double usdAmount = converter.convertToUsd(100.0, Currency.EUR);
 * </pre>
 *
 * @see Currency
 * @see BankAtm#depositFunds(String, double, Currency)
 * @since 1.0
 */
public class CurrencyConverter {

  private final Map<Currency, Double> exchangeRates;

  /**
   * Creates a new currency converter with predefined exchange rates.
   *
   * <p>Exchange rates are initialized with approximate values for demonstration. USD is the base
   * currency with a rate of 1.0.
   */
  public CurrencyConverter() {
    exchangeRates = new HashMap<>();
    initializeExchangeRates();
  }

  /**
   * Converts an amount from the specified currency to USD.
   *
   * <p>The conversion uses the current exchange rates stored in this converter. If the source
   * currency is USD, the amount is returned unchanged.
   *
   * @param amount The amount to convert (must be positive)
   * @param fromCurrency The source currency to convert from
   * @return The equivalent amount in USD
   * @throws IllegalArgumentException if amount is negative
   * @throws IllegalArgumentException if fromCurrency is null
   * @throws UnsupportedOperationException if the currency is not supported
   */
  public double convertToUsd(double amount, Currency fromCurrency) {
    if (amount < 0) {
      throw new IllegalArgumentException("Amount cannot be negative");
    }
    if (fromCurrency == null) {
      throw new IllegalArgumentException("Currency cannot be null");
    }

    Double rate = exchangeRates.get(fromCurrency);
    if (rate == null) {
      throw new UnsupportedOperationException("Unsupported currency: " + fromCurrency);
    }

    return amount * rate;
  }

  /**
   * Gets the current exchange rate for a currency to USD.
   *
   * @param currency The currency to get the rate for
   * @return The exchange rate to USD, or null if currency is not supported
   */
  public Double getExchangeRate(Currency currency) {
    return exchangeRates.get(currency);
  }

  /**
   * Checks if a currency is supported by this converter.
   *
   * @param currency The currency to check
   * @return true if the currency is supported, false otherwise
   */
  public boolean isCurrencySupported(Currency currency) {
    return exchangeRates.containsKey(currency);
  }

  /**
   * Initializes the exchange rates for supported currencies.
   *
   * <p>These are simplified rates for demonstration purposes. In production, rates would be fetched
   * from financial data providers.
   */
  private void initializeExchangeRates() {
    // Base currency
    exchangeRates.put(Currency.USD, 1.0);

    // Major currencies (approximate rates for demonstration)
    exchangeRates.put(Currency.EUR, 1.08); // 1 EUR = 1.08 USD
    exchangeRates.put(Currency.GBP, 1.27); // 1 GBP = 1.27 USD
    exchangeRates.put(Currency.JPY, 0.0067); // 1 JPY = 0.0067 USD
    exchangeRates.put(Currency.CAD, 0.74); // 1 CAD = 0.74 USD
    exchangeRates.put(Currency.AUD, 0.66); // 1 AUD = 0.66 USD
    exchangeRates.put(Currency.CHF, 1.10); // 1 CHF = 1.10 USD
    exchangeRates.put(Currency.CNY, 0.14); // 1 CNY = 0.14 USD
  }
}
