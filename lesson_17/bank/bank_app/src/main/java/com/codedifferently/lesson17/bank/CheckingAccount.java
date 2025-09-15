package com.codedifferently.lesson17.bank;

import java.util.Set;

import com.codedifferently.lesson17.bank.exceptions.InsufficientFundsException;

/** Represents a checking account. */
public class CheckingAccount extends Account {
  
  /**
   * Creates a new checking account.
   *
   * @param accountNumber The account number.
   * @param customers The owners of the account.
   * @param balance The initial balance of the account.
   */
  public CheckingAccount(String accountNumber, Set<Customer> customers, Double balance) {
    super(accountNumber, customers, balance, false); // false means not closed
  }

  /**
   * Gets the owners of the account.
   *
   * @return The owners of the account.
   */
  public Set<Customer> getOwners() {
    return getCustomers();
  }

  @Override
  public void deposit(Double amount) throws IllegalStateException {
    if (isClosed()) {
      throw new IllegalStateException("Cannot deposit to a closed account");
    }
    if (amount <= 0) {
      throw new IllegalArgumentException("Deposit amount must be positive");
    }
    setBalance(getBalance() + amount);
  }

  @Override
  public void withdraw(Double amount) throws InsufficientFundsException {
    if (isClosed()) {
      throw new IllegalStateException("Cannot withdraw from a closed account");
    }
    if (amount <= 0) {
      throw new IllegalStateException("Withdrawal amount must be positive");
    }
    if (getBalance() < amount) {
      throw new InsufficientFundsException("Account does not have enough funds for withdrawal");
    }
    setBalance(getBalance() - amount);
  }

  /** Closes the account. */
  public void closeAccount() throws IllegalStateException {
    if (getBalance() > 0) {
      throw new IllegalStateException("Cannot close account with a positive balance");
    }
    close(); // Use the inherited close() method
  }

  @Override
  public int hashCode() {
    return getAccountNumber().hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof CheckingAccount other) {
      return getAccountNumber().equals(other.getAccountNumber());
    }
    return false;
  }

  @Override
  public String toString() {
    return "CheckingAccount{"
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
