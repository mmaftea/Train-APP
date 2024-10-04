CREATE TABLE Route (
    id INT AUTO_INCREMENT PRIMARY KEY,
    route_name VARCHAR(100) NOT NULL
);

CREATE TABLE RouteStation (
    id INT AUTO_INCREMENT PRIMARY KEY,
    route_id INT,
    line_Element INT,
    station_index INT,
    distance INT,
    departure_duration INT,
    FOREIGN KEY (line_Element) REFERENCES TrainLine_Element(id) ON DELETE CASCADE,
    FOREIGN KEY (route_id) REFERENCES Route(id) ON DELETE CASCADE
);
