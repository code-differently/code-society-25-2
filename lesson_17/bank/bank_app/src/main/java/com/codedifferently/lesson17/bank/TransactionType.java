package com.codedifferently.lesson17.bank;

/** Represents the different types of transactions that can occur in the banking system. */
public enum TransactionType {
  /** Money being added to an account */
  DEPOSIT,

  /** Money being removed from an account */
  WITHDRAWAL,

  /** Money being moved from one account to another (via check) */
  TRANSFER,

  /** Account being opened */
  ACCOUNT_OPENED,

  /** Account being closed */
  ACCOUNT_CLOSED
}
