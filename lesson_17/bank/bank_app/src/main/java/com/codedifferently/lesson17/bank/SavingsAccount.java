package com.codedifferently.lesson17.bank;

public class SavingsAccount extends CheckingAccount {
    public SavingsAccount(String accountNumber, double initialBalance) {
        super(accountNumber, java.util.Collections.emptySet(), initialBalance);
    }

public void writeCheck(double amount) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Cannot write checks from a savings account");
    }
  }
