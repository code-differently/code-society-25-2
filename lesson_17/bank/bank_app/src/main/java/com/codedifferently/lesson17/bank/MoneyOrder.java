package com.codedifferently.lesson17.bank;

final class MoneyOrder extends Check {
  public MoneyOrder(CheckingAccount source, double amount) {
    super(amount);
    source.withdraw(amount);
  }
}
