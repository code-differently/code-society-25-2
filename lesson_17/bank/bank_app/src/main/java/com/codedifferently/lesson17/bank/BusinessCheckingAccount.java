package com.codedifferently.lesson17.bank;

import java.util.Set;

/** 
 * Represents a business checking account that extends the standard checking account functionality.
 * 
 * <p>A business checking account enforces that at least one owner must be a business customer
 * ({@link BusinessCustomer}). This validation occurs during account creation and will throw
 * an {@link IllegalArgumentException} if no business owners are present.
 * 
 * <p>All standard checking account operations are inherited from {@link CheckingAccount},
 * including deposits, withdrawals, balance inquiries, and account closure functionality.
 * 
 * <p>Business accounts can be seamlessly integrated with the {@link BankAtm} system
 * through polymorphism, requiring no changes to existing ATM operations.
 * 
 * @see CheckingAccount
 * @see BusinessCustomer
 * @see BankAtm
 * @since 1.0
 */
public class BusinessCheckingAccount extends CheckingAccount {

  /**
   * Creates a new business checking account with validation for business ownership.
   * 
   * <p>This constructor validates that at least one owner in the provided set is a
   * {@link BusinessCustomer}. If no business customers are found among the owners,
   * an {@link IllegalArgumentException} is thrown.
   * 
   * <p>The account inherits all functionality from {@link CheckingAccount} including
   * deposit/withdrawal operations, balance management, and account closure capabilities.
   *
   * @param accountNumber The unique account number for this business account
   * @param owners The set of account owners, must contain at least one {@link BusinessCustomer}
   * @param initialBalance The initial balance of the account (must be non-negative)
   * @throws IllegalArgumentException if the owners set is empty or contains no business customers
   * @throws IllegalArgumentException if the account number is null or empty
   * @throws IllegalArgumentException if the initial balance is negative
   * 
   * @see BusinessCustomer
   * @see CheckingAccount#CheckingAccount(String, Set, double)
   */
  public BusinessCheckingAccount(String accountNumber, Set<Customer> owners, double initialBalance) {
    super(accountNumber, owners, initialBalance);
    
    // Validate that at least one owner is a business customer
    boolean hasBusinessOwner = owners.stream()
        .anyMatch(owner -> owner instanceof BusinessCustomer);
    
    if (!hasBusinessOwner) {
      throw new IllegalArgumentException("Business checking account requires at least one business owner");
    }
  }

  /**
   * Returns a string representation of this business checking account.
   * 
   * <p>The string includes the account number, current balance, and active status,
   * formatted specifically for business accounts to distinguish them from regular
   * checking accounts in logging and debugging scenarios.
   *
   * @return A formatted string containing account details
   */
  @Override
  public String toString() {
    return "BusinessCheckingAccount{"
        + "accountNumber='"
        + getAccountNumber()
        + '\''
        + ", balance="
        + getBalance()
        + ", isActive="
        + !isClosed()
        + '}';
  }
}