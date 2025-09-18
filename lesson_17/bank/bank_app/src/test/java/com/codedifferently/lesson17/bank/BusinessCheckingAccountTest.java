package com.codedifferently.lesson17.bank;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.codedifferently.lesson17.bank.exceptions.InsufficientFundsException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BusinessCheckingAccountTest {

  private BusinessCheckingAccount classUnderTest;
  private Set<Customer> businessOwners;
  private Set<Customer> individualOwners;
  private Customer businessCustomer;
  private Customer individualCustomer;

  @BeforeEach
  void setUp() {
    businessCustomer = new Customer(UUID.randomUUID(), "ABC Corp", true);
    individualCustomer = new Customer(UUID.randomUUID(), "John Doe", false);
    
    businessOwners = new HashSet<>();
    businessOwners.add(businessCustomer);
    businessOwners.add(individualCustomer);
    
    individualOwners = new HashSet<>();
    individualOwners.add(individualCustomer);
    
    classUnderTest = new BusinessCheckingAccount("BIZ123456", businessOwners, 1000.0);
  }

  @Test
  void testBusinessCheckingAccountCreation() {
    assertEquals("BIZ123456", classUnderTest.getAccountNumber());
    assertEquals(1000.0, classUnderTest.getBalance());
    assertEquals(businessOwners, classUnderTest.getOwners());
    assertFalse(classUnderTest.isClosed());
  }

  @Test
  void testBusinessCheckingAccountRequiresBusinessOwner() {
    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> new BusinessCheckingAccount("NOBIZ123", individualOwners, 500.0))
        .withMessage("Business checking account requires at least one business owner");
  }

  @Test
  void testBusinessCheckingAccountWithOnlyBusinessOwner() {
    Set<Customer> onlyBusiness = new HashSet<>();
    onlyBusiness.add(businessCustomer);
    
    BusinessCheckingAccount account = new BusinessCheckingAccount("BIZ789", onlyBusiness, 2000.0);
    
    assertEquals("BIZ789", account.getAccountNumber());
    assertEquals(2000.0, account.getBalance());
    assertEquals(onlyBusiness, account.getOwners());
  }

  @Test
  void testDepositToBusinessAccount() {
    classUnderTest.deposit(500.0);
    assertEquals(1500.0, classUnderTest.getBalance());
  }

   @Test
  void testWithdrawFromBusinessAccount() {
    classUnderTest.withdraw(300.0);
    assertEquals(700.0, classUnderTest.getBalance());
  }

   @Test
  void testWithdrawMoreThanBalance() {
    assertThatExceptionOfType(InsufficientFundsException.class)
        .isThrownBy(() -> classUnderTest.withdraw(1500.0));
  }

   @Test
  void testCloseBusinessAccount() {
    classUnderTest.withdraw(1000.0);
    classUnderTest.closeAccount();
    assertTrue(classUnderTest.isClosed());
  }

  @Test
  void testBusinessCheckingAccountToString() {
    String expected = "BusinessCheckingAccount{accountNumber='BIZ123456', balance=1000.0, isActive=true}";
    assertEquals(expected, classUnderTest.toString());
  }
  
   @Test
  void testBusinessCheckingAccountEquals() {
    BusinessCheckingAccount otherAccount = new BusinessCheckingAccount("BIZ123456", businessOwners, 2000.0);
    assertEquals(classUnderTest, otherAccount);
  }

   @Test
  void testBusinessCheckingAccountInheritance() {
    // Verify it inherits all CheckingAccount functionality
    assertTrue(classUnderTest instanceof CheckingAccount);
  }
}