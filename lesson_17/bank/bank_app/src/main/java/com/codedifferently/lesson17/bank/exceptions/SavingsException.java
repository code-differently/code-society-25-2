package com.codedifferently.lesson17.bank.exceptions;

public class SavingsException extends IllegalAccessException{
    public SavingsException(String message) {
        super(message);
    }
}
