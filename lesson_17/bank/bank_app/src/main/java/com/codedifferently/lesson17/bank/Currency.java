package com.codedifferently.lesson17.bank;

/**
 * Enumeration of supported currencies for banking transactions.
 *
 * <p>This enum defines the various currencies that can be used for deposits and other banking
 * operations. All currency amounts are converted to USD for internal account balance management.
 *
 * @see CurrencyConverter
 * @since 1.0
 */
public enum Currency {
  /** United States Dollar (base currency) */
  USD,

  /** Euro */
  EUR,

  /** British Pound Sterling */
  GBP,

  /** Japanese Yen */
  JPY,

  /** Canadian Dollar */
  CAD,

  /** Australian Dollar */
  AUD,

  /** Swiss Franc */
  CHF,

  /** Chinese Yuan */
  CNY
}
