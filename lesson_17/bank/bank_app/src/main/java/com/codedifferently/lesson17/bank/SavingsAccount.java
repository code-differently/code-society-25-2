package com.codedifferently.lesson17.bank;

import java.util.Set;

public class SavingsAccount extends Account {

  /**
   * Represents a savings account that doesn't allow writing checks. Works like a CheckingAccount
   * otherwise.
   */
  public SavingsAccount(String accountNumber, Set<Customer> owners, double initialBalance) {
    super(accountNumber, owners, initialBalance);
  }

  /** Savings accounts cannot write checks, so always return false. */
  @Override
  public boolean canWriteChecks() {
    return false;
  }
}
