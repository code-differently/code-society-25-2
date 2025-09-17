package com.codedifferently.lesson17.bank;

public class MoneyOrder {
    private final String moneyOrderNumber;
    private final String payee;
    private final String sourceAccountNumber;
    private final Double amount;
    private Boolean deposited = false;

    public MoneyOrder(String moneyOrderNumber, String payee, String sourceAccountNumber, Double amount) { 
        if (amount <= 0) {
            throw new IllegalArgumentException("Invalid amount");
        }
        this.moneyOrderNumber = moneyOrderNumber;
        this.payee = payee;
        this.sourceAccountNumber = sourceAccountNumber;
        this.amount = amount;
    }

    public void depositFunds(CheckingAccount account) {
        if (deposited) {
            throw new IllegalStateException("Money order " + moneyOrderNumber + " has already been deposited.");
        }
        account.deposit(amount);
        deposited = true;
    }

    public String getMoneyOrderNumber() {
        return moneyOrderNumber;
    }

    public String getPayee() {
        return payee;
    }

    public String getSourceAccountNumber() {
        return sourceAccountNumber;
    }

    public Double getAmount() {
        return amount;
    }

    public Boolean getDeposited() {
        return deposited;
    }
}