package com.codedifferently.lesson17.bank;

public class MoneyOrder extends Check {
  public MoneyOrder(
      String checkNumber,
      double amount,
      Account fromAccount,
      Account toAccount,
      String currencyType) {
    super(checkNumber, amount, fromAccount);
    depositFunds(toAccount, currencyType);
  }
}
