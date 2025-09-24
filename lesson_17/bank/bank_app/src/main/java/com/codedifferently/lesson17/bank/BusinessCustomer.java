package com.codedifferently.lesson17.bank;

import java.util.UUID;

/** Represents a business customer of the bank. */
public class BusinessCustomer extends Customer {

  /**
   * Creates a new business customer.
   *
   * @param id The ID of the customer.
   * @param name The name of the business.
   */
  public BusinessCustomer(UUID id, String name) {
    super(id, name);
  }

  @Override
  public String toString() {
    return "BusinessCustomer{" + "id=" + getId() + ", name='" + getName() + '\'' + '}';
  }
}
