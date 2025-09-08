package com.codedifferently.lesson14.ecommerce;

public class Product {
  private String productId;
  private String name;
  private int stock;
  private String category;
  private boolean refundable;
  private double price;

  public Product(String productId, String name) {
    this.productId = productId;
    this.name = name;
    this.stock = 0;
    this.category = "GENERAL";
    this.refundable = true;
    this.price = 0.0;
  }

  public Product(
      String productId, String name, int stock, String category, boolean refundable, double price) {
    this.productId = productId;
    this.name = name;
    this.stock = stock;
    this.category = category;
    this.refundable = refundable;
    this.price = price;
  }

  public String getProductId() {
    return productId;
  }

  public String getName() {
    return name;
  }

  public int getStock() {
    return stock;
  }

  public void setStock(int stock) {
    this.stock = stock;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public boolean isRefundable() {
    return refundable;
  }

  public void setRefundable(boolean refundable) {
    this.refundable = refundable;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public void reduceStock(int quantity) {
    this.stock = Math.max(0, this.stock - quantity);
  }

  public void increaseStock(int quantity) {
    this.stock += quantity;
  }
}
