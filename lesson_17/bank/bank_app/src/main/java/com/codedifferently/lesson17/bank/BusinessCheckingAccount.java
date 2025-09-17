package com.codedifferently.lesson17.bank;

import java.util.Set;

public class BusinessCheckingAccount extends CheckingAccount {
  public BusinessCheckingAccount(
      String accountNumber, Set<Customer> owners, double initialBalance) {
    super(accountNumber, owners, initialBalance);
    boolean hasBusiness = owners.stream().anyMatch(Customer::isBusiness);
    if (!hasBusiness) {
      throw new IllegalArgumentException(
          "BusinessCheckingAccount requires at least one business owner");
    }
  }
}
