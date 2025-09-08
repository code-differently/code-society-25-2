package com.codedifferently.lesson14.ecommerce;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class EcommerceSystem {
  private Map<String, Product> products;
  private Map<String, Order> orders;

  public EcommerceSystem() {
    products = new HashMap<>();
    orders = new HashMap<>();
  }

  public void addProduct(String productId, String name) {
    products.put(productId, new Product(productId, name));
  }

  private boolean productExists(String productId) {
    return products.containsKey(productId);
  }

  private boolean orderExists(String orderId) {
    return orders.containsKey(orderId);
  }


  // checks to see if an order or product exists if not throws an exception
  private void validateitem(Exception e, boolean exists) throws Exception {
    if (!exists) {
      throw e;
    }
  }

  public String placeOrder(String productId, int quantity) throws Exception {
    validateitem(
        new ProductNotFoundException("Product with ID " + productId + " not found"),
        productExists(productId));

    Product product = products.get(productId);
    String orderId = UUID.randomUUID().toString();
    orders.put(orderId, new Order(orderId, product, quantity));
    return orderId;
  }

  public void cancelOrder(String orderId) throws Exception {
    validateitem(
        new OrderNotFoundException("Order with ID " + orderId + " not found"),
        orderExists(orderId));
    orders.remove(orderId);
  }

  public String checkOrderStatus(String orderId) throws Exception {
    validateitem(
        new OrderNotFoundException("Order with ID " + orderId + " not found"),
        orderExists(orderId));
    Order order = orders.get(orderId);
    return "Order ID: "
        + orderId
        + ", Product: "
        + order.getProduct().getName()
        + ", Quantity: "
        + order.getQuantity();
  }
}
