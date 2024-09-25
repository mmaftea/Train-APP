package com.app.train.model;

import java.util.*;

// RouteGraph Class to store connections between stations
class RouteGraph {
    private Map<Integer, List<Connection>> adjacencyList = new HashMap<>();

    public static class Connection {
        int station;
        String route;
        int travelTime;

        public Connection(int station, String route, int travelTime) {
            this.station = station;
            this.route = route;
            this.travelTime = travelTime;
        }
    }

    public void addConnection(int stationA, int stationB, String route, int travelTime) {
        adjacencyList.computeIfAbsent(stationA, k -> new ArrayList<>())
                .add(new Connection(stationB, route, travelTime));
        adjacencyList.computeIfAbsent(stationB, k -> new ArrayList<>())
                .add(new Connection(stationA, route, travelTime));
    }

    public List<Connection> getConnections(int station) {
        return adjacencyList.getOrDefault(station, new ArrayList<>());
    }
}

class RaptorAlgorithm {
    private static final int INF = Integer.MAX_VALUE;

    // Accumulate routes that serve the marked stops
    private List<RouteGraph.Connection> accumulateRoutes(RouteGraph graph, Set<Integer> markedStops) {
        List<RouteGraph.Connection> queue = new ArrayList<>();

        for (int stop : markedStops) {
            for (RouteGraph.Connection conn : graph.getConnections(stop)) {
                queue.add(conn);
            }
        }

        return queue;
    }

    // Find the best routes with a transfer limit from start to end
    public int[] raptor(RouteGraph graph, int startStation, int endStation, int maxRounds) {
        int n = graph.getConnections(startStation).size();  // Total number of stations
        int[] arrivalTimes = new int[n];
        Arrays.fill(arrivalTimes, INF); // Initialize all arrival times to infinity
        arrivalTimes[startStation] = 0; // Start station at time 0

        int[] bestArrivalTimes = new int[n];
        Arrays.fill(bestArrivalTimes, INF);
        bestArrivalTimes[startStation] = 0;

        Set<Integer> markedStops = new HashSet<>();
        markedStops.add(startStation);

        for (int round = 0; round < maxRounds; round++) {
            List<RouteGraph.Connection> routeQueue = accumulateRoutes(graph, markedStops);
            markedStops.clear(); // Clear marked stops for the next round

            for (RouteGraph.Connection conn : routeQueue) {
                int currentArrivalTime = arrivalTimes[conn.station];
                int newArrivalTime = bestArrivalTimes[conn.station] + conn.travelTime;

                // Check if we can improve the arrival time at the station
                if (newArrivalTime < bestArrivalTimes[conn.station]) {
                    arrivalTimes[conn.station] = newArrivalTime;
                    bestArrivalTimes[conn.station] = newArrivalTime;
                    markedStops.add(conn.station);  // Mark the station for next round
                }
            }

            if (markedStops.isEmpty()) {
                break; // Stop if no new stations are marked
            }
        }

        return arrivalTimes;
    }
}

// Main class to test the RaptorAlgorithm
public class Main {
    public static void main(String[] args) {
        // Initialize the RouteGraph
        RouteGraph graph = new RouteGraph();

        // Adding connections for Route1
        graph.addConnection(1, 2, "Route1", 10);
        graph.addConnection(2, 3, "Route1", 15);
        graph.addConnection(3, 4, "Route1", 20);

        // Adding connections for Route2
        graph.addConnection(5, 3, "Route2", 25);
        graph.addConnection(3, 6, "Route2", 30);

        // Create the RaptorAlgorithm instance with max transfers
        RaptorAlgorithm raptor = new RaptorAlgorithm();

        // Call the Raptor algorithm to find best routes from station 1 to 6
        int[] result = raptor.raptor(graph, 1, 6, 3);

        // Print the result
        System.out.println("Best arrival times for each station:");
        for (int i = 1; i < result.length; i++) {
            System.out.println("Station " + i + ": " + result[i]);
        }
    }
}
