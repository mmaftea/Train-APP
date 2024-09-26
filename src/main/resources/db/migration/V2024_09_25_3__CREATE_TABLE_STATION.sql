CREATE TABLE Station (
    id INT AUTO_INCREMENT PRIMARY KEY,
    country_code VARCHAR(2) NOT NULL,
    station_name VARCHAR(100) NOT NULL,
    station_lat DECIMAL(9, 6),
    station_long DECIMAL(9, 6)
);
