package com.codedifferently.lesson17.bank;

import com.codedifferently.lesson17.bank.exceptions.AccountNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/** Represents a bank ATM. */
public class BankAtm {

  private final Map<UUID, Customer> customerById = new HashMap<>();
  private final Map<String, Account> accountByNumber = new HashMap<>();
  private final AuditLog auditLog;

  /** Creates a new BankAtm with a new audit log. */
  public BankAtm() {
    this.auditLog = new AuditLog();
  }

  /**
   * Creates a new BankAtm with the specified audit log. This allows for dependency injection for
   * testing.
   */
  public BankAtm(AuditLog auditLog) {
    this.auditLog = auditLog;
  }

  /**
   * Adds an account to the bank.
   *
   * @param account The account to add.
   */
  public void addAccount(Account account) {
    accountByNumber.put(account.getAccountNumber(), account);
    account
        .getOwners()
        .forEach(
            owner -> {
              customerById.put(owner.getId(), owner);
            });
  }

  /**
   * Adds a checking account to the bank.
   *
   * @param account The checking account to add.
   */
  public void addAccount(CheckingAccount account) {
    addAccount((Account) account);
  }

  /**
   * Adds a savings account to the bank.
   *
   * @param account The savings account to add.
   */
  public void addAccount(SavingsAccount account) {
    addAccount((Account) account);
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
    auditLog.logDeposit(accountNumber, amount);
  }

  /**
   * Deposits funds into an account using a check.
   *
   * @param accountNumber The account number.
   * @param check The check to deposit.
   */
  public void depositFunds(String accountNumber, Check check) {
    Account account = getAccountOrThrow(accountNumber);

    // Check deposits only work with checking accounts
    if (!(account instanceof CheckingAccount)) {
      throw new IllegalArgumentException("Check deposits are only allowed for checking accounts");
    }

    CheckingAccount checkingAccount = (CheckingAccount) account;
    check.depositFunds(checkingAccount);

    // Log the transfer from source to destination account
    auditLog.logTransfer(
        check.getSourceAccount().getAccountNumber(),
        accountNumber,
        check.getAmount(),
        check.getCheckNumber());
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
    auditLog.logWithdrawal(accountNumber, amount);
  }

  /**
   * Gets the audit log for transaction history.
   *
   * @return The audit log.
   */
  public AuditLog getAuditLog() {
    return auditLog;
  }

  /**
   * Gets an account by its number or throws an exception if not found.
   *
   * @param accountNumber The account number.
   * @return The account.
   */
  private Account getAccountOrThrow(String accountNumber) {
    Account account = accountByNumber.get(accountNumber);
    if (account == null || account.isClosed()) {
      throw new AccountNotFoundException("Account not found");
    }
    return account;
  }
}
