package com.codedifferently.lesson17.bank;

import java.util.UUID;

/** Represents a business customer of the bank. */
public class BusinessCustomer extends Customer {

  private final String businessName;
  private final String taxId;

  /**
   * Creates a new business customer.
   *
   * @param id The ID of the business customer.
   * @param businessName The name of the business.
   * @param taxId The tax identification number of the business.
   */
  public BusinessCustomer(UUID id, String businessName, String taxId) {
    super(id, businessName);
    this.businessName = businessName;
    this.taxId = taxId;
  }

  /**
   * Gets the business name.
   *
   * @return The business name.
   */
  public String getBusinessName() {
    return businessName;
  }

  /**
   * Gets the tax identification number.
   *
   * @return The tax identification number.
   */
  public String getTaxId() {
    return taxId;
  }

  /**
   * Determines if this customer is a business.
   *
   * @return True, since this is a business customer.
   */
  public boolean isBusiness() {
    return true;
  }

  @Override
  public String toString() {
    return "BusinessCustomer{"
        + "id=" + getId()
        + ", businessName='" + businessName + '\''
        + ", taxId='" + taxId + '\''
        + '}';
  }
}
