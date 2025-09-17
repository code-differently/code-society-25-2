package com.codedifferently.lesson17.bank;

import static org.junit.jupiter.api.Assertions.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AccountTest {

    private Account account;
    private Set<Customer> customers;

    @BeforeEach
    void setUp() {
        customers = new HashSet<>();
        customers.add(new Customer(UUID.randomUUID(), "Test Customer"));
        account = new CheckingAccount("123456789", customers, 100.0);
    }

    @Test
    void testGettersAndSetters() {
        assertEquals("123456789", account.getAccountNumber());
        assertEquals(customers, account.getCustomers());
        assertEquals(100.0, account.getBalance());
        assertFalse(account.isClosed());
    }

    @Test
    void testCloseAccount() {
        account.close();
        assertTrue(account.isClosed());
    }
}