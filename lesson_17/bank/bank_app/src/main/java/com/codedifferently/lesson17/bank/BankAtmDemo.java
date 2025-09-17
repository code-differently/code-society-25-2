package com.codedifferently.lesson17.bank;

import java.util.Set;
import java.util.UUID;

/** Demonstration class showing all the enhanced ATM features. */
public class BankAtmDemo {

  public static void main(String[] args) {
    System.out.println("=== Bank ATM Enhancement Demo ===\n");

    // Create BankAtm instance
    BankAtm bankAtm = new BankAtm();

    // 1. Create different types of customers
    Customer regularCustomer = new Customer(UUID.randomUUID(), "John Doe");
    BusinessCustomer businessCustomer = new BusinessCustomer(
        UUID.randomUUID(), "Acme Corporation", "REG123456");

    // 2. Create different types of accounts
    CheckingAccount checkingAccount = new CheckingAccount(
        "CHK001", Set.of(regularCustomer), 1000.0);
    
    SavingsAccount savingsAccount = new SavingsAccount(
        "SAV001", Set.of(regularCustomer), 2000.0);
    
    BusinessCheckingAccount businessAccount = new BusinessCheckingAccount(
        "BIZ001", Set.of(businessCustomer, regularCustomer), 10000.0);

    // Add accounts to bank
    bankAtm.addAccount(checkingAccount);
    bankAtm.addAccount(savingsAccount);
    bankAtm.addAccount(businessAccount);

    System.out.println("Created accounts:");
    System.out.println("- Checking: $" + checkingAccount.getBalance());
    System.out.println("- Savings: $" + savingsAccount.getBalance());
    System.out.println("- Business: $" + businessAccount.getBalance());
    System.out.println();

    // 3. Demonstrate multi-currency deposits
    System.out.println("=== Multi-Currency Deposits ===");
    bankAtm.depositFunds("CHK001", 100.0, Currency.EUR); // Convert EUR to USD
    bankAtm.depositFunds("SAV001", 75.0, Currency.GBP);  // Convert GBP to USD
    
    System.out.println("After currency deposits:");
    System.out.println("- Checking: $" + String.format("%.2f", checkingAccount.getBalance()));
    System.out.println("- Savings: $" + String.format("%.2f", savingsAccount.getBalance()));
    System.out.println();

    // 4. Demonstrate money order
    System.out.println("=== Money Order Demo ===");
    double businessBalanceBefore = businessAccount.getBalance();
    
    MoneyOrder moneyOrder = new MoneyOrder("MO001", 500.0, businessAccount);
    System.out.println("Created money order for $500 from business account");
    System.out.println(
        "Business account after money order creation: $" + businessAccount.getBalance());
    
    bankAtm.depositFunds("CHK001", moneyOrder);
    System.out.println("Deposited money order into checking account");
    System.out.println("Checking account balance: $" + checkingAccount.getBalance());
    System.out.println();

    // 5. Demonstrate traditional check operation
    System.out.println("=== Traditional Check Demo ===");
    Check check = new Check("CHK001001", 200.0, checkingAccount);
    bankAtm.depositFunds("SAV001", check);
    
    System.out.println("Deposited $200 check from checking to savings");
    System.out.println("Checking balance: $" + checkingAccount.getBalance());
    System.out.println(
        "Savings balance: $" + String.format("%.2f", savingsAccount.getBalance()));
    System.out.println();

    // 6. Show audit trail
    System.out.println("=== Audit Trail ===");
    var allTransactions = bankAtm.getAuditLog().getAllTransactions();
    System.out.println("Total transactions recorded: " + allTransactions.size());
    
    System.out.println("\nChecking account transactions:");
    var checkingTransactions = bankAtm.getAuditLog().getTransactionsForAccount("CHK001");
    for (var transaction : checkingTransactions) {
      System.out.println(
          "- "
              + transaction.getType()
              + ": $"
              + String.format("%.2f", transaction.getAmount())
              + " - "
              + transaction.getDescription());
    }

    System.out.println("\nSavings account transactions:");
    var savingsTransactions = bankAtm.getAuditLog().getTransactionsForAccount("SAV001");
    for (var transaction : savingsTransactions) {
      System.out.println(
          "- "
              + transaction.getType()
              + ": $"
              + String.format("%.2f", transaction.getAmount())
              + " - "
              + transaction.getDescription());
    }

    System.out.println("\n=== Demo Complete ===");
    System.out.println("All enhanced features demonstrated successfully!");
  }
}
