/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.codedifferently.lesson17.bank;

/**
 * @author vscode
 */
public class MoneyOrder extends FundTransfer{

    private CheckingAccount toAccount;

    public MoneyOrder(String moneyOrderID,double amount,CheckingAccount account,CheckingAccount toAccount) {
        super(moneyOrderID,amount,account);
        depositFunds(toAccount);

    }



}
