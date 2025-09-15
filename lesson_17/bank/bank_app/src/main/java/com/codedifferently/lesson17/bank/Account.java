package com.codedifferently.lesson17.bank;

import com.codedifferently.lesson17.bank.exceptions.InsufficientFundsException;
import java.util.Set;

/** Interface representing a bank account. */
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
   */
  void deposit(double amount);

  /**
   * Withdraws funds from the account.
   *
   * @param amount The amount to withdraw.
   * @throws InsufficientFundsException If insufficient funds.
   */
  void withdraw(double amount) throws InsufficientFundsException;

  /**
   * Gets the balance of the account.
   *
   * @return The balance of the account.
   */
  double getBalance();

  /** Closes the account. */
  void closeAccount();

  /**
   * Checks if the account is closed.
   *
   * @return True if the account is closed, otherwise false.
   */
  boolean isClosed();
}
