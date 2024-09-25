CREATE TABLE BaseTicket(
    id INT AUTO_INCREMENT PRIMARY KEY,
    ticket_travel_id INT,
    boarding_station_index INT,
    exit_station_index INT,
    seat_number INT,
    train_vagon_id INT,
	ticket_price FLOAT,
    FOREIGN KEY (train_vagon_id) REFERENCES Train_Vagon(id) ON DELETE CASCADE,
    FOREIGN KEY (ticket_travel_id)REFERENCES Travel(id)
);

CREATE TABLE BaseTicket(
    id INT AUTO_INCREMENT PRIMARY KEY,
    ticket_travel_id INT,
    boarding_station_index INT,
    exit_station_index INT,
	ticket_price FLOAT,

    FOREIGN KEY (ticket_travel_id)REFERENCES Travel(id)
);
