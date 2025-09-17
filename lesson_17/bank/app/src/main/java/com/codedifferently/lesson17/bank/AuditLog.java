package com.codedifferently.lesson17.bank;

import java.util.ArrayList;
import java.util.List;

/**
 * AuditLog keeps a record of all debits and credits to any account.
 */
public class AuditLog {
    private final List<String> entries = new ArrayList<>();

    public void log(String entry) {
        entries.add(entry);
    }

    public List<String> getEntries() {
        return new ArrayList<>(entries);
    }
}
