package com.app.train.service.impl;

import com.app.train.dao.interfaces.RouteRepository;
import com.app.train.dao.interfaces.RouteStationRepository;
import com.app.train.dao.interfaces.StationRepository;
import com.app.train.model.entity.Route;
import com.app.train.model.entity.RouteStation;
import com.app.train.model.entity.Station;
import com.app.train.util.raptor.FastestRaptor;
import com.app.train.util.raptor.PathFinder;
import com.app.train.util.raptor.SimpleRaptor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RouteServiceImpl {

    private final StationRepository stationRepository;
    private final RouteRepository routeRepository;
    private final RouteStationRepository repository;
    private final RouteStationServiceImpl service;

    public List<List<String>> getAllRoutesThroughPoints(Integer a, Integer b) {
        return callRaptor(a, b);
    }


    private List<List<String>> callRaptor(Integer a, Integer b) {
        List<Station> stations = stationRepository.findAll();
        List<RouteStation> routeStations = repository.findAll();
        log.info("{}", routeStations);
        Map<Integer, SimpleRaptor.RRouteStop> routeStopsMap = stations.stream()
                .map(station -> new SimpleRaptor.RRouteStop(station.getId()))
                .collect(Collectors.toMap(stop -> stop.stopId, stop -> stop));

        Map<Integer, List<SimpleRaptor.RRouteStop>> routeMap = new HashMap<>();
        for (RouteStation routeStation : routeStations) {
            routeMap.computeIfAbsent(routeStation.getRoute().getId(), k -> new ArrayList<>());
//                    .add(routeStopsMap.get(routeStation.getStation().getId()));
        }

        List<SimpleRaptor.RRoute> allRoutes = routeMap.entrySet().stream()
                .map(entry -> new SimpleRaptor.RRoute(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

        SimpleRaptor raptor = new SimpleRaptor();

        SimpleRaptor.RRouteStop startStop = routeStopsMap.get(a);
        SimpleRaptor.RRouteStop endStop = routeStopsMap.get(b);

        List<List<String>> routes = raptor.findAllRoutes(startStop, endStop, allRoutes, 1);
        for (List<String> route : routes) {
            System.out.println("Route: " + String.join(" -> ", route));
        }
        return routes;
    }

    public List<Route> findFastestRoute(Integer a, Integer b) {
        Station startStation = stationRepository.findById(a).orElseThrow();
        Station endStation = stationRepository.findById(b).orElseThrow();

        List<Route> routes = routeRepository.findAll();
        List<RouteStation> routeStations = repository.findAll();

        Map<Station, List<FastestRaptor.RRouteStop>> stationRouteMap = initializeStationRouteMap(routeStations);


        FastestRaptor fastestRaptor = new FastestRaptor(routes, stationRouteMap);


        List<Route> fastestRoute = fastestRaptor.findFastestPath(startStation, endStation);

        return fastestRoute;
    }

    private Map<Station, List<FastestRaptor.RRouteStop>> initializeStationRouteMap(List<RouteStation> routeStations) {
        Map<Station, List<FastestRaptor.RRouteStop>> stationRouteMap = new HashMap<>();

//        for (RouteStation rs : routeStations) {
//            FastestRaptor.RRouteStop stop = new FastestRaptor.RRouteStop(rs.getStation(), rs.getRoute(), rs.getStationIndex(), rs.getDistance(), rs.getDuration());
//            stationRouteMap.computeIfAbsent(rs.getStation(), k -> new ArrayList<>()).add(stop);
//        }

        return stationRouteMap;
    }

    public List<Route> findFastestPath(Integer startStationId, Integer endStationId) {
        PathFinder pathFinder = new PathFinder(service);

        Station startStation = stationRepository.findById(startStationId)
                .orElseThrow(() -> new RuntimeException("Start station not found"));

        Station endStation = stationRepository.findById(endStationId)
                .orElseThrow(() -> new RuntimeException("End station not found"));

        // Call the findFastestPath method from PathFinder
        return pathFinder.findFastestPath(startStation, endStation);
    }

}
