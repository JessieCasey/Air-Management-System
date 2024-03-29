package com.air.managment.system.common.mapper;

import com.air.managment.system.aircompany.AirCompany;
import com.air.managment.system.aircompany.dto.AirCompanyDto;
import com.air.managment.system.aircompany.dto.AirCompanyResponseDto;
import com.air.managment.system.airplane.Airplane;
import com.air.managment.system.airplane.dto.AirplaneResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {AirplaneMapper.class, FlightMapper.class})
public interface AirCompanyMapper {

    /**
     * Converts {@link AirCompanyDto} into {@link AirCompany}.
     */
    AirCompany toAirCompany(AirCompanyDto airCompanyDto);

    /**
     * Converts {@link AirCompany} into {@link AirCompanyDto}.
     */
    AirCompanyResponseDto toAirCompanyDto(AirCompany airCompanyDto);


    /**
     * Converts {@link List<AirCompany>} into {@link List<AirCompanyDto>}.
     */
    List<AirCompanyResponseDto> toAirCompanyDtoList(List<AirCompany> airCompanies);

    /**
     * This method updates the AirCompany object with the data from the AirCompanyDto.
     *
     * @param airCompany    The User object to be updated.
     * @param airCompanyDto The source of the updated data.
     */
    void updateAirCompany(@MappingTarget AirCompany airCompany, AirCompanyDto airCompanyDto);
}
