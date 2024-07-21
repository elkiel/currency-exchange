package org.example.currencyexchange.controller.error.exception;

import jakarta.persistence.PersistenceException;

public class InsufficientBalanceException extends PersistenceException {
    private String balance;

    public InsufficientBalanceException(String message, String balance) {
        super(message);
        this.balance = balance;
    }
}