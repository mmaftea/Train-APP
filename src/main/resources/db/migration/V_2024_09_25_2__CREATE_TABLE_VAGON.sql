CREATE TABLE Vagon_Class(
	id VARCHAR(25) PRIMARY KEY,
    number_of_elements int,
    stand_up_capacity INT,
    class_price_index float
);

CREATE TABLE Vagon (
   id INT AUTO_INCREMENT PRIMARY KEY,
   vagon_class VARCHAR(25),
   FOREIGN KEY (vagon_class) REFERENCES Vagon_Class(id)
);


