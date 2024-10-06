package com.app.train.model.anotations;

import com.app.train.model.entity.Travel;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ConsecutiveTimeFilterValidator implements ConstraintValidator<ConsecutiveTimeFilter,Travel> {
    @Override
    public boolean isValid(Travel travel, ConstraintValidatorContext constraintValidatorContext) {
        return true; /*IntStream.range(0, travelsToTake.size() - 1)
                .allMatch(i -> travelsToTake.get(i).getArrivalTime().isBefore(travelsToTake.get(i + 1).getDepartureTime())) */
    }
}
