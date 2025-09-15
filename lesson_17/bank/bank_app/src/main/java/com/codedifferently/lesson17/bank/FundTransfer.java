/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.codedifferently.lesson17.bank;

import com.codedifferently.lesson17.bank.exceptions.FundTransferVoidedException;

/**
 * @author vscode
 */
public abstract class FundTransfer {

  protected final String id;
  protected final double amount;
  protected final CheckingAccount account;
  protected boolean isVoided = false;

  public FundTransfer(String id, double amount, CheckingAccount account) {
    if (amount < 0) {
      throw new IllegalArgumentException("Cannot transfer negative amount");
    }
    this.id = id;
    this.amount = amount;
    this.account = account;
  }

  public void depositFunds(CheckingAccount toAccount) {
    if (isVoided) {
      throw new FundTransferVoidedException("Check is voided");
    }
    account.withdraw(amount);
    toAccount.deposit(amount);
  }

  public boolean getIsVoided() {
    return isVoided;
  }

  /** Voids the check. */
  public void voidCheck() {
    isVoided = true;
  }
}
