package org.example.currencyexchange.repository;

import org.example.currencyexchange.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByPesel(String pesel);

    boolean existsByPesel(String pesel);
}

