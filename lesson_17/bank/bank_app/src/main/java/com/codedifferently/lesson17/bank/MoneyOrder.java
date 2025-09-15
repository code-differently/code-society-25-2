package com.codedifferently.lesson17.bank;

public class MoneyOrder extends Check {
    public MoneyOrder(String checkNumber, double amount, Account account) {
        super(checkNumber, amount, account);
        depositFunds(account);
        
    }
}