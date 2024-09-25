CREATE TABLE BaseTicket(
    id INT AUTO_INCREMENT PRIMARY KEY,
    travel_id INT,
    boarding_station_index INT,
    exit_station_index INT,
    seat_number INT,
    vagon_index INT,
	price FLOAT,

    FOREIGN KEY (travel_id)REFERENCES Travel(id)
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
