package com.codedifferently.lesson17.bank;

import com.codedifferently.lesson17.bank.exceptions.InsufficientFundsException;
import java.util.Set;

public class BusinessCheckingAccount extends CheckingAccount {
    public BusinessCheckingAccount(String accountNumber, Set<Customer> owners, double initialBalance) {
        super(accountNumber, owners, initialBalance);
    }

    @Override
    public String toString() {
        return "BusinessCheckingAccount{"
                + "accountNumber='"
                + accountNumber
                + '\''
                + ", balance="
                + balance
                + ", isActive="
                + isActive
                + '}';
    }
}