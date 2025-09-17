package com.codedifferently.lesson17.bank;

import java.util.Set;

/**
 * Represents a business checking account that requires at least one business owner.
 * This account type extends the regular checking account but adds validation
 * to ensure that at least one of the account owners is a business entity.
 */
public class BusinessCheckingAccount extends CheckingAccount {
  /**
   * Creates a new business checking account with the specified account number, owners, and initial balance.
   * Validates that at least one owner is a business entity.
   *
   * @param accountNumber The unique identifier for this account
   * @param owners The set of customers who own this account
   * @param initialBalance The starting balance for the account
   * @throws IllegalArgumentException if none of the owners is a business entity
   */
    public BusinessCheckingAccount(
      String accountNumber, Set<Customer> owners, double initialBalance) {
    super(accountNumber, owners, initialBalance);
    validateBusinessOwners(owners);
  }

  /**
   * Validates that at least one of the account owners is a business entity.
   * 
   * @param owners The set of customers to validate
   * @throws IllegalArgumentException if no business owner is found in the set
   */
  private void validateBusinessOwners(Set<Customer> owners) {
    boolean hasBusinessOwner = owners.stream().anyMatch(customer -> customer.isBusiness());

    if (!hasBusinessOwner) {
      throw new IllegalArgumentException(
          "Business checking account must have at least one business owner");
    }
  }
}
