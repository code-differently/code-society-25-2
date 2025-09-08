package com.codedifferently.lesson14.ecommerce;

import java.util.HashMap;
import java.util.Map;

public class EcommerceSystem {
  private final Map<String, String> products = new HashMap<>();
  private final Map<String, Order> orders = new HashMap<>();

  private static class Order {
    final String productId;
    final int quantity;

    Order(String productId, int quantity) {
      this.productId = productId;
      this.quantity = quantity;
    }
  }

  public void addProduct(String id, String name) {
    products.put(id, name);
  }

  public String placeOrder(String productId, int quantity) throws ProductNotFoundException {
    if (!products.containsKey(productId)) {
      throw new ProductNotFoundException("Product with ID " + productId + " not found");
    }
    String orderId = productId;
    orders.put(orderId, new Order(productId, quantity));
    return orderId;
  }

  public void cancelOrder(String orderId) {
    orders.remove(orderId);
  }

  public String checkOrderStatus(String orderId) throws OrderNotFoundException {
    Order order = orders.get(orderId);
    if (order == null) {
      throw new OrderNotFoundException("Order with ID " + orderId + " not found");
    }
    String productName = products.get(order.productId);
    return "Order ID: " + orderId + ", Product: " + productName + ", Quantity: " + order.quantity;
  }
}
