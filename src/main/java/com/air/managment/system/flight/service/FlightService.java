package com.air.managment.system.flight.service;

import com.air.managment.system.flight.Flight;
import com.air.managment.system.flight.FlightStatus;
import com.air.managment.system.flight.dto.FlightDto;
import lombok.NonNull;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface FlightService {
    Flight getFlightById(@Min(1) final long id);

    List<Flight> getCompanyFlightsByStatus(@NonNull final String companyName, @NonNull final FlightStatus status);

    List<Flight> getActiveFlightsStartedMoreThan24HoursAgo();

    Flight createFlight(final @Valid @NotNull FlightDto flight);

    Flight changeFlightStatus(@Min(1) final long flightId, FlightStatus newStatus);

    List<Flight> getCompletedFlightsWithExceededEndTime();

}
