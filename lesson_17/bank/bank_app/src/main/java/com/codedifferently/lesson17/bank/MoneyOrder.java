package com.codedifferently.lesson17.bank;

/**
 * Represents a money order that withdraws funds immediately upon creation.
 */
public class MoneyOrder {

  private final String moneyOrderNumber;
  private final double amount;
  private final String sourceAccountNumber;
  private boolean isRedeemed = false;

  /**
   * Creates a new money order and immediately withdraws funds from the source account.
   *
   * @param moneyOrderNumber The money order number.
   * @param amount The amount of the money order.
   * @param sourceAccount The account to withdraw funds from.
   */
  public MoneyOrder(String moneyOrderNumber, double amount, Account sourceAccount) {
    if (amount <= 0) {
      throw new IllegalArgumentException("Money order amount must be positive");
    }
    this.moneyOrderNumber = moneyOrderNumber;
    this.amount = amount;
    this.sourceAccountNumber = sourceAccount.getAccountNumber();
    
    sourceAccount.withdraw(amount);
  }

  public String getMoneyOrderNumber() {
    return moneyOrderNumber;
  }

  public double getAmount() {
    return amount;
  }

  public String getSourceAccountNumber() {
    return sourceAccountNumber;
  }

  public boolean isRedeemed() {
    return isRedeemed;
  }

  /**
   * Deposits the money order into an account.
   *
   * @param toAccount The account to deposit the money order into.
   * @throws IllegalStateException If the money order has already been redeemed.
   */
  public void depositFunds(Account toAccount) {
    if (isRedeemed) {
      throw new IllegalStateException("Money order has already been redeemed");
    }
    toAccount.deposit(amount);
    isRedeemed = true;
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
        + ", isRedeemed="
        + isRedeemed
        + '}';
  }
}
