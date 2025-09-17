package com.codedifferently.lesson17.bank;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AuditLogTest {
    private AuditLog auditLog;
    private UUID customerIdOne;
    private UUID customerIdTwo;

    @BeforeEach
    void setUp(){
        auditLog = new AuditLog();
        customerIdOne = UUID.randomUUID();
        customerIdTwo = UUID.randomUUID();
    }

    @Test
    void testAddToList(){
        // Arrange
        String logEntry1 = "Customer " + customerIdOne + " deposited $100.00";
        String logEntry2 = "Customer " + customerIdTwo + " withdrew $50.00";

        // Act
        auditLog.addToList(logEntry1);
        auditLog.addToList(logEntry2);

        // Assert
        assert(auditLog.getLogSize() == 2);
        assert(auditLog.getLogEntries().contains(logEntry1));
        assert(auditLog.getLogEntries().contains(logEntry2));
    }
}
