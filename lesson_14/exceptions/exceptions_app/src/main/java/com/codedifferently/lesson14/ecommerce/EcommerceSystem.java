package com.codedifferently.lesson14.ecommerce;

import com.codedifferently.lesson14.exceptions.EmptyCartException;
import com.codedifferently.lesson14.exceptions.InvalidCategoryException;
import com.codedifferently.lesson14.exceptions.InvalidProductException;
import com.codedifferently.lesson14.exceptions.NonRefundableProductException;
import com.codedifferently.lesson14.exceptions.OutOfStockException;
import com.codedifferently.lesson14.exceptions.PartialRefundNotAllowedException;
import com.codedifferently.lesson14.exceptions.SplitPaymentNotSupportedException;
import com.codedifferently.lesson14.exceptions.UnsupportedPaymentMethodException;
import com.codedifferently.lesson14.exceptions.UnsupportedShippingCountryException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class EcommerceSystem {
  private Map<String, Product> products;
  private Map<String, Order> orders;
  private Set<String> validCategories;
  private Set<String> validUSStates;

  public EcommerceSystem() {
    products = new HashMap<>();
    orders = new HashMap<>();
    validCategories =
        Set.of("ELECTRONICS", "CLOTHING", "BOOKS", "HOME", "SPORTS", "DIGITAL", "GENERAL");
    validUSStates =
        Set.of(
            "AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN",
            "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV",
            "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN",
            "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY");
  }

  public void addProduct(String productId, String name) {
    products.put(productId, new Product(productId, name));
  }

  public void addProduct(
      String productId, String name, int stock, String category, boolean refundable, double price)
      throws InvalidCategoryException {
    if (!validCategories.contains(category.toUpperCase())) {
      throw new InvalidCategoryException(category);
    }
    products.put(
        productId, new Product(productId, name, stock, category.toUpperCase(), refundable, price));
  }

  public String placeOrder(String productId, int quantity)
      throws InvalidProductException, OutOfStockException {
    Product product = products.get(productId);

    // Check if product exists
    if (product == null) {
      throw new InvalidProductException(productId);
    }

    // Check if sufficient stock
    if (product.getStock() < quantity) {
      throw new OutOfStockException(product.getName(), quantity, product.getStock());
    }

    // Reduce stock and create order
    product.reduceStock(quantity);
    String orderId = UUID.randomUUID().toString();
    orders.put(orderId, new Order(orderId, product, quantity));
    return orderId;
  }

  public String createCartOrder() {
    String orderId = UUID.randomUUID().toString();
    orders.put(orderId, new Order(orderId));
    return orderId;
  }

  public void addToCart(String orderId, String productId)
      throws InvalidProductException, OutOfStockException {
    Order order = orders.get(orderId);
    Product product = products.get(productId);

    // Check if product exists
    if (product == null) {
      throw new InvalidProductException(productId);
    }

    // Check if sufficient stock
    if (product.getStock() < 1) {
      throw new OutOfStockException(product.getName(), 1, product.getStock());
    }

    if (order != null && order.isCart()) {
      order.addToCart(product);
      product.reduceStock(1);
    }
  }

  public void checkout(String orderId) throws EmptyCartException {
    Order order = orders.get(orderId);

    if (order != null && order.isCart() && order.isCartEmpty()) {
      throw new EmptyCartException();
    }
  }

  public void setShippingAddress(String orderId, String country, String state)
      throws UnsupportedShippingCountryException {
    Order order = orders.get(orderId);

    if (!isValidShippingAddress(country, state)) {
      throw new UnsupportedShippingCountryException(country, state);
    }

    if (order != null) {
      order.setShippingCountry(country);
      order.setShippingState(state);
    }
  }

  private boolean isValidShippingAddress(String country, String state) {
    if (!"US".equalsIgnoreCase(country) && !"USA".equalsIgnoreCase(country)) {
      return false;
    }
    return validUSStates.contains(state.toUpperCase());
  }

  public void refundOrder(String orderId)
      throws NonRefundableProductException, InvalidProductException {
    Order order = orders.get(orderId);

    if (order == null) {
      throw new InvalidProductException("Order not found: " + orderId);
    }

    // Check if single product order
    if (!order.isCart() && order.getProduct() != null) {
      if (!order.getProduct().isRefundable()) {
        throw new NonRefundableProductException(order.getProduct().getName());
      }
      // Restore stock
      order.getProduct().increaseStock(order.getQuantity());
    } else if (order.isCart()) {
      // Check all cart items for refundability
      for (Product product : order.getCartItems()) {
        if (!product.isRefundable()) {
          throw new NonRefundableProductException(product.getName());
        }
      }
      // Restore stock for all items
      for (Product product : order.getCartItems()) {
        product.increaseStock(1);
      }
    }

    // Remove the order
    orders.remove(orderId);
  }

  public void cancelOrder(String orderId) {
    Order order = orders.get(orderId);
    if (order != null) {
      // Restore stock when canceling
      if (!order.isCart() && order.getProduct() != null) {
        order.getProduct().increaseStock(order.getQuantity());
      } else if (order.isCart()) {
        for (Product product : order.getCartItems()) {
          product.increaseStock(1);
        }
      }
    }
    orders.remove(orderId);
  }

  public String checkOrderStatus(String orderId) throws InvalidProductException {
    Order order = orders.get(orderId);

    if (order == null) {
      throw new InvalidProductException("Order not found: " + orderId);
    }

    if (order.isCart()) {
      return "Order ID: " + orderId + ", Cart Items: " + order.getCartItems().size();
    } else {
      return "Order ID: "
          + orderId
          + ", Product: "
          + order.getProduct().getName()
          + ", Quantity: "
          + order.getQuantity();
    }
  }

  // Simulate unsupported payment method
  public void payWithMethod(String paymentMethod) throws UnsupportedPaymentMethodException {
    if (!Set.of("CREDIT_CARD", "DEBIT_CARD", "CASH").contains(paymentMethod.toUpperCase())) {
      throw new UnsupportedPaymentMethodException(paymentMethod);
    }
  }

  // Simulate split payment not supported
  public void splitPayment(String... paymentMethods) throws SplitPaymentNotSupportedException {
    if (paymentMethods.length > 1) {
      throw new SplitPaymentNotSupportedException();
    }
  }

  // Simulate partial refund not allowed
  public void refundPartialOrder(List<String> missingItems)
      throws PartialRefundNotAllowedException {
    if (missingItems != null && !missingItems.isEmpty()) {
      throw new PartialRefundNotAllowedException(missingItems);
    }
  }

  // Helper methods for testing
  public Product getProduct(String productId) {
    return products.get(productId);
  }

  public Order getOrder(String orderId) {
    return orders.get(orderId);
  }

  public Map<String, Product> getAllProducts() {
    return new HashMap<>(products);
  }

  public Map<String, Order> getAllOrders() {
    return new HashMap<>(orders);
  }
}
