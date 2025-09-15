package com.codedifferently.lesson17.bank;

import com.codedifferently.lesson17.bank.exceptions.CheckVoidedException;

/** Represents a money order that withdraws funds immediately upon creation. */
public class MoneyOrder {
  private final String moneyOrderNumber;
  private final double amount;
  private final Account account;
  private boolean isUsed = false;

  /**
   * Creates a new money order and immediately withdraws funds from the source account.
   *
   * @param moneyOrderNumber The money order number.
   * @param amount The amount of the money order.
   * @param account The account to withdraw funds from immediately.
   */
  public MoneyOrder(String moneyOrderNumber, double amount, Account account) {
    if (amount < 0) {
      throw new IllegalArgumentException("MoneyOrder amount must be positive");
    }
    this.moneyOrderNumber = moneyOrderNumber;
    this.amount = amount;
    this.account = account;

    // Immediately withdraw funds from source account (key difference from Check)
    account.withdraw(amount);
  }

  /**
   * Gets the used status of the money order.
   *
   * @return True if the money order is used, and false otherwise.
   */
  public boolean getIsUsed() {
    return isUsed;
  }

  /** Marks the money order as used. */
  public void useMoneyOrder() {
    isUsed = true;
  }

  /**
   * Deposits the money order into an account.
   *
   * @param toAccount The account to deposit the money order into.
   */
  public void depositFunds(Account toAccount) {
    if (isUsed) {
      throw new CheckVoidedException("MoneyOrder is already used");
    }
    // No withdrawal needed here since funds were already withdrawn in constructor
    toAccount.deposit(amount);
    useMoneyOrder();
  }

  @Override
  public int hashCode() {
    return moneyOrderNumber.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof MoneyOrder other) {
      return moneyOrderNumber.equals(other.moneyOrderNumber);
    }
    return false;
  }

  @Override
  public String toString() {
    return "MoneyOrder{"
        + "moneyOrderNumber='"
        + moneyOrderNumber
        + '\''
        + ", amount="
        + amount
        + ", account="
        + account.getAccountNumber()
        + '}';
  }
}
