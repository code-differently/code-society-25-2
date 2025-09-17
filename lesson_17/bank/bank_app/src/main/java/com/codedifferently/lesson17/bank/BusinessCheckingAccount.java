package com.codedifferently.lesson17.bank;

import java.util.Set;

/**
 * Represents a business checking account. At least one owner must be a business.
 */
public class BusinessCheckingAccount extends CheckingAccount {
    public BusinessCheckingAccount(String accountNumber, Set<Customer> owners, double initialBalance) {
        super(accountNumber, owners, initialBalance);
        if (owners.stream().noneMatch(Customer::isBusiness)) {
            throw new IllegalArgumentException("At least one owner must be a business");
        }
    }
}
