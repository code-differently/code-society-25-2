package com.codedifferently.lesson17.bank;

/**
 * Represents a money order, which withdraws funds from a source account immediately.
 */
public class MoneyOrder {
    private final String orderNumber;
    private final double amount;
    private final CheckingAccount sourceAccount;
    private boolean isProcessed = false;

    public MoneyOrder(String orderNumber, double amount, CheckingAccount sourceAccount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Money order amount must be positive");
        }
        this.orderNumber = orderNumber;
        this.amount = amount;
        this.sourceAccount = sourceAccount;
        process();
    }

    private void process() {
        sourceAccount.withdraw(amount);
        isProcessed = true;
    }

    public boolean isProcessed() {
        return isProcessed;
    }

    public double getAmount() {
        return amount;
    }
}
