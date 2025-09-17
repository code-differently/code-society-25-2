package com.codedifferently.lesson17.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;
import org.junit.jupiter.api.Test;

class BusinessCustomerTest {

  @Test
  void testBusinessCustomer() {
    // Arrange
    UUID id = UUID.randomUUID();
    
    // Act
    BusinessCustomer customer = new BusinessCustomer(id, "Acme Corp", "REG123456");

    // Assert
    assertEquals(id, customer.getId());
    assertEquals("Acme Corp", customer.getName());
    assertEquals("REG123456", customer.getBusinessRegistrationNumber());
    assertEquals(true, customer.isBusiness());
  }

  @Test
  void testToString() {
    // Arrange
    UUID id = UUID.randomUUID();
    BusinessCustomer customer = new BusinessCustomer(id, "Acme Corp", "REG123456");

    // Act
    String result = customer.toString();

    // Assert
    String expected =
        "BusinessCustomer{id="
            + id
            + ", name='Acme Corp', businessRegistrationNumber='REG123456'}";
    assertEquals(expected, result);
  }
}
