package com.codedifferently.lesson17.bank;

import com.codedifferently.lesson17.bank.exceptions.CheckVoidedException;

/** Represents a check. */
public class Check {

  private final String checkNumber;
  private final double amount;
  private final CheckingAccount account;
  private boolean isVoided = false;

  /**
   * Creates a new check.
   *
   * @param checkNumber The check number.
   * @param amount The amount of the check.
   * @param account The account the check is drawn on.
   */
  public Check(String checkNumber, double amount, CheckingAccount account) {
    if (amount < 0) {
      throw new IllegalArgumentException("Check amount must be positive");
    }
    this.checkNumber = checkNumber;
    this.amount = amount;
    this.account = account;
  }

  /**
   * Gets the amount of the check.
   *
   * @return The amount.
   */
  public double getAmount() {
    return amount;
  }

  /** Voids the check. */
  public void voidCheck() {
    isVoided = true;
  }

  /**
   * Deposits the check into an account.
   *
   * @param toAccount The account to deposit the check into.
   */
  public void depositFunds(Account toAccount) {
    if (isVoided) {
      throw new CheckVoidedException("Check is voided");
    }
    if (!(toAccount instanceof CheckingAccount)) {
      throw new IllegalArgumentException("Checks can only be deposited into checking accounts");
    }
    account.withdraw(amount);
    toAccount.deposit(amount);
    voidCheck();
  }

  @Override
  public int hashCode() {
    return checkNumber.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Check other) {
      return checkNumber.equals(other.checkNumber);
    }
    return false;
  }

  @Override
  public String toString() {
    return "Check{"
        + "checkNumber='"
        + checkNumber
        + '\''
        + ", amount="
        + amount
        + ", account="
        + account.getAccountNumber()
        + '}';
  }
}
