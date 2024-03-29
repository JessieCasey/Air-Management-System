package com.air.managment.system.flight.dto;

import com.air.managment.system.common.validator.trimmed.Trimmed;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static com.air.managment.system.common.ApplicationConstants.Web.DataValidation.*;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FlightDto {

    @Min(1)
    Long id;

    @Min(1)
    @NotNull
    Long airCompanyId;

    @Min(1)
    @NotNull
    Long airplaneId;

    @Trimmed
    @NotBlank(message = "Departure country is required")
    @Size(min = MIN_SIZE_OF_DEPARTURE_COUNTRY, max = MAX_SIZE_OF_DEPARTURE_COUNTRY)
    String departureCountry;

    @Trimmed
    @NotBlank(message = "Destination country is required")
    @Size(min = MIN_SIZE_OF_DESTINATION_COUNTRY, max = MAX_SIZE_OF_DESTINATION_COUNTRY)
    String destinationCountry;

    @Min(0)
    @NotNull
    long estimatedFlightTimeMinutes;

    @Min(0)
    @NotNull
    Double distance;
}
