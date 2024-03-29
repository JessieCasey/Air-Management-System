package com.air.managment.system.airplane;

import com.air.managment.system.aircompany.AirCompany;
import com.air.managment.system.common.validator.trimmed.Trimmed;
import com.air.managment.system.common.validator.unique.UniqueName;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;

import static com.air.managment.system.common.ApplicationConstants.Web.DataValidation.*;

@Getter
@Setter
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Table(name = "airplanes")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Airplane {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    Long id;

    @Trimmed
    @NotBlank(message = "Name is required")
    @Size(min = MIN_SIZE_OF_AIRPLANE_NAME, max = MAX_SIZE_OF_AIRPLANE_NAME)
    String name;

    @Trimmed
    @NotBlank(message = "Factory Serial Number is required")
    @UniqueName(message = "Factory Serial Number for airplane must be unique", entityClass = Airplane.class)
    @Size(min = MIN_SIZE_OF_FACTORY_SERIAL_NUMBER, max = MAX_SIZE_OF_FACTORY_SERIAL_NUMBER)
    @Column(name = "factory_serial_number", unique = true, length = MAX_SIZE_OF_FACTORY_SERIAL_NUMBER)
    String factorySerialNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "air_company_id")
    AirCompany airCompany;

    @Min(0)
    @Column(name = "number_of_flights")
    Integer numberOfFlights;

    @Min(0)
    @Column(name = "flight_distance")
    Double flightDistance;

    @Min(0)
    @Column(name = "fuel_capacity")
    Double fuelCapacity;

    @Trimmed
    @NotBlank(message = "Type is required")
    String type;

    @PastOrPresent(message = "createdAt date must be in the past or present")
    @Column(name = "created_at")
    LocalDate createdAt;
}

