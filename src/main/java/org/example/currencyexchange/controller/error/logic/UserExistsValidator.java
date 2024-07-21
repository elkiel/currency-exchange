package org.example.currencyexchange.controller.error.logic;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.example.currencyexchange.controller.error.annotation.ExistUser;
import org.example.currencyexchange.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserExistsValidator implements ConstraintValidator<ExistUser, String> {

    private final UserRepository userRepository;

    public boolean isValid(String pesel, ConstraintValidatorContext constraintValidatorContext) {
        return !userRepository.existsByPesel(pesel);
    }
}
