insert into route (id,route_name)
values
    (1,'Ocnita - Balti'),
    (2,'Balti - Ocnita'),
    (3,'Balti - Ribnita'),
    (4,'Ribnita - Balti'),
    (5,'Ungheni - Balti'),
    (6,'Balti - Ungheni'),
    (7,'Chisinau - Ungheni'),-- done
    (8,'Ungheni - Chisinau'),-- done
    (9,'Tiraspol - Chisinau'),-- done
    (10,'Chisinau - Tiraspol'),-- done
    (11,'Chisinau - Comrat'),
    (12,'Comrat - Chisinau'),
    (13,'Comrat - Bender'),
    (14,'Bender - Comrat'),
    (15,'Comrat - Giugiulesti'),-- done
    (16,'Giugiulesti - Comrat'),-- done
    (17,'Etulia - Comrat'), -- done
    (18,'Comrat - Etulia'); -- done


insert into
  routestation
  (id, route_id, line_Element, station_index, departure_duration)
values
  -- (id,route,lineElement,index,waiting)
  -- Ungheni - Chisinau
  (1, 8, 13, 0, 0),
  (2, 8, 14, 1, 1),
  (3, 8, 15, 2, 1),
  (4, 8, 16, 3, 1),
  (5, 8, 17, 4, 1),
  (6, 8, 18, 5, 1),
  (7, 8, 19, 6, 1),
  (8, 8, 20, 7, 1),
  (9, 8, 21, 8, 1),
  (10, 8, 22, 9, 1),
  (11, 8, 23, 10, 1),
  (12, 8, 24, 11, 1),
  (13, 8, 25, 12, 1),
  (14, 8, 26, 13, 1),
  (15, 8, 27, 14, 1),
  (16, 8, 28, 15, 0),

  -- Chisinau-Ungheni
  (17, 7, 136, 0, 0),
  (18, 7, 137, 1, 1),
  (19, 7, 138, 2, 1),
  (20, 7, 139, 3, 1),
  (21, 7, 140, 4, 1),
  (22, 7, 141, 5, 1),
  (23, 7, 142, 6, 1),
  (24, 7, 143, 7, 1),
  (25, 7, 144, 8, 1),
  (26, 7, 145, 9, 1),
  (27, 7, 146, 10, 1),
  (28, 7, 147, 11, 1),
  (29, 7, 148, 12, 1),
  (30, 7, 149, 13, 1),
  (31, 7, 150, 14, 1),
  (32, 7, 151, 15, 0),

   -- Tiraspol-Chisinau
  (33, 9, 121, 0, 0),
  (34, 9, 122, 1, 1),
  (35, 9, 123, 2, 1),
  (36, 9, 124, 3, 1),
  (37, 9, 125, 4, 1),
  (38, 9, 126, 5, 1),
  (39, 9, 127, 6, 1),
  (40, 9, 128, 7, 1),
  (41, 9, 129, 8, 1),
  (42, 9, 130, 9, 1),
  (43, 9, 131, 10, 1),
  (44, 9, 132, 11, 1),
  (45, 9, 133, 12, 1),
  (46, 9, 134, 13, 1),
  (47, 9, 135, 14, 1),
  (48, 9, 136, 15, 0),

   -- Chisinau-Tiraspol
  (49, 10, 28, 0, 0),
  (50, 10, 29, 1, 1),
  (51, 10, 30, 2, 1),
  (52, 10, 31, 3, 1),
  (53, 10, 32, 4, 1),
  (54, 10, 33, 5, 1),
  (55, 10, 34, 6, 1),
  (56, 10, 35, 7, 1),
  (57, 10, 36, 8, 1),
  (58, 10, 37, 9, 1),
  (59, 10, 38, 10, 1),
  (60, 10, 39, 11, 1),
  (61, 10, 40, 12, 1),
  (62, 10, 41, 13, 1),
  (63, 10, 42, 14, 1),
  (64, 10, 43, 15, 0),

   -- Giugiulesti-Comrat
  (65, 16, 248, 0, 0),
  (66, 16, 249, 1, 1),
  (67, 16, 250, 2, 1),
  (68, 16, 251, 3, 1),
  (69, 16, 252, 4, 1),
  (70, 16, 253, 5, 1),
  (71, 16, 254, 6, 1),
  (72, 16, 255, 7, 1),
  (73, 16, 256, 8, 1),
  (74, 16, 257, 9, 1),
  (75, 16, 258, 10, 1),
  (76, 16, 259, 11, 1),
  (77, 16, 260, 12, 1),
  (78, 16, 261, 13, 1),
  (79, 16, 262, 14, 1),
  (80, 16, 263, 15, 1),
  (81, 16, 264, 16, 1),
  (82, 16, 265, 17, 0),

  -- Comrat-Giugiulesti
  (83, 15, 230, 0, 0),
  (84, 15, 231, 1, 1),
  (85, 15, 232, 2, 1),
  (86, 15, 233, 3, 1),
  (87, 15, 234, 4, 1),
  (88, 15, 235, 5, 1),
  (89, 15, 236, 6, 1),
  (90, 15, 237, 7, 1),
  (91, 15, 238, 8, 1),
  (92, 15, 239, 9, 1),
  (93, 15, 240, 10, 1),
  (94, 15, 241, 11, 1),
  (95, 15, 242, 12, 1),
  (96, 15, 243, 13, 1),
  (97, 15, 244, 14, 1),
  (98, 15, 245, 15, 1),
  (99, 15, 246, 16, 1),
  (100, 15, 247, 17, 0),

  -- Etulia - Comrat
  (101, 17, 284, 0, 0),
  (102, 17, 285, 1, 1),
  (103, 17, 286, 2, 1),
  (104, 17, 287, 3, 1),
  (105, 17, 288, 4, 1),
  (106, 17, 289, 5, 1),
  (107, 17, 290, 6, 1),
  (108, 17, 291, 7, 1),
  (109, 17, 292, 8, 1),
  (110, 17, 293, 9, 1), -- line change
  (111, 17, 229, 10, 1),
  (112, 17, 230, 11, 0),

  -- Comrat - Etulia
  (113, 18, 265, 0, 0),
  (114, 18, 266, 1, 1),
  (115, 18, 267, 2, 1), -- line change
  (116, 18, 295, 3, 1),
  (117, 18, 296, 4, 1),
  (118, 18, 297, 5, 1),
  (119, 18, 298, 6, 1),
  (120, 18, 299, 7, 1),
  (121, 18, 300, 8, 1),
  (122, 18, 301, 9, 1),
  (123, 18, 302, 10, 1),
  (124, 18, 303, 11, 0);
