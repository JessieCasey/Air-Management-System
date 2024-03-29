package com.air.managment.system.common.mapper;

import com.air.managment.system.aircompany.AirCompany;
import com.air.managment.system.aircompany.dto.AirCompanyDto;
import com.air.managment.system.airplane.Airplane;
import com.air.managment.system.airplane.dto.AirplaneDto;
import com.air.managment.system.airplane.dto.AirplaneResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.Duration;

@Mapper(componentModel = "spring")
public interface AirplaneMapper {

    /**
     * Converts {@link AirCompanyDto} into {@link AirCompany}.
     */
    Airplane toAirplane(AirplaneDto airplaneDto);

    /**
     * Converts {@link AirCompany} into {@link AirCompanyDto}.
     */
    @Mapping(source = "airCompany", target = "airCompanyId", qualifiedByName = "getIdFromAirCompany")
    AirplaneResponseDto toAirplaneResponseDto(Airplane airplaneDto);

    @Named("getIdFromAirCompany")
    default Long getIdFromAirCompany(AirCompany airCompany) {
        return airCompany != null ? airCompany.getId() : null;
    }
}
