package org.example.currencyexchange.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.currencyexchange.controller.error.annotation.ValidPesel;
import org.example.currencyexchange.model.Account;
import org.example.currencyexchange.model.User;
import org.example.currencyexchange.model.dto.CreateAccountCommand;
import org.example.currencyexchange.model.dto.ExchangeCurrencyCommand;
import org.example.currencyexchange.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
@Validated
public class AccountController implements AccountControllerIF {

    private final AccountService accountService;

    @Override
    @PostMapping
    public ResponseEntity<Account> createAccount(@Valid CreateAccountCommand request) {
        return new ResponseEntity<>(accountService.createAccount(request.firstName(), request.lastName(), request.pesel(), request.initialBalance()), HttpStatus.OK);
    }

    @Override
    @GetMapping
    public ResponseEntity<User> getUserByPesel(@Valid @ValidPesel String pesel) {
        return new ResponseEntity<>(accountService.getUserDetails(pesel), HttpStatus.OK);
    }

    @Override
    @PostMapping("/exchange")
    public ResponseEntity<Account> exchangeCurrency(@Valid @RequestBody ExchangeCurrencyCommand request) {
        Account account = accountService.exchangeCurrency(
                request.pesel(),
                request.amount(),
                request.fromCurrency(),
                request.toCurrency()
        );
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

}
