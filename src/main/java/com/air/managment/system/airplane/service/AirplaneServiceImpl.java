package com.air.managment.system.airplane.service;

import com.air.managment.system.aircompany.AirCompany;
import com.air.managment.system.aircompany.service.AirCompanyService;
import com.air.managment.system.airplane.Airplane;
import com.air.managment.system.airplane.dto.AirplaneDto;
import com.air.managment.system.airplane.repository.AirplaneRepository;
import com.air.managment.system.common.mapper.AirplaneMapper;
import com.air.managment.system.exception.NullArgumentException;
import com.air.managment.system.exception.airCompany.AirCompanyNotExistException;
import com.air.managment.system.exception.airplane.AirplaneAlreadyExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Service
@Validated
@RequiredArgsConstructor
public class AirplaneServiceImpl implements AirplaneService {

    private final AirplaneRepository airplaneRepository;
    private final AirCompanyService airCompanyService;
    private final AirplaneMapper airplaneMapper;

    @Override
    public Airplane createAirplane(final @NotNull @Valid AirplaneDto airplaneDto) {
        final var airplaneId = airplaneDto.getId();
        if (airplaneId != null && existsAirplaneById(airplaneId)) {
            throw new AirplaneAlreadyExistException(airplaneId);
        } else {
            Long airCompanyId = airplaneDto.getAirCompanyId();
            Airplane airplane = airplaneMapper.toAirplane(airplaneDto);
            if (airCompanyId != null) {
                if (airCompanyService.notExistsAirCompanyById(airCompanyId)) {
                    throw new NullArgumentException("airCompany");
                } else {
                    airplane.setAirCompany(airCompanyService.getAirCompanyById(airCompanyId));
                }
            }
            return airplaneRepository.save(airplane);
        }
    }

    @Override
    public boolean existsAirplaneById(@Min(1) final long airplaneId) {
        return airplaneRepository.existsById(airplaneId);
    }

    @Override
    @Transactional
    public void moveAirplaneToCompany(@Min(1) final long airplaneId, @Min(1) final long moveToCompanyId) {
        final var airplane = getAirplaneById(airplaneId);
        final var airplaneAirCompany = airplane.getAirCompany();
        if (airplaneAirCompany != null) {
            airplaneAirCompany.getAirplanes().remove(airplane);
        }
        final var airCompany = airCompanyService.getAirCompanyById(moveToCompanyId);
        airplane.setAirCompany(airCompany);
        airCompany.getAirplanes().add(airplane);
        airplaneRepository.save(airplane);
    }

    @Override
    public Airplane getAirplaneById(@Min(1) final long id) {
        return airplaneRepository.findById(id).orElseThrow(() -> new AirCompanyNotExistException(id));
    }

}
