package com.app.train.service.impl;

import com.app.train.dao.interfaces.RouteRepository;
import com.app.train.dao.interfaces.RouteStationRepository;
import com.app.train.dao.interfaces.StationRepository;
import com.app.train.model.Raptor;
import com.app.train.model.entity.Route;
import com.app.train.model.entity.RouteStation;
import com.app.train.model.entity.Station;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RouteServiceImpl {

    private final StationRepository stationRepository;
    private final RouteRepository routeRepository;
    private final RouteStationRepository routeStationRepository;

    public List<List<Route>> getTransferRoutes(Integer startStationId, Integer endStationId) {
        Station startStation = stationRepository.findById(startStationId)
                .orElseThrow(() -> new EntityNotFoundException("Start station not found"));

        Station endStation = stationRepository.findById(endStationId)
                .orElseThrow(() -> new EntityNotFoundException("End station not found"));

        List<Route> routes = routeRepository.findAll();
        List<RouteStation> routeStations = routeStationRepository.findAll();

        return Raptor.findTransferRoutes(startStation, endStation, routes, routeStations);
    }
}
