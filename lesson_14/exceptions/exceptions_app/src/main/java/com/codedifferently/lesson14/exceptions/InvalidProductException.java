package com.codedifferently.lesson14.exceptions;

/**
 * Exception thrown when an invalid product is referenced
 */
public class InvalidProductException extends Exception {
    private final String productId;

    public InvalidProductException(String productId) {
        super(String.format("Product with ID '%s' does not exist or is invalid.", productId));
        this.productId = productId;
    }

    public String getProductId() { return productId; }
}
