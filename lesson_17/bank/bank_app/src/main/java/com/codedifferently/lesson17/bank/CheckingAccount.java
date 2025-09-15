package com.codedifferently.lesson17.bank;

import com.codedifferently.lesson17.bank.exceptions.InsufficientFundsException;
import java.util.Set;

/** Represents a checking account. */
public class CheckingAccount extends Account {

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
