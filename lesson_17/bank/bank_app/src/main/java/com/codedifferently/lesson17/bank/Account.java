package com.codedifferently.lesson17.bank;

import java.util.Set;

/**
 * Represents a bank account interface.
 * Defines the common operations that all account types must support.
 */
public interface Account {
    
    /**
     * Gets the account number.
     *
     * @return The account number.
     */
    String getAccountNumber();
    
    /**
     * Gets the owners of the account.
     *
     * @return The owners of the account.
     */
    Set<Customer> getOwners();
    
    /**
     * Deposits funds into the account.
     *
     * @param amount The amount to deposit.
     * @throws IllegalArgumentException if amount is not positive.
     * @throws IllegalStateException if account is closed.
     */
    void deposit(double amount);
    
    /**
     * Withdraws funds from the account.
     *
     * @param amount The amount to withdraw.
     * @throws IllegalArgumentException if amount is not positive.
     * @throws IllegalStateException if account is closed.
     * @throws com.codedifferently.lesson17.bank.exceptions.InsufficientFundsException if insufficient funds.
     */
    void withdraw(double amount) throws com.codedifferently.lesson17.bank.exceptions.InsufficientFundsException;
    
    /**
     * Gets the balance of the account.
     *
     * @return The balance of the account.
     */
    double getBalance();
    
    /**
     * Closes the account.
     * 
     * @throws IllegalStateException if account cannot be closed.
     */
    void closeAccount();
    
    /**
     * Checks if the account is closed.
     *
     * @return True if the account is closed, otherwise false.
     */
    boolean isClosed();
}
