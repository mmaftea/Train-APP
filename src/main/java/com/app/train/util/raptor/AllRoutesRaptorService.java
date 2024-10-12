package com.app.train.util.raptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import static com.app.train.util.Constants.MAX_TRANSFERS;

@Service
public class AllRoutesRaptorService {

    @Autowired
    private RaptorEntityMapper entityMapper;

    public List<Path> findAllRoutes(int startStopId, int endStopId) {
        Map<Integer, Stop> stops = entityMapper.loadStops();
        Queue<Path> queue = new LinkedList<>();
        List<Path> allPaths = new ArrayList<>();

        Stop startStop = stops.get(startStopId);
        if (startStop == null) return allPaths;

        for (Connection connection : startStop.getConnections()) {
            Path initialPath = new Path();
            initialPath.addConnection(connection);
            queue.add(initialPath);
        }

        while (!queue.isEmpty()) {
            Path currentPath = queue.poll();
            int lastStopId = currentPath.getLastStopId();

            if (lastStopId == endStopId) {
                allPaths.add(currentPath);
                continue;
            }

            if (currentPath.getTransfers() > MAX_TRANSFERS) continue;

            Stop lastStop = stops.get(lastStopId);
            if (lastStop == null) continue;

            for (Connection connection : lastStop.getConnections()) {
                if (!containsStopInPath(currentPath, connection.getDestinationStopId()) &&
                        notGoingSameRouteReverse(currentPath, connection)) {
                    Path newPath = new Path(currentPath);
                    newPath.addConnection(connection);
                    queue.add(newPath);
                }
            }
        }

        return allPaths;
    }

    private boolean notGoingSameRouteReverse(Path currentPath, Connection connection) {
        return Math.abs(currentPath.getLastConnection().getRouteId() - connection.getRouteId()) != 1;
    }

    private boolean containsStopInPath(Path path, int stopId) {
        for (Connection conn : path.getConnections()) {
            if (conn.getDestinationStopId() == stopId) {
                return true;
            }
        }
        return false;
    }
}

