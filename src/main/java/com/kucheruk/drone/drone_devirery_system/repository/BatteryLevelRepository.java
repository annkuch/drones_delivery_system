package com.kucheruk.drone.drone_devirery_system.repository;

import com.kucheruk.drone.drone_devirery_system.model.BatteryLevel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BatteryLevelRepository extends JpaRepository<BatteryLevel, Long> {
}
