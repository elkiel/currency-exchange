package org.example.currencyexchange.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.currencyexchange.controller.error.exception.InsufficientBalanceException;
import org.example.currencyexchange.model.Account;
import org.example.currencyexchange.model.User;
import org.example.currencyexchange.model.enums.Currency;
import org.example.currencyexchange.repository.AccountRepository;
import org.example.currencyexchange.repository.UserRepository;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class AccountService implements AccountServiceIF {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final ExchangeRateService exchangeRateService;


    @Override
    @Transactional
    public Account createAccount(String firstName, String lastName, String pesel, BigDecimal initialBalance) {
        User user = findOrCreateUser(firstName, lastName, pesel);
        return createAndSaveAccount(user, initialBalance);
    }

    @Override
    @Transactional
    public User getUserDetails(String pesel) {
        return userRepository.findByPesel(pesel)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    @Override
    @Transactional
    public Account exchangeCurrency(String pesel, BigDecimal amount, Currency fromCurrency, Currency toCurrency) {
        User user = findUserByPesel(pesel);
        Account sourceAccount = findAccountByCurrency(user, fromCurrency, "Source account with specified currency not found");
        Account targetAccount = findAccountByCurrency(user, toCurrency, "Target account with specified currency not found");
        validateSufficientFunds(sourceAccount, amount);

        performCurrencyExchange(sourceAccount, targetAccount, amount, fromCurrency, toCurrency);

        return targetAccount;
    }

    private User findUserByPesel(String pesel) {
        return userRepository.findByPesel(pesel)
                .orElseThrow(() -> new EntityNotFoundException("User with specified PESEL not found"));
    }

    private Account findAccountByCurrency(User user, Currency currency, String errorMessage) {
        return user.getAccounts().stream()
                .filter(account -> currency.equals(account.getCurrency()))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException(errorMessage));
    }

    private void validateSufficientFunds(Account sourceAccount, BigDecimal amount) {
        if (sourceAccount.getBalance().compareTo(amount) < 0) {
            throw new InsufficientBalanceException("Insufficient funds in the source account", sourceAccount.getBalance().toString());
        }
    }

    private void performCurrencyExchange(Account sourceAccount, Account targetAccount, BigDecimal amount, Currency fromCurrency, Currency toCurrency) {
        sourceAccount.setBalance(sourceAccount.getBalance().subtract(amount));

        BigDecimal exchangeRate = exchangeRateService.getExchangeRate(fromCurrency, toCurrency);
        BigDecimal exchangedAmount = amount.multiply(exchangeRate);

        targetAccount.setBalance(targetAccount.getBalance().add(exchangedAmount));

        accountRepository.save(sourceAccount);
        accountRepository.save(targetAccount);
    }

    private User findOrCreateUser(String firstName, String lastName, String pesel) {
        return userRepository.findByPesel(pesel).orElseGet(() -> {
            User newUser = User.builder()
                    .pesel(pesel)
                    .firstName(firstName)
                    .lastName(lastName)
                    .build();
            return userRepository.save(newUser);
        });
    }

    private Account createAndSaveAccount(User user, BigDecimal initialBalance) {
        Account newAccount = Account.builder()
                .balance(initialBalance)
                .currency(Currency.PLN)
                .user(user)
                .build();
        return accountRepository.save(newAccount);
    }

}
