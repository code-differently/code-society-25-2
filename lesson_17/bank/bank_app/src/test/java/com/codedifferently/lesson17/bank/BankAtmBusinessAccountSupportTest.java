package com.codedifferently.lesson17.bank;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.Test;

/** Test to demonstrate that BankAtm fully supports BusinessCheckingAccount */
class BankAtmBusinessAccountSupportTest {

  @Test
  void testBankAtmSupportsBusinessCheckingAccount() {
    // Arrange
    BankAtm atm = new BankAtm();

    // Create business customer and regular customer
    BusinessCustomer businessCustomer =
        new BusinessCustomer(UUID.randomUUID(), "Tech Solutions LLC", "12-3456789");
    Customer regularCustomer = new Customer(UUID.randomUUID(), "John Manager");

    // Create business checking account with mixed owners
    Set<Customer> owners = Set.of(businessCustomer, regularCustomer);
    BusinessCheckingAccount businessAccount =
        new BusinessCheckingAccount("BIZ-987654321", owners, 5000.0);

    // Add accounts to customer objects (required for findAccountsByCustomerId)
    businessCustomer.addAccount(businessAccount);
    regularCustomer.addAccount(businessAccount);

    // Act - Add business account to ATM
    atm.addAccount(businessAccount);

    // Assert - Verify ATM can find accounts by both customer types
    Set<CheckingAccount> businessCustomerAccounts =
        atm.findAccountsByCustomerId(businessCustomer.getId());
    Set<CheckingAccount> regularCustomerAccounts =
        atm.findAccountsByCustomerId(regularCustomer.getId());

    assertThat(businessCustomerAccounts).containsOnly(businessAccount);
    assertThat(regularCustomerAccounts).containsOnly(businessAccount);

    // Test deposit operations work with business account
    atm.depositFunds("BIZ-987654321", 1000.0, Currency.USD);
    assertThat(businessAccount.getBalance()).isEqualTo(6000.0);

    // Test multi-currency deposits
    atm.depositFunds("BIZ-987654321", 100.0, Currency.EUR); // Should convert to 108 USD
    assertThat(businessAccount.getBalance()).isEqualTo(6108.0);

    // Test withdrawals
    atm.withdrawFunds("BIZ-987654321", 500.0);
    assertThat(businessAccount.getBalance()).isEqualTo(5608.0);

    System.out.println("✅ BankAtm fully supports BusinessCheckingAccount!");
    System.out.println("   - Can add business accounts");
    System.out.println("   - Can find accounts by business customer ID");
    System.out.println("   - Can deposit funds (USD and foreign currencies)");
    System.out.println("   - Can withdraw funds");
    System.out.println("   - All operations work seamlessly through polymorphism");
  }

  @Test
  void testBusinessAccountPolymorphism() {
    // This test proves BusinessCheckingAccount works as CheckingAccount
    BankAtm atm = new BankAtm();

    BusinessCustomer business = new BusinessCustomer(UUID.randomUUID(), "ACME Corp", "98-7654321");
    BusinessCheckingAccount businessAccount =
        new BusinessCheckingAccount("POLY-123456", Set.of(business), 1000.0);
    business.addAccount(businessAccount);

    // The key test: BankAtm.addAccount accepts CheckingAccount parameter
    // BusinessCheckingAccount should work due to inheritance
    CheckingAccount polymorphicReference = businessAccount; // This is the polymorphism
    atm.addAccount(polymorphicReference); // Should work without casting

    // Verify it works
    Set<CheckingAccount> accounts = atm.findAccountsByCustomerId(business.getId());
    assertThat(accounts).containsOnly(businessAccount);

    System.out.println("✅ Polymorphism test passed!");
    System.out.println("   - BusinessCheckingAccount IS-A CheckingAccount");
    System.out.println("   - BankAtm treats them identically");
  }
}
