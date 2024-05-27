package com.kucheruk.drone.drone_devirery_system.repository;


import com.kucheruk.drone.drone_devirery_system.model.Delivery;
import com.kucheruk.drone.drone_devirery_system.model.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

    Delivery findByDrone(Drone drone);
    @Modifying
    @Transactional
    @Query("UPDATE Delivery d SET d.dispatchedAt = :dispatchedAt WHERE d.id = :id")
    void setDispatched(@Param("dispatchedAt") Instant dispatchedAt, @Param("id") Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Delivery d SET d.deliveredAt = :deliveredAt WHERE d.id = :id")
    void setDelivered(@Param("deliveredAt") Instant deliveredAt, @Param("id") Long id);
}
