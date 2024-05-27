package com.kucheruk.drone.drone_devirery_system.service;

import com.kucheruk.drone.drone_devirery_system.model.Drone;
import com.kucheruk.drone.drone_devirery_system.repository.BatteryLevelRepository;
import com.kucheruk.drone.drone_devirery_system.repository.DroneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DroneService {
    @Autowired
    private DroneRepository droneRepository;

    @Autowired
    private BatteryLevelRepository batteryLevelRepository;

    public Drone register(Drone drone)
    {
        return droneRepository.save(drone);
    }

    public List<Drone> available(String state)
    {
        return droneRepository.findAllByState(state);
    }
    public Drone get(String serialNumber) {
        Optional<Drone> optionalDrone = droneRepository.findBySerialNumber(serialNumber);
        return optionalDrone.orElse(null);
    }
}
