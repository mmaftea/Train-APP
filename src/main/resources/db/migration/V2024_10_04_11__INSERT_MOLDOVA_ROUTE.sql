insert into route (id,route_name)
values
    (1,'Ocnita - Balti'),
    (2,'Balti - Ocnita'),
    (3,'Balti - Ribnita'),
    (4,'Ribnita - Balti'),
    (5,'Ungheni - Balti'),
    (6,'Balti - Ungheni'),
    (7,'Chisinau - Ungheni'),
    (8,'Ungheni - Chisinau'),
    (9,'Tiraspol - Chisinau'),
    (10,'Chisinau - Tiraspol'),
    (11,'Chisinau - Comrat'),
    (12,'Comrat - Chisinau'),
    (13,'Comrat - Bender'),
    (14,'Bender - Comrat'),
    (15,'Giugiulesti - Comrat'),
    (16,'Comrat - Giugiulesti'),
    (17,'Etulia - Comrat'),
    (18,'Comrat - Etulia');


insert into
  routestation
  (id, route_id, line_Element, station_index, departure_duration)
values
  -- (id,route,lineElement,index,waiting)
  -- balti - ocnita
  (1, 2, 1, 0, 0),
  (2, 2, 2, 1, 1),
  (3, 2, 3, 2, 1),
  (4, 2, 4, 3, 1),
  (5, 2, 5, 4, 1),
  (6, 2, 6, 5, 1),
  (7, 2, 7, 6, 1),
  (8, 2, 8, 7, 1),
  (9, 2, 9, 8, 1),
  (10, 2, 10, 9, 1),
  (11, 2, 11, 10, 0),

  -- ocnita - balti
  (12, 1, 12, 0, 0),
  (13, 1, 13, 1, 1),
  (14, 1, 14, 2, 1),
  (15, 1, 15, 3, 1),
  (16, 1, 16, 4, 1),
  (17, 1, 17, 5, 1),
  (18, 1, 18, 6, 1),
  (19, 1, 19, 7, 1),
  (20, 1, 20, 8, 1),
  (21, 1, 21, 9, 1),
  (22, 1, 22, 10, 0),

  -- balti - ribnita
  (23, 3, 23, 0, 0),
  (24, 3, 24, 1, 1),
  (25, 3, 25, 2, 1),
  (26, 3, 26, 3, 1),
  (27, 3, 27, 4, 1),
  (28, 3, 28, 5, 1),
  (29, 3, 29, 6, 1),
  (30, 3, 30, 7, 1),
  (31, 3, 31, 8, 1),
  (32, 3, 32, 9, 1),
  (33, 3, 33, 10, 1),
  (34, 3, 34, 11, 1),
  (35, 3, 35, 12, 1),
  (36, 3, 36, 13, 1),
  (37, 3, 37, 14, 1),
  (38, 3, 38, 15, 0),

    -- ribnita - balti
  (39, 4, 47, 0, 0),
  (40, 4, 48, 1, 1),
  (41, 4, 49, 2, 1),
  (42, 4, 50, 3, 1),
  (43, 4, 51, 4, 1),
  (44, 4, 52, 5, 1),
  (45, 4, 53, 6, 1),
  (46, 4, 54, 7, 1),
  (47, 4, 55, 8, 1),
  (48, 4, 56, 9, 1),
  (49, 4, 57, 10, 1),
  (50, 4, 58, 11, 1),
  (51, 4, 59, 12, 1),
  (52, 4, 60, 13, 1),
  (53, 4, 61, 14, 1),
  (54, 4, 62, 15, 0);


-- OCNITA-BALTI TEST TRAVEL
INSERT
INTO
  travel
  (id, route_id)
VALUES
  (1, 1);


-- BALTI-OCNITA TEST TRAVEL
INSERT
INTO
  travel
  (id, route_id)
VALUES
  (2, 2);



-- BALTI-RIBNITA TEST TRAVEL
INSERT
INTO
  travel
  (id, route_id)
VALUES
  (3, 3);

  -- RIBNITA-BALTI TEST TRAVEL
INSERT
INTO
  travel
  (id, route_id)
VALUES
  (4, 4);

