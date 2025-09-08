package com.codedifferently.lesson14.exceptions;

/**
 * Exception thrown when attempting to split payments between multiple methods
 */
public class SplitPaymentNotSupportedException extends Exception {
    public SplitPaymentNotSupportedException() {
        super("Split payments between multiple payment methods are not supported. Please use a single payment method.");
    }
}
