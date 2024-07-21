package org.example.currencyexchange.repository;

import org.example.currencyexchange.model.Account;
import org.example.currencyexchange.model.enums.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {
    @Query("SELECT a FROM Account a WHERE a.user.pesel = :pesel")
    Optional<Account> findByUserPesel(@Param("pesel") String pesel);
    @Query("SELECT a FROM Account a WHERE a.user.pesel = :pesel AND a.currency = :currency")
    Optional<Account> findByUserPeselAndCurrency(@Param("pesel") String pesel, @Param("currency") Currency currency);

}
