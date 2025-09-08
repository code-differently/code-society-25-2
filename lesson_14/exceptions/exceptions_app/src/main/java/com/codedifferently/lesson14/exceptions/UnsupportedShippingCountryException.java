package com.codedifferently.lesson14.exceptions;

public class UnsupportedShippingCountryException extends Exception {
  private final String country;
  private final String state;

  public UnsupportedShippingCountryException(String country, String state) {
    super(
        String.format("Shipping to country '%s' and state '%s' is not supported.", country, state));
    this.country = country;
    this.state = state;
  }

  public String getCountry() {
    return country;
  }

  public String getState() {
    return state;
  }
}
