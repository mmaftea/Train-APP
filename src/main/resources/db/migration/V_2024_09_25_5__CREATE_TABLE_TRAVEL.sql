CREATE TABLE Travel (
        id INT AUTO_INCREMENT PRIMARY KEY,
        route_id INT NOT NULL,
        start_date_time DATETIME,
        FOREIGN KEY (route_id) REFERENCES Route(id) ON DELETE CASCADE,
);