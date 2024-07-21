package org.example.currencyexchange.service;

import org.example.currencyexchange.model.enums.Currency;

import java.math.BigDecimal;

public interface ExchangeRateServiceIF {

    BigDecimal getExchangeRate(Currency fromCurrency, Currency toCurrency);
}
