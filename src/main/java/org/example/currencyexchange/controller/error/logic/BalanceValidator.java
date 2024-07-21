package org.example.currencyexchange.controller.error.logic;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.currencyexchange.controller.error.annotation.ValidBalance;

import java.math.BigDecimal;

public class BalanceValidator implements ConstraintValidator<ValidBalance, BigDecimal> {

    @Override
    public void initialize(ValidBalance constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(BigDecimal bigDecimal, ConstraintValidatorContext constraintValidatorContext) {
        if (bigDecimal == null) {
            return false;
        }
        return bigDecimal.compareTo(BigDecimal.ZERO) > 0;
    }
}

