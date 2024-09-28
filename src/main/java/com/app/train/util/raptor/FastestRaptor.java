package com.app.train.util.raptor;

import com.app.train.model.entity.Route;
import com.app.train.model.entity.Station;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
//not really working
public class FastestRaptor {
    private List<Route> routes;
    private Map<Station, List<RRouteStop>> stationRouteMap;

    public FastestRaptor(List<Route> routes, Map<Station, List<RRouteStop>> stationRouteMap) {
        this.routes = routes;
        this.stationRouteMap = stationRouteMap;
    }

    public List<Route> findFastestPath(Station startStation, Station endStation) {
        PriorityQueue<PathElement> pq = new PriorityQueue<>(Comparator.comparingInt(PathElement::getArrivalTime));
        Set<RRouteStop> visitedStops = new HashSet<>();

        List<RRouteStop> startRouteStops = stationRouteMap.get(startStation);
        if (startRouteStops == null) {
            return Collections.emptyList();
        }


        for (RRouteStop startStop : startRouteStops) {
            if (stationRouteMap.get(endStation) != null) {
                for (RRouteStop stop : stationRouteMap.get(endStation)) {
                    if (stop.route.equals(startStop.route)) {
                        return Collections.singletonList(stop.route);
                    }
                }
            }

            PathElement startElement = new PathElement(null, startStop, 0, 0, 0, 0, 0.0, 0.0, 0, 0, false, null, null);
            pq.add(startElement);
            visitedStops.add(startStop);
        }

        Map<Station, Integer> bestArrivalTime = new HashMap<>();
        bestArrivalTime.put(startStation, 0);


        while (!pq.isEmpty()) {
            PathElement current = pq.poll();

            if (current.toRouteStop.station.equals(endStation)) {
                return reconstructPath(current);
            }

            List<RRouteStop> nextStops = getNextStops(current.toRouteStop);
            for (RRouteStop nextStop : nextStops) {
                int newArrivalTime = current.arrivalTime + (nextStop.duration - current.toRouteStop.duration);
                if (newArrivalTime < bestArrivalTime.getOrDefault(nextStop.station, Integer.MAX_VALUE) && !visitedStops.contains(nextStop)) {
                    bestArrivalTime.put(nextStop.station, newArrivalTime);
                    PathElement nextElement = new PathElement(current, nextStop, current.firstDepartureTime, current.boardingTime, current.vehDepartureTime, newArrivalTime, current.arrivalTravelCost, current.arrivalTransferCost, 0, current.transferCount, false, null, null);
                    pq.add(nextElement);
                    visitedStops.add(nextStop);
                }
            }

            if (canTransfer(current.toRouteStop)) {
                List<RRouteStop> transferStops = getTransferStops(current.toRouteStop.station);
                for (RRouteStop transferStop : transferStops) {
                    int transferTime = current.arrivalTime + 5;
                    if (!visitedStops.contains(transferStop)) {
                        PathElement transferElement = new PathElement(current, transferStop, current.firstDepartureTime, current.boardingTime, current.vehDepartureTime, transferTime, current.arrivalTravelCost, current.arrivalTransferCost, 0, current.transferCount + 1, true, null, null);
                        pq.add(transferElement);
                    }
                }
            }
        }


        return Collections.emptyList();
    }


    private List<RRouteStop> getNextStops(RRouteStop currentStop) {
        List<RRouteStop> nextStops = new ArrayList<>();
        Route currentRoute = currentStop.route;


        List<RRouteStop> allStops = stationRouteMap.get(currentStop.station);
        if (allStops != null) {
            for (RRouteStop stop : allStops) {
                if (stop.route.equals(currentRoute) && stop.stationIndex > currentStop.stationIndex) {
                    nextStops.add(stop);
                }
            }
        }
        return nextStops;
    }

    private List<RRouteStop> getTransferStops(Station station) {
        return stationRouteMap.get(station);
    }

    private boolean canTransfer(RRouteStop stop) {
        return stationRouteMap.get(stop.station).size() > 1;
    }

    private List<Route> reconstructPath(PathElement destination) {
        List<Route> path = new ArrayList<>();
        PathElement current = destination;

        while (current != null) {
            if (!path.contains(current.toRouteStop.route)) {
                path.add(current.toRouteStop.route);
            }
            current = current.comingFrom;
        }
        Collections.reverse(path);
        return path;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RRouteStop {
        private Station station;
        private Route route;
        private int stationIndex;
        private int distance;
        private int duration;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class PathElement {
        public PathElement comingFrom;
        public RRouteStop toRouteStop;
        public int firstDepartureTime;
        public int boardingTime;
        public int vehDepartureTime;
        public int arrivalTime;
        public double arrivalTravelCost;
        public double arrivalTransferCost;
        public int waitTime;
        public int transferCount;
        public boolean isTransfer;
        public String lastTripId;
        public String currentTripId;
    }
}
