package com.air.managment.system.aircompany.service;

import com.air.managment.system.aircompany.AirCompany;
import com.air.managment.system.aircompany.dto.AirCompanyDto;
import com.air.managment.system.aircompany.repository.AirCompanyRepository;
import com.air.managment.system.airplane.service.AirplaneService;
import com.air.managment.system.common.mapper.AirCompanyMapper;
import com.air.managment.system.exception.airCompany.AirCompanyAlreadyExistException;
import com.air.managment.system.exception.airCompany.AirCompanyNotExistException;
import com.air.managment.system.flight.service.FlightService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Validated
@RequiredArgsConstructor
public class AirCompanyServiceImpl implements AirCompanyService {

    private final AirCompanyRepository airCompanyRepository;
    private final AirCompanyMapper airCompanyMapper;
//    private final AirplaneService airplaneService;

    @Override
    public AirCompany createAirCompany(final @NotNull @Valid AirCompanyDto airCompanyDto) {
        final var airCompanyId = airCompanyDto.getId();
        if (airCompanyId != null && airCompanyExistById(airCompanyId)) {
            throw new AirCompanyAlreadyExistException(airCompanyId);
        } else {
            AirCompany airCompany = airCompanyMapper.toAirCompany(airCompanyDto);
//            airCompany.setAirplanes(airCompanyDto.getAirplanesIds().stream().map(airplaneService::getAirplaneById).collect(Collectors.toList()));

            return airCompanyRepository.save(airCompany);
        }
    }

    private boolean airCompanyExistById(@Min(1) final Long id) {
        return airCompanyRepository.existsById(id);
    }

    @Override
    public List<AirCompany> getAllAirCompanies() {
        return airCompanyRepository.findAll();
    }

    @Override
    public AirCompany getAirCompanyById(@Min(1) final long id) {
        return airCompanyRepository.findById(id).orElseThrow(() -> new AirCompanyNotExistException(id));
    }

    @Override
    public boolean notExistsAirCompanyById(@Min(1) final long id) {
        return !airCompanyRepository.existsById(id);
    }

    @Override
    public boolean existsAirCompanyByCompanyName(@NonNull final String companyName) {
        return airCompanyRepository.existsByName(companyName);
    }

    @Override
    public AirCompany updateAirCompany(@NonNull final AirCompanyDto airCompanyDto) {
        final var airCompanyToUpdateId = airCompanyDto.getId();
        final var airCompany = getAirCompanyById(airCompanyToUpdateId);

        airCompanyMapper.updateAirCompany(airCompany, airCompanyDto);
        return airCompanyRepository.save(airCompany);
    }

    @Override
    public void deleteAirCompany(@Min(1) final long id) {
        airCompanyRepository.delete(getAirCompanyById(id));
    }
}
