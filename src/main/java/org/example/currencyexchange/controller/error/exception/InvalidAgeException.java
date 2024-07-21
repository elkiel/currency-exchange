package org.example.currencyexchange.controller.error.exception;

import jakarta.persistence.PersistenceException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvalidAgeException extends PersistenceException {
    private String pesel;

    public InvalidAgeException(String message, String pesel) {
        super(message);
        this.pesel = pesel;
    }
}
