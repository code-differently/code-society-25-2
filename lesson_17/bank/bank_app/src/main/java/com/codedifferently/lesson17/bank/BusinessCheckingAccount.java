package com.codedifferently.lesson17.bank;

import java.util.Set;

/**
 * Represents a business checking account that requires at least one business owner.
 */
public class BusinessCheckingAccount extends CheckingAccount {

  /**
   * Creates a new business checking account.
   *
   * @param accountNumber The account number.
   * @param owners The owners of the account.
   * @param initialBalance The initial balance of the account.
   * @throws IllegalArgumentException If no business owners are provided.
   */
  public BusinessCheckingAccount(
      String accountNumber, Set<Customer> owners, double initialBalance) {
    super(accountNumber, owners, initialBalance);
    validateBusinessOwnership(owners);
  }

  /**
   * Validates that at least one owner is a business.
   *
   * @param owners The owners to validate.
   * @throws IllegalArgumentException If no business owners are found.
   */
  private void validateBusinessOwnership(Set<Customer> owners) {
    boolean hasBusinessOwner = owners.stream().anyMatch(Customer::isBusiness);
    if (!hasBusinessOwner) {
      throw new IllegalArgumentException(
          "Business checking account requires at least one business owner");
    }
  }

  @Override
  public String toString() {
    return "BusinessCheckingAccount{"
        + "accountNumber='"
        + getAccountNumber()
        + '\''
        + ", balance="
        + getBalance()
        + ", isActive="
        + !isClosed()
        + '}';
  }
}
