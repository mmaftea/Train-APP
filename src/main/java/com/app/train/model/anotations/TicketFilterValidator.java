package com.app.train.model.anotations;

import com.app.train.model.entity.Travel;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;
import java.util.Objects;

public class TicketFilterValidator implements ConstraintValidator<TicketFilter, List<Travel>> {
    @Override
    public boolean isValid(List<Travel> travels, ConstraintValidatorContext constraintValidatorContext) {
        return travels.stream()
                .allMatch(travel ->
                                Objects.nonNull(travel.getRoute())
                        /* ticketRepository.remainingTickets(travel.getRoute()) >= numOfTickets */);
    }
}
