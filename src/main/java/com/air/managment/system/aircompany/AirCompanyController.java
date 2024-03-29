package com.air.managment.system.aircompany;

import com.air.managment.system.aircompany.dto.AirCompanyDto;
import com.air.managment.system.aircompany.dto.AirCompanyResponseDto;
import com.air.managment.system.aircompany.service.AirCompanyService;
import com.air.managment.system.common.mapper.AirCompanyMapper;
import com.air.managment.system.common.mapper.AirplaneMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

/**
 * CRUD operations for {@link AirCompany} entity
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/air-companies")
public class AirCompanyController {

    private final AirCompanyService airCompanyService;
    private final AirCompanyMapper airCompanyMapper;

    @PostMapping
    public ResponseEntity<AirCompanyResponseDto> createAirCompany(@RequestBody @NotNull @Valid final AirCompanyDto airCompanyRequest) {
        final var createdCompany = airCompanyService.createAirCompany(airCompanyRequest);
        log.info("Air company created successfully: {}", createdCompany.getId());
        final var airCompanyResponse = airCompanyMapper.toAirCompanyDto(createdCompany);
        return ResponseEntity.status(HttpStatus.CREATED).body(airCompanyResponse);
    }

    @GetMapping
    public ResponseEntity<List<AirCompanyResponseDto>> getAllAirCompanies() {
        final var airCompanies = airCompanyService.getAllAirCompanies();
        final var airCompaniesResponse = airCompanyMapper.toAirCompanyDtoList(airCompanies);
        return ResponseEntity.ok().body(airCompaniesResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AirCompanyResponseDto> getAirCompanyById(@PathVariable @Min(1) final long id) {
        final var airCompany = airCompanyService.getAirCompanyById(id);
        final var airCompanyResponse = airCompanyMapper.toAirCompanyDto(airCompany);
        return ResponseEntity.ok().body(airCompanyResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AirCompanyResponseDto> updateAirCompany(@PathVariable @Min(1) final long id,
                                                                  @RequestBody @NotNull @Valid final AirCompanyDto airCompanyRequest) {
        airCompanyRequest.setId(id);
        final var airCompanyUpdated = airCompanyService.updateAirCompany(airCompanyRequest);
        log.info("User with id[{}] was updated", airCompanyUpdated.getId());
        final var airCompanyResponse = airCompanyMapper.toAirCompanyDto(airCompanyUpdated);
        return ResponseEntity.ok().body(airCompanyResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAirCompany(@PathVariable @Min(1) final long id) {
        airCompanyService.deleteAirCompany(id);
        log.info("Air company with id[{}] was deleted", id);
        return ResponseEntity.noContent().build();
    }
}

