package com.codedifferently.lesson17.bank;

public class MoneyOrder extends Check {
    public MoneyOrder(String checkNumber, double amount, Account fromAccount, Account toAccount) {
        super(checkNumber, amount, fromAccount);
        depositFunds(toAccount);
        
    }
}