CREATE TABLE TrainVagon(
    train_loaded_vagons_id INT,
    vagon_id INT,
    vagon_index INT,

    FOREIGN KEY (train_loaded_vagons_id) REFERENCES TrainLoadedVagons(id),
    FOREIGN KEY (vagon_id) REFERENCES Vagon(id)
);

CREATE TABLE Travel (
        id INT AUTO_INCREMENT PRIMARY KEY,
        route_id INT NOT NULL,
        travel_train_loaded_vagons_id INT,
        start_date_time DATETIME,

        FOREIGN KEY (travel_train_loaded_vagons_id) REFERENCES TrainLoadedVagons(id),
        FOREIGN KEY (route_id) REFERENCES Route(id) ON DELETE CASCADE
);