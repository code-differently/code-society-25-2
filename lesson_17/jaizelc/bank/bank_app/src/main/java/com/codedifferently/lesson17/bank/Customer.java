package com.codedifferently.lesson17.bank;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/** Represents a customer of the bank. */
public class Customer {

  private final UUID id;
  private final String name;
  private final Set<CheckingAccount> accounts = new HashSet<>();

  /**
   * @param id The customer ID.
   * @param name The customer name.
   */
  public Customer(UUID id, String name) {
    this.id = id;
    this.name = name;
  }

  /**
   * @return The customer ID.
   */
  public UUID getId() {
    return id;
  }

  /**
   * @return The customer name.
   */
  public String getName() {
    return name;
  }

  /**
   * @param account The account to add.
   */
  public void addAccount(CheckingAccount account) {
    accounts.add(account);
  }

  /**
   * @return The accounts owned by the customer.
   */
  public Set<CheckingAccount> getAccounts() {
    return accounts;
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
