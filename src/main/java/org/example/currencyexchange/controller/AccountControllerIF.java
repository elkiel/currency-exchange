package org.example.currencyexchange.controller;

import org.example.currencyexchange.model.Account;
import org.example.currencyexchange.model.User;
import org.example.currencyexchange.model.dto.CreateAccountCommand;
import org.example.currencyexchange.model.dto.ExchangeCurrencyCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Validated
public interface AccountControllerIF {

    ResponseEntity<Account> createAccount(@RequestBody CreateAccountCommand request);

    ResponseEntity<User> getUserByPesel(@PathVariable String pesel);

    ResponseEntity<Account> exchangeCurrency(@RequestBody ExchangeCurrencyCommand request);
}
