package com.codedifferently.lesson17.bank;

import java.util.Set;

public class BusinessCheckingAccount extends CheckingAccount {

  public BusinessCheckingAccount(
      String accountNumber, Set<Customer> owners, double initialBalance) {
    super(accountNumber, owners, initialBalance);

    boolean hasBusinessOwner =
        owners.stream().anyMatch(owner -> owner.getOwnerType() == AccountType.OwnerType.BUSINESS);

    if (!hasBusinessOwner) {
      throw new IllegalArgumentException(
          "BusinessCheckingAccount requires at least one business owner");
    }
  }

  public AccountType.Type getAccountType() {
    return AccountType.Type.BUSINESS_CHECKING;
  }
}
