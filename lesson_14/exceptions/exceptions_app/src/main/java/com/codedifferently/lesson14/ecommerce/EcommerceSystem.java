package com.codedifferently.lesson14.ecommerce;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import main.java.com.codedifferently.lesson14.ecommerce.CancelOrderException;

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

  public String placeOrder(String productId, int quantity) throws OrderNotFoundException {
    Product product = products.get(productId);
    {
      if (product == null) {
        throw new OrderNotFoundException("Product with ID " + productId + " not found");
      }
      String orderId = UUID.randomUUID().toString();
      orders.put(orderId, new Order(orderId, product, quantity));
      return orderId;
    }
  }

  public void cancelOrder(String orderId) throws CancelOrderException {
    Order order = orders.remove(orderId);
    {
      if (orderId == null) {
        throw new CancelOrderException("Order ID" + orderId + "canceled");
      }
    }
  }

  public String checkOrderStatus(String orderId) throws OrderNotFoundException {
    Order order = orders.get(orderId);
    {
      if (orderId == null) {
        throw new OrderNotFoundException("Order ID" + orderId + " not found");
      }
    }
    return "Order ID: "
        + orderId
        + ", Product: "
        + order.getProduct().getName()
        + ", Quantity: "
        + order.getQuantity();
  }
}
