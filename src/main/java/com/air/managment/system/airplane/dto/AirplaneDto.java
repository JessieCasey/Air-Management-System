package com.air.managment.system.airplane.dto;

import com.air.managment.system.airplane.Airplane;
import com.air.managment.system.common.validator.trimmed.Trimmed;
import com.air.managment.system.common.validator.unique.UniqueName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static com.air.managment.system.common.ApplicationConstants.Web.DataValidation.MAX_SIZE_OF_AIRPLANE_NAME;
import static com.air.managment.system.common.ApplicationConstants.Web.DataValidation.MIN_SIZE_OF_AIRPLANE_NAME;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AirplaneDto {

    @Min(1)
    Long id;

    @Trimmed
    @NotBlank(message = "Name is required")
    @Size(min = MIN_SIZE_OF_AIRPLANE_NAME, max = MAX_SIZE_OF_AIRPLANE_NAME)
    String name;

    @Trimmed
    @NotBlank(message = "Factory Serial Number is required")
    @UniqueName(message = "Factory Serial Number for airplane must be unique", entityClass = Airplane.class)
    String factorySerialNumber;

    @Min(1)
    Long airCompanyId;

    @Min(0)
    Integer numberOfFlights;

    @Min(0)
    Double flightDistance;

    @Min(0)
    Double fuelCapacity;

    @Trimmed
    @NotBlank(message = "Type is required")
    String type;
}

