/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.codedifferently.lesson17.bank;

/**
 *
 * @author vscode
 */
public class AuditLogInfo {
    private TranscationType transcationType;
    private Double amount;
    

   public AuditLogInfo(TranscationType transcationType, Double amount) {
        if (transcationType == TranscationType.DEPOSIT && amount <0) {
            throw new IllegalArgumentException("Deposit type must have positive amount");
        } else if(transcationType == TranscationType.WITHDRAWAL && amount >0) {
            throw new IllegalArgumentException("Withdrawal type must have negative amount");

        }

        this.amount = amount;
        this.transcationType = transcationType;
        
   }


   public TranscationType getTranscationType() {
    return null;
   }

   public Double getAmount() {
    return null;

   }

   



    

}
