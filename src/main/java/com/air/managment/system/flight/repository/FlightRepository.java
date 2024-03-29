package com.air.managment.system.flight.repository;

import com.air.managment.system.flight.Flight;
import com.air.managment.system.flight.FlightStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findByAirCompanyNameAndFlightStatus(String companyName, FlightStatus status);

    @Query("SELECT f FROM Flight f WHERE f.flightStatus = 'ACTIVE' AND f.startedAt < :twentyFourHoursAgo")
    List<Flight> findActiveFlightsStartedMoreThan24HoursAgo(@Param("twentyFourHoursAgo") ZonedDateTime twentyFourHoursAgo);

    List<Flight> findAllByFlightStatus(FlightStatus flightStatus);

}
