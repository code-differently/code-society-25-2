package com.codedifferently.lesson17.bank;

import com.codedifferently.lesson17.bank.exceptions.AccountNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * Represents a bank ATM with support for multiple account types and comprehensive audit logging.
 * Enhanced to follow SOLID principles.
 */
public class BankAtm {

  private final Map<UUID, Customer> customerById = new HashMap<>();
  private final Map<String, Account> accountByNumber = new HashMap<>();
  private final AuditLog auditLog = new AuditLog();

  /**
   * Adds a checking account to the bank.
   *
   * @param account The account to add.
   */
  public void addAccount(CheckingAccount account) {
    addAccountInternal(account);
  }

  /**
   * Adds a savings account to the bank.
   *
   * @param account The savings account to add.
   */
  public void addAccount(SavingsAccount account) {
    addAccountInternal(account);
  }

  /**
   * Internal method to add any type of account.
   *
   * @param account The account to add.
   */
  private void addAccountInternal(Account account) {
    accountByNumber.put(account.getAccountNumber(), account);
    account
        .getOwners()
        .forEach(
            owner -> {
              customerById.put(owner.getId(), owner);
            });
  }

  /**
   * Finds all accounts owned by a customer.
   *
   * @param customerId The ID of the customer.
   * @return The unique set of accounts owned by the customer.
   */
  public Set<Account> findAccountsByCustomerId(UUID customerId) {
    return customerById.containsKey(customerId)
        ? customerById.get(customerId).getAccounts()
        : Set.of();
  }

  /**
   * Deposits funds into an account.
   *
   * @param accountNumber The account number.
   * @param amount The amount to deposit.
   */
  public void depositFunds(String accountNumber, double amount) {
    Account account = getAccountOrThrow(accountNumber);
    account.deposit(amount);
    auditLog.recordCredit(accountNumber, amount, "Cash deposit");
  }

  /**
   * Deposits funds into an account using a check. Note: Only checking accounts support check
   * deposits.
   *
   * @param accountNumber The account number.
   * @param check The check to deposit.
   * @throws IllegalArgumentException if trying to deposit a check to a savings account.
   */
  public void depositFunds(String accountNumber, Check check) {
    Account account = getAccountOrThrow(accountNumber);

    // Only checking accounts can accept check deposits
    if (!(account instanceof CheckingAccount)) {
      throw new IllegalArgumentException(
          "Cannot deposit checks to savings accounts. Account type: "
              + account.getClass().getSimpleName());
    }

    CheckingAccount checkingAccount = (CheckingAccount) account;
    check.depositFunds(checkingAccount);
    auditLog.recordCredit(
        accountNumber,
        check.getAmount(),
        String.format(
            "Check deposit from account %s", check.getSourceAccount().getAccountNumber()));
  }

  /**
   * Withdraws funds from an account.
   *
   * @param accountNumber The account number.
   * @param amount The amount to withdraw.
   */
  public void withdrawFunds(String accountNumber, double amount) {
    Account account = getAccountOrThrow(accountNumber);
    account.withdraw(amount);
    auditLog.recordDebit(accountNumber, amount, "Cash withdrawal");
  }

  /**
   * Gets the audit log for transaction tracking.
   *
   * @return The audit log instance.
   */
  public AuditLog getAuditLog() {
    return auditLog;
  }

  /**
   * Gets an account by its number or throws an exception if not found.
   *
   * @param accountNumber The account number.
   * @return The account.
   * @throws AccountNotFoundException if the account is not found or is closed.
   */
  private Account getAccountOrThrow(String accountNumber) {
    Account account = accountByNumber.get(accountNumber);
    if (account == null || account.isClosed()) {
      throw new AccountNotFoundException("Account not found");
    }
    return account;
  }
}
