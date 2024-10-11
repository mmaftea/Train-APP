package com.app.train.model.anotations;

import com.app.train.model.entity.Travel;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class ConsecutiveTimeFilterValidator implements ConstraintValidator<ConsecutiveTimeFilter, List<Travel>> {
    @Override
    public boolean isValid(List<Travel> travel, ConstraintValidatorContext constraintValidatorContext) {
        return true;
    }
}
