CREATE TABLE Vagon_Class(
	id VARCHAR(25) PRIMARY KEY,
    number_of_elements int,
    stand_up_capacity INT,
    class_price_index float
);

CREATE TABLE Vagon (
   id INT AUTO_INCREMENT PRIMARY KEY,
   train_id INT,
   vagon_class VARCHAR(25),
   vagon_index INT,
   FOREIGN KEY (train_id) REFERENCES Train(id),
   FOREIGN KEY (vagon_class) REFERENCES Vagon_Class(id)
);

CREATE TABLE Train_Vagon(
    id INT AUTO_INCREMENT PRIMARY KEY,
    train_id INT,
    vagon_id INT,
    FOREIGN KEY (train_id) REFERENCES Train (id),
    FOREIGN KEY (vagon_id) REFERENCES Vagon (id),
);
