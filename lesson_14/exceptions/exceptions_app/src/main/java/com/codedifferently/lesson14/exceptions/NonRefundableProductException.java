package com.codedifferently.lesson14.exceptions;

/** Exception thrown when attempting to refund non-refundable products */
public class NonRefundableProductException extends Exception {
  private final String productName;

  public NonRefundableProductException(String productName) {
    super(String.format("Product '%s' is non-refundable and cannot be returned.", productName));
    this.productName = productName;
  }

  public String getProductName() {
    return productName;
  }
}
