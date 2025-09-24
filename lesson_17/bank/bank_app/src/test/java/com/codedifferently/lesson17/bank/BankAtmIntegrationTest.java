package com.codedifferently.lesson17.bank;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Integration tests for BankAtm to verify support for different account types. These tests focus on
 * validating that SavingsAccount and BusinessCheckingAccount are properly integrated with the
 * BankAtm system.
 */
class BankAtmIntegrationTest {

  private BankAtm bankAtm;

  @BeforeEach
  void setUp() {
    bankAtm = new BankAtm();
  }

  @Test
  void testBankAtmSupportsAllAccountTypes() {
    // Arrange - Create different types of accounts
    Customer regularCustomer = new Customer(UUID.randomUUID(), "John Doe");
    BusinessCustomer businessCustomer = new BusinessCustomer(UUID.randomUUID(), "ABC Corp");

    CheckingAccount checkingAccount =
        new CheckingAccount("CHK-001", Set.of(regularCustomer), 1000.0);
    SavingsAccount savingsAccount = new SavingsAccount("SAV-001", Set.of(regularCustomer), 5000.0);
    BusinessCheckingAccount businessAccount =
        new BusinessCheckingAccount("BIZ-001", "ABC Corp", businessCustomer);
    businessAccount.deposit(10000.0);

    // Link accounts to customers
    regularCustomer.addAccount(checkingAccount);
    regularCustomer.addAccount(savingsAccount);
    businessCustomer.addAccount(businessAccount);

    // Act - Add all account types to the ATM
    bankAtm.addAccount(checkingAccount);
    bankAtm.addAccount(savingsAccount);
    bankAtm.addAccount(businessAccount);

    // Assert - Verify all accounts are accessible
    Set<Account> regularCustomerAccounts =
        bankAtm.findAccountsByCustomerId(regularCustomer.getId());
    Set<Account> businessCustomerAccounts =
        bankAtm.findAccountsByCustomerId(businessCustomer.getId());

    assertThat(regularCustomerAccounts).containsExactlyInAnyOrder(checkingAccount, savingsAccount);
    assertThat(businessCustomerAccounts).containsOnly(businessAccount);
  }

  @Test
  void testCashOperationsWorkWithAllAccountTypes() {
    // Arrange
    Customer customer = new Customer(UUID.randomUUID(), "Multi Account Owner");
    BusinessCustomer businessCustomer = new BusinessCustomer(UUID.randomUUID(), "Business Owner");

    CheckingAccount checkingAccount = new CheckingAccount("CHK-CASH", Set.of(customer), 1000.0);
    SavingsAccount savingsAccount = new SavingsAccount("SAV-CASH", Set.of(customer), 2000.0);
    BusinessCheckingAccount businessAccount =
        new BusinessCheckingAccount("BIZ-CASH", "Test Business", businessCustomer);
    businessAccount.deposit(5000.0);

    customer.addAccount(checkingAccount);
    customer.addAccount(savingsAccount);
    businessCustomer.addAccount(businessAccount);

    bankAtm.addAccount(checkingAccount);
    bankAtm.addAccount(savingsAccount);
    bankAtm.addAccount(businessAccount);

    // Act & Assert - Test cash deposits
    bankAtm.depositFunds("CHK-CASH", 500.0);
    bankAtm.depositFunds("SAV-CASH", 1000.0);
    bankAtm.depositFunds("BIZ-CASH", 2000.0);

    assertThat(checkingAccount.getBalance()).isEqualTo(1500.0);
    assertThat(savingsAccount.getBalance()).isEqualTo(3000.0);
    assertThat(businessAccount.getBalance()).isEqualTo(7000.0);

    // Act & Assert - Test cash withdrawals
    bankAtm.withdrawFunds("CHK-CASH", 200.0);
    bankAtm.withdrawFunds("SAV-CASH", 500.0);
    bankAtm.withdrawFunds("BIZ-CASH", 1000.0);

    assertThat(checkingAccount.getBalance()).isEqualTo(1300.0);
    assertThat(savingsAccount.getBalance()).isEqualTo(2500.0);
    assertThat(businessAccount.getBalance()).isEqualTo(6000.0);
  }

  @Test
  void testCheckOperationsWorkWithAllAccountTypes() {
    // Arrange - Create accounts of different types
    Customer individual = new Customer(UUID.randomUUID(), "Individual Customer");
    BusinessCustomer business = new BusinessCustomer(UUID.randomUUID(), "Business Customer");

    CheckingAccount sourceChecking = new CheckingAccount("CHK-SOURCE", Set.of(individual), 3000.0);
    SavingsAccount targetSavings = new SavingsAccount("SAV-TARGET", Set.of(individual), 1000.0);
    BusinessCheckingAccount targetBusiness =
        new BusinessCheckingAccount("BIZ-TARGET", "Target Business", business);
    targetBusiness.deposit(5000.0);

    individual.addAccount(sourceChecking);
    individual.addAccount(targetSavings);
    business.addAccount(targetBusiness);

    bankAtm.addAccount(sourceChecking);
    bankAtm.addAccount(targetSavings);
    bankAtm.addAccount(targetBusiness);

    // Act & Assert - Check from checking to savings
    Check checkToSavings = new Check("SAV-TARGET", 800.0, sourceChecking);
    bankAtm.depositFunds("SAV-TARGET", checkToSavings);

    assertThat(sourceChecking.getBalance()).isEqualTo(2200.0); // 3000 - 800
    assertThat(targetSavings.getBalance()).isEqualTo(1800.0); // 1000 + 800

    // Act & Assert - Check from checking to business account
    Check checkToBusiness = new Check("BIZ-TARGET", 1200.0, sourceChecking);
    bankAtm.depositFunds("BIZ-TARGET", checkToBusiness);

    assertThat(sourceChecking.getBalance()).isEqualTo(1000.0); // 2200 - 1200
    assertThat(targetBusiness.getBalance()).isEqualTo(6200.0); // 5000 + 1200
  }

  @Test
  void testBusinessAccountSpecificFeatures() {
    // Arrange - Business account with business customer
    BusinessCustomer businessOwner = new BusinessCustomer(UUID.randomUUID(), "Tech Startup LLC");
    BusinessCheckingAccount businessAccount =
        new BusinessCheckingAccount("BIZ-STARTUP", "Tech Startup LLC", businessOwner);
    businessAccount.deposit(50000.0);
    businessOwner.addAccount(businessAccount);

    Customer regularCustomer = new Customer(UUID.randomUUID(), "Regular Customer");
    CheckingAccount regularAccount =
        new CheckingAccount("CHK-REGULAR", Set.of(regularCustomer), 2000.0);
    regularCustomer.addAccount(regularAccount);

    bankAtm.addAccount(businessAccount);
    bankAtm.addAccount(regularAccount);

    // Act & Assert - Business account can perform all standard operations
    bankAtm.depositFunds("BIZ-STARTUP", 10000.0);
    assertThat(businessAccount.getBalance()).isEqualTo(60000.0);

    bankAtm.withdrawFunds("BIZ-STARTUP", 5000.0);
    assertThat(businessAccount.getBalance()).isEqualTo(55000.0);

    // Business account can write checks
    Check businessCheck = new Check("CHK-REGULAR", 3000.0, businessAccount);
    bankAtm.depositFunds("CHK-REGULAR", businessCheck);

    assertThat(businessAccount.getBalance()).isEqualTo(52000.0); // 55000 - 3000
    assertThat(regularAccount.getBalance()).isEqualTo(5000.0); // 2000 + 3000
  }

  @Test
  void testSavingsAccountSpecificFeatures() {
    // Arrange - Savings account with regular customer
    Customer customer = new Customer(UUID.randomUUID(), "Savings Customer");
    SavingsAccount savingsAccount = new SavingsAccount("SAV-SPECIAL", Set.of(customer), 10000.0);
    CheckingAccount checkingAccount = new CheckingAccount("CHK-SOURCE", Set.of(customer), 3000.0);

    customer.addAccount(savingsAccount);
    customer.addAccount(checkingAccount);
    bankAtm.addAccount(savingsAccount);
    bankAtm.addAccount(checkingAccount);

    // Act & Assert - Savings account can receive deposits
    bankAtm.depositFunds("SAV-SPECIAL", 2000.0);
    assertThat(savingsAccount.getBalance()).isEqualTo(12000.0);

    // Savings account can have withdrawals
    bankAtm.withdrawFunds("SAV-SPECIAL", 1500.0);
    assertThat(savingsAccount.getBalance()).isEqualTo(10500.0);

    // Savings account can receive check deposits
    Check depositCheck = new Check("SAV-SPECIAL", 1000.0, checkingAccount);
    bankAtm.depositFunds("SAV-SPECIAL", depositCheck);

    assertThat(savingsAccount.getBalance()).isEqualTo(11500.0); // 10500 + 1000
    assertThat(checkingAccount.getBalance()).isEqualTo(2000.0); // 3000 - 1000
  }

  @Test
  void testComplexScenarioWithAllAccountTypes() {
    // Arrange - Create a complex scenario with multiple customers and account types
    Customer alice = new Customer(UUID.randomUUID(), "Alice");
    BusinessCustomer companyXYZ = new BusinessCustomer(UUID.randomUUID(), "Company XYZ");
    Customer bob = new Customer(UUID.randomUUID(), "Bob");

    // Alice has checking and savings
    CheckingAccount aliceChecking = new CheckingAccount("CHK-ALICE", Set.of(alice), 5000.0);
    SavingsAccount aliceSavings = new SavingsAccount("SAV-ALICE", Set.of(alice), 15000.0);

    // Company has business account
    BusinessCheckingAccount companyAccount =
        new BusinessCheckingAccount("BIZ-XYZ", "Company XYZ", companyXYZ);
    companyAccount.deposit(100000.0);

    // Bob has checking
    CheckingAccount bobChecking = new CheckingAccount("CHK-BOB", Set.of(bob), 3000.0);

    alice.addAccount(aliceChecking);
    alice.addAccount(aliceSavings);
    companyXYZ.addAccount(companyAccount);
    bob.addAccount(bobChecking);

    bankAtm.addAccount(aliceChecking);
    bankAtm.addAccount(aliceSavings);
    bankAtm.addAccount(companyAccount);
    bankAtm.addAccount(bobChecking);

    // Act - Simulate a complex transaction scenario
    // 1. Company pays Alice via check
    Check salaryCheck = new Check("CHK-ALICE", 5000.0, companyAccount);
    bankAtm.depositFunds("CHK-ALICE", salaryCheck);

    // 2. Alice transfers money to savings
    Check savingsTransfer = new Check("SAV-ALICE", 3000.0, aliceChecking);
    bankAtm.depositFunds("SAV-ALICE", savingsTransfer);

    // 3. Alice pays Bob
    Check paymentToBob = new Check("CHK-BOB", 1500.0, aliceChecking);
    bankAtm.depositFunds("CHK-BOB", paymentToBob);

    // 4. Bob deposits cash
    bankAtm.depositFunds("CHK-BOB", 500.0);

    // Assert - Verify all balances
    assertThat(companyAccount.getBalance()).isEqualTo(95000.0); // 100000 - 5000
    assertThat(aliceChecking.getBalance()).isEqualTo(5500.0); // 5000 + 5000 - 3000 - 1500
    assertThat(aliceSavings.getBalance()).isEqualTo(18000.0); // 15000 + 3000
    assertThat(bobChecking.getBalance()).isEqualTo(5000.0); // 3000 + 1500 + 500
  }
}
