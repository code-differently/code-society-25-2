package com.codedifferently.lesson17.bank;

import com.codedifferently.lesson17.bank.exceptions.CheckVoidedException;

/**
 * Represents a money order that withdraws funds from a source account immediately upon creation.
 * Unlike checks, money orders are prepaid and guaranteed funds.
 */
public class MoneyOrder {

  private final String moneyOrderNumber;
  private final double amount;
  private final String sourceAccountNumber;
  private boolean isVoided = false;

  /**
   * Creates a new money order and immediately withdraws funds from the source account.
   *
   * @param moneyOrderNumber The money order number.
   * @param amount The amount of the money order.
   * @param sourceAccount The account to withdraw funds from.
   * @throws IllegalArgumentException if amount is negative or zero.
   */
  public MoneyOrder(String moneyOrderNumber, double amount, Account sourceAccount) {
    if (amount <= 0) {
      throw new IllegalArgumentException("Money order amount must be positive");
    }
    this.moneyOrderNumber = moneyOrderNumber;
    this.amount = amount;
    this.sourceAccountNumber = sourceAccount.getAccountNumber();

    // Immediately withdraw funds from source account
    sourceAccount.withdraw(amount);
  }

  /**
   * Gets the money order number.
   *
   * @return The money order number.
   */
  public String getMoneyOrderNumber() {
    return moneyOrderNumber;
  }

  /**
   * Gets the amount of the money order.
   *
   * @return The amount.
   */
  public double getAmount() {
    return amount;
  }

  /**
   * Gets the source account number.
   *
   * @return The source account number.
   */
  public String getSourceAccountNumber() {
    return sourceAccountNumber;
  }

  /**
   * Gets the voided status of the money order.
   *
   * @return True if the money order is voided, false otherwise.
   */
  public boolean getIsVoided() {
    return isVoided;
  }

  /** Voids the money order. */
  public void voidMoneyOrder() {
    isVoided = true;
  }

  /**
   * Deposits the money order into an account.
   *
   * @param toAccount The account to deposit the money order into.
   * @throws CheckVoidedException if the money order is voided.
   */
  public void depositFunds(Account toAccount) {
    if (isVoided) {
      throw new CheckVoidedException("Money order is voided");
    }
    toAccount.deposit(amount);
    voidMoneyOrder();
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
        + ", sourceAccountNumber='"
        + sourceAccountNumber
        + '\''
        + ", isVoided="
        + isVoided
        + '}';
  }
}
