package com.codedifferently.lesson17.bank;

import java.util.HashMap;
import java.util.Map;

/**
 * CurrencyConverter converts amounts from various currencies to USD.
 */
public class CurrencyConverter {
    private final Map<String, Double> rates = new HashMap<>();

    public CurrencyConverter() {
        // Example rates; in real life, these would be dynamic
        rates.put("USD", 1.0);
        rates.put("EUR", 1.1);
        rates.put("JPY", 0.007);
        rates.put("GBP", 1.3);
    }

    public double convertToUSD(double amount, String currency) {
        Double rate = rates.get(currency);
        if (rate == null) {
            throw new IllegalArgumentException("Unsupported currency: " + currency);
        }
        return amount * rate;
    }
}
