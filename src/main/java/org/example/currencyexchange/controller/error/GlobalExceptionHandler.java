package org.example.currencyexchange.controller.error;

import jakarta.persistence.EntityNotFoundException;
import org.example.currencyexchange.controller.error.exception.InsufficientBalanceException;
import org.example.currencyexchange.controller.error.exception.InvalidAgeException;
import org.example.currencyexchange.controller.error.exception.InvalidPeselException;
import org.example.currencyexchange.controller.error.model.ConstraintValidationExceptionErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ConstraintValidationExceptionErrorDto>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<ConstraintValidationExceptionErrorDto> errors = ex.getBindingResult().getAllErrors().stream()
                .map(error -> new ConstraintValidationExceptionErrorDto(((FieldError) error).getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidPeselException.class)
    public ResponseEntity<ConstraintValidationExceptionErrorDto> handleInvalidPeselException(InvalidPeselException ex) {
        ConstraintValidationExceptionErrorDto error = new ConstraintValidationExceptionErrorDto("pesel", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidAgeException.class)
    public ResponseEntity<ConstraintValidationExceptionErrorDto> handleInvalidAgeException(InvalidAgeException ex) {
        ConstraintValidationExceptionErrorDto error = new ConstraintValidationExceptionErrorDto("pesel", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<ConstraintValidationExceptionErrorDto> handleInsufficientBalanceException(InvalidAgeException ex) {
        ConstraintValidationExceptionErrorDto error = new ConstraintValidationExceptionErrorDto("pesel", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
