package com.codedifferently.lesson17.bank;

import java.util.Set;

/** Represents a savings account that doesn't allow check operations. */
public class SavingsAccount extends Account {

    /**
     * Creates a new savings account.
     *
     * @param accountNumber The account number.
     * @param customers The owners of the account.
     * @param balance The initial balance of the account.
     */
    public SavingsAccount(String accountNumber, Set<Customer> customers, Double balance) {
        super(accountNumber, customers, balance, false); // false means not closed
    }
    
    @Override
    public String toString() {
        return "SavingsAccount{"
            + "accountNumber='"
            + getAccountNumber()
            + '\''
            + ", balance="
            + getBalance()
            + ", isActive="
            + !isClosed()
            + '}';
    }
}
