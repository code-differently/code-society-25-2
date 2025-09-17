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
   * Deposits funds into the account. The amount is automatically converted to USD and the
   * transaction is logged in the audit trail.
   *
   * @param accountNumber The account number.
   * @param amount The amount to deposit.
   * @param currencyType The currency type of the deposit amount
   * @throws AccountNotFoundException if the account is not found or is closed
   * @throws IllegalArgumentException if the currency type is unsupported or amount is invalid
   * @throws IllegalStateException if the account is closed
   */
  public void depositFunds(String accountNumber, double amount, String currencyType) {
    Account account = getAccountOrThrow(accountNumber);
    // Change the amount based on the currency type
    amount = CurrencyConverter.convertToUSD(amount, currencyType);
    account.deposit(amount);
    account.getAuditLog().log(account, "DEPOSIT", amount);
  }

  public void depositFunds(String accountNumber, double amount) {
    depositFunds(accountNumber, amount, "usd");
  }

  /**
   * Deposits funds into an account using a check.
   *
   * @param accountNumber The account number.
   * @param check The check to deposit.
   * @param currencyType The currency type for the transaction
   */
  public void depositFunds(String accountNumber, Check check, String currencyType) {
    Account account = getAccountOrThrow(accountNumber);
    check.depositFunds(account, currencyType);
    account.getAuditLog().log(account, "DEPOSIT", check.getAmount());
  }

  public void depositFunds(String accountNumber, Check check) {
    depositFunds(accountNumber, check, "usd");
  }

  /**
   * Withdraws funds from an account.
   *
   * @param accountNumber
   * @param amount
   * @param currencyType
   */
  public void withdrawFunds(String accountNumber, double amount, String currencyType) {
    Account account = getAccountOrThrow(accountNumber);
    amount = CurrencyConverter.convertToUSD(amount, currencyType);
    account.withdraw(amount);
    account.getAuditLog().log(account, "WITHDRAW", amount);
  }

  public void withdrawFunds(String accountNumber, double amount) {
    withdrawFunds(accountNumber, amount, "usd");
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
