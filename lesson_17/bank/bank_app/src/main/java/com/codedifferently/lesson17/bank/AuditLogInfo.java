/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.codedifferently.lesson17.bank;

/**
 * @author vscode
 */
public class AuditLogInfo {

  private TranscationType transcationType;
  private Double amount;
  private Double currentBalance;
  private String accountNummber;

  public AuditLogInfo(
      TranscationType transcationType, Double amount, Double currentBalance, String accountNumber) {
    if (transcationType == TranscationType.DEPOSIT && amount < 0) {
      throw new IllegalArgumentException("Deposit type must have positive amount");
    } else if (transcationType == TranscationType.WITHDRAWAL && amount > 0) {
      throw new IllegalArgumentException("Withdrawal type must have negative amount");
    }

    this.amount = amount;
    this.transcationType = transcationType;
    this.accountNummber = accountNumber;
  }

  public TranscationType getTranscationType() {
    return transcationType;
  }

  public Double getAmount() {
    return amount;
  }

  public Double getBalance() {
    return currentBalance;
  }

  public String getAccountNumber() {
    return accountNummber;
  }
}
