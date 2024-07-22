package org.example.currencyexchange.controller.error.annotation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.currencyexchange.controller.error.logic.PeselValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = PeselValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPesel {
    String message() default "Invalid PESEL or user is not an adult";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
