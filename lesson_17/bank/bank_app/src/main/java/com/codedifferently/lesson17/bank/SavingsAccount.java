package com.codedifferently.lesson17.bank;

import java.util.Set;

/** Represents a savings account. */
public class SavingsAccount {
    private final Set<Customer> owners;
    private final String accountNumber;
    private double balance;
    private boolean isActive;

    public SavingsAccount(String accountNumber, Set<Customer> owners, double initialBalance) {
        this.accountNumber = accountNumber;
        this.owners = owners;
        this.balance = initialBalance;
        isActive = true;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Set<Customer> getOwners() {
        return owners;
    }

    public void deposit(double amount) {
        if (isClosed()) {
            throw new IllegalStateException("Cannot deposit to a closed account");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        balance += amount;
    }

    public void withdraw(double amount) {
        if (isClosed()) {
            throw new IllegalStateException("Cannot withdraw from a closed account");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        if (balance < amount) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        balance -= amount;
    }

    public double getBalance() {
        return balance;
    }

    public boolean isClosed() {
        return !isActive;
    }

    public void close() {
        isActive = false;
    }
}
