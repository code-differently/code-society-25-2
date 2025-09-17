package com.codedifferently.lesson17.bank;

import java.util.HashSet;

public class BusinessCheckingAccount extends CheckingAccount {
    private String businessName;

    public BusinessCheckingAccount(String accountNumber, String businessName) {
        super(accountNumber, new HashSet<>(), 0.0);
        
        // Validate business name
        if (businessName == null || businessName.trim().isEmpty()) {
            throw new IllegalArgumentException("Business name cannot be null or empty");
        }
        
        this.businessName = businessName.trim();
    }

    // Alternative constructor that accepts a business customer
    public BusinessCheckingAccount(String accountNumber, String businessName, BusinessCustomer businessCustomer) {
        super(accountNumber, new HashSet<>(), 0.0);
        
        if (businessName == null || businessName.trim().isEmpty()) {
            throw new IllegalArgumentException("Business name cannot be null or empty");
        }
        if (businessCustomer == null) {
            throw new IllegalArgumentException("Business customer cannot be null");
        }
        
        this.businessName = businessName.trim();
        addCustomer(businessCustomer);
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        if (businessName == null || businessName.trim().isEmpty()) {
            throw new IllegalArgumentException("Business name cannot be null or empty");
        }
        this.businessName = businessName.trim();
    }

    // Check if this account has any business customers
    public boolean hasBusinessCustomers() {
        return getCustomers().stream()
            .anyMatch(customer -> customer instanceof BusinessCustomer);
    }

    // Get the business customer (assuming one primary business customer)
    public BusinessCustomer getBusinessCustomer() {
        return getCustomers().stream()
            .filter(customer -> customer instanceof BusinessCustomer)
            .map(customer -> (BusinessCustomer) customer)
            .findFirst()
            .orElse(null);
    }

    // Override to ensure business accounts only accept business customers
    public void addCustomer(Customer customer) {
        if (customer instanceof BusinessCustomer) {
            getCustomers().add(customer);
        } else {
            throw new IllegalArgumentException("Business checking accounts can only have business customers");
        }
    }

    @Override
    public String toString() {
        return String.format("BusinessCheckingAccount{accountNumber='%s', businessName='%s', balance=%.2f, customers=%d}", 
            getAccountNumber(), businessName, getBalance(), getCustomers().size());
    }
}