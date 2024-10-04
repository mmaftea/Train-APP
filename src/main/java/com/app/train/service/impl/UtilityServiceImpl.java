package com.app.train.service.impl;

import com.app.train.dao.interfaces.RouteStationRepository;
import com.app.train.dao.interfaces.TrainLineElementRepository;
import com.app.train.model.dto.TravelResult;
import com.app.train.model.dto.UtilityResult;
import com.app.train.model.entity.Route;
import com.app.train.model.entity.RouteStation;
import com.app.train.model.entity.TrainLine;
import com.app.train.model.entity.TrainLineElement;
import com.app.train.service.UtilityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UtilityServiceImpl implements UtilityService {

    private final RouteStationRepository routeStationRepository;
    private final TrainLineElementRepository trainLineElementRepository;

    @Override
    public UtilityResult calculateDistanceAndDuration(TravelResult travelResult) {
        Route route = travelResult.getTravel().getRoute();

        RouteStation startStation = routeStationRepository.findByStationAndRoute(travelResult.getStartId(), route)
                .orElseThrow(() -> new IllegalArgumentException("Start station not found for the given route"));
        RouteStation endStation = routeStationRepository.findByStationAndRoute(travelResult.getEndId(), route)
                .orElseThrow(() -> new IllegalArgumentException("End station not found for the given route"));

        log.info("Start Station: {} ; End Station: {}", startStation, endStation);

        var linesTraveled = routeStationRepository.getTrainLinesInBetweenStations(
                startStation.getStationIndex(),
                endStation.getStationIndex(),
                route
        );

        double totalDistance;

        if (linesTraveled.size() == 1) totalDistance =
                endStation.getLineElement().getKm() - startStation.getLineElement().getKm();

        else totalDistance = linesTraveled.stream()
                .mapToDouble(trainLine -> calculateDistanceForTrainLine(trainLine, startStation, endStation))
                .sum();

        log.info("Total distance calculated: {}", totalDistance);
        return new UtilityResult(totalDistance, calculateDuration(totalDistance));

    }

    @Override
    public long calculateDuration(double km) {
        return Math.round((km * 80) / 60);
    }

    private double calculateDistanceForTrainLine(TrainLine trainLine, RouteStation startStation, RouteStation endStation) {
        double distance = 0;
        if (trainLine.equals(startStation.getLineElement().getTrainLine())) {
            TrainLineElement lastElement = trainLineElementRepository.findLastElementByTrainLine(trainLine);
            distance = lastElement.getKm() - startStation.getLineElement().getKm();
        } else if (trainLine.equals(endStation.getLineElement().getTrainLine())) {
            distance = endStation.getLineElement().getKm();
        } else {
            TrainLineElement lastElement = trainLineElementRepository.findLastElementByTrainLine(trainLine);
            distance = lastElement.getKm();
        }
        log.debug("Distance for train line {}: {}", trainLine.getName(), distance);
        return distance;
    }

}
