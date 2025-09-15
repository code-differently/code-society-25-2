/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.codedifferently.lesson17.bank;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author vscode
 */
public class MoneyOrderTest {

    private CheckingAccount account1;
    private CheckingAccount account2;
    private MoneyOrder classUnderTest;


    @BeforeEach
    public void setUp() {
        account1 = new CheckingAccount("123456789", null, 100.0);
        account2 = new CheckingAccount("987654321", null, 200.0);
        classUnderTest = new MoneyOrder("123456789", 50.0, account1,account2);
    }

    @Test
    public void moneyOrderTest_removesMoneyOnCreationOfOrder() {
        assertThat(account1.getBalance() == 150.0);
        assertThat(account2.getBalance() == 150.0);

    }

}
