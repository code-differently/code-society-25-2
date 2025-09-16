package com.codedifferently.lesson17.bank;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


public class Business implements AccountOwner {

  private final UUID id;
  private final String businessName;
  private final String taxId;
  private final Set<CheckingAccount> accounts = new HashSet<>();
  private final Set<SavingAccount> savingsAccounts = new HashSet<>();

  
  public Business(UUID id, String businessName, String taxId) {
    this.id = id;
    this.businessName = businessName;
    this.taxId = taxId;
  }

 
  @Override
  public UUID getId() {
    return id;
  }

 
  @Override
  public String getName() {
    return businessName;
  }

  
  public String getBusinessName() {
    return businessName;
  }

  
  public String getTaxId() {
    return taxId;
  }

  
  @Override
  public void addAccount(CheckingAccount account) {
    accounts.add(account);
  }

  
  @Override
  public void addSavingsAccount(SavingAccount account) {
    savingsAccounts.add(account);
  }

  
  @Override
  public Set<CheckingAccount> getAccounts() {
    return accounts;
  }

 
  @Override
  public Set<SavingAccount> getSavingsAccounts() {
    return savingsAccounts;
  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Business other) {
      return id.equals(other.id);
    }
    return false;
  }

  @Override
  public String toString() {
    return "Business{" + "id=" + id + ", businessName='" + businessName + '\'' + ", taxId='" + taxId + '\'' + '}';
  }
}