package com.codedifferently.lesson17.bank;

import java.util.Set;
import java.util.UUID;

/** Represents a customer of the bank. */
public class Customer {

  private final UUID id;
  private final String name;
  private final AccountType.OwnerType ownerType;
  private final Set<CheckingAccount> accounts = new java.util.HashSet<>();

  /**
   * Creates a new customer.
   *
   * @param id The ID of the customer.
   * @param name The name of the customer.
   * @param ownerType The owner type of the customer.
   */
  public Customer(UUID id, String name, AccountType.OwnerType ownerType) {
    this.id = id;
    this.name = name;
    this.ownerType = ownerType;
  }

  /**
   * Gets the ID of the customer.
   *
   * @return The ID of the customer.
   */
  public UUID getId() {
    return id;
  }

  /**
   * Gets the name of the customer.
   *
   * @return The name of the customer.
   */
  public String getName() {
    return name;
  }

  /**
   * Gets the owner type of the customer.
   *
   * @return The owner type of the customer.
   */
  public AccountType.OwnerType getOwnerType() {
    return ownerType;
  }

  public void addAccount(CheckingAccount account) {
    accounts.add(account);
  }

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

  public static void main(String[] args) {
    Customer customer =
        new Customer(UUID.randomUUID(), "John Doe", AccountType.OwnerType.INDIVIDUAL);
    System.out.println(customer);
  }
}
