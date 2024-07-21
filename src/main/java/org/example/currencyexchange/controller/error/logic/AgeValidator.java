package org.example.currencyexchange.controller.error.logic;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.currencyexchange.controller.error.annotation.ValidAge;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class AgeValidator implements ConstraintValidator<ValidAge, String> {

    @Override
    public void initialize(ValidAge constraintAnnotation) {
    }

    @Override
    public boolean isValid(String pesel, ConstraintValidatorContext context) {
        if (pesel == null || pesel.length() != 11) {
            return false;
        }

        try {
            LocalDate birthDate = PeselValidator.extractBirthDate(pesel);
            long age = ChronoUnit.YEARS.between(birthDate, LocalDate.now());
            return age >= 18;
        } catch (Exception e) {
            return false;
        }
    }
}

