package com.air.managment.system.airplane.service;

import com.air.managment.system.airplane.Airplane;
import com.air.managment.system.airplane.dto.AirplaneDto;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public interface AirplaneService {
    void moveAirplaneToCompany(@Min(1) final long airplaneId, @Min(1) final long moveToCompanyId);

    Airplane getAirplaneById(@Min(1) final long id);

    Airplane createAirplane(final @NotNull @Valid AirplaneDto airCompany);

    boolean existsAirplaneById(@Min(1) final long airplaneId);

}
