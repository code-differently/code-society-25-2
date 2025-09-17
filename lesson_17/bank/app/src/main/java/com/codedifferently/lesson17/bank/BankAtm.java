package com.codedifferently.lesson17.bank;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/** Represents a bank ATM. */
public class BankAtm {

  private final Map<UUID, Customer> customerById = new HashMap<>();
  private final Map<String, CheckingAccount> accountByNumber = new HashMap<>();
  private final Map<String, SavingsAccount> savingsAccountByNumber = new HashMap<>();
  private final AuditLog auditLog = new AuditLog();
  private final CurrencyConverter currencyConverter = new CurrencyConverter();

  /**
   * Adds a checking account to the bank.
   *
   * @param account The account to add.
   */
  public void addAccount(CheckingAccount account) {
    // BusinessCheckingAccount logic: require at least one business owner
    if (account instanceof BusinessCheckingAccount) {
      boolean hasBusinessOwner = account.getOwners().stream().anyMatch(Customer::isBusiness);
      if (!hasBusinessOwner) {
        throw new IllegalArgumentException("BusinessCheckingAccount must have at least one business owner");
      }
    }
    accountByNumber.put(account.getAccountNumber(), account);
    account
        .getOwners()
        .forEach(
            owner -> {
              customerById.put(owner.getId(), owner);
            });
    auditLog.log("Added CheckingAccount: " + account.getAccountNumber());
  }

  /**
   * Adds a savings account to the bank.
   *
   * @param account The savings account to add.
   */
  public void addAccount(SavingsAccount account) {
    savingsAccountByNumber.put(account.getAccountNumber(), account);
    account
        .getOwners()
        .forEach(
            owner -> {
              customerById.put(owner.getId(), owner);
            });
    auditLog.log("Added SavingsAccount: " + account.getAccountNumber());
  }

  /**
   * Finds all accounts owned by a customer.
   *
   * @param customerId The ID of the customer.
   * @return The unique set of accounts owned by the customer.
   */
  public Set<CheckingAccount> findAccountsByCustomerId(UUID customerId) {
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
    if (accountByNumber.containsKey(accountNumber)) {
      CheckingAccount account = getAccountOrThrow(accountNumber);
      account.deposit(amount);
      auditLog.log("Deposited $" + amount + " to CheckingAccount: " + accountNumber);
    } else if (savingsAccountByNumber.containsKey(accountNumber)) {
      SavingsAccount account = savingsAccountByNumber.get(accountNumber);
      account.deposit(amount);
      auditLog.log("Deposited $" + amount + " to SavingsAccount: " + accountNumber);
    } else {
      throw new AccountNotFoundException("Account not found");
    }
  }

  /**
   * Deposits funds into an account using a check.
   *
   * @param accountNumber The account number.
   * @param check The check to deposit.
   */
  public void depositFunds(String accountNumber, Check check) {
    CheckingAccount account = getAccountOrThrow(accountNumber);
    check.depositFunds(account);
  }

  /**
   * Deposits funds into an account using a money order.
   *
   * @param accountNumber The account number.
   * @param moneyOrder The money order to deposit.
   */
  public void depositFunds(String accountNumber, MoneyOrder moneyOrder) throws AccountNotFoundException {
    if (!moneyOrder.isProcessed()) {
      throw new IllegalStateException("Money order not processed");
    }
    if (accountByNumber.containsKey(accountNumber)) {
      CheckingAccount account = getAccountOrThrow(accountNumber);
      account.deposit(moneyOrder.getAmount());
      auditLog.log("Deposited $" + moneyOrder.getAmount() + " via MoneyOrder to CheckingAccount: " + accountNumber);
    } else if (savingsAccountByNumber.containsKey(accountNumber)) {
      SavingsAccount account = savingsAccountByNumber.get(accountNumber);
      account.deposit(moneyOrder.getAmount());
      auditLog.log("Deposited $" + moneyOrder.getAmount() + " via MoneyOrder to SavingsAccount: " + accountNumber);
    } else {
      throw new AccountNotFoundException("Account not found");
    }
  }

  /**
   * Deposits funds into an account in a specified currency.
   *
   * @param accountNumber The account number.
   * @param amount The amount to deposit.
   * @param currency The currency type.
   */
  public void depositFunds(String accountNumber, double amount, String currency) throws AccountNotFoundException {
    double usdAmount = currencyConverter.convertToUSD(amount, currency);
    depositFunds(accountNumber, usdAmount);
    auditLog.log("Deposited " + amount + " " + currency + " (converted to $" + usdAmount + ") to account: " + accountNumber);
  }

  /**
   * Withdraws funds from an account.
   *
   * @param accountNumber
   * @param amount
   */
  public void withdrawFunds(String accountNumber, double amount) {
    if (accountByNumber.containsKey(accountNumber)) {
      CheckingAccount account = getAccountOrThrow(accountNumber);
      account.withdraw(amount);
      auditLog.log("Withdrew $" + amount + " from CheckingAccount: " + accountNumber);
    } else if (savingsAccountByNumber.containsKey(accountNumber)) {
      SavingsAccount account = savingsAccountByNumber.get(accountNumber);
      account.withdraw(amount);
      auditLog.log("Withdrew $" + amount + " from SavingsAccount: " + accountNumber);
    } else {
      throw new AccountNotFoundException("Account not found");
    }
  }

  /**
   * Gets an account by its number or throws an exception if not found.
   *
   * @param accountNumber The account number.
   * @return The account.
   */
  private CheckingAccount getAccountOrThrow(String accountNumber) {
    CheckingAccount account = accountByNumber.get(accountNumber);
    if (account == null || account.isClosed()) {
      throw new AccountNotFoundException("Account not found");
    }
    return account;
  }

  public AuditLog getAuditLog() {
    return auditLog;
  }
}
