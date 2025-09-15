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
        account = account;
    }

    public CheckingAccount getAccount() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAccount'");
    }

}
