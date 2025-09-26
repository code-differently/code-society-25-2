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
  protected final Account account;
  protected boolean isVoided = false;

  public FundTransfer(String id, double amount, Account account) {
    if (amount < 0) {
      throw new IllegalArgumentException("Cannot transfer negative amount");
    }
    this.id = id;
    this.amount = amount;
    this.account = account;
  }

  public void depositFunds(Account toAccount) {
    if (isVoided) {
      throw new FundTransferVoidedException("voided");
    }
    account.withdraw(amount);
    toAccount.deposit(amount);
    voidFundTransfer();
  }

  public boolean getIsVoided() {
    return isVoided;
  }

  /** Voids the check. */
  public void voidFundTransfer() {
    isVoided = true;
  }
}
