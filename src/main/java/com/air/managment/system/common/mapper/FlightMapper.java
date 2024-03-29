package com.air.managment.system.common.mapper;

import com.air.managment.system.aircompany.AirCompany;
import com.air.managment.system.aircompany.dto.AirCompanyDto;
import com.air.managment.system.airplane.Airplane;
import com.air.managment.system.flight.Flight;
import com.air.managment.system.flight.dto.FlightDto;
import com.air.managment.system.flight.dto.FlightResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.Duration;
import java.util.List;

@Mapper(componentModel = "spring")
public interface FlightMapper {

    /**
     * Converts {@link AirCompanyDto} into {@link AirCompany}.
     */
    @Mapping(source = "estimatedFlightTimeMinutes", target = "estimatedFlightTime", qualifiedByName = "transformDurationFromDTO")
    Flight toFlight(FlightDto flightDto);

    @Named("transformDurationFromDTO")
    default Duration transformCompanyToDTO(long estimatedFlightTimeSeconds) {
        return Duration.ofMinutes(estimatedFlightTimeSeconds);
    }

    /**
     * Converts {@link Flight} into {@link FlightResponseDto}.
     */
    @Mapping(source = "estimatedFlightTime", target = "estimatedFlightTimeMinutes", qualifiedByName = "transformDurationToSeconds")
    @Mapping(source = "airCompany", target = "airCompanyId", qualifiedByName = "getIdFromAirCompany")
    @Mapping(source = "airplane", target = "airplaneId", qualifiedByName = "getIdFromAirplane")
    FlightResponseDto toFlightResponseDto(Flight flight);

    @Named("transformDurationToSeconds")
    default long transformDurationToSeconds(Duration estimatedFlightTime) {
        return estimatedFlightTime.toMinutes();
    }

    @Named("getIdFromAirCompany")
    default Long getIdFromAirCompany(AirCompany airCompany) {
        return airCompany != null ? airCompany.getId() : null;
    }

    @Named("getIdFromAirplane")
    default Long getIdFromAirplane(Airplane airplane) {
        return airplane != null ? airplane.getId() : null;
    }

    /**
     * Converts {@link List <Flight>} into {@link List<FlightResponseDto>}.
     */
    List<FlightResponseDto> toFlightDtoList(List<Flight> flights);
}
