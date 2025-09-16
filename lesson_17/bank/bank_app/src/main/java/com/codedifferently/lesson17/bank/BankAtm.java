package com.codedifferently.lesson17.bank;

import com.codedifferently.lesson17.bank.exceptions.AccountNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class BankAtm {

  private final Map<UUID, AccountOwner> accountOwnerById = new HashMap<>();
  private final Map<String, CheckingAccount> accountByNumber = new HashMap<>();
  private final Map<String, SavingAccount> savingsAccountByNumber = new HashMap<>();

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

  public Set<CheckingAccount> findAccountsByCustomerId(UUID customerId) {
    return accountOwnerById.containsKey(customerId)
        ? accountOwnerById.get(customerId).getAccounts()
        : Set.of();
  }

  public void depositFunds(String accountNumber, double amount) {

    CheckingAccount checkingAccount = accountByNumber.get(accountNumber);
    if (checkingAccount != null) {
      checkingAccount.deposit(amount);
      return;
    }

    SavingAccount savingsAccount = savingsAccountByNumber.get(accountNumber);
    if (savingsAccount != null) {
      savingsAccount.deposit(amount);
      return;
    }

    throw new AccountNotFoundException("Account not found");
  }

  public void depositFunds(String accountNumber, Check check) {
    CheckingAccount account = getAccountOrThrow(accountNumber);
    check.depositFunds(account);
  }

  public void withdrawFunds(String accountNumber, double amount) {

    CheckingAccount checkingAccount = accountByNumber.get(accountNumber);
    if (checkingAccount != null) {
      checkingAccount.withdraw(amount);
      return;
    }

    SavingAccount savingsAccount = savingsAccountByNumber.get(accountNumber);
    if (savingsAccount != null) {
      savingsAccount.withdraw(amount);
      return;
    }

    throw new AccountNotFoundException("Account not found");
  }

  private CheckingAccount getAccountOrThrow(String accountNumber) {
    CheckingAccount account = accountByNumber.get(accountNumber);
    if (account == null) {
      throw new AccountNotFoundException("Checking account not found");
    }
    return account;
  }
}
