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

CREATE TABLE trainloadedvagons (
  id INT NOT NULL,
   train_id INT NULL,
   CONSTRAINT pk_trainloadedvagons PRIMARY KEY (id)
);

ALTER TABLE trainloadedvagons ADD CONSTRAINT FK_TRAINLOADEDVAGONS_ON_TRAIN FOREIGN KEY (train_id) REFERENCES train (id);


