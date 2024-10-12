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
  (54, 4, 62, 15, 0),

    -- ungheni - balti
  (55, 5, 63, 0, 0),
  (56, 5, 64, 1, 1),
  (57, 5, 65, 2, 1),
  (58, 5, 66, 3, 1),
  (59, 5, 67, 4, 1),
  (60, 5, 68, 5, 1),
  (61, 5, 69, 6, 1),
  (62, 5, 70, 7, 1),
  (63, 5, 71, 8, 1),
  (64, 5, 72, 9, 1),
  (65, 5, 73, 10, 1),
  (66, 5, 74, 11, 1),
  (67, 5, 75, 12, 1),
  (68, 5, 76, 13, 1),
  (69, 5, 77, 14, 1),
  (70, 5, 78, 15, 1),
  (71, 5, 79, 16, 1),
  (72, 5, 80, 17, 0),

    -- balti - ungheni
  (73, 6, 81, 0, 0),
  (74, 6, 82, 1, 1),
  (75, 6, 83, 2, 1),
  (76, 6, 84, 3, 1),
  (77, 6, 85, 4, 1),
  (78, 6, 86, 5, 1),
  (79, 6, 87, 6, 1),
  (80, 6, 88, 7, 1),
  (81, 6, 89, 8, 1),
  (82, 6, 90, 9, 1),
  (83, 6, 91, 10, 1),
  (84, 6, 92, 11, 1),
  (85, 6, 93, 12, 1),
  (86, 6, 94, 13, 1),
  (87, 6, 95, 14, 1),
  (88, 6, 96, 15, 1),
  (89, 6, 97, 16, 1),
  (90, 6, 98, 17, 0);




-- OCNITA-BALTI TEST TRAVEL
insert
into
  travel
  (id, route_id,start_date_time)
values
  (1, 1,NOW());


-- BALTI-OCNITA TEST TRAVEL
insert
into
  travel
  (id, route_id,start_date_time)
values
  (2, 2,NOW());



-- BALTI-RIBNITA TEST TRAVEL
insert
into
  travel
  (id, route_id,start_date_time)
values
  (3, 3,NOW());

  -- RIBNITA-BALTI TEST TRAVEL
insert
into
  travel
  (id, route_id,start_date_time)
values
  (4, 4,NOW());

insert
into
  travel
  (id, route_id,start_date_time)
values
  (5, 5,NOW());

insert
into
  travel
  (id, route_id,start_date_time)
values
  (6, 6,NOW());

-- OCNITA-BALTI TEST TRAVEL - Future Date (2 hours later)
insert into travel (id, route_id, start_date_time)
values (7, 1, NOW() + INTERVAL 2 HOUR + INTERVAL 30 MINUTE);

-- BALTI-OCNITA TEST TRAVEL - Future Date (2 hours later)
insert into travel (id, route_id, start_date_time)
values (8, 2, NOW() + INTERVAL 2 HOUR + INTERVAL 30 MINUTE);

-- BALTI-RIBNITA TEST TRAVEL - Future Date (2 hours later)
insert into travel (id, route_id, start_date_time)
values (9, 3, NOW() + INTERVAL 2 HOUR + INTERVAL 30 MINUTE);

-- RIBNITA-BALTI TEST TRAVEL - Future Date (2 hours later)
insert into travel (id, route_id, start_date_time)
values (10, 4, NOW() + INTERVAL 2 HOUR + INTERVAL 30 MINUTE);

-- RIBNITA-BALTI TEST TRAVEL - Future Date (2 hours later)
insert into travel (id, route_id, start_date_time)
values (11, 5, NOW() + INTERVAL 2 HOUR + INTERVAL 30 MINUTE);

-- RIBNITA-BALTI TEST TRAVEL - Future Date (2 hours later)
insert into travel (id, route_id, start_date_time)
values (12, 6, NOW() + INTERVAL 2 HOUR + INTERVAL 30 MINUTE);

