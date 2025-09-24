package com.codedifferently.lesson17.bank;

import java.util.UUID;

/** Represents a business customer of the bank. */
public class BusinessCustomer extends Customer {
  public BusinessCustomer(UUID id, String name) {
    super(id, name);
  }
}
