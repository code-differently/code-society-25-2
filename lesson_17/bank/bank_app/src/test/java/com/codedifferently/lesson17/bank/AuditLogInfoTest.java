/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.codedifferently.lesson17.bank;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author vscode
 */
public class AuditLogInfoTest {

    private AuditLogInfo auditLogInfo;

    @BeforeEach
    public void setUp() {
        auditLogInfo = new AuditLogInfo(TranscationType.WITHDRAWAL, -50.0);
    }

    @Test
    public void constructorTest() {
        //Given 
        //When
    
        //Then 
        assertThat(auditLogInfo.getAmount() == -50.0);
        assertThat(auditLogInfo.getTranscationType() == TranscationType.WITHDRAWAL);
    }

    @Test
    public void constructorTest_amountShouldMatchTrancationType() {
        // Given
        assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> new AuditLogInfo(TranscationType.DEPOSIT, -50.0));
        // When
    }
    



}
