package com.air.managment.system.airplane;

import com.air.managment.system.airplane.dto.AirplaneDto;
import com.air.managment.system.airplane.dto.AirplaneResponseDto;
import com.air.managment.system.airplane.service.AirplaneService;
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

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/airplanes")
public class AirplaneController {

    private final AirplaneService airplaneService;
    private final AirplaneMapper airplaneMapper;

    @PostMapping
    public ResponseEntity<AirplaneResponseDto> createAirplane(@RequestBody @NotNull @Valid final AirplaneDto airplaneRequest) {
        final var createdAirplane = airplaneService.createAirplane(airplaneRequest);
        log.info("Airplane created successfully: id[{}]", createdAirplane.getId());
        final var airplaneResponse = airplaneMapper.toAirplaneResponseDto(createdAirplane);
        return ResponseEntity.status(HttpStatus.CREATED).body(airplaneResponse);
    }

    @PutMapping("/{airplaneId}/assign")
    public ResponseEntity<Void> moveAirplaneToCompany(@PathVariable @Min(1) final long airplaneId,
                                                      @RequestParam @Min(1) final long moveToCompanyId) {
        airplaneService.moveAirplaneToCompany(airplaneId, moveToCompanyId);
        log.info("Airplane moved successfully: airplane with id[{}] to company with id[{}]", airplaneId, moveToCompanyId);
        return ResponseEntity.noContent().build();
    }
}

