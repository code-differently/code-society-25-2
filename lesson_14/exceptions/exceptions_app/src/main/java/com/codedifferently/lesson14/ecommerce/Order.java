package com.codedifferently.lesson14.ecommerce;

public class Order {
  private String orderId;
  private Product product;
  private int quantity;
  private boolean isCart;
  private java.util.List<Product> cartItems;
  private String shippingCountry;
  private String shippingState;

  public Order(String orderId, Product product, int quantity) {
    this.orderId = orderId;
    this.product = product;
    this.quantity = quantity;
  }

  public Order(String orderId) {
    this.orderId = orderId;
    this.isCart = true;
    this.cartItems = new java.util.ArrayList<>();
  }

  public String getOrderId() {
    return orderId;
  }

  public Product getProduct() {
    return product;
  }

  public int getQuantity() {
    return quantity;
  }

  public boolean isCart() {
    return isCart;
  }

  public void addToCart(Product product) {
    if (cartItems != null) cartItems.add(product);
  }

  public java.util.List<Product> getCartItems() {
    return cartItems;
  }

  public boolean isCartEmpty() {
    return cartItems == null || cartItems.isEmpty();
  }

  public void setShippingCountry(String country) {
    this.shippingCountry = country;
  }

  public void setShippingState(String state) {
    this.shippingState = state;
  }
}
