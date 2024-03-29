-- Insert test data for Air Companies
INSERT INTO air_companies (name, company_type, founded_at)
VALUES ('AirCanada', 'International', '1964-01-01'),
       ('IcelandAir', 'International', '1937-06-03'),
       ('Ryanair', 'Low-cost', '1984-07-28');


-- Insert test data for Airplanes
INSERT INTO airplanes (name, factory_serial_number, air_company_id, number_of_flights, flight_distance, fuel_capacity,
                       type, created_at)
VALUES
    ('Boeing 737', 'B737001', 1, 100, 5000.0, 20000.0, 'Passenger', '2020-01-01'),
    ('Airbus A320', 'A320001', 2, 150, 6000.0, 22000.0, 'Passenger', '2018-06-15'),
    ('Boeing 747', 'B747001', 3, 80, 10000.0, 50000.0, 'Cargo', '2019-03-10'),
    ('Boeing 787', 'B787001', 1, 120, 8000.0, 30000.0, 'Passenger', '2017-02-28'),
    ('Airbus A350', 'A350001', 2, 90, 7000.0, 28000.0, 'Passenger', '2019-11-10'),
    ('Boeing 777', 'B777001', 3, 110, 9000.0, 35000.0, 'Cargo', '2016-09-03'),
    ('Embraer E190', 'E190001', 1, 95, 4000.0, 18000.0, 'Passenger', '2018-08-20'),
    ('Airbus A380', 'A380001', 2, 130, 12000.0, 45000.0, 'Passenger', '2015-07-12'),
    ('Boeing 737 MAX', 'B737MAX001', 3, 70, 5500.0, 21000.0, 'Passenger', '2019-05-05'),
    ('Bombardier CRJ900', 'CRJ900001', 1, 85, 3500.0, 16000.0, 'Passenger', '2016-11-30');

--
-- -- Insert test data for Flights
INSERT INTO flights (flight_status, air_company_id, airplane_id, departure_country, destination_country,
                     distance, estimated_flight_time, started_at, ended_at, delay_started_at, created_at)
VALUES
    ('ACTIVE', 1, 1, 'USA', 'UK', 4000.0, 10800, '2024-03-26 12:00:00', NULL, NULL, '2023-01-01 10:00:00'),
    ('DELAYED', 1, 2, 'France', 'Germany', 500.0, 5000, '2023-01-02 08:00:00', NULL, '2023-01-02 08:15:00', '2023-01-02 07:00:00'),
    ('COMPLETED', 3, 3, 'China', 'Australia', 8000.0, 15800, '2023-01-03 15:00:00', '2023-01-03 23:00:00', NULL, '2023-01-03 12:00:00'),
    ('PENDING', 2, 4, 'Germany', 'USA', 6000.0, 36000, NULL, NULL, NULL, '2024-03-27 08:00:00'),
    ('ACTIVE', 3, 5, 'Australia', 'Japan', 4000.0, 31000, '2024-03-25 13:30:00', NULL, NULL, '2024-03-24 12:00:00'),
    ('ACTIVE', 1, 6, 'UK', 'China', 7000.0, 10800, '2024-03-29 08:45:00', NULL, NULL, '2024-03-29 07:00:00'),
    ('COMPLETED', 2, 7, 'France', 'Australia', 9000.0, 36000, '2024-03-30 09:00:00', '2024-03-30 21:00:00', NULL, '2024-03-30 07:00:00'),
    ('PENDING', 3, 8, 'Japan', 'USA', 10000.0, 780000, NULL, NULL, NULL, '2024-03-31 09:00:00'),
    ('COMPLETED', 1, 9, 'Germany', 'UK', 2000.0, 240000, '2024-04-01 07:30:00', '2024-04-01 11:00:00', NULL, '2024-04-01 05:00:00'),
    ('COMPLETED', 2, 10, 'Australia', 'China', 5000.0, 540000, '2023-04-02 14:00:00', '2023-04-02 20:00:00', NULL, '2023-04-02 12:00:00');

