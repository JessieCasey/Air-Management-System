package com.air.managment.system.aircompany.dto;

import com.air.managment.system.airplane.dto.AirplaneResponseDto;
import com.air.managment.system.flight.dto.FlightResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AirCompanyResponseDto extends AirCompanyDto {
    List<AirplaneResponseDto> airplanes;
    List<FlightResponseDto> flights;
}
