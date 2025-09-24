package com.codedifferently.lesson17.bank;

/**
 * MoneyOrder is prepaid: funds are withdrawn from the source immediately on creation. Later,
 * depositing the money order only credits the target (no second withdrawal).
 */
public class MoneyOrder extends Check {
  private final double prepaidAmount;

  public MoneyOrder(AllowsChecks source, double amount) {
    super(source, amount);
    ((CheckingAccount) source).withdraw(amount); // prepay now
    this.prepaidAmount = amount;
  }

  @Override
  public void depositFunds(CheckingAccount target) {
    // No second debit from the source — just credit the target with the prepaid amount.
    target.deposit(prepaidAmount);
  }
}
