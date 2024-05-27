package com.kucheruk.drone.drone_devirery_system.repository;

import com.kucheruk.drone.drone_devirery_system.model.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface DroneRepository extends JpaRepository<Drone, Long>{
    List<Drone> findAllByState(String state);

    Optional<Drone> findBySerialNumber(String serialNumber);

    @Modifying
    @Transactional
    @Query("UPDATE Drone d SET d.state = :state WHERE  d.serialNumber = :serialNumber")
    void setState(@Param("state") String status, @Param("serialNumber") String serialNumber);


}
