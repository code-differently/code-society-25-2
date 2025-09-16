package com.codedifferently.lesson17.bank;

import java.util.UUID;

final class MoneyOrder extends Check {
  public MoneyOrder(CheckingAccount source, double amount) {
    super("MO-" + UUID.randomUUID(), amount, source);
    source.withdraw(amount);
  }
}
