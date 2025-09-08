package com.codedifferently.lesson14.exceptions;

/**
 * Exception thrown when an unsupported payment method is used
 */
public class UnsupportedPaymentMethodException extends Exception {
    private final String paymentMethod;

    public UnsupportedPaymentMethodException(String paymentMethod) {
        super(String.format("Payment method '%s' is not supported. Supported methods: CREDIT_CARD, DEBIT_CARD, CASH ", paymentMethod));
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentMethod() { return paymentMethod; }
}
