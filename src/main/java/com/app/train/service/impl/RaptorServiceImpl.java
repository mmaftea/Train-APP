package com.app.train.service.impl;

import com.app.train.dao.interfaces.RouteStationRepository;
import com.app.train.dao.interfaces.StationRepository;
import com.app.train.dao.interfaces.TravelRepository;
import com.app.train.model.anotations.ConsecutiveTimeFilter;
import com.app.train.model.anotations.TicketFilter;
import com.app.train.model.dto.TravelResult;
import com.app.train.model.entity.RouteStation;
import com.app.train.model.entity.Travel;
import com.app.train.service.RaptorService;
import com.app.train.util.raptor.AllRoutesRaptorService;
import com.app.train.util.raptor.Connection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RaptorServiceImpl implements RaptorService {

    private final AllRoutesRaptorService allRoutes;
    private final TravelRepository travelRepository;
    private final RouteStationRepository routeIntegerRepository;
    private final StationRepository stationRepository;

    private Integer startIndex = -1;
    private Integer startStation = 1;

    @Override
    public List<List<TravelResult>> searchForTravels(Integer start, Integer end, LocalDate date, int numOfTickets) {
        var paths = allRoutes.findAllRoutes(start, end);
        startIndex *= start;
        startStation *= start;

        Map<Integer, Connection> lastConnectionByRoute = paths.stream()
                .flatMap(path -> path.getConnections().stream())
                .collect(Collectors.toMap(
                        Connection::getRouteId,
                        connection -> connection,
                        (existing, replacement) -> replacement
                ));

        List<List<Travel>> travelsToTake = paths.stream()
                .map(path -> path.getConnections().stream()
                        .map(Connection::getRouteId)
                        .distinct()
                        .map(routeId -> travelRepository.findByDateAndRoute(routeId, date)
                                .orElseThrow())
                        .toList())
                .toList();

        return mapToResult(travelsToTake, start, end, lastConnectionByRoute);
    }

    private List<List<TravelResult>> mapToResult(@TicketFilter @ConsecutiveTimeFilter List<List<Travel>> travels, Integer start, Integer end, Map<Integer, Connection> lastConnectionByRoute) {
        List<List<TravelResult>> result = new ArrayList<>();
        for (List<Travel> t : travels) {
            var list = t.stream()
                    .map(travel -> this.resultBuilder(travel, lastConnectionByRoute))
                    .toList();
            result.add(list);
        }
        startIndex = -1;
        return result;
    }

    private TravelResult resultBuilder(Travel t, Map<Integer, Connection> lastConnectionByRoute) {
        if (startIndex < 0) {
            startIndex = routeIntegerRepository.getIndexByStation(t.getRouteId(), -startIndex).orElseThrow();
        } else {
            startIndex = routeIntegerRepository.findByStationAndRoute(startStation, t.getRoute()).orElseThrow().getStationIndex();
        }

        var temp = getStation(t, lastConnectionByRoute);
        var lastIndex = temp.getStationIndex();

        TravelResult build = TravelResult.builder()
                .travel(t)
                .startId(startIndex)
                .endId(lastIndex)
                .build();
        startIndex = lastIndex;
        startStation = temp.getLineElement().getStation().getId();
        return build;
    }

    private RouteStation getStation(Travel t, Map<Integer, Connection> lastConnectionByRoute) {
        return routeIntegerRepository
                .findByStationAndRoute(lastConnectionByRoute.get(t.getRouteId()).getDestinationStopId(),
                        t.getRoute()).orElseThrow();
    }

    @Override
    public List<TravelResult> fastestTravelPossible(Integer start, Integer end, LocalDateTime dateTime, int numOfTickets) {
        return List.of();
    }

}
