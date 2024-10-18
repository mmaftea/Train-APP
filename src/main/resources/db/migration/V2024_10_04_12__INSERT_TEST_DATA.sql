INSERT INTO vagon_class
VALUES
    ('1st class with seat', 54, 0, 1.7),
    ('1st class without seat', 54, 25, 1.3),
    ('2st class with seat', 72, 0, 1.5),
    ('2st class without seat', 72, 25, 1.1);

INSERT INTO train
VALUES
    (1,'MD-NA-001'),
    (2,'MD-NA-002'),
    (3,'MD-NA-003'),
    (4,'MD-NA-004');

INSERT INTO trainloadedvagons
VALUES
    (1, 1),
    (2, 2),
    (3, 3),
    (4, 4);

INSERT INTO vagon
VALUES
    (1,'1st class with seat'),
    (2,'2st class with seat'),
    (3,'2st class without seat'),
    (4,'2st class without seat');

INSERT INTO trainvagon
VALUES
    (1,1,1),
    (1,2,2),
    (1,3,3),
    (1,4,4),

    (2,1,1),
    (2,2,2),
    (2,3,3),
    (2,4,4),

    (3,1,1),
    (3,2,2),
    (3,3,3),
    (3,4,4),

    (4,1,1),
    (4,2,2),
    (4,3,3),
    (4,4,4);



-- Ungheni-Chisinau TEST TRAVEL
INSERT INTO travel
VALUES
    (1, 8, 1, '2024-01-01 08:30:00'),
    (2, 8, 1, '2024-01-02 08:30:00'),
    (3, 8, 1, '2024-01-01 15:00:00'),
    (4, 8, 1, '2024-01-02 15:00:00');

-- Chisinau-Ungheni TEST TRAVEL
INSERT INTO travel
VALUES
    (5, 7, 1, '2024-01-01 08:30:00'),
    (6, 7, 1, '2024-01-02 08:30:00'),
    (7, 7, 1, '2024-01-01 15:00:00'),
    (8, 7, 1, '2024-01-02 15:00:00');

-- Tiraspol-Chisinau TEST TRAVEL
INSERT INTO travel
VALUES
    (9, 9, 1, '2024-01-01 08:30:00'),
    (10, 9, 1, '2024-01-02 08:30:00'),
    (11, 9, 1, '2024-01-01 15:00:00'),
    (12, 9, 1, '2024-01-02 15:00:00');

-- Chisinau-Tiraspol TEST TRAVEL
INSERT INTO travel
VALUES
    (13, 10, 1, '2024-01-01 08:30:00'),
    (14, 10, 1, '2024-01-02 08:30:00'),
    (15, 10, 1, '2024-01-01 15:00:00'),
    (16, 10, 1, '2024-01-02 15:00:00');




