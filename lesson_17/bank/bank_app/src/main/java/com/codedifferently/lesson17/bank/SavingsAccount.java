package com.codedifferently.lesson17.bank;

import java.util.Set;

public class SavingsAccount extends Account {
  /**
   * Creates a new checking account.
   *
   * @param accountNumber The account number.
   * @param owners The owners of the account.
   * @param initialBalance The initial balance of the account.
   */
  public SavingsAccount(String accountNumber, Set<Customer> owners, double initialBalance) {
    this.accountNumber = accountNumber;
    this.owners = owners;
    this.balance = initialBalance;
    this.isActive = true;
  }
}
