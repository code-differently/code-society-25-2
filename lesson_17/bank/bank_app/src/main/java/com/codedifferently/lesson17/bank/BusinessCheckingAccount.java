package com.codedifferently.lesson17.bank;
import java.util.Set;

public class BusinessCheckingAccount extends CheckingAccount {

    public BusinessCheckingAccount(String accountNumber, Set<Customer> owners) {
        super(accountNumber, owners);
        validateBusinessOwners();
    }

    public BusinessCheckingAccount(String accountNumber, Set<Customer> owners, double initialBalance) {
        super(accountNumber, owners, initialBalance);
        validateBusinessOwners();
    }

    private void validateBusinessOwners() {
        boolean hasBusinessOwner = getOwners().stream()
            .anyMatch(Customer::isBusiness);

        if (!hasBusinessOwner) {
            throw new IllegalArgumentException("At least one owner must be a business.");
        }
    }

    @Override
    public String getAccountType() {
        return "Business Checking Account";
    }
}
