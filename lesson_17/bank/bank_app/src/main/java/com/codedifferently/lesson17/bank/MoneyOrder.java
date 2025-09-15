package com.codedifferently.lesson17.bank;
import com.codedifferently.lesson17.bank.exceptions.SavingsException;
import com.codedifferently.lesson17.bank.exceptions.CheckVoidedException;

public class MoneyOrder extends Check {
  public MoneyOrder(
      String checkNumber,
      double amount,
      Account fromAccount,
      Account toAccount,
      String currencyType) throws CheckVoidedException, SavingsException {
    super(checkNumber, amount, fromAccount);
    depositFunds(toAccount, currencyType);
  }
}
