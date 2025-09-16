package com.codedifferently.lesson17.bank;

public class MoneyOrder extends Check {
  private Account fromAccount;
  private Account toAccount;
  
  public MoneyOrder(
      String checkNumber,
      double amount,
      Account fromAccount,
      Account toAccount,
      String currencyType) {
    super(checkNumber, amount, fromAccount);
    this.fromAccount = fromAccount;
    this.toAccount = toAccount;
    depositFunds(toAccount, currencyType);
  }

  @Override
  public String toString() {
      return "MoneyOrder{"
          + "orderNumber='"
          + checkNumber
          + '\''
          + ", amount="
          + amount
          + ", fromAccount="
          + this.fromAccount.getAccountNumber()
          + ", toAccount="
          + this.toAccount.getAccountNumber()
          + '}';
  }
}
