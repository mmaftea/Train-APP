package com.app.train.model.trash;

import java.util.*;

class Stop {
    private String name;

    public Stop(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Stop)) return false;
        Stop stop = (Stop) obj;
        return name.equals(stop.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }
}

class Trip {
    private Route route;
    private Stop departureStop;
    private Stop arrivalStop;

    public Trip(Route route, Stop departureStop, Stop arrivalStop) {
        this.route = route;
        this.departureStop = departureStop;
        this.arrivalStop = arrivalStop;
    }

    public Route getRoute() {
        return route;
    }

    public Stop getDepartureStop() {
        return departureStop;
    }

    public Stop getArrivalStop() {
        return arrivalStop;
    }
}

class Route {
    private String name;
    private List<Stop> stops;
    private List<Trip> trips;

    public Route(String name) {
        this.name = name;
        this.stops = new ArrayList<>();
        this.trips = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addStop(Stop stop) {
        stops.add(stop);
    }

    public List<Stop> getStops() {
        return stops;
    }

    public void addTrip(Trip trip) {
        trips.add(trip);
    }

    public List<Trip> getTripsForStop(Stop stop) {
        List<Trip> tripsAtStop = new ArrayList<>();
        for (Trip trip : trips) {
            if (trip.getDepartureStop().equals(stop)) {
                tripsAtStop.add(trip);
            }
        }
        return tripsAtStop;
    }
}


class Rsssaptor {
    private List<Route> routes;
    private Map<Stop, List<Route>> stopRoutesMap;

    public Rsssaptor(List<Route> routes) {
        this.routes = routes;
        this.stopRoutesMap = new HashMap<>();
        initializeStopRoutes();
    }

    private void initializeStopRoutes() {
        for (Route route : routes) {
            for (Stop stop : route.getStops()) {
                stopRoutesMap.computeIfAbsent(stop, k -> new ArrayList<>()).add(route);
            }
        }
    }

    public List<List<Route>> findTransferRoutes(Stop source, Stop target, int maxTransfers) {
        List<List<Route>> allTransferRoutes = new ArrayList<>();
        Queue<RouteNode> queue = new LinkedList<>();
        Map<Stop, Integer> visited = new HashMap<>();


        List<Route> sourceRoutes = stopRoutesMap.get(source);
        if (sourceRoutes == null) return allTransferRoutes;

        for (Route route : sourceRoutes) {
            queue.add(new RouteNode(route, source, new ArrayList<>(), 0));
        }


        while (!queue.isEmpty()) {
            RouteNode node = queue.poll();
            Stop currentStop = node.currentStop;
            Route currentRoute = node.currentRoute;
            List<Route> currentRouteList = node.routeList;


            visited.put(currentStop, node.transfers);

            for (Trip trip : currentRoute.getTripsForStop(currentStop)) {
                Stop nextStop = trip.getArrivalStop();

                if (nextStop.equals(target)) {

                    List<Route> completeRouteList = new ArrayList<>(currentRouteList);
                    completeRouteList.add(currentRoute);
                    allTransferRoutes.add(completeRouteList);
                }


                if (node.transfers < maxTransfers && (!visited.containsKey(nextStop) || visited.get(nextStop) > node.transfers)) {
                    List<Route> nextAvailableRoutes = stopRoutesMap.get(nextStop);
                    if (nextAvailableRoutes != null) {
                        for (Route nextRoute : nextAvailableRoutes) {
                            List<Route> newRouteList = new ArrayList<>(currentRouteList);
                            newRouteList.add(currentRoute); // Record the current route
                            queue.add(new RouteNode(nextRoute, nextStop, newRouteList, node.transfers + 1));
                        }
                    }
                }
            }
        }

        return allTransferRoutes;
    }

    private class RouteNode {
        Route currentRoute;
        Stop currentStop;
        List<Route> routeList;
        int transfers;

        RouteNode(Route currentRoute, Stop currentStop, List<Route> routeList, int transfers) {
            this.currentRoute = currentRoute;
            this.currentStop = currentStop;
            this.routeList = routeList;
            this.transfers = transfers;
        }
    }
}


public class Main {
    public static void main(String[] args) {

        Stop stationA = new Stop("A");
        Stop stationB = new Stop("B");
        Stop stationC = new Stop("C");
        Stop stationD = new Stop("D");


        Route route1 = new Route("Route 1");
        route1.addStop(stationA);
        route1.addStop(stationB);
        route1.addTrip(new Trip(route1, stationA, stationB));

        Route route2 = new Route("Route 2");
        route2.addStop(stationB);
        route2.addStop(stationC);
        route2.addTrip(new Trip(route2, stationB, stationC));

        Route route3 = new Route("Route 3");
        route3.addStop(stationB);
        route3.addStop(stationC);
        route3.addTrip(new Trip(route3, stationB, stationC));

        List<Route> routes = new ArrayList<>();
        routes.add(route1);
        routes.add(route2);
        routes.add(route3);


        Rsssaptor rsssaptor = new Rsssaptor(routes);

        List<List<Route>> transferRoutes = rsssaptor.findTransferRoutes(stationA, stationC, 1); // Allow up to 1 transfer

        if (transferRoutes.isEmpty()) {
            System.out.println("No transfer routes found.");
        } else {
            for (List<Route> routeList : transferRoutes) {
                System.out.println("Transfer Route: ");
                for (Route route : routeList) {
                    System.out.println(route.getName());
                }
            }
        }
    }
}
