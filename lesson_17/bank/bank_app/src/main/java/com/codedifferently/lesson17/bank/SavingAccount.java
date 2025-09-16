package com.codedifferently.lesson17.bank;

import java.util.Set;

import com.codedifferently.lesson17.bank.exceptions.InsufficientFundsException;


public class SavingAccount {

  private final Set<AccountOwner> owners;
  private final String accountNumber;
  private double balance;
  private boolean isActive;

  
  public SavingAccount(String accountNumber, Set<AccountOwner> owners, double initialBalance) {
    this.accountNumber = accountNumber;
    this.owners = owners;
    this.balance = initialBalance;
    isActive = true;
  }

 
  public String getAccountNumber() {
    return accountNumber;
  }

  
  public Set<AccountOwner> getOwners() {
    return owners;
  }

 
  public void deposit(double amount) throws IllegalStateException {
    if (isClosed()) {
      throw new IllegalStateException("Cannot deposit to a closed account");
    }
    if (amount <= 0) {
      throw new IllegalArgumentException("Deposit amount must be positive");
    }
    balance += amount;
  }

  
  public void withdraw(double amount) throws InsufficientFundsException {
    if (isClosed()) {
      throw new IllegalStateException("Cannot withdraw from a closed account");
    }
    if (amount <= 0) {
      throw new IllegalArgumentException("Withdrawal amount must be positive");
    }
    if (balance < amount) {
      throw new InsufficientFundsException("Account does not have enough funds for withdrawal");
    }
    balance -= amount;
  }

 
  public double getBalance() {
    return balance;
  }

  
  public void closeAccount() throws IllegalStateException {
    if (balance > 0) {
      throw new IllegalStateException("Cannot close account with a positive balance");
    }
    isActive = false;
  }

  
  public boolean isClosed() {
    return !isActive;
  }

  @Override
  public int hashCode() {
    return accountNumber.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof SavingAccount other) {
      return accountNumber.equals(other.accountNumber);
    }
    return false;
  }

  @Override
  public String toString() {
    return "SavingsAccount{"
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

    

