package com.codedifferently.lesson17.bank;

import com.codedifferently.lesson17.bank.exceptions.InsufficientFundsException;
import java.util.Set;

public abstract class Account {
  private final String accountNumber;
  private final Set<Customer> owners;
  private double balance;
  private boolean isActive;

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

  public void deposit(double amount) {
    if (amount < 0) throw new IllegalArgumentException("Deposit must be positive");
    balance += amount;
  }

  public void withdraw(double amount) {
    if (amount < 0) throw new IllegalStateException("Withdrawal amount must be positive");
    if (amount > balance)
      throw new InsufficientFundsException("Account does not have enough funds for withdrawal");
    balance -= amount;
  }

  public void closeAccount() {
    if (balance != 0) throw new IllegalStateException("Cannot close account with non-zero balance");
    isActive = false;
  }

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
