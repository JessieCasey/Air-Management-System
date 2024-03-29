package com.air.managment.system.flight.dto;

import com.air.managment.system.flight.FlightStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FlightResponseDto extends FlightDto {

    FlightStatus flightStatus;

    ZonedDateTime startedAt;

    ZonedDateTime endedAt;

    ZonedDateTime delayStartedAt;

    ZonedDateTime createdAt;
}
