package com.codedifferently.lesson17.bank;

/** Enumeration of supported currencies. */
public enum Currency {
  USD(1.0),
  EUR(0.85),
  GBP(0.75),
  CAD(1.25),
  JPY(110.0),
  AUD(1.35);

  private final double conversionRateToUSD;

  Currency(double conversionRateToUSD) {
    this.conversionRateToUSD = conversionRateToUSD;
  }

  public double getConversionRateToUSD() {
    return conversionRateToUSD;
  }
}
