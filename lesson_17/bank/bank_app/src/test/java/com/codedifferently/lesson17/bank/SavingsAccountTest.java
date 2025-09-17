package com.codedifferently.lesson17.bank;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SavingsAccountTest {

  private SavingsAccount classUnderTest;
  private Set<Customer> owners;

  @BeforeEach
  void setUp() {
    owners = new HashSet<>();
    owners.add(new Customer(UUID.randomUUID(), "John Doe"));
    owners.add(new Customer(UUID.randomUUID(), "Jane Smith"));
    classUnderTest = new SavingsAccount("SAV123456789", owners, 100.0);
  }

  @Test
  void testGetAccountNumber() {
    assertEquals("SAV123456789", classUnderTest.getAccountNumber());
  }

  @Test
  void testGetOwners() {
    assertEquals(owners, classUnderTest.getOwners());
  }

  @Test
  void testDeposit() {
    classUnderTest.deposit(50.0);
    assertEquals(150.0, classUnderTest.getBalance());
  }

  @Test
  void testWithdraw() {
    classUnderTest.withdraw(50.0);
    assertEquals(50.0, classUnderTest.getBalance());
  }

  @Test
  void testGetBalance() {
    assertEquals(100.0, classUnderTest.getBalance());
  }

  @Test
  void testCloseAccount() {
    classUnderTest.withdraw(100.0);
    classUnderTest.closeAccount();
    assertTrue(classUnderTest.isClosed());
  }

  @Test
  void testIsClosed() {
    assertFalse(classUnderTest.isClosed());
  }

  @Test
  void testEquals() {
    SavingsAccount otherAccount = new SavingsAccount("SAV123456789", owners, 200.0);
    assertEquals(classUnderTest, otherAccount);
  }

  @Test
  void testHashCode() {
    SavingsAccount otherAccount = new SavingsAccount("SAV123456789", owners, 200.0);
    assertEquals(classUnderTest.hashCode(), otherAccount.hashCode());
  }

  @Test
  void testToString() {
    String expected = "SavingsAccount{accountNumber='SAV123456789', balance=100.0, isActive=true}";
    assertEquals(expected, classUnderTest.toString());
  }
}
