package com.codedifferently.lesson17.bank;

import com.codedifferently.lesson17.bank.exceptions.InsufficientFundsException;
import java.util.Set;

/**
 * Common interface for all account types. Provides a contract for basic account operations
 * following the Interface Segregation Principle.
 */
public interface Account {

  /**
   * Gets the account number.
   *
   * @return The account number.
   */
  String getAccountNumber();

  /**
   * Gets the owners of the account.
   *
   * @return The set of customers who own the account.
   */
  Set<Customer> getOwners();

  /**
   * Gets the current balance of the account.
   *
   * @return The current balance.
   */
  double getBalance();

  /**
   * Deposits funds into the account.
   *
   * @param amount The amount to deposit.
   * @throws IllegalArgumentException if amount is negative or zero.
   * @throws IllegalStateException if account is closed.
   */
  void deposit(double amount);

  /**
   * Withdraws funds from the account.
   *
   * @param amount The amount to withdraw.
   * @throws InsufficientFundsException if insufficient funds.
   * @throws IllegalArgumentException if amount is negative or zero.
   * @throws IllegalStateException if account is closed.
   */
  void withdraw(double amount) throws InsufficientFundsException;

  /**
   * Checks if the account is closed.
   *
   * @return True if the account is closed, false otherwise.
   */
  boolean isClosed();

  /**
   * Closes the account.
   *
   * @throws IllegalStateException if account cannot be closed.
   */
  void closeAccount();
}
