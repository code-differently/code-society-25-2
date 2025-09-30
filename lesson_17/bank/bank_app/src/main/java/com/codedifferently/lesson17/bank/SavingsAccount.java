package com.codedifferently.lesson17.bank;

import java.util.Set;

public class SavingsAccount extends CheckingAccount {

  /**
   * Creates a new savings account.
   *
   * @param accountNumber The account number.
   * @param owners The owners of the account.
   * @param initialBalance The initial balance of the account.
   */
  public SavingsAccount(String accountNumber, Set<Customer> owners, double initialBalance) {
    super(accountNumber, owners, initialBalance);
  }

  // Override the withdraw method to prevent withdrawals from a savings account
  @Override
  public void withdraw(double amount) throws IllegalStateException {
    throw new IllegalStateException("Cannot withdraw from a savings account");
  }

  // Remove @Override since supertype does not declare this method
  public void deposit(Check check) {
    throw new UnsupportedOperationException("Cannot deposit checks into a savings account");
  }
}
