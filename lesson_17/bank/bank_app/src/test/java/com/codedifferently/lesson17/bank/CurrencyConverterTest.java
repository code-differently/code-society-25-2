package com.codedifferently.lesson17.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CurrencyConverterTest {

  @Test
  void testConvertToUSD_FromUSD() {
    double result = CurrencyConverter.convertToUSD(100.0, Currency.USD);
    assertEquals(100.0, result, 0.001);
  }

  @Test
  void testConvertToUSD_FromEUR() {
    // EUR to USD: amount / 0.85
    double result = CurrencyConverter.convertToUSD(85.0, Currency.EUR);
    assertEquals(100.0, result, 0.001);
  }

  @Test
  void testConvertToUSD_FromGBP() {
    // GBP to USD: amount / 0.75
    double result = CurrencyConverter.convertToUSD(75.0, Currency.GBP);
    assertEquals(100.0, result, 0.001);
  }

  @Test
  void testConvertToUSD_FromCAD() {
    // CAD to USD: amount / 1.25
    double result = CurrencyConverter.convertToUSD(125.0, Currency.CAD);
    assertEquals(100.0, result, 0.001);
  }

  @Test
  void testConvertToUSD_FromJPY() {
    // JPY to USD: amount / 110.0
    double result = CurrencyConverter.convertToUSD(11000.0, Currency.JPY);
    assertEquals(100.0, result, 0.001);
  }

  @Test
  void testConvertToUSD_FromAUD() {
    // AUD to USD: amount / 1.35
    double result = CurrencyConverter.convertToUSD(135.0, Currency.AUD);
    assertEquals(100.0, result, 0.001);
  }
}
