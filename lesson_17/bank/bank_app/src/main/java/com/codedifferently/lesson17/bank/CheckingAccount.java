package com.codedifferently.lesson17.bank;

import java.util.Set;

/**
 * Represents a checking account that supports check writing and all standard banking operations.
 */
public class CheckingAccount extends BaseAccount {

  /**
   * Creates a new checking account.
   *
   * @param accountNumber The account number.
   * @param owners The owners of the account.
   * @param initialBalance The initial balance of the account.
   */
  public CheckingAccount(String accountNumber, Set<Customer> owners, double initialBalance) {
    super(accountNumber, owners, initialBalance);
  }
}
