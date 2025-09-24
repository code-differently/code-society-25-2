package com.codedifferently.lesson17.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * An audit log system that tracks and stores transaction records for bank customers. This class
 * maintains a mapping of customer IDs to their respective transaction histories, allowing for
 * transaction logging, retrieval, and management operations.
 *
 * <p>The audit log is thread-safe for basic operations but may require external synchronization for
 * complex multi-threaded scenarios.
 *
 * @author Code Differently
 * @version 1.0
 * @since 1.0
 */
public class AuditLog {
  private final Map<UUID, List<String>> logByCustomerId = new HashMap<>();

  /**
   * Logs a transaction for a specific customer. If this is the first transaction for the customer,
   * a new transaction list is created.
   *
   * @param customerId the unique identifier of the customer performing the transaction
   * @param transaction a descriptive string of the transaction that occurred
   */
  public void logTransaction(UUID customerId, String transaction) {
    logByCustomerId.putIfAbsent(customerId, new ArrayList<>());
    logByCustomerId.get(customerId).add(transaction);
  }

  /**
   * Retrieves all transactions for a specific customer in chronological order.
   *
   * @param customerId the unique identifier of the customer whose transactions to retrieve
   * @return an immutable list of transaction strings for the customer, or an empty list if no
   *     transactions exist for the customer
   */
  public List<String> getTransactions(UUID customerId) {
    return logByCustomerId.getOrDefault(customerId, List.of());
  }

  /**
   * Retrieves a specific transaction by its index position for a given customer. Transactions are
   * indexed in chronological order starting from 0.
   *
   * @param customerId the unique identifier of the customer
   * @param index the zero-based index of the transaction to retrieve
   * @return the transaction string at the specified index
   * @throws IllegalArgumentException if the index is negative
   * @throws IndexOutOfBoundsException if the customer has no transactions or the index is greater
   *     than or equal to the number of transactions for the customer
   */
  public String getTransactionsByNumber(UUID customerId, int index) {
    if (index < 0) {
      throw new IllegalArgumentException("Index must be non-negative");
    }

    if (!logByCustomerId.containsKey(customerId)
        || index >= logByCustomerId.get(customerId).size()) {
      throw new IndexOutOfBoundsException("Index out of bounds for customer transactions");
    }

    return logByCustomerId.get(customerId).get(index);
  }

  /**
   * Clears all transaction records for all customers from the audit log. This operation cannot be
   * undone.
   */
  public void clearLog() {
    logByCustomerId.clear();
  }

  /**
   * Clears all transaction records for a specific customer from the audit log. If the customer has
   * no existing records, this operation has no effect. This operation cannot be undone.
   *
   * @param customerId the unique identifier of the customer whose transactions should be cleared
   */
  public void clearLogForCustomer(UUID customerId) {
    logByCustomerId.remove(customerId);
  }
}
