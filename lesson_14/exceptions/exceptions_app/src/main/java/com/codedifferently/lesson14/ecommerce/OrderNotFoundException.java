/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.codedifferently.lesson14.ecommerce;

/** Exception thrown when an order with the specified ID cannot be found. */
public class OrderNotFoundException extends Exception {

  /**
   * Constructs a new OrderNotFoundException with the specified detail message.
   *
   * @param message the detail message explaining why the exception was thrown
   */
  public OrderNotFoundException(String message) {
    super(message);
  }

  /**
   * Constructs a new OrderNotFoundException with a default message for the given order ID.
   *
   * @param orderId the ID of the order that was not found
   */
  public OrderNotFoundException(String orderId, boolean isOrderId) {
    super("Order with ID " + orderId + " not found");
  }
}
