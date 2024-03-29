package com.air.managment.system.aircompany.service;

import com.air.managment.system.aircompany.AirCompany;
import com.air.managment.system.aircompany.dto.AirCompanyDto;
import lombok.NonNull;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface AirCompanyService {
    AirCompany createAirCompany(final @NotNull @Valid AirCompanyDto airCompanyDto);

    List<AirCompany> getAllAirCompanies();

    AirCompany getAirCompanyById(@Min(1) final long id);

    AirCompany updateAirCompany(@NonNull final AirCompanyDto airCompany);

    void deleteAirCompany(@Min(1) final long id);

    boolean notExistsAirCompanyById(@Min(1) final long id);

    boolean existsAirCompanyByCompanyName(@NonNull final String companyName);

}
