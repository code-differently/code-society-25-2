package com.codedifferently.lesson17.bank;

import java.util.Set;

public class BusinessCheckingAccount extends CheckingAccount {
  private Customer business;

  public BusinessCheckingAccount(
      Customer business, String accountNumber, Set<Customer> owners, double initialBalance) {
    super(accountNumber, owners, initialBalance);
    this.business = business;
    owners.add(business);
    business.addAccount(this);
  }

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
