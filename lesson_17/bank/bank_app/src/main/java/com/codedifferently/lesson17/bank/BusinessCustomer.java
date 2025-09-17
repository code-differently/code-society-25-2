package com.codedifferently.lesson17.bank;

import java.util.UUID;

/**
 * Represents a business customer of the bank. Business customers have additional properties and can
 * be owners of business checking accounts.
 */
public class BusinessCustomer extends Customer {

  private final String businessLicense;
  private final String taxId;

  /**
   * Creates a new business customer.
   *
   * @param id The ID of the customer.
   * @param name The name of the business.
   * @param businessLicense The business license number.
   * @param taxId The tax identification number.
   */
  public BusinessCustomer(UUID id, String name, String businessLicense, String taxId) {
    super(id, name);
    this.businessLicense = businessLicense;
    this.taxId = taxId;
  }

  /**
   * Gets the business license number.
   *
   * @return The business license number.
   */
  public String getBusinessLicense() {
    return businessLicense;
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
   * Checks if this customer is a business.
   *
   * @return Always true for BusinessCustomer.
   */
  public boolean isBusiness() {
    return true;
  }

  @Override
  public String toString() {
    return "BusinessCustomer{"
        + "id="
        + getId()
        + ", name='"
        + getName()
        + '\''
        + ", businessLicense='"
        + businessLicense
        + '\''
        + ", taxId='"
        + taxId
        + '\''
        + '}';
  }
}
