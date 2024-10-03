package com.app.train.service;

import com.app.train.model.dto.TravelResult;
import com.app.train.model.entity.Station;

import java.time.LocalDateTime;
import java.util.List;

public interface RaptorService {
    public List<List<TravelResult>> searchForTravels(Station start, Station end, LocalDateTime dateTime, int numOfTickets);
    public List<TravelResult> fastestTravelPossible(Station start, Station end, LocalDateTime dateTime, int numOfTickets);
}
