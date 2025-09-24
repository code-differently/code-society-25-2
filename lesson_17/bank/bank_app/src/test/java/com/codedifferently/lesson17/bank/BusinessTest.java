package com.codedifferently.lesson17.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BusinessTest {

  private Business business;
  private UUID businessId;
  private String businessName;
  private String taxId;

  @BeforeEach
  void setUp() {
    businessId = UUID.randomUUID();
    businessName = "Test Business LLC";
    taxId = "12-3456789";
    business = new Business(businessId, businessName, taxId);
  }

  @Test
  void testConstructor() {
    // Test that constructor properly initializes all fields
    assertEquals(businessId, business.getId());
    assertEquals(businessName, business.getName());
    assertEquals(businessName, business.getBusinessName());
    assertEquals(taxId, business.getTaxId());
    assertTrue(business.getAccounts().isEmpty());
    assertTrue(business.getSavingsAccounts().isEmpty());
  }

  @Test
  void testGetId() {
    assertEquals(businessId, business.getId());
  }

  @Test
  void testGetName() {
    assertEquals(businessName, business.getName());
  }

  @Test
  void testGetBusinessName() {
    assertEquals(businessName, business.getBusinessName());
  }

  @Test
  void testGetTaxId() {
    assertEquals(taxId, business.getTaxId());
  }

  @Test
  void testAddAccount() {
    Set<AccountOwner> owners = new HashSet<>();
    owners.add(business);
    CheckingAccount account = new CheckingAccount(UUID.randomUUID().toString(), owners, 100.0);

    business.addAccount(account);

    assertTrue(business.getAccounts().contains(account));
    assertEquals(1, business.getAccounts().size());
  }

  @Test
  void testAddSavingsAccount() {
    Set<AccountOwner> owners = new HashSet<>();
    owners.add(business);
    SavingAccount savingsAccount = new SavingAccount(UUID.randomUUID().toString(), owners, 100.0);

    business.addSavingsAccount(savingsAccount);

    assertTrue(business.getSavingsAccounts().contains(savingsAccount));
    assertEquals(1, business.getSavingsAccounts().size());
  }

  @Test
  void testGetAccounts() {
    Set<CheckingAccount> accounts = business.getAccounts();
    assertNotNull(accounts);
    assertTrue(accounts.isEmpty());

    // Test that adding an account shows up in getAccounts
    Set<AccountOwner> owners = new HashSet<>();
    owners.add(business);
    CheckingAccount account = new CheckingAccount(UUID.randomUUID().toString(), owners, 100.0);
    business.addAccount(account);

    assertEquals(1, business.getAccounts().size());
    assertTrue(business.getAccounts().contains(account));
  }

  @Test
  void testGetSavingsAccounts() {
    Set<SavingAccount> savingsAccounts = business.getSavingsAccounts();
    assertNotNull(savingsAccounts);
    assertTrue(savingsAccounts.isEmpty());

    // Test that adding a savings account shows up in getSavingsAccounts
    Set<AccountOwner> owners = new HashSet<>();
    owners.add(business);
    SavingAccount savingsAccount = new SavingAccount(UUID.randomUUID().toString(), owners, 100.0);
    business.addSavingsAccount(savingsAccount);

    assertEquals(1, business.getSavingsAccounts().size());
    assertTrue(business.getSavingsAccounts().contains(savingsAccount));
  }

  @Test
  void testEquals() {
    // Test equality with same id
    Business sameBusiness = new Business(businessId, "Different Name", "Different Tax ID");
    assertTrue(business.equals(sameBusiness));

    // Test inequality with different id
    Business differentBusiness = new Business(UUID.randomUUID(), businessName, taxId);
    assertFalse(business.equals(differentBusiness));

    // Test inequality with null
    assertNotEquals(business, null);

    // Test inequality with different type
    assertNotEquals(business, "Not a business");

    // Test reflexivity
    assertTrue(business.equals(business));
  }

  @Test
  void testHashCode() {
    assertEquals(businessId.hashCode(), business.hashCode());

    // Test that equal objects have equal hash codes
    Business sameBusiness = new Business(businessId, "Different Name", "Different Tax ID");
    assertEquals(business.hashCode(), sameBusiness.hashCode());
  }

  @Test
  void testToString() {
    String expected =
        "Business{id="
            + businessId
            + ", businessName='"
            + businessName
            + "', taxId='"
            + taxId
            + "'}";
    assertEquals(expected, business.toString());
  }

  @Test
  void testAccountOwnerInterface() {
    // Test that Business properly implements AccountOwner interface
    assertTrue(business instanceof AccountOwner);

    // Test interface methods work correctly
    assertEquals(businessId, business.getId());
    assertEquals(businessName, business.getName());

    Set<AccountOwner> owners = new HashSet<>();
    owners.add(business);
    CheckingAccount checkingAccount =
        new CheckingAccount(UUID.randomUUID().toString(), owners, 100.0);
    SavingAccount savingAccount = new SavingAccount(UUID.randomUUID().toString(), owners, 100.0);

    business.addAccount(checkingAccount);
    business.addSavingsAccount(savingAccount);

    assertEquals(1, business.getAccounts().size());
    assertEquals(1, business.getSavingsAccounts().size());
  }
}
