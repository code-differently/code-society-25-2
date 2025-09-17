package com.codedifferently.lesson17.bank;

import java.util.Set;

/**
 * Represents a business checking account that requires at least one business owner. This account
 * type extends CheckingAccount but adds business-specific validation.
 */
public class BusinessCheckingAccount extends CheckingAccount {

  /**
   * Creates a new business checking account.
   *
   * @param accountNumber The account number.
   * @param owners The owners of the account.
   * @param initialBalance The initial balance of the account.
   * @throws IllegalArgumentException if no business owners are found.
   */
  public BusinessCheckingAccount(
      String accountNumber, Set<Customer> owners, double initialBalance) {
    super(accountNumber, owners, initialBalance);
    validateBusinessOwnership(owners);
  }

  /**
   * Validates that at least one owner is a business customer.
   *
   * @param owners The set of owners to validate.
   * @throws IllegalArgumentException if no business customers are found among the owners.
   */
  private void validateBusinessOwnership(Set<Customer> owners) {
    boolean hasBusiness = owners.stream().anyMatch(owner -> owner instanceof BusinessCustomer);
    if (!hasBusiness) {
      throw new IllegalArgumentException(
          "Business checking account requires at least one business customer as owner");
    }
  }

  /**
   * Checks if this account has business ownership.
   *
   * @return True if at least one owner is a business customer.
   */
  public boolean hasBusinessOwnership() {
    return getOwners().stream().anyMatch(owner -> owner instanceof BusinessCustomer);
  }
}
