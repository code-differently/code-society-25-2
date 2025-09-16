package com.codedifferently.lesson14;

import java.util.HashMap;
import java.util.Map;

public class Lesson14 {
  private Map<String, String> product = new HashMap<>();
  private Map<String, String> orders = new HashMap<>();

  public static void main(String[] args) {
    System.out.println("Hello World");
  }

  public String placeOrder(String productId, int quantity) throws ProductNotFoundException {
    if (!product.containsKey(productId)) {
      throw new ProductNotFoundException("Product with ID " + productId + " not found");
    }
    return productId;
  }

  public String checkOrderStatus(String orderId) throws OrderNotFoundException {
    if (!orders.containsKey(orderId)) {
      throw new OrderNotFoundException("Order with ID " + orderId + " not found");
    }
    return orderId;
  }
}
