package com.codedifferently.lesson14.ecommerce;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class EcommerceSystem {

  // Store products: productId -> productName
  private final Map<String, String> products = new HashMap<>();

  // Store orders: orderId -> Order object
  private final Map<String, Order> orders = new HashMap<>();

  // To generate unique order IDs (1, 2, 3…)
  private final AtomicInteger orderSeq = new AtomicInteger(1);

  // Add a product to the catalog
  public void addProduct(String productId, String productName) {
    products.put(productId, productName);
  }

  // Place an order (if product exists, otherwise throw ProductNotFoundException)
  public String placeOrder(String productId, int quantity) throws ProductNotFoundException {
    String productName = products.get(productId);
    if (productName == null) {
      throw new ProductNotFoundException("Product with ID " + productId + " not found");
    }

    String orderId = String.valueOf(orderSeq.getAndIncrement());
    orders.put(orderId, new Order(orderId, productId, productName, quantity));
    return orderId;
  }

  // Cancel an order (just removes it)
  public void cancelOrder(String orderId) {
    orders.remove(orderId);
  }

  // Check order status (if not found, throw OrderNotFoundException)
  public String checkOrderStatus(String orderId) throws OrderNotFoundException {
    Order order = orders.get(orderId);
    if (order == null) {
      throw new OrderNotFoundException("Order with ID " + orderId + " not found");
    }
    return "Order ID: " + order.id + ", Product: " + order.productName + ", Quantity: " + order.quantity;
  }

  // Inner class for storing order details
  private static class Order {
    final String id;
    final String productId;
    final String productName;
    final int quantity;

    Order(String id, String productId, String productName, int quantity) {
      this.id = id;
      this.productId = productId;
      this.productName = productName;
      this.quantity = quantity;
    }
  }
}
