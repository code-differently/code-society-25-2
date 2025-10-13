package com.codedifferently.lesson17.bank;

import java.util.Set;

/**
 * Represents a savings account that extends CheckingAccount functionality but does not allow check
 * writing operations.
 *
 * <p>This class follows the Liskov Substitution Principle - it can be used anywhere a
 * CheckingAccount is expected, but restricts check operations.
 */
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

  /**
   * Overrides toString to indicate this is a savings account.
   *
   * @return String representation of the savings account.
   */
  @Override
  public String toString() {
    return "SavingsAccount{"
        + "accountNumber='"
        + getAccountNumber()
        + '\''
        + ", balance="
        + getBalance()
        + ", isActive="
        + !isClosed()
        + '}';
  }
}
