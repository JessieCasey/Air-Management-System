package com.air.managment.system.airplane.repository;

import com.air.managment.system.airplane.Airplane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirplaneRepository extends JpaRepository<Airplane, Long> {
    boolean existsByFactorySerialNumber(String factorySerialNumber);
}
