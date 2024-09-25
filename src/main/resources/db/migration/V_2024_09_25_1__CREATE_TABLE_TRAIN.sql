CREATE TABLE Train (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);
V_2024_09_25__CREATE_TABLE_TRAIN.sql
CREATE TABLE Vagon_Class(
	id VARCHAR(25) PRIMARY KEY,
    number_of_elements int,
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

CREATE TABLE Route (
    id INT AUTO_INCREMENT PRIMARY KEY,
    route_name VARCHAR(100) NOT NULL
);

CREATE TABLE Station (
    id INT AUTO_INCREMENT PRIMARY KEY,
    country_code VARCHAR(2) NOT NULL,
    station_name VARCHAR(100) NOT NULL,
    station_lat DECIMAL(9, 6),
    station_long DECIMAL(9, 6)
);

CREATE TABLE RouteStation (
    id INT AUTO_INCREMENT PRIMARY KEY,
    route_id INT NOT NULL,
    station_id INT NOT NULL,
    station_index INT NOT NULL,
    distance INT,
    duration TIME,
    departure_duration TIME,
    FOREIGN KEY (station_id) REFERENCES Station(id) ON DELETE CASCADE,
    FOREIGN KEY (route_id) REFERENCES Route(id) ON DELETE CASCADE
);

CREATE TABLE Travel (
        id INT AUTO_INCREMENT PRIMARY KEY,
        route_id INT NOT NULL,
        train_id INT NOT NULL,
        start_date_time DATETIME,
        FOREIGN KEY (route_id) REFERENCES Route(id) ON DELETE CASCADE,
        FOREIGN KEY (train_id) REFERENCES Train(id) ON DELETE CASCADE
    );

CREATE TABLE BaseTicket(
    id INT AUTO_INCREMENT PRIMARY KEY,
    ticket_travel_id INT,
    boarding_station_index INT,
    exit_station_index INT,
	ticket_price FLOAT,

    FOREIGN KEY (ticket_travel_id)REFERENCES Travel(id)
);

CREATE TABLE PassDocumentData(
    idnp VARCHAR(16) PRIMARY KEY,
    name VARCHAR(50),
    surname VARCHAR(50),
    date_of_birth DATE,
    date_of_issue DATE,
    date_of_expiry DATE
);


CREATE TABLE InterNationalTicket(
    id INT AUTO_INCREMENT PRIMARY KEY,
    inter_national_base_ticket_id INT,
    inter_national_pass_document_data_id VARCHAR(16),

    FOREIGN KEY (inter_national_base_ticket_id)REFERENCES BaseTicket(id),
    FOREIGN KEY (inter_national_pass_document_data_id)REFERENCES PassDocumentData(idnp)
);

CREATE TABLE Expense(
	id INT AUTO_INCREMENT PRIMARY KEY,
    category VARCHAR(20) ,
    expense_price_index FLOAT
);
CREATE TABLE TAX(
	id INT AUTO_INCREMENT PRIMARY KEY,
    tax_base_ticket_id INT,
    tax_expense_id INT,

    FOREIGN KEY (tax_base_ticket_id) REFERENCES BaseTicket(id),
    FOREIGN KEY (tax_expense_id) REFERENCES Expense(id)
);