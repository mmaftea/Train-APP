package com.app.train.model.trash;

import com.app.train.model.entity.Route;
import com.app.train.model.entity.RouteStation;
import com.app.train.model.entity.Station;

import java.util.*;


//TODO: THIS TRASH NEED TO IMPLEMENT
public class Raptor {

    public static List<List<Route>> findTransferRoutes(Station startStation, Station endStation, List<Route> routes, List<RouteStation> routeStations) {

        Map<Station, List<RouteStation>> stationToRouteStations = new HashMap<>();


        for (RouteStation routeStation : routeStations) {
//            stationToRouteStations
//                    .computeIfAbsent(routeStation.getStation(), k -> new ArrayList<>())
//                    .add(routeStation);
        }


        Queue<Station> queue = new LinkedList<>();
        queue.add(startStation);


        Map<Station, List<List<Route>>> routesMap = new HashMap<>();
        routesMap.put(startStation, new ArrayList<>(Collections.singletonList(new ArrayList<>())));


        Set<Station> visitedStations = new HashSet<>();
        visitedStations.add(startStation);


        while (!queue.isEmpty()) {
            Station currentStation = queue.poll();
            List<List<Route>> currentRoutes = routesMap.get(currentStation);


            List<Station> nextStations = new ArrayList<>();

            for (RouteStation rs : stationToRouteStations.getOrDefault(currentStation, Collections.emptyList())) {
                Route currentRoute = rs.getRoute();
//                List<List<Route>> nextRoutes = routesMap.computeIfAbsent(rs.getStation(), k -> new ArrayList<>());

                for (List<Route> routeList : currentRoutes) {
                    List<Route> newRouteList = new ArrayList<>(routeList);
                    newRouteList.add(currentRoute);

//                    if (!nextRoutes.contains(newRouteList)) {
//                        nextRoutes.add(newRouteList);
//                        nextStations.add(rs.getStation());
//                    }
                }
            }

            for (Station nextStation : nextStations) {
                if (!visitedStations.contains(nextStation)) {
                    visitedStations.add(nextStation);
                    queue.add(nextStation);
                }
            }
        }
        return routesMap.getOrDefault(endStation, Collections.emptyList());
    }
}
