CREATE TABLE TrainLine(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name varchar(20)
);

CREATE TABLE TrainLine_Element(
    id INT AUTO_INCREMENT PRIMARY KEY,
    train_Line INT,
    station INT,
    km INT,
    FOREIGN KEY (station) REFERENCES Station(id) ON DELETE CASCADE,
    FOREIGN KEY (train_Line) REFERENCES TrainLine(id) ON DELETE CASCADE
);
