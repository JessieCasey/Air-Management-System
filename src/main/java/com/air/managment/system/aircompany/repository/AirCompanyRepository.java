package com.air.managment.system.aircompany.repository;

import com.air.managment.system.aircompany.AirCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirCompanyRepository extends JpaRepository<AirCompany, Long> {
    boolean existsByName(String name);

}
