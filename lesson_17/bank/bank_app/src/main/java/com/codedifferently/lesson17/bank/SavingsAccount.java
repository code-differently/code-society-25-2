package com.codedifferently.lesson17.bank;

import com.codedifferently.lesson17.bank.exceptions.InsufficientFundsException;
import java.util.Set;

/**
 * Checks if this customer is a business entity. This method was added to support
 * BusinessCheckingAccount requirements, which mandate that at least one owner must be a business
 * entity.
 *
 * @return true if this customer is a business entity, false otherwise
 */
public class SavingsAccount extends CheckingAccount {
  public SavingsAccount(String accountNumber, Set<Customer> owners, double initialBalance) {
    super(accountNumber, owners, initialBalance);
  }

  /**
   * Overrides the check processing functionality to prevent checks from being used with savings
   * accounts. This implementation fulfills the requirement that savings accounts cannot process
   * checks by throwing an UnsupportedOperationException whenever check processing is attempted.
   *
   * @param check The check that was attempted to be processed
   * @throws UnsupportedOperationException always, as savings accounts don't support checks
   * @throws InsufficientFundsException never, as check processing is not supported
   */
  @Override
  public void processCheck(Check check) throws InsufficientFundsException {
    throw new UnsupportedOperationException("Savings accounts do not support check transactions.");
  }
}
