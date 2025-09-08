package com.codedifferently.lesson14.exceptions;

/**
 * Exception thrown when a product is out of stock
 */
public class OutOfStockException extends Exception {
    private final String productName;
    private final int requestedQuantity;
    private final int availableQuantity;

    public OutOfStockException(String productName, int requestedQuantity, int availableQuantity) {
        super(String.format("Product '%s' is out of stock. Requested: %d, Available: %d", 
              productName, requestedQuantity, availableQuantity));
        this.productName = productName;
        this.requestedQuantity = requestedQuantity;
        this.availableQuantity = availableQuantity;
    }

    public String getProductName() { return productName; }
    public int getRequestedQuantity() { return requestedQuantity; }
    public int getAvailableQuantity() { return availableQuantity; }
}
