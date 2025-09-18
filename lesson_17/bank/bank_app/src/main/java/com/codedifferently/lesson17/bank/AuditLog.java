package com.codedifferently.lesson17.bank;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Audit log for tracking all bank transactions.
 * Maintains a record of all debits and credits to accounts.
 */
public class AuditLog {
    
    private final List<AuditEntry> entries = new ArrayList<>();
    
    /**
     * Records a debit transaction.
     *
     * @param accountNumber The account number.
     * @param amount The amount debited.
     * @param description Description of the transaction.
     */
    public void recordDebit(String accountNumber, double amount, String description) {
        entries.add(new AuditEntry(accountNumber, amount, TransactionType.DEBIT, description, LocalDateTime.now()));
    }
    
    /**
     * Records a credit transaction.
     *
     * @param accountNumber The account number.
     * @param amount The amount credited.
     * @param description Description of the transaction.
     */
    public void recordCredit(String accountNumber, double amount, String description) {
        entries.add(new AuditEntry(accountNumber, amount, TransactionType.CREDIT, description, LocalDateTime.now()));
    }
    
    /**
     * Gets all audit entries.
     *
     * @return List of all audit entries.
     */
    public List<AuditEntry> getEntries() {
        return new ArrayList<>(entries);
    }
    
    /**
     * Gets audit entries for a specific account.
     *
     * @param accountNumber The account number.
     * @return List of audit entries for the account.
     */
    public List<AuditEntry> getEntriesForAccount(String accountNumber) {
        return entries.stream()
                .filter(entry -> entry.getAccountNumber().equals(accountNumber))
                .toList();
    }
    
    /**
     * Clears all audit entries.
     * This method is primarily for testing purposes.
     */
    public void clear() {
        entries.clear();
    }
    
    /**
     * Represents a single audit entry.
     */
    public static class AuditEntry {
        private final String accountNumber;
        private final double amount;
        private final TransactionType type;
        private final String description;
        private final LocalDateTime timestamp;
        
        /**
         * Creates a new audit entry.
         *
         * @param accountNumber The account number.
         * @param amount The transaction amount.
         * @param type The transaction type.
         * @param description The transaction description.
         * @param timestamp The transaction timestamp.
         */
        public AuditEntry(String accountNumber, double amount, TransactionType type, 
                         String description, LocalDateTime timestamp) {
            this.accountNumber = accountNumber;
            this.amount = amount;
            this.type = type;
            this.description = description;
            this.timestamp = timestamp;
        }
        
        /**
         * Gets the account number.
         *
         * @return The account number.
         */
        public String getAccountNumber() {
            return accountNumber;
        }
        
        /**
         * Gets the transaction amount.
         *
         * @return The transaction amount.
         */
        public double getAmount() {
            return amount;
        }
        
        /**
         * Gets the transaction type.
         *
         * @return The transaction type.
         */
        public TransactionType getType() {
            return type;
        }
        
        /**
         * Gets the transaction description.
         *
         * @return The transaction description.
         */
        public String getDescription() {
            return description;
        }
        
        /**
         * Gets the transaction timestamp.
         *
         * @return The transaction timestamp.
         */
        public LocalDateTime getTimestamp() {
            return timestamp;
        }
        
        @Override
        public String toString() {
            return String.format("[%s] %s %s: $%.2f - %s", 
                    timestamp, accountNumber, type, amount, description);
        }
    }
    
    /**
     * Enum representing transaction types.
     */
    public enum TransactionType {
        DEBIT, CREDIT
    }
}
