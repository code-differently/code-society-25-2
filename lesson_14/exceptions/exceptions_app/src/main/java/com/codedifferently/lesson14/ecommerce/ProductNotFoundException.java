package com.codedifferently.lesson14.ecommerce; // Change this line

public class ProductNotFoundException extends Exception {
  public ProductNotFoundException(String message) {
    super(message);
  }
}
