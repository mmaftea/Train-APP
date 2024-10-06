package com.app.train.model.anotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ConsecutiveTimeFilterValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ConsecutiveTimeFilter {
    String message() default "Travel times are not consecutive.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}