package com.app.train.util.raptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Path {
    private List<Connection> connections = new ArrayList<>();
    private long totalTravelTime;
    private int transfers;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Path path)) return false;
        return totalTravelTime == path.totalTravelTime && transfers == path.transfers;
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalTravelTime, transfers);
    }

    public Path() {
        this.totalTravelTime = 0;
        this.transfers = 0;
    }

    public Path(Path otherPath) {
        this.connections = new ArrayList<>(otherPath.connections);
        this.totalTravelTime = otherPath.totalTravelTime;
        this.transfers = otherPath.transfers;
    }

    public void addConnection(Connection connection) {
        updateTransfers(connection);
        this.totalTravelTime += connection.getTravelTime();
        this.connections.add(connection);
    }

    private void updateTransfers(Connection connection) {
        if (!connections.isEmpty() && getLastConnection().getRouteId() != connection.getRouteId()) {
            this.transfers++;
        }
    }

    public Connection getLastConnection() {
        var size = connections.size() - 1;
        return connections.get(size);
    }

    public int getLastStopId() {
        return connections.isEmpty() ? -1 : connections.get(connections.size() - 1).getDestinationStopId();
    }

    public long getTotalTravelTime() {
        return totalTravelTime;
    }

    public int getTransfers() {
        return transfers;
    }

    public List<Connection> getConnections() {
        return connections;
    }

    @Override
    public String toString() {
        return "Path with " + connections.size() + " connections, total time: " + totalTravelTime + " minutes, transfers: " + transfers;
    }
}
