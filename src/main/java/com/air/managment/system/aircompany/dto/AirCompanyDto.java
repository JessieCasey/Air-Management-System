package com.air.managment.system.aircompany.dto;

import com.air.managment.system.aircompany.AirCompany;
import com.air.managment.system.airplane.Airplane;
import com.air.managment.system.common.validator.trimmed.Trimmed;
import com.air.managment.system.common.validator.unique.UniqueName;
import com.air.managment.system.flight.Flight;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.air.managment.system.common.ApplicationConstants.Web.DataValidation.*;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AirCompanyDto {

    @Min(1)
    Long id;

    @Trimmed
    @NotBlank(message = "Name is required")
    @UniqueName(message = "Name for Air Company must be unique", entityClass = AirCompany.class)
    @Size(min = MIN_SIZE_OF_AIR_COMPANY_NAME, max = MAX_SIZE_OF_AIR_COMPANY_NAME)
    String name;

    @Trimmed
    @NotBlank(message = "Company type is required")
    @Size(min = MIN_SIZE_OF_AIR_COMPANY_TYPE, max = MAX_SIZE_OF_AIR_COMPANY_TYPE)
    String companyType;

    @PastOrPresent(message = "Founded date must be in the past or present")
    @NotNull(message = "Founded date is required")
    LocalDate foundedAt;

    @JsonIgnore
    List<Long> airplanesIds = new ArrayList<>();
}