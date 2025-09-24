package com.codedifferently.lesson17.bank;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/** Represents a customer of the bank. */
public class Customer implements AccountOwner {

  private final UUID id;
  private final String name;
  private final Set<CheckingAccount> accounts = new HashSet<>();
  private final Set<SavingAccount> savingsAccounts = new HashSet<>();

  /**
   * Creates a new customer.
   *
   * @param id The ID of the customer.
   * @param name The name of the customer.
   */
  public Customer(UUID id, String name) {
    this.id = id;
    this.name = name;
  }

  /**
   * Gets the ID of the customer.
   *
   * @return The ID of the customer.
   */
  @Override
  public UUID getId() {
    return id;
  }

  /**
   * Gets the name of the customer.
   *
   * @return The name of the customer.
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * Adds a checking account to the customer.
   *
   * @param account The account to add.
   */
  @Override
  public void addAccount(CheckingAccount account) {
    accounts.add(account);
  }

  /**
   * Adds a savings account to the customer.
   *
   * @param account The savings account to add.
   */
  @Override
  public void addSavingsAccount(SavingAccount account) {
    savingsAccounts.add(account);
  }

  /**
   * Gets the checking accounts owned by the customer.
   *
   * @return The unique set of checking accounts owned by the customer.
   */
  @Override
  public Set<CheckingAccount> getAccounts() {
    return accounts;
  }

  /**
   * Gets the savings accounts owned by the customer.
   *
   * @return The unique set of savings accounts owned by the customer.
   */
  @Override
  public Set<SavingAccount> getSavingsAccounts() {
    return savingsAccounts;
  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Customer other) {
      return id.equals(other.id);
    }
    return false;
  }

  @Override
  public String toString() {
    return "Customer{" + "id=" + id + ", name='" + name + '\'' + '}';
  }
}
