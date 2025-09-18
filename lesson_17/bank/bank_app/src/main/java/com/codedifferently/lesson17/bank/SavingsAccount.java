package com.codedifferently.lesson17.bank;

import java.util.Set;

/** SavingsAccount works like CheckingAccount, except you cannot write checks against it. */
public class SavingsAccount extends CheckingAccount {

  public SavingsAccount(String accountNumber, Set<Customer> owners, double openingBalance) {
    super(accountNumber, owners, openingBalance);
  }

  // We do NOT implement AllowsChecks here,
  // so this account type cannot be used to write checks.
}
