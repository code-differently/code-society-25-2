package com.codedifferently.lesson17.bank;

import java.util.Set;

public interface Account {
  String getAccountNumber();

  Set<Customer> getOwners();

  void deposit(double amount);

  void withdraw(double amount);

  double getBalance();

  void closeAccount();

  boolean isClosed();
}
