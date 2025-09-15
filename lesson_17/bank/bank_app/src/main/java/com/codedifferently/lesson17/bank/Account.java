package com.codedifferently.lesson17.bank;

import java.util.Set;


public class Account {
    private String accountNumber;
    private Set<Customer> customers;
    private Double balance;
    private Boolean isActive;
    private Boolean isClosed;

    //Constructors
    public Account(String account, Set<Customer> customers, Double balance, Boolean closed) {
        this.accountNumber = account;
        this.customers = customers;
        this.balance = balance;
        this.isActive = !closed; // active is opposite of closed
        this.isClosed = closed;
    }

    //Getters and Setters
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Set<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    // Banking Operations
    public void deposit(Double amount) {
        if (amount > 0 && !isClosed) {
            this.balance += amount;
        }
    }

    public void withdraw(Double amount) {
        if (amount > 0 && amount <= balance && !isClosed) {
            this.balance -= amount;
        }
    }
    public void close() {
        this.isClosed = true;
    }

    public boolean isClosed() {
        return isClosed;
    }
    
    public void setClosed(Boolean closed) {
        this.isClosed = closed;
    }
}