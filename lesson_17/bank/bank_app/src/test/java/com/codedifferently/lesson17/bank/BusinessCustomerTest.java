package com.codedifferently.lesson17.bank;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BusinessCustomerTest {

  private BusinessCustomer classUnderTest;
  private UUID businessId;
  private String businessName;
  private String taxId;

  @BeforeEach
  void setUp() {
    businessId = UUID.randomUUID();
    businessName = "ACME Corporation";
    taxId = "12-3456789";
    classUnderTest = new BusinessCustomer(businessId, businessName, taxId);
  }

  @Test
  void getId() {
    assertEquals(businessId, classUnderTest.getId());
  }

  @Test
  void getName() {
    assertEquals(businessName, classUnderTest.getName());
  }

  @Test
  void getBusinessName() {
    assertEquals(businessName, classUnderTest.getBusinessName());
  }

  @Test
  void getTaxId() {
    assertEquals(taxId, classUnderTest.getTaxId());
  }

  @Test
  void isBusiness() {
    assertTrue(classUnderTest.isBusiness());
  }

  @Test
  void toStringTest() {
    String expected = "BusinessCustomer{" +
        "id=" + businessId +
        ", businessName='" + businessName + '\'' +
        ", taxId='" + taxId + '\'' +
        '}';
    assertEquals(expected, classUnderTest.toString());
  }

  @Test
  void equals() {
    BusinessCustomer otherBusiness = new BusinessCustomer(businessId, "Other Name", "Other Tax ID");
    assertEquals(classUnderTest, otherBusiness);
  }

  @Test
  void hashCodeTest() {
    BusinessCustomer otherBusiness = new BusinessCustomer(businessId, "Other Name", "Other Tax ID");
    assertEquals(classUnderTest.hashCode(), otherBusiness.hashCode());
  }

  @Test
  void addAccount() {
    // Arrange
    CheckingAccount account = new CheckingAccount("123456789", java.util.Set.of(classUnderTest), 1000.0);

    // Act
    classUnderTest.addAccount(account);

    // Assert
    assertThat(classUnderTest.getAccounts()).containsOnly(account);
  }
}
