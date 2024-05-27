package com.kucheruk.drone.drone_devirery_system.controller;

import com.kucheruk.drone.drone_devirery_system.data.response.ApiResponse;
import com.kucheruk.drone.drone_devirery_system.data.response.BatteryResponse;
import com.kucheruk.drone.drone_devirery_system.model.Drone;
import com.kucheruk.drone.drone_devirery_system.service.DroneService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/drone/")
public class DroneController {
    @Autowired
    private DroneService droneService;
    @PostMapping("register")
    public ResponseEntity<ApiResponse> register(@Valid @RequestBody Drone param)
    {
        Drone drone = droneService.register(param);
        ApiResponse response = new ApiResponse(
                "success",
                "Drone registered successfully",
                drone
        );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @GetMapping("available")
    public ResponseEntity<ApiResponse> available()
    {
        List<Drone> drones = droneService.available("IDLE");
        ApiResponse response = new ApiResponse(
                "success",
                "Available drones loaded successfully",
                drones
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("battery/{serialNumber}")
    public ResponseEntity<ApiResponse> battery(@PathVariable(name = "serialNumber") String serialNumber)
    {
        Drone drone = droneService.get(serialNumber);
        if(drone == null)
        {
            return new ResponseEntity<>(new ApiResponse(
                    "error",
                    "Drone with serial number " + serialNumber + " not found!",
                    null
            ), HttpStatus.NOT_FOUND);
        }
        else
        {
            return new ResponseEntity<>(new ApiResponse(
                    "success",
                    "Drone loaded successfully!",
                    new BatteryResponse(drone.getSerialNumber(), drone.getBatteryCapacity() + "%")
            ), HttpStatus.OK);
        }
    }
}
