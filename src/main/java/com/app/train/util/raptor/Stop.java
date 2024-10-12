package com.app.train.util.raptor;

import java.util.ArrayList;
import java.util.List;

public class Stop {
    private int id;
    private String name;
    private List<Connection> connections = new ArrayList<>();

    public Stop(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public List<Connection> getConnections() { return connections; }

    public void addConnection(Connection connection) {
        this.connections.add(connection);
    }
}

