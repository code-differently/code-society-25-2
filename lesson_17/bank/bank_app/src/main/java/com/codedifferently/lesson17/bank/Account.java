package com.codedifferently.lesson17.bank;

import com.codedifferently.lesson17.bank.exceptions.InsufficientFundsException;
import java.util.Set;

/** Represents a bank account interface that defines common account operations. */
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
   * @return The owners of the account.
   */
  Set<Customer> getOwners();

  /**
   * Deposits funds into the account.
   *
   * @param amount The amount to deposit.
   * @throws IllegalStateException If the account is closed.
   * @throws IllegalArgumentException If the amount is not positive.
   */
  void deposit(double amount) throws IllegalStateException, IllegalArgumentException;

  /**
   * Withdraws funds from the account.
   *
   * @param amount The amount to withdraw.
   * @throws InsufficientFundsException When there are not enough funds.
   * @throws IllegalStateException If the account is closed or amount is not positive.
   */
  void withdraw(double amount) throws InsufficientFundsException, IllegalStateException;

  /**
   * Gets the balance of the account.
   *
   * @return The balance of the account.
   */
  double getBalance();

  /**
   * Closes the account.
   *
   * @throws IllegalStateException If the account cannot be closed.
   */
  void closeAccount() throws IllegalStateException;

  /**
   * Checks if the account is closed.
   *
   * @return True if the account is closed, otherwise false.
   */
  boolean isClosed();
}
