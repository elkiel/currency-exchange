package org.example.currencyexchange.service;

import org.example.currencyexchange.model.Account;
import org.example.currencyexchange.model.User;
import org.example.currencyexchange.model.enums.Currency;

import java.math.BigDecimal;

public interface AccountServiceIF {
    /**
     * Creates a new account for the user with the given details.
     *
     * @param firstName      The first name of the user.
     * @param lastName       The last name of the user.
     * @param pesel          The PESEL of the user.
     * @param initialBalance The initial balance for the new account in PLN.
     * @return The created Account.
     */
    Account createAccount(String firstName, String lastName, String pesel, BigDecimal initialBalance);

    /**
     * Exchanges currency between sub-accounts of the same user.
     *
     * @param pesel        The PESEL of the user.
     * @param amount       The amount to exchange.
     * @param fromCurrency The currency to exchange from.
     * @param toCurrency   The currency to exchange to.
     * @return The updated Account.
     */
    Account exchangeCurrency(String pesel, BigDecimal amount, Currency fromCurrency, Currency toCurrency);

    /**
     * Retrieves user details by PESEL.
     *
     * @param pesel The PESEL of the user.
     * @return The User details.
     */
    User getUserDetails(String pesel);
}
