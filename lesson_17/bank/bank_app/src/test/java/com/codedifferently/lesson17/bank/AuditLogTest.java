package com.codedifferently.lesson17.bank;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AuditLogTest {

    private AuditLog auditLog;

    @BeforeEach
    void setUp() {
        auditLog = new AuditLog();
    }

    @Test
    void testRecordDebit() {
        // Act
        auditLog.recordDebit("123456789", 100.0, "Cash withdrawal");

        // Assert
        List<AuditLog.AuditEntry> entries = auditLog.getEntries();
        assertThat(entries).hasSize(1);
        
        AuditLog.AuditEntry entry = entries.get(0);
        assertThat(entry.getAccountNumber()).isEqualTo("123456789");
        assertThat(entry.getAmount()).isEqualTo(100.0);
        assertThat(entry.getType()).isEqualTo(AuditLog.TransactionType.DEBIT);
        assertThat(entry.getDescription()).isEqualTo("Cash withdrawal");
        assertThat(entry.getTimestamp()).isBeforeOrEqualTo(LocalDateTime.now());
    }

    @Test
    void testRecordCredit() {
        // Act
        auditLog.recordCredit("987654321", 250.0, "Cash deposit");

        // Assert
        List<AuditLog.AuditEntry> entries = auditLog.getEntries();
        assertThat(entries).hasSize(1);
        
        AuditLog.AuditEntry entry = entries.get(0);
        assertThat(entry.getAccountNumber()).isEqualTo("987654321");
        assertThat(entry.getAmount()).isEqualTo(250.0);
        assertThat(entry.getType()).isEqualTo(AuditLog.TransactionType.CREDIT);
        assertThat(entry.getDescription()).isEqualTo("Cash deposit");
        assertThat(entry.getTimestamp()).isBeforeOrEqualTo(LocalDateTime.now());
    }

    @Test
    void testGetEntriesForAccount() {
        // Arrange
        auditLog.recordDebit("123456789", 100.0, "Cash withdrawal");
        auditLog.recordCredit("987654321", 250.0, "Cash deposit");
        auditLog.recordDebit("123456789", 50.0, "Check payment");

        // Act
        List<AuditLog.AuditEntry> entriesForAccount123 = auditLog.getEntriesForAccount("123456789");
        List<AuditLog.AuditEntry> entriesForAccount987 = auditLog.getEntriesForAccount("987654321");

        // Assert
        assertThat(entriesForAccount123).hasSize(2);
        assertThat(entriesForAccount987).hasSize(1);
        
        assertThat(entriesForAccount123.get(0).getAccountNumber()).isEqualTo("123456789");
        assertThat(entriesForAccount123.get(1).getAccountNumber()).isEqualTo("123456789");
        assertThat(entriesForAccount987.get(0).getAccountNumber()).isEqualTo("987654321");
    }

    @Test
    void testGetEntriesForAccount_NoEntries() {
        // Act
        List<AuditLog.AuditEntry> entries = auditLog.getEntriesForAccount("nonexistent");

        // Assert
        assertThat(entries).isEmpty();
    }

    @Test
    void testGetEntries_MultipleTransactions() {
        // Arrange
        auditLog.recordDebit("123456789", 100.0, "Cash withdrawal");
        auditLog.recordCredit("987654321", 250.0, "Cash deposit");
        auditLog.recordDebit("555555555", 75.0, "Check payment");

        // Act
        List<AuditLog.AuditEntry> allEntries = auditLog.getEntries();

        // Assert
        assertThat(allEntries).hasSize(3);
        assertThat(allEntries.get(0).getAccountNumber()).isEqualTo("123456789");
        assertThat(allEntries.get(1).getAccountNumber()).isEqualTo("987654321");
        assertThat(allEntries.get(2).getAccountNumber()).isEqualTo("555555555");
    }

    @Test
    void testClear() {
        // Arrange
        auditLog.recordDebit("123456789", 100.0, "Cash withdrawal");
        auditLog.recordCredit("987654321", 250.0, "Cash deposit");
        
        // Act
        auditLog.clear();

        // Assert
        assertThat(auditLog.getEntries()).isEmpty();
    }

    @Test
    void testAuditEntry_ToString() {
        // Arrange
        auditLog.recordDebit("123456789", 100.0, "Cash withdrawal");
        
        // Act
        AuditLog.AuditEntry entry = auditLog.getEntries().get(0);
        String toString = entry.toString();

        // Assert
        assertThat(toString).contains("123456789");
        assertThat(toString).contains("DEBIT");
        assertThat(toString).contains("$100.00");
        assertThat(toString).contains("Cash withdrawal");
    }

    @Test
    void testGetEntries_ReturnsDefensiveCopy() {
        // Arrange
        auditLog.recordDebit("123456789", 100.0, "Cash withdrawal");
        
        // Act
        List<AuditLog.AuditEntry> entries = auditLog.getEntries();
        entries.clear(); // Try to modify the returned list

        // Assert - original audit log should be unaffected
        assertThat(auditLog.getEntries()).hasSize(1);
    }
}
