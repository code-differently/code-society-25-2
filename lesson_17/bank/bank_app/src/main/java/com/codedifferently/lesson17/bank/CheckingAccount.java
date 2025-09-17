package com.codedifferently.lesson17.bank;

import java.util.Set;

/**
 * Represents a checking account, which is a type of Account. Unlike SavingsAccount, CheckingAccount
 * allows writing checks.
 */
public class CheckingAccount extends Account {

  /**
   * Creates a new checking account with given account number, owners, and initial balance.
   *
   * @param accountNumber the unique account number
   * @param owners set of Customer objects who own the account
   * @param initialBalance starting balance
   */
  public CheckingAccount(String accountNumber, Set<Customer> owners, double initialBalance) {
    super(accountNumber, owners, initialBalance);
  }

  /**
   * Indicates whether this account can write checks. For checking accounts, this always returns
   * true.
   *
   * @return true because checks are allowed
   */
  @Override
  public boolean canWriteChecks() {
    return true;
  }
}
