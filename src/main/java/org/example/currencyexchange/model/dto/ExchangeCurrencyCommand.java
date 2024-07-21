package org.example.currencyexchange.model.dto;

import lombok.Builder;
import org.example.currencyexchange.controller.error.annotation.ExistUser;
import org.example.currencyexchange.controller.error.annotation.ValidBalance;
import org.example.currencyexchange.controller.error.annotation.ValidPesel;
import org.example.currencyexchange.model.enums.Currency;

import java.math.BigDecimal;

@Builder
public record ExchangeCurrencyCommand(
        @ValidPesel
        @ExistUser
        String pesel,
        @ValidBalance
        BigDecimal amount,
        Currency fromCurrency,
        Currency toCurrency) {
}