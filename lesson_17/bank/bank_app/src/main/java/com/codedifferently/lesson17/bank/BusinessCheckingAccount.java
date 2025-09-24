package com.codedifferently.lesson17.bank;

import java.util.Set;

/** Represents a business checking account. Requires at least one owner to be a BusinessCustomer. */
public class BusinessCheckingAccount extends CheckingAccount {

  public BusinessCheckingAccount(
      String accountNumber, Set<Customer> owners, double initialBalance) {
    super(accountNumber, owners, initialBalance);

    boolean hasBusinessOwner = owners.stream().anyMatch(o -> o instanceof BusinessCustomer);
    if (!hasBusinessOwner) {
      throw new IllegalArgumentException(
          "BusinessCheckingAccount requires at least one BusinessCustomer as an owner");
    }
  }
}
