package com.codedifferently.lesson17.bank;
import java.util.Objects;
import java.util.Set;

public class BusinessCheckingAccount extends CheckingAccount {

    public BusinessCheckingAccount(String accountNumber, Set<Customer> owners) {
        super(accountNumber, owners, 0.0);
        validateBusinessOwners();
    }
    
    public BusinessCheckingAccount(
            String accountNumber, Set<Customer> owners, double initialBalance) {
        super(accountNumber, owners, initialBalance);
        validateBusinessOwners();
    }
    
    private void validateBusinessOwners() {
        Set<Customer> owners = getOwners();
        
       
        if (owners == null || owners.isEmpty()) {
            throw new IllegalArgumentException(
                "Business checking account must have at least one owner.");
        }
        
        
        boolean hasBusinessOwner = owners.stream()
            .filter(Objects::nonNull)  
            .anyMatch(owner -> owner.isBusiness());
        
        if (!hasBusinessOwner) {
            throw new IllegalArgumentException(
                "Business checking account requires at least one business customer as owner.");
        }
    }
    
        public String getAccountType() {
            return "Business Checking Account";
        }
    }