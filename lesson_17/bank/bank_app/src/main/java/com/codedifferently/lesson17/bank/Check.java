package com.codedifferently.lesson17.bank;

import com.codedifferently.lesson17.bank.exceptions.CheckVoidedException;

/** Represents a check. */
public class Check extends FundTransfer{

  

  /**
   * Creates a new check.
   *
   * @param checkNumber The check number.
   * @param amount The amount of the check.
   * @param account The account the check is drawn on.
   */
  public Check(String checkNumber, double amount, CheckingAccount account) {
    super(checkNumber,amount,account);
  }

  /**
   * Gets the voided status of the check.
   *
   * @return True if the check is voided, and false otherwise.
   */
  public boolean getIsVoided() {
    return isVoided;
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
  

  @Override
  public int hashCode() {
    return id.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Check other) {
      return id.equals(other.id);
    }
    return false;
  }

  @Override
  public String toString() {
    return "Check{"
        + "checkNumber='"
        + id
        + '\''
        + ", amount="
        + amount
        + ", account="
        + account.getAccountNumber()
        + '}';
  }
}
