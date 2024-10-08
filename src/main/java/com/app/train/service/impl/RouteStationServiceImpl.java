package com.app.train.service.impl;

import com.app.train.dao.interfaces.RouteRepository;
import com.app.train.dao.interfaces.RouteStationRepository;
import com.app.train.model.dto.TicketMetaData;
import com.app.train.model.dto.TravelResult;
import com.app.train.model.entity.Route;
import com.app.train.model.entity.Station;
import com.app.train.model.entity.Travel;
import com.app.train.service.UtilityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RouteStationServiceImpl {
    private final RouteStationRepository repository;
    private final RouteRepository routeRepository;
    private final UtilityService utilityService;

    public Station getStationFromIndex(int index, int routeId) {
        Route route = routeRepository.findById(routeId).orElseThrow();
        return repository.findByIndexAndRoute(route, index).orElseThrow().getLineElement().getStation();
    }

    public List<TicketMetaData> squashResults(List<TravelResult> list) {
        if (list.size() == 1) {
            Result result = getResult(list, 0);
            return Collections.singletonList(TicketMetaData.builder()
                    .departureTime(result.travel().getStartDateTime())
                    .arrivalTime(result.travel().getStartDateTime().plusMinutes(result.minutes()))
                    .startStation(repository.findByIndexAndRoute(result.travel().getRoute(),
                            result.travelResult().getStartId()).orElseThrow().getLineElement().getStation())
                    .stopStation(repository.findByIndexAndRoute(result.travel().getRoute(),
                            result.travelResult().getEndId()).orElseThrow().getLineElement().getStation())
                    .build());
        } else {
            List<TicketMetaData> ticketMetaData = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                Result startPoint = getResult(list, i);
                ticketMetaData.add(TicketMetaData.builder()
                        .departureTime(startPoint.travel().getStartDateTime())
                        .arrivalTime(startPoint.travel().getStartDateTime().plusMinutes(startPoint.minutes()))
                        .startStation(repository.findByIndexAndRoute(startPoint.travel().getRoute(),
                                startPoint.travelResult().getStartId()).orElseThrow().getLineElement().getStation())
                        .stopStation(repository.findByIndexAndRoute(startPoint.travel().getRoute(),
                                startPoint.travelResult().getEndId()).orElseThrow().getLineElement().getStation())
                        .build());
            }
            log.info("{}",ticketMetaData);
            return ticketMetaData;

        }
    }

    private Result getResult(List<TravelResult> list, int index) {
        TravelResult travelResult = list.get(index);
        Travel travel = travelResult.getTravel();
        long minutes = utilityService.calculateDistanceAndDuration(travelResult).getMinutes();
        return new Result(travelResult, travel, minutes);
    }

    private record Result(TravelResult travelResult, Travel travel, long minutes) {
    }
}
