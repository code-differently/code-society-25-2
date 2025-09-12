/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.codedifferently.lesson14.ecommerce;

public class ProductNotFoundException extends Exception {
  public ProductNotFoundException(String productId) {
    super("Product with ID " + productId + " not found");
  }

  public ProductNotFoundException(String productId, String operation) {
    super("Operation '" + operation + "' is not supported for product with ID " + productId);
  }
}
