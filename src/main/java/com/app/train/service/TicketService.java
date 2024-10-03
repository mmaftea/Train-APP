package com.app.train.service;

import com.app.train.model.dto.Person;
import com.app.train.model.dto.TravelResult;
import jakarta.annotation.Nullable;

import java.util.List;

public interface TicketService {
    void createTicket(Person person, List<TravelResult> travelResult);
}
