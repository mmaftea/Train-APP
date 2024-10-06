package com.app.train.service.impl;

import com.app.train.dao.interfaces.TravelRepository;
import com.app.train.model.anotations.ConsecutiveTimeFilter;
import com.app.train.model.anotations.TicketFilter;
import com.app.train.model.dto.TravelResult;
import com.app.train.model.entity.Station;
import com.app.train.model.entity.Travel;
import com.app.train.service.RaptorService;
import com.app.train.util.raptor.AllRoutesRaptorService;
import com.app.train.util.raptor.Connection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RaptorServiceImpl implements RaptorService {

    private final AllRoutesRaptorService allRoutes;
    private final TravelRepository travelRepository;

    @Override
    public List<List<TravelResult>> searchForTravels(Station start, Station end, LocalDateTime dateTime, int numOfTickets) {
        var paths = allRoutes.findAllRoutes(start.getId(), end.getId());

        List<List<Travel>> travelsToTake = paths.stream()
                .map(path -> path.getConnections().stream()
                        .map(Connection::getRouteId)
                        .distinct()
                        .map(routeId -> travelRepository.findByDateAndRoute(routeId, dateTime)
                                .orElseThrow())
                        .toList())
                .toList();

        return mapToResult(travelsToTake, start, end);
    }

    private List<List<TravelResult>> mapToResult(@TicketFilter @ConsecutiveTimeFilter List<List<Travel>> travels, Station start, Station end) {
        List<List<TravelResult>> result = new ArrayList<>();
        for (List<Travel> t : travels) {
            var list = t.stream().map(travel -> this.resultBuilder(travel, start.getId(), end.getId())).toList();
            result.add(list);
        }
        return result;
    }

    private TravelResult resultBuilder(Travel t, int start, int end) {
        return TravelResult.builder()
                .travel(t)
                .startId(start)
                .endId(end)
                .build();
    }

    @Override
    public List<TravelResult> fastestTravelPossible(Station start, Station end, LocalDateTime dateTime, int numOfTickets) {
        return List.of();
    }

}
