package com.codedifferently.lesson17.bank;

/**
 * The MoneyOrder class represents a financial instrument that immediately transfers funds from one
 * account to another upon creation.
 */
public class MoneyOrder extends Check {
  private final Account fromAccount;
  private final Account toAccount;

  /**
   * Constructs a new MoneyOrder and immediately processes the fund transfer. The funds are
   * withdrawn from the source account and deposited into the destination account using the
   * specified currency conversion.
   *
   * @param checkNumber The unique identifier for this money order
   * @param amount The amount to transfer
   * @param fromAccount The account from which funds will be withdrawn
   * @param toAccount The account to which funds will be deposited
   * @param currencyType The currency type for the transaction
   */
  public MoneyOrder(
      String checkNumber,
      double amount,
      Account fromAccount,
      Account toAccount,
      String currencyType) {
    super(checkNumber, amount, fromAccount);
    this.fromAccount = fromAccount;
    this.toAccount = toAccount;
    depositFunds(toAccount, currencyType);
    this.isVoided = true;
  }

  public MoneyOrder(String checkNumber, double amount, Account fromAccount, Account toAccount) {
    super(checkNumber, amount, fromAccount);
    this.fromAccount = fromAccount;
    this.toAccount = toAccount;
    depositFunds(toAccount);
    this.isVoided = true;
  }

  @Override
  public String toString() {
    return "MoneyOrder{"
        + "orderNumber='"
        + checkNumber
        + '\''
        + ", amount="
        + amount
        + ", fromAccount="
        + this.fromAccount.getAccountNumber()
        + ", toAccount="
        + this.toAccount.getAccountNumber()
        + '}';
  }
}
