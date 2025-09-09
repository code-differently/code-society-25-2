/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.codedifferently.lesson14.ecommerce;

/** Exception thrown when a product with the specified ID cannot be found. */
public class ProductNotFoundException extends Exception {

  /**
   * Constructs a new ProductNotFoundException with the specified detail message.
   *
   * @param message the detail message explaining why the exception was thrown
   */
  public ProductNotFoundException(String message) {
    super(message);
  }
}
