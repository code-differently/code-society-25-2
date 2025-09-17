package com.codedifferently.lesson17.bank;

import java.util.Set;

/**
 * Represents a savings account that works like a checking account but doesn't support check
 * writing. Savings accounts provide basic deposit and withdrawal functionality without check
 * processing capabilities.
 */
public class SavingsAccount extends BaseAccount {

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
}
