package com.codedifferently.lesson17.bank;

import com.codedifferently.lesson17.bank.exceptions.InsufficientFundsException;
import java.util.Set;

/**
 * Abstract representation of a bank account, encapsulating common properties and behaviors. This
 * class serves as a base for specific account types like CheckingAccount and SavingsAccount. It
 * manages account ownership, balance, activation status, and provides methods for deposits,
 * withdrawals, and account closure.
 */
public abstract class Account {

  protected Set<Customer> owners;
  protected String accountNumber;
  protected double balance;
  protected boolean isActive;

  /**
   * Gets the account number.
   *
   * @return The account number.
   */
  public String getAccountNumber() {
    return accountNumber;
  }

  /**
   * Gets the owners of the account.
   *
   * @return The owners of the account.
   */
  public Set<Customer> getOwners() {
    return owners;
  }

  /**
   * Deposits funds into the account.
   *
   * @param amount The amount to deposit.
   */
  public void deposit(double amount) throws IllegalStateException {
    if (isClosed()) {
      throw new IllegalStateException("Cannot deposit to a closed account");
    }
    if (amount <= 0) {
      throw new IllegalArgumentException("Deposit amount must be positive");
    }
    balance += amount;
  }

  /**
   * Withdraws funds from the account.
   *
   * @param amount
   * @throws InsufficientFundsException
   */
  public void withdraw(double amount) throws InsufficientFundsException {
    if (isClosed()) {
      throw new IllegalStateException("Cannot withdraw from a closed account");
    }
    if (amount <= 0) {
      throw new IllegalStateException("Withdrawal amount must be positive");
    }
    if (balance < amount) {
      throw new InsufficientFundsException("Account does not have enough funds for withdrawal");
    }
    balance -= amount;
  }

  /**
   * Gets the balance of the account.
   *
   * @return The balance of the account.
   */
  public double getBalance() {
    return balance;
  }

  /** Closes the account. */
  public void closeAccount() throws IllegalStateException {
    if (balance > 0) {
      throw new IllegalStateException("Cannot close account with a positive balance");
    }
    isActive = false;
  }

  /**
   * Checks if the account is closed.
   *
   * @return True if the account is closed, otherwise false.
   */
  public boolean isClosed() {
    return !isActive;
  }

  @Override
  public int hashCode() {
    return accountNumber.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof CheckingAccount other) {
      return accountNumber.equals(other.accountNumber);
    }
    return false;
  }

  @Override
  public String toString() {
    return "CheckingAccount{"
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
