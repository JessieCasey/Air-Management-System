package com.air.managment.system.flight;

import com.air.managment.system.aircompany.AirCompany;
import com.air.managment.system.airplane.Airplane;
import com.air.managment.system.common.convertor.DurationToLongConverter;
import com.air.managment.system.common.validator.trimmed.Trimmed;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Duration;
import java.time.ZonedDateTime;

import static com.air.managment.system.common.ApplicationConstants.Web.DataValidation.MAX_SIZE_OF_DESTINATION_COUNTRY;
import static com.air.managment.system.common.ApplicationConstants.Web.DataValidation.MIN_SIZE_OF_DESTINATION_COUNTRY;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "flights")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "flight_status")
    FlightStatus flightStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "air_company_id")
    AirCompany airCompany;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airplane_id")
    Airplane airplane;

    @Trimmed
    @NotBlank(message = "Destination country is required")
    @Size(min = MIN_SIZE_OF_DESTINATION_COUNTRY, max = MAX_SIZE_OF_DESTINATION_COUNTRY)
    @Column(name = "departure_country")
    String departureCountry;

    @Trimmed
    @NotBlank(message = "Destination country is required")
    @Size(min = MIN_SIZE_OF_DESTINATION_COUNTRY, max = MAX_SIZE_OF_DESTINATION_COUNTRY)
    @Column(name = "destination_country")
    String destinationCountry;

    @Min(0)
    @NotNull
    Double distance;

    @Column(name = "estimated_flight_time")
    @Convert(converter = DurationToLongConverter.class)
    Duration estimatedFlightTime;

    @Column(name = "started_at")
    ZonedDateTime startedAt;

    @Column(name = "ended_at")
    ZonedDateTime endedAt;

    @Column(name = "delay_started_at")
    ZonedDateTime delayStartedAt;

    @Column(name = "created_at")
    ZonedDateTime createdAt;
}
