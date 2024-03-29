package com.air.managment.system.aircompany;

import com.air.managment.system.airplane.Airplane;
import com.air.managment.system.common.validator.trimmed.Trimmed;
import com.air.managment.system.common.validator.unique.UniqueName;
import com.air.managment.system.flight.Flight;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.air.managment.system.common.ApplicationConstants.Web.DataValidation.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "air_companies")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AirCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Trimmed
    @NotBlank(message = "Name is required")
    @Size(min = MIN_SIZE_OF_AIR_COMPANY_NAME, max = MAX_SIZE_OF_AIR_COMPANY_NAME)
    @UniqueName(message = "Name for Air Company must be unique", entityClass = AirCompany.class)
    @Column(name = "name", unique = true, length = MAX_SIZE_OF_AIR_COMPANY_NAME)
    String name;

    @Trimmed
    @NotBlank(message = "Company type is required")
    @Size(min = MIN_SIZE_OF_AIR_COMPANY_TYPE, max = MAX_SIZE_OF_AIR_COMPANY_TYPE)
    @Column(name = "company_type")
    String companyType;

    @PastOrPresent(message = "Founded date must be in the past or present")
    @NotNull(message = "Founded date is required")
    @Column(name = "founded_at")
    LocalDate foundedAt;

    @OneToMany(mappedBy = "airCompany", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
    List<Airplane> airplanes = new ArrayList<>();

    @OneToMany(mappedBy = "airCompany", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    List<Flight> flights = new ArrayList<>();

}