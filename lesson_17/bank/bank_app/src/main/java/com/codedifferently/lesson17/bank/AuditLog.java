/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.codedifferently.lesson17.bank;

import java.util.List;

/**
 *
 * @author vscode
 */
public class AuditLog {

    private List<AuditLogInfo> logInfo;
    private CheckingAccount account;

    public AuditLog(List<AuditLogInfo> logInfo,CheckingAccount account) {
        this.logInfo = logInfo;
        this.account = account;
    }

    public CheckingAccount getAccount() {
        return account;
    }

    public List<AuditLogInfo> getLogInfo() {
        return logInfo;
    }

    public void addLog(AuditLogInfo logToAdd) {
        logInfo.add(logToAdd);
    }

    public String showLog() {
        String logs = "Account: " +  account.getAccountNumber() + "\n" ;

        for (AuditLogInfo auditLogInfo : logInfo) {
            logs+= String.format("%s %f %f \n", 
            auditLogInfo.getTranscationType()
            ,auditLogInfo.getAmount(),
            auditLogInfo.getBalance()
            );
        }
        

        return logs;
    }

}
