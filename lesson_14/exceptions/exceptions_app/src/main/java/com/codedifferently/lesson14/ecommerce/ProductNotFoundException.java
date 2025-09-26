package com.codedifferently.lesson14.ecommerce;

public class ProductNotFoundException extends Exception {
  public ProductNotFoundException(String message) {
    super(message);
  }

  public ProductNotFoundException(String productid, boolean isproductid) {
    super("Product with ID " + productid + " not found");
  }
}
