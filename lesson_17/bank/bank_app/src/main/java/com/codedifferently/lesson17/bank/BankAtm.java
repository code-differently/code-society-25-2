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
  private final AuditLog auditLog = new AuditLog();

  /**
   * Adds a checking account to the bank.
   *
   * @param account The account to add.
   */
  public void addAccount(CheckingAccount account) {
    addAccount((Account) account);
  }

  public void addAccount(SavingsAccount account) {
    addAccount((Account) account);
  }

  public void addAccount(BusinessCheckingAccount account) {
    addAccount((Account) account);
  }

  /**
   * Adds any type of account to the bank.
   *
   * @param account The account to add.
   */
  private void addAccount(Account account) {
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
    depositFunds(accountNumber, amount, Currency.USD);
  }

  /**
   * Deposits funds into an account with currency conversion.
   *
   * @param accountNumber The account number.
   * @param amount The amount to deposit in the specified currency.
   * @param currency The currency of the deposit amount.
   */
  public void depositFunds(String accountNumber, double amount, Currency currency) {
    Account account = getAccountOrThrow(accountNumber);
    double usdAmount = CurrencyConverter.convertToUSD(amount, currency);
    account.deposit(usdAmount);
    auditLog.recordCredit(accountNumber, usdAmount, 
        "Cash deposit" + (currency != Currency.USD ? " (converted from " + currency + ")" : ""));
  }

  /**
   * Deposits funds into an account using a check.
   *
   * @param accountNumber The account number.
   * @param check The check to deposit.
   */
  public void depositFunds(String accountNumber, Check check) {
    Account account = getAccountOrThrow(accountNumber);
    
    // Record the debit for the source account (check will handle the actual withdrawal)
    auditLog.recordDebit(check.getAccount().getAccountNumber(), check.getAmount(), 
        "Check written: " + check.getCheckNumber());
    
    check.depositFunds((CheckingAccount) account);
    
    // Record the credit for the destination account
    auditLog.recordCredit(accountNumber, check.getAmount(), 
        "Check deposit: " + check.getCheckNumber());
  }

  /**
   * Deposits funds using a money order.
   *
   * @param accountNumber The account number.
   * @param moneyOrder The money order to deposit.
   */
  public void depositFunds(String accountNumber, MoneyOrder moneyOrder) {
    Account account = getAccountOrThrow(accountNumber);
    
    // Record the debit for the source account (money order already handled withdrawal)
    auditLog.recordDebit(moneyOrder.getSourceAccountNumber(), moneyOrder.getAmount(), 
        "Money order issued: " + moneyOrder.getMoneyOrderNumber());
    
    moneyOrder.depositFunds(account);
    
    // Record the credit for the destination account
    auditLog.recordCredit(accountNumber, moneyOrder.getAmount(), 
        "Money order deposit: " + moneyOrder.getMoneyOrderNumber());
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
