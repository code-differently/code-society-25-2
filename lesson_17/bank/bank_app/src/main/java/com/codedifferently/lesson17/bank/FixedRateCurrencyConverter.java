package com.codedifferently.lesson17.bank;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

/** Fixed-rate currency converter for supported currencies to USD. */
public class FixedRateCurrencyConverter implements CurrencyConverter {
  private static final Map<Currency, Double> RATES =
      Map.of(
          Currency.USD, 1.0,
          Currency.EUR, 1.08,
          Currency.GBP, 1.25);

  @Override
  public double convertToUsd(double amount, Currency currency) {
    if (currency == null) {
      throw new UnsupportedOperationException("Unsupported currency: null");
    }
    Double rate = RATES.get(currency);
    if (rate == null) {
      throw new UnsupportedOperationException("Unsupported currency: " + currency);
    }
    double usd = amount * rate;
    return BigDecimal.valueOf(usd).setScale(2, RoundingMode.HALF_UP).doubleValue();
  }
}
