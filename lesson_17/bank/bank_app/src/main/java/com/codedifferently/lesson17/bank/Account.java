package com.codedifferently.lesson17.bank;

import java.util.Set;

/** Base interface for all account types. */
public interface Account {
  String getAccountNumber();

  Set<Customer> getOwners();

  void deposit(double amount);

  void withdraw(double amount);

  double getBalance();

  boolean isClosed();

  void closeAccount();
}
