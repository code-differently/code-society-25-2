package com.codedifferently.lesson17.bank;

import java.util.Set;
import java.util.UUID;


public interface AccountOwner {
  
  
  UUID getId();
  
 
  String getName();
  
 
  void addAccount(CheckingAccount account);
  
 
  void addSavingsAccount(SavingAccount account);
  
 
  Set<CheckingAccount> getAccounts();
  
 
  Set<SavingAccount> getSavingsAccounts();
}