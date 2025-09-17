package com.codedifferently.lesson17.bank;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.codedifferently.lesson17.bank.exceptions.AccountNotFoundException;

/** 
 * Represents a bank ATM system that manages customer accounts and transactions.
 * 
 * <p>This ATM supports various types of checking accounts including standard
 * {@link CheckingAccount} and specialized {@link BusinessCheckingAccount} instances.
 * The system uses polymorphism to treat all account types uniformly, enabling
 * seamless operations regardless of the specific account implementation.
 * 
 * <p>Supported operations include:
 * <ul>
 *   <li>Adding accounts to the system</li>
 *   <li>Finding accounts by customer ID</li>
 *   <li>Depositing funds (cash or check) with multi-currency support</li>
 *   <li>Withdrawing funds</li>
 * </ul>
 * 
 * <p>The ATM supports deposits in multiple currencies including USD, EUR, GBP, JPY,
 * CAD, AUD, CHF, and CNY. All currency conversions are handled automatically using
 * the integrated {@link CurrencyConverter}, with all account balances maintained in USD.
 * 
 * <p>The ATM maintains internal mappings of customers and accounts for efficient
 * lookup operations and enforces business rules such as account existence validation.
 * 
 * @see CheckingAccount
 * @see BusinessCheckingAccount
 * @see Customer
 * @see BusinessCustomer
 * @see Currency
 * @see CurrencyConverter
 * @since 1.0
 */
public class BankAtm {

  private final Map<UUID, Customer> customerById = new HashMap<>();
  private final Map<String, CheckingAccount> accountByNumber = new HashMap<>();
  private final CurrencyConverter currencyConverter;
  
  /**
   * Creates a new BankAtm with default currency conversion support.
   * 
   * <p>Initializes the ATM system with an empty set of accounts and customers,
   * and sets up currency conversion capabilities for multi-currency deposits.
   */
  public BankAtm() {
    this.currencyConverter = new CurrencyConverter();
  }

  /**
   * Adds a checking account to the ATM system.
   * 
   * <p>This method accepts any type of checking account, including standard
   * {@link CheckingAccount} and {@link BusinessCheckingAccount} instances.
   * The account and all its owners are registered in the system for lookup operations.
   * 
   * <p>After adding an account, customers can perform transactions using the
   * account number, and the account can be found using any owner's customer ID.
   *
   * @param account The checking account to add (including business accounts)
   * @throws NullPointerException if the account is null
   * 
   * @see BusinessCheckingAccount
   * @see #findAccountsByCustomerId(UUID)
   */
  public void addAccount(CheckingAccount account) {
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
   * Deposits funds into an account with automatic currency conversion to USD.
   * 
   * <p>This method accepts deposits in various currencies and automatically
   * converts the amount to USD using current exchange rates before depositing
   * into the account. All account balances are maintained in USD.
   * 
   * <p>Supported currencies include USD, EUR, GBP, JPY, CAD, AUD, CHF, and CNY.
   * Exchange rates are managed by the internal {@link CurrencyConverter}.
   * For USD deposits, the amount is deposited directly without conversion overhead.
   *
   * @param accountNumber The account number to deposit funds into
   * @param amount The amount to deposit in the specified currency
   * @param currency The currency of the deposit amount
   * @throws AccountNotFoundException if the account is not found or is closed
   * @throws IllegalArgumentException if the amount is negative
   * @throws IllegalArgumentException if the currency is null
   * @throws UnsupportedOperationException if the currency is not supported
   * 
   * @see Currency
   * @see CurrencyConverter
   */
  public void depositFunds(String accountNumber, double amount, Currency currency) {
    CheckingAccount account = getAccountOrThrow(accountNumber);
    double usdAmount = currencyConverter.convertToUsd(amount, currency);
    account.deposit(usdAmount);
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
   * Withdraws funds from an account.
   *
   * @param accountNumber
   * @param amount
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
