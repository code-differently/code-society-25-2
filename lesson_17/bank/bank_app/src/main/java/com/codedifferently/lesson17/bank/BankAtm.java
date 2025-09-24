package com.codedifferently.lesson17.bank;

import com.codedifferently.lesson17.bank.exceptions.AccountNotFoundException;
import com.codedifferently.lesson17.bank.exceptions.CheckVoidedException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/** Represents a bank ATM. */
public class BankAtm {

  private final Map<UUID, Customer> customerById = new HashMap<>();
  private final Map<String, CheckingAccount> accountByNumber = new HashMap<>();
  // Track processed check numbers to prevent double-deposit (even across Check instances)
  private final Set<String> processedCheckNumbers = new HashSet<>();

  /**
   * Adds a checking account to the bank.
   *
   * @param account The account to add.
   */
  public void addAccount(CheckingAccount account) {
    accountByNumber.put(account.getAccountNumber(), account);
    account.getOwners().forEach(owner -> customerById.put(owner.getId(), owner));
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
    CheckingAccount account = getAccountOrThrow(accountNumber);
    account.deposit(amount);
  }

  /**
   * Deposits funds into an account using a check.
   *
   * @param accountNumber The account number.
   * @param check The check to deposit.
   */
  public void depositFunds(String accountNumber, Check check) {
    CheckingAccount account = getAccountOrThrow(accountNumber);

    // Prevent re-deposit of the same check number or an already-voided check
    String num = check.getCheckNumber();
    if (processedCheckNumbers.contains(num) || check.isVoided()) {
      throw new CheckVoidedException("Check is voided");
    }

    // Move the money; Check.depositFunds will also void this check instance
    check.depositFunds(account);

    // Record that this check number has been processed
    processedCheckNumbers.add(num);
  }

  /**
   * Withdraws funds from an account.
   *
   * @param accountNumber The account number.
   * @param amount The amount to withdraw.
   */
  public void withdrawFunds(String accountNumber, double amount) {
    CheckingAccount account = getAccountOrThrow(accountNumber);
    account.withdraw(amount);
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
}
