package com.codedifferently.lesson17.bank;

import java.util.Set;

/** Represents a Savings Account */
public class SavingsAccount extends Account {

  /**
   * Constructs a new SavingsAccount
   * 
   * @param accountNumber The unique identifier for this savings account
   * @param owners The set of customers who have ownership rights to this account
   * @param initialBalance The starting balance for the account
   */
  public SavingsAccount(String accountNumber, Set<Customer> owners, double initialBalance) {
    super(accountNumber, owners, initialBalance);
  }

  @Override
  public String toString() {
    return "SavingsAccount{"
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
