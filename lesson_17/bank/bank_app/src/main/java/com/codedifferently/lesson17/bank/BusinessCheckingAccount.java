package com.codedifferently.lesson17.bank;

import java.util.Set;


public class BusinessCheckingAccount extends CheckingAccount {

  
  public BusinessCheckingAccount(String accountNumber, Set<AccountOwner> owners, double initialBalance) {
    super(accountNumber, owners, initialBalance);
    validateBusinessOwnership(owners);
  }

  
  private void validateBusinessOwnership(Set<AccountOwner> owners) {
    boolean hasBusiness = owners.stream()
        .anyMatch(owner -> owner instanceof Business);
    
    if (!hasBusiness) {
      throw new IllegalArgumentException("Business checking account must have at least one business owner");
    }
  }

  @Override
  public String toString() {
    return "BusinessCheckingAccount{"
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