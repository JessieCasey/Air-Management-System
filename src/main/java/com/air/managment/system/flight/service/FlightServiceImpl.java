package com.air.managment.system.flight.service;

import com.air.managment.system.aircompany.service.AirCompanyService;
import com.air.managment.system.airplane.service.AirplaneService;
import com.air.managment.system.common.mapper.FlightMapper;
import com.air.managment.system.exception.NullArgumentException;
import com.air.managment.system.exception.airCompany.AirCompanyNotExistException;
import com.air.managment.system.exception.flight.FlightAlreadyExistException;
import com.air.managment.system.exception.flight.FlightNotExistException;
import com.air.managment.system.exception.flight.StatusFlightNotExistException;
import com.air.managment.system.flight.Flight;
import com.air.managment.system.flight.FlightStatus;
import com.air.managment.system.flight.dto.FlightDto;
import com.air.managment.system.flight.repository.FlightRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Validated
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;
    private final AirCompanyService airCompanyService;
    private final AirplaneService airplaneService;
    private final FlightMapper flightMapper;

    @Override
    public Flight createFlight(final @Valid @NotNull FlightDto flightDto) {
        final var flightId = flightDto.getId();
        if (flightId != null && flightIdExistById(flightId)) {
            throw new FlightAlreadyExistException(flightId);
        } else {
            Flight flight = flightMapper.toFlight(flightDto);
            final var airCompanyId = flightDto.getAirCompanyId();
            if (airCompanyId == null) {
                throw new NullArgumentException("airCompany");
            } else {
                flight.setAirCompany(airCompanyService.getAirCompanyById(airCompanyId));
            }
            final var airplaneId = flightDto.getAirplaneId();
            if (airplaneId == null) {
                throw new NullArgumentException("airplane");
            } else {
                flight.setAirplane(airplaneService.getAirplaneById(airplaneId));
            }
            flight.setFlightStatus(FlightStatus.PENDING);
            flight.setCreatedAt(ZonedDateTime.now());
            return flightRepository.save(flight);
        }
    }

    private boolean flightIdExistById(@Min(1) final long flightId) {
        return flightRepository.existsById(flightId);
    }

    @Override
    public Flight getFlightById(final long id) {
        return flightRepository.findById(id).orElseThrow(() -> new FlightNotExistException(id));
    }

    @Override
    public List<Flight> getCompanyFlightsByStatus(@NonNull final String companyName, @NonNull final FlightStatus status) {
        if (airCompanyService.existsAirCompanyByCompanyName(companyName)) {
            return flightRepository.findByAirCompanyNameAndFlightStatus(companyName, status);
        } else {
            throw new AirCompanyNotExistException(companyName);
        }
    }

    @Override
    public List<Flight> getActiveFlightsStartedMoreThan24HoursAgo() {
        return flightRepository.findActiveFlightsStartedMoreThan24HoursAgo(ZonedDateTime.now().minusHours(24));
    }

    @Override
    public Flight changeFlightStatus(@Min(1) final long flightId, @NonNull final FlightStatus newStatus) {
        Flight flightById = getFlightById(flightId);
        ZonedDateTime now = ZonedDateTime.now();
        switch (newStatus) {
            case DELAYED:
                flightById.setDelayStartedAt(now);
                break;
            case ACTIVE:
                flightById.setStartedAt(now);
                break;
            case COMPLETED:
                flightById.setEndedAt(now);
                break;
            case PENDING:
                flightById.setStartedAt(null);
                flightById.setEndedAt(null);
                flightById.setDelayStartedAt(null);
                break;
            default:
                throw new StatusFlightNotExistException();
        }
        flightById.setFlightStatus(newStatus);
        return flightRepository.save(flightById);
    }

    @Override
    public List<Flight> getCompletedFlightsWithExceededEndTime() {
        return flightRepository.findAllByFlightStatus(FlightStatus.COMPLETED).stream()
                .filter(this::isEndTimeDelayed)
                .collect(Collectors.toList());
    }

    private boolean isEndTimeDelayed(@NonNull final Flight flight) {
        Duration estimatedFlightDuration = flight.getEstimatedFlightTime();
        Duration actualFlightDuration = Duration.between(flight.getStartedAt(), flight.getEndedAt());
        return actualFlightDuration.minus(estimatedFlightDuration).toMinutes() > 0;
    }
}
