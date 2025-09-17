package com.codedifferently.lesson17.bank;

import java.util.UUID;

/** Represents a business customer of the bank. */
public class BusinessCustomer extends Customer {

  private final String businessRegistrationNumber;

  /**
   * Creates a new business customer.
   *
   * @param id The ID of the customer.
   * @param name The name of the business.
   * @param businessRegistrationNumber The business registration number.
   */
  public BusinessCustomer(UUID id, String name, String businessRegistrationNumber) {
    super(id, name);
    this.businessRegistrationNumber = businessRegistrationNumber;
  }

  public String getBusinessRegistrationNumber() {
    return businessRegistrationNumber;
  }

  public boolean isBusiness() {
    return true;
  }

  @Override
  public String toString() {
    return "BusinessCustomer{"
        + "id=" + getId()
        + ", name='" + getName() + '\''
        + ", businessRegistrationNumber='" + businessRegistrationNumber + '\''
        + '}';
  }
}
