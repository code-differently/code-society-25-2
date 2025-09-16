package com.codedifferently.lesson17.bank;

import com.codedifferently.lesson17.bank.exceptions.CheckVoidedException;


public class Check {

  private final String checkNumber;
  private final double amount;
  private final CheckingAccount account;
  private boolean isVoided = false;

  
  public Check(String checkNumber, double amount, CheckingAccount account) {
    if (amount <= 0) {
      throw new IllegalArgumentException("Check amount must be positive");
    }
    this.checkNumber = checkNumber;
    this.amount = amount;
    this.account = account;
  }

  
  public String getCheckNumber() {
    return checkNumber;
  }

  
  public double getAmount() {
    return amount;
  }

 
  public CheckingAccount getAccount() {
    return account;
  }

  
  public boolean getIsVoided() {
    return isVoided;
  }

  
  public void voidCheck() {
    isVoided = true;
  }

  
  public void depositFunds(CheckingAccount toAccount) {
    if (isVoided) {
      throw new CheckVoidedException("Check is voided");
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
