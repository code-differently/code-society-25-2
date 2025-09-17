package com.codedifferently.lesson17.bank;

import com.codedifferently.lesson17.bank.exceptions.InsufficientFundsException;
import java.util.Set;

public class SavingsAccount extends CheckingAccount {
  public SavingsAccount(String accountNumber, Set<Customer> owners, double initialBalance) {
    super(accountNumber, owners, initialBalance);
  }

  @Override
  public void processCheck(Check check) throws InsufficientFundsException {
    throw new UnsupportedOperationException("Savings accounts do not support check transactions.");
  }
}
