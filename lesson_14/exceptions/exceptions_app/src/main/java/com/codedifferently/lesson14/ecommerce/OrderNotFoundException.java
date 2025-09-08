package com.codedifferently.lesson14.ecommerce;

public class OrderNotFoundException extends Exception {
  public OrderNotFoundException(String message) {
    super(message);
  }

  public OrderNotFoundException(String orderid, boolean isOrderid) {
    super("Order with ID " + orderid + " not found");
  }
}
