package org.example.currencyexchange.controller.error.logic;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.currencyexchange.controller.error.annotation.ValidPesel;

import java.time.LocalDate;

public class PeselValidator implements ConstraintValidator<ValidPesel, String> {

    @Override
    public void initialize(ValidPesel constraintAnnotation) {
    }

    @Override
    public boolean isValid(String pesel, ConstraintValidatorContext context) {
        if (pesel == null || pesel.length() != 11) {
            return false;
        }

        try {
            extractBirthDate(pesel);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static LocalDate extractBirthDate(String pesel) {
        String yearPart = pesel.substring(0, 2);
        String monthPart = pesel.substring(2, 4);
        String dayPart = pesel.substring(4, 6);

        int year = Integer.parseInt(yearPart);
        int month = Integer.parseInt(monthPart);
        int day = Integer.parseInt(dayPart);

        if (month > 80) {
            year += 1800;
            month -= 80;
        } else if (month > 60) {
            year += 2200;
            month -= 60;
        } else if (month > 40) {
            year += 2100;
            month -= 40;
        } else if (month > 20) {
            year += 2000;
            month -= 20;
        } else {
            year += 1900;
        }

        try {
            return LocalDate.of(year, month, day);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid PESEL: Cannot extract birth date");
        }
    }
}
