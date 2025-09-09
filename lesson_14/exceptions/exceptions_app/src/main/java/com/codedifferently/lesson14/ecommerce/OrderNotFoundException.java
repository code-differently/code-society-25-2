package com.codedifferently.lesson14.ecommerce;

public class OrderNotFoundException extends Exception {
  public OrderNotFoundException(String message) {
    super(message);
  }
}
