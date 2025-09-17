package com.codedifferently.lesson17.bank;

import com.codedifferently.lesson17.bank.exceptions.InsufficientFundsException;
import java.util.Set;

/** Abstract base class for all types of accounts (Checking, Savings, etc.). */
public abstract class Account {
  private final String accountNumber;
  private final Set<Customer> owners;
  private double balance;
  private boolean isActive;

  /**
   * Creates a new account with initial balance and owners.
   *
   * @param accountNumber unique account identifier
   * @param owners set of customers owning this account
   * @param initialBalance starting balance
   */
  public Account(String accountNumber, Set<Customer> owners, double initialBalance) {
    this.accountNumber = accountNumber;
    this.owners = owners;
    this.balance = initialBalance;
    this.isActive = true;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public Set<Customer> getOwners() {
    return owners;
  }

  public double getBalance() {
    return balance;
  }

  public boolean isClosed() {
    return !isActive;
  }

  /**
   * Deposits a positive amount into the account.
   *
   * @param amount amount to deposit
   */
  public void deposit(double amount) {
    if (amount < 0) throw new IllegalArgumentException("Deposit must be positive");
    balance += amount;
  }

  /**
   * Withdraws a positive amount from the account, ensuring sufficient funds.
   *
   * @param amount amount to withdraw
   */
  public void withdraw(double amount) {
    if (amount < 0) throw new IllegalStateException("Withdrawal amount must be positive");
    if (amount > balance)
      throw new InsufficientFundsException("Account does not have enough funds for withdrawal");
    balance -= amount;
  }

  /** Closes the account if the balance is zero. */
  public void closeAccount() {
    if (balance != 0) throw new IllegalStateException("Cannot close account with non-zero balance");
    isActive = false;
  }

  /**
   * Determines if this account can write checks.
   *
   * @return true for CheckingAccount, false for SavingsAccount
   */
  public abstract boolean canWriteChecks();

  @Override
  public int hashCode() {
    return accountNumber.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Account other) {
      return accountNumber.equals(other.accountNumber);
    }
    return false;
  }

  @Override
  public String toString() {
    return getClass().getSimpleName()
        + "{accountNumber='"
        + accountNumber
        + "', balance="
        + balance
        + ", isActive="
        + isActive
        + "}";
  }
}
