package com.codedifferently.lesson17.bank;

import java.util.Set;

/**
 * Represents a BusinessChecking Account. A specialized type of checking account designed for
 * business customers. It automatically includes the business as an owner and establishes the
 * relationship between the business and the account.
 */
public class BusinessCheckingAccount extends CheckingAccount {
  private Customer business;

  /**
   * Constructs a new BusinessCheckingAccount with the specified parameters. Adds the business
   * customer to the owners.
   *
   * @param business The primary business customer who owns this account
   * @param accountNumber
   * @param owners The set of customers who have ownership rights to this account
   * @param initialBalance The starting balance for the account
   */
  public BusinessCheckingAccount(
      Customer business, String accountNumber, Set<Customer> owners, double initialBalance) {
    super(accountNumber, owners, initialBalance);
    this.business = business;
    owners.add(business);
    business.addAccount(this);
  }

  /**
   * Returns a string representation of this business checking account.
   *
   * @return A formatted string containing the account details including business owner information
   */
  @Override
  public String toString() {
    return "BusinessCheckingAccount{"
        + "Business Owner: "
        + business.getName()
        + "accountNumber='"
        + accountNumber
        + '\''
        + ", balance="
        + balance
        + ", isActive="
        + isActive
        + '}';
  }
}
