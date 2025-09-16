package com.codedifferently.lesson17.bank;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AuditLogTest {

  private AuditLog classUnderTest;
  private UUID customerId;

  @BeforeEach
  void setUp() {
    classUnderTest = new AuditLog();
    customerId = UUID.randomUUID();
  }

  @Test
  void testLogTransaction() {
    classUnderTest.logTransaction(customerId, "Deposit $100");
    List<String> entries = classUnderTest.getEntries();
    assertThat(entries).hasSize(1);
    assertThat(entries.get(0)).contains("Deposit $100");
  }

  @Test
  void testMultipleTransactions() {
    classUnderTest.logTransaction(customerId, "Deposit $50");
    classUnderTest.logTransaction(customerId, "Withdraw $20");
    List<String> entries = classUnderTest.getEntries();
    assertThat(entries).hasSize(2);
    assertThat(entries.get(0)).contains("Deposit $50");
    assertThat(entries.get(1)).contains("Withdraw $20");
  }

  @Test
  void testAuditLogEmptyInitially() {
    assertThat(classUnderTest.getEntries()).isEmpty();
  }
}
