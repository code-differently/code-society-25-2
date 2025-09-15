package com.codedifferently.lesson17.bank;

import com.codedifferently.lesson17.bank.exceptions.InsufficientFundsException;
import java.util.Set;

public class SavingsAccount extends Account {

    public SavingsAccount(String accountNumber, Set<Customer> owners, double initialBalance) {
        super(accountNumber, owners, initialBalance);
    }

    @Override
    public String toString() {
        return "SavingsAccount{"
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
