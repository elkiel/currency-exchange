package org.example.currencyexchange.model.dto;

import lombok.Builder;
import org.example.currencyexchange.controller.error.annotation.ExistUser;
import org.example.currencyexchange.controller.error.annotation.ValidAge;
import org.example.currencyexchange.controller.error.annotation.ValidBalance;
import org.example.currencyexchange.controller.error.annotation.ValidPesel;

import java.math.BigDecimal;

@Builder
public record CreateAccountCommand(
        @ValidAge
        @ValidPesel
        @ExistUser
        String pesel,
        String firstName,
        String lastName,
        @ValidBalance
        BigDecimal initialBalance) {
}