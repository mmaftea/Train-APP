package com.app.train.util.raptor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
//quite works
public class SimpleRaptor {
    static class PathElement {
        final RRoute currentRoute;
        final RRouteStop toRouteStop;
        final PathElement comingFrom;
        final int transfers;
        final Set<String> usedRouteIds;

        PathElement(RRoute currentRoute, RRouteStop toRouteStop, PathElement comingFrom, int transfers, Set<String> usedRouteIds) {
            this.currentRoute = currentRoute;
            this.toRouteStop = toRouteStop;
            this.comingFrom = comingFrom;
            this.transfers = transfers;
            this.usedRouteIds = usedRouteIds;
        }
    }

    public static class RRouteStop {
        public final Integer stopId;

        public RRouteStop(Integer stopId) {
            this.stopId = stopId;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof RRouteStop)) return false;
            RRouteStop other = (RRouteStop) obj;
            return this.stopId.equals(other.stopId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(stopId);
        }
    }

    public static class RRoute {
        final Integer routeId;
        final List<RRouteStop> stops;

        public RRoute(Integer routeId, List<RRouteStop> stops) {
            this.routeId = routeId;
            this.stops = stops;
        }
    }

    public List<List<String>> findAllRoutes(RRouteStop startStop, RRouteStop endStop, List<RRoute> allRoutes, int maxTransfers) {
        Set<List<String>> allFoundRoutes = new HashSet<>();
        List<PathElement> stack = new ArrayList<>();

        for (RRoute route : allRoutes) {
            if (route.stops.contains(startStop)) {
                Set<String> usedRoutes = new HashSet<>();
                usedRoutes.add(route.routeId.toString());
                stack.add(new PathElement(route, startStop, null, 0, usedRoutes));
            }
        }

        while (!stack.isEmpty()) {
            PathElement current = stack.remove(stack.size() - 1);
            RRouteStop currentStop = current.toRouteStop;


            if (currentStop.equals(endStop)) {
                List<String> routeIds = new ArrayList<>(current.usedRouteIds);
                Collections.sort(routeIds);
                allFoundRoutes.add(routeIds);
                continue;
            }

            for (RRoute route : allRoutes) {
                int currentIndex = route.stops.indexOf(currentStop);
                if (currentIndex != -1) {

                    if (current.currentRoute.routeId.equals(route.routeId)) {

                        if (currentIndex < route.stops.size() - 1) {
                            RRouteStop nextStop = route.stops.get(currentIndex + 1);
                            stack.add(new PathElement(route, nextStop, current, current.transfers, current.usedRouteIds));
                        }
                    } else if (current.transfers < maxTransfers) {

                        for (int i = 0; i < route.stops.size(); i++) {
                            RRouteStop nextStop = route.stops.get(i);
                            if (currentStop.equals(nextStop)) {

                                if (!(current.transfers == 0 && nextStop.equals(startStop))) {
                                    Set<String> newUsedRoutes = new HashSet<>(current.usedRouteIds);
                                    newUsedRoutes.add(route.routeId.toString());
                                    stack.add(new PathElement(route, nextStop, current, current.transfers + 1, newUsedRoutes));
                                }
                            }
                        }
                    }
                }
            }
        }

        return new ArrayList<>(allFoundRoutes);
    }

}
