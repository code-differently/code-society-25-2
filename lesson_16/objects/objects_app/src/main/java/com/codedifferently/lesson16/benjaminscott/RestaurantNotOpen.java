package com.codedifferently.lesson16.benjaminscott;

public class RestaurantNotOpen extends RuntimeException {
  public RestaurantNotOpen(String message) {
    super(message);
  }
}
