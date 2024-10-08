package com.app.train.service;

import com.app.train.model.dto.TravelResult;
import com.app.train.model.entity.Station;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface RaptorService {
    public List<List<TravelResult>> searchForTravels(Integer startId, Integer endId, LocalDate dateTime, int numOfTickets);
    public List<TravelResult> fastestTravelPossible(Integer start, Integer end, LocalDateTime dateTime, int numOfTickets);
}
