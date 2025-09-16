package com.codedifferently.lesson17.bank;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.codedifferently.lesson17.bank.exceptions.AccountNotFoundException;

/** Represents a bank ATM. */
public class BankAtm {

  private final Map<UUID, AccountOwner> accountOwnerById = new HashMap<>();
  private final Map<String, CheckingAccount> accountByNumber = new HashMap<>();
  private final Map<String, SavingAccount> savingsAccountByNumber = new HashMap<>();
  /**
   * Adds a checking account to the bank. Also supports BusinessCheckingAccount.
   *
   * @param account The account to add.
   */
  public void addAccount(CheckingAccount account) {
    accountByNumber.put(account.getAccountNumber(), account);
    account
        .getOwners()
        .forEach(
            owner -> {
              accountOwnerById.put(owner.getId(), owner);
              owner.addAccount(account);
            });
  }

  /**
   * Adds a savings account to the bank.
   *
   * @param account The account to add.
   */
  public void addAccount(SavingAccount account) {
    savingsAccountByNumber.put(account.getAccountNumber(), account);
    account
        .getOwners()
        .forEach(
            owner -> {
              accountOwnerById.put(owner.getId(), owner);
              owner.addSavingsAccount(account);
            });
  }

  /**
   * Finds all checking accounts owned by a customer or business.
   * Updated to support both Customer and Business owners.
   *
   * @param customerId The ID of the customer or business.
   * @return The unique set of checking accounts owned by the customer or business.
   */
  public Set<CheckingAccount> findAccountsByCustomerId(UUID customerId) {
    return accountOwnerById.containsKey(customerId)
        ? accountOwnerById.get(customerId).getAccounts()
        : Set.of();
  }

  /**
   * Deposits funds into an account. Now supports both checking and savings accounts.
   * The method first tries to find a checking account, then a savings account.
   *
   * @param accountNumber The account number.
   * @param amount The amount to deposit.
   */
  public void depositFunds(String accountNumber, double amount) {
    // Try checking account first
    CheckingAccount checkingAccount = accountByNumber.get(accountNumber);
    if (checkingAccount != null) {
      checkingAccount.deposit(amount);
      return;
    }
    
    // Try savings account
    SavingAccount savingsAccount = savingsAccountByNumber.get(accountNumber);
    if (savingsAccount != null) {
      savingsAccount.deposit(amount);
      return;
    }
    
    throw new AccountNotFoundException("Account not found");
  }

  /**
   * Deposits funds into a checking account using a check.
   * Note: Checks can only be deposited into checking accounts, not savings accounts.
   *
   * @param accountNumber The checking account number.
   * @param check The check to deposit.
   */
  public void depositFunds(String accountNumber, Check check) {
    CheckingAccount account = getAccountOrThrow(accountNumber);
    check.depositFunds(account);
  }

  /**
   * Withdraws funds from an account. Now supports both checking and savings accounts.
   * The method first tries to find a checking account, then a savings account.
   *
   * @param accountNumber The account number.
   * @param amount The amount to withdraw.
   */
  public void withdrawFunds(String accountNumber, double amount) {
    // Try checking account first
    CheckingAccount checkingAccount = accountByNumber.get(accountNumber);
    if (checkingAccount != null) {
      checkingAccount.withdraw(amount);
      return;
    }
    
    // Try savings account
    SavingAccount savingsAccount = savingsAccountByNumber.get(accountNumber);
    if (savingsAccount != null) {
      savingsAccount.withdraw(amount);
      return;
    }
    
    throw new AccountNotFoundException("Account not found");
  }

  /**
   * Gets a checking account by its number or throws an exception if not found.
   *
   * @param accountNumber The account number.
   * @return The checking account.
   * @throws AccountNotFoundException If the checking account is not found.
   */
  private CheckingAccount getAccountOrThrow(String accountNumber) {
    CheckingAccount account = accountByNumber.get(accountNumber);
    if (account == null) {
      throw new AccountNotFoundException("Checking account not found");
    }
    return account;
  }
}
