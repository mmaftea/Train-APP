CREATE TABLE Route (
    id INT AUTO_INCREMENT PRIMARY KEY,
    route_name VARCHAR(100) NOT NULL
);

CREATE TABLE RouteStation (
    id INT AUTO_INCREMENT PRIMARY KEY,
    route_id INT NOT NULL,
    station_id INT NOT NULL,
    station_index INT,
    distance INT,
    duration TIME,
    departure_duration TIME,
    FOREIGN KEY (station_id) REFERENCES Station(id) ON DELETE CASCADE,
    FOREIGN KEY (route_id) REFERENCES Route(id) ON DELETE CASCADE
);
