package org.example.currencyexchange.controller.error.exception;

import jakarta.persistence.PersistenceException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvalidPeselException extends PersistenceException {
    private String pesel;

    public InvalidPeselException(String message, String pesel) {
        super(message);
        this.pesel = pesel;
    }
}
