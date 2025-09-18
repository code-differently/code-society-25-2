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
  
}