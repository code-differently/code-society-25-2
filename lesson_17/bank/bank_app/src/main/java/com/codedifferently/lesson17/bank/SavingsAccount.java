package com.codedifferently.lesson17.bank;

public class SavingsAccount extends CheckingAccount {
  public SavingsAccount(String accountNumber, double initialBalance) {
    super(accountNumber, java.util.Collections.emptySet(), initialBalance);
  }

  public void writeCheck(double amount) {
    throw new UnsupportedOperationException("Checks are not allowed on a SavingsAccount.");
  }
}
