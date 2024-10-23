package com.app.train.service.impl;

import com.app.train.dao.interfaces.RouteStationRepository;
import com.app.train.dao.interfaces.TrainLineElementRepository;
import com.app.train.model.dto.TravelResult;
import com.app.train.model.dto.UtilityResult;
import com.app.train.model.entity.Route;
import com.app.train.model.entity.RouteStation;
import com.app.train.model.entity.TrainLineElement;
import com.app.train.model.entity.TrainLineInfo;
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

        RouteStation startStation = routeStationRepository.findByIndexAndRoute(route, travelResult.getStartId())
                .orElseThrow(() -> new IllegalArgumentException("Start station not found for the given route"));
        RouteStation endStation = routeStationRepository.findByIndexAndRoute(route, travelResult.getEndId())
                .orElseThrow(() -> new IllegalArgumentException("End station not found for the given route"));

        log.info("Start Station: {} ; End Station: {}", startStation, endStation);

        var linesTraveled = routeStationRepository.getTrainLinesInBetweenStationsWithCount(
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

    private double calculateDistanceForTrainLine(TrainLineInfo trainLine, RouteStation startStation, RouteStation endStation) {
        double distance = 0;
        if (trainLine.getTrainLine().equals(startStation.getLineElement().getTrainLine())) {
            TrainLineElement lastElement = trainLineElementRepository.findById(startStation.getLineElement().getId() + trainLine.getElementCount() - 1).orElseThrow();
            distance = lastElement.getKm() - startStation.getLineElement().getKm();
        } else if (trainLine.getTrainLine().equals(endStation.getLineElement().getTrainLine())) {
            TrainLineElement lastElement = trainLineElementRepository.findById(endStation.getLineElement().getId() - trainLine.getElementCount()).orElseThrow();
            distance = endStation.getLineElement().getKm() - lastElement.getKm();
        } else {
            TrainLineElement lastElement = trainLineElementRepository.findLastElementByTrainLine(trainLine.getTrainLine());
            distance = lastElement.getKm();
        }
        log.debug("Distance for train line {}: {}", trainLine.getTrainLine().getName(), distance);
        return distance;
    }

}
