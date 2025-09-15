/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.codedifferently.lesson17.bank;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author vscode
 */
public class AuditLogTest {

    private AuditLog classToTest;
    List<AuditLogInfo> logInfo = new ArrayList<>();
    CheckingAccount account;

    @BeforeEach
    public void setUp() {
        account =  new CheckingAccount("123456789", null, 100.0);
        classToTest = new AuditLog(logInfo, account);

    }

    @Test
    public void constructorTest() {
        CheckingAccount expected = classToTest.getAccount();
        assertThat(expected == account);
        assertThat(classToTest.getLogInfo() == logInfo);

    }

    @Test
    public void showLogsTest() {

    }

    @Test
    public void addLogInfoTest() {
        //Given
        var logToAdd = new AuditLogInfo(TranscationType.DEPOSIT, 50.0);
        //When
        classToTest.addLog(logToAdd);
        List<AuditLogInfo> expected  = new ArrayList<>();
        expected.add(logToAdd);
        //Then
        assertTrue(expected.equals(classToTest.getLogInfo()));

    }



    

}
