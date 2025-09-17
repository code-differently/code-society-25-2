package com.codedifferently.lesson17.bank;

/**
 * Provides functionality to convert various currencies to USD.
 * Uses predefined exchange rates for conversion.
 */
public class CurrencyConverter {
  private static double USD = 1.0;
  private static double EUR = 1.18;
  private static double AUD = 0.67;
  private static double GBP = 1.36;
  private static double IDR = 0.011;
  private static double JPY = 0.0068;
  private static double MEX = 0.055;
  private static double CAD = 0.72;

  /**
   * @param amount The monetary amount to convert
   * @param currencyType The source currency type
   * @return The equivalent amount in USD
   */
  public static double convertToUSD(double amount, String currencyType) {
    return switch (currencyType.toUpperCase()) {
      case "USD" -> amount * USD;
      case "EUR" -> amount * EUR;
      case "AUD" -> amount * AUD;
      case "GBP" -> amount * GBP;
      case "IDR" -> amount * IDR;
      case "JPY" -> amount * JPY;
      case "MEX" -> amount * MEX;
      case "CAD" -> amount * CAD;
      default -> throw new IllegalArgumentException("Unsupported currency type: " + currencyType);
    };
  }
}
