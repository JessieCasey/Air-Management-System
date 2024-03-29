package com.air.managment.system.flight;

import com.air.managment.system.common.mapper.FlightMapper;
import com.air.managment.system.flight.dto.FlightDto;
import com.air.managment.system.flight.dto.FlightResponseDto;
import com.air.managment.system.flight.service.FlightService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/flights")
@RequiredArgsConstructor
@Validated
public class FlightController {

    private final FlightService flightService;
    private final FlightMapper flightMapper;

    @PostMapping
    public ResponseEntity<FlightResponseDto> createFlight(@RequestBody @Valid @NotNull final FlightDto flightRequest) {
        final var createdFlight = flightService.createFlight(flightRequest);
        log.info("Flight created successfully: {}", createdFlight.getId());
        final var flightResponse = flightMapper.toFlightResponseDto(createdFlight);
        return ResponseEntity.status(HttpStatus.CREATED).body(flightResponse);
    }

    @GetMapping("/{companyName}")
    public ResponseEntity<List<FlightResponseDto>> getCompanyFlightsByStatus(@PathVariable @NotNull final String companyName,
                                                                             @RequestParam @NotNull final FlightStatus status) {
        final var flights = flightService.getCompanyFlightsByStatus(companyName, status);
        if (flights.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            var flightResponseDtoList = flightMapper.toFlightDtoList(flights);
            return ResponseEntity.ok(flightResponseDtoList);
        }
    }

    @GetMapping("/active/started-more-than-24-hours-ago")
    public ResponseEntity<List<FlightResponseDto>> getActiveFlightsStartedMoreThan24HoursAgo() {
        final var flights = flightService.getActiveFlightsStartedMoreThan24HoursAgo();
        if (flights.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            final var flightResponseDtoList = flightMapper.toFlightDtoList(flights);
            return ResponseEntity.ok(flightResponseDtoList);
        }
    }

    @PutMapping("/{flightId}/status")
    public ResponseEntity<FlightResponseDto> changeFlightStatus(@PathVariable @Min(1) final long flightId,
                                                                @RequestParam @NotNull final FlightStatus newStatus) {
        final var flight = flightService.changeFlightStatus(flightId, newStatus);
        log.info("Flight status changed successfully: flight with id[{}] updated to status[{}]", flightId, newStatus);
        final var flightResponseDto = flightMapper.toFlightResponseDto(flight);
        return ResponseEntity.ok().body(flightResponseDto);
    }

    @GetMapping("/completed/exceeded-estimated-time")
    public ResponseEntity<List<FlightResponseDto>> getCompletedFlightsWithExceededEndTime() {
        final var completedFlights = flightService.getCompletedFlightsWithExceededEndTime();
        final var filteredFlights = flightMapper.toFlightDtoList(completedFlights);
        return ResponseEntity.ok(filteredFlights);
    }
}

