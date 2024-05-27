package com.kucheruk.drone.drone_devirery_system.controller;

import com.kucheruk.drone.drone_devirery_system.data.request.DeliveryRequest;
import com.kucheruk.drone.drone_devirery_system.data.request.DroneRequest;
import com.kucheruk.drone.drone_devirery_system.data.response.ApiResponse;
import com.kucheruk.drone.drone_devirery_system.data.response.DeliveryResponse;
import com.kucheruk.drone.drone_devirery_system.data.response.DroneItemResponse;
import com.kucheruk.drone.drone_devirery_system.model.Delivery;
import com.kucheruk.drone.drone_devirery_system.model.Drone;
import com.kucheruk.drone.drone_devirery_system.model.Item;
import com.kucheruk.drone.drone_devirery_system.service.DeliveryService;
import com.kucheruk.drone.drone_devirery_system.service.DroneService;
import com.kucheruk.drone.drone_devirery_system.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
@Validated
@RestController
@RequestMapping("/api/delivery/")
public class DeliveryController {
    @Autowired
    private DeliveryService dispatchService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private DroneService droneService;

    @PostMapping("load")
    public ResponseEntity<ApiResponse> load(@Valid @RequestBody DeliveryRequest param)
    {
        // Validate Drone Serial Number
        Drone drone = droneService.get(param.getSerialNumber());
        if(drone == null)
        {
            return new ResponseEntity<>(new ApiResponse(
                    "error",
                    "Drone with serial number " + param.getSerialNumber() + " not found!",
                    null
            ), HttpStatus.NOT_FOUND);
        }
        else
        {
            //Check if IDLE
            if(!Objects.equals(drone.getState(), "IDLE"))
            {
                return new ResponseEntity<>(new ApiResponse(
                        "error",
                        "Drone is not available! State: " + drone.getState(),
                        drone
                ), HttpStatus.BAD_REQUEST);
            }

            //Check if IDLE
            if(drone.getBatteryCapacity() < 25)
            {
                return new ResponseEntity<>(new ApiResponse(
                        "error",
                        "Drone is not available! Battery Capacity: " + drone.getBatteryCapacity() + "%",
                        drone
                ), HttpStatus.BAD_REQUEST);
            }
        }


        // Validate Medication Codes and Total Weight
        double weight = 0;
        for(String code : param.getItems())
        {
            Item item = itemService.get(code);
            if(item == null)
            {
                return new ResponseEntity<>(new ApiResponse(
                        "error",
                        "Item with code " + code + " not found!",
                        null
                ), HttpStatus.NOT_FOUND);
            }
            else
            {
                weight += item.getWeight();
            }
        }

        //Check weight
        if(weight > drone.getWeightLimit())
        {
            return new ResponseEntity<>(new ApiResponse(
                    "error",
                    "Total weight of items " + weight + " grams exceeds drone weight limit of " + drone.getWeightLimit() + " grams",
                    drone
            ), HttpStatus.BAD_REQUEST);
        }


        DeliveryResponse deliveryResponse = dispatchService.load(param);

        ApiResponse response = new ApiResponse(
                "success",
                "Drone loaded successfully",
                deliveryResponse
        );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("items/{serialNumber}")
    public ResponseEntity<ApiResponse> items(@PathVariable(name = "serialNumber") String serialNumber)
    {
        if(serialNumber.trim().isEmpty())
        {
            return new ResponseEntity<>(new ApiResponse(
                    "error",
                    "Serial Number is required",
                    null
            ), HttpStatus.BAD_REQUEST);
        }
        else
        {
            DroneItemResponse droneItemResponse = dispatchService.items(serialNumber);
            if(droneItemResponse.getDelivery() == null)
            {
                return new ResponseEntity<>(new ApiResponse(
                        "error",
                        "No delivery for drone with serial number " + serialNumber + "!",
                        null
                ), HttpStatus.NOT_FOUND);
            }
            else
            {
                ApiResponse response = new ApiResponse(
                        "success",
                        "Drone items loaded successfully",
                        droneItemResponse
                );

                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }
    }

    @PostMapping("dispatch")
    public ResponseEntity<ApiResponse> dispatch(@Valid @RequestBody DroneRequest param)
    {
        Delivery delivery = dispatchService.get(param.getSerialNumber());
        if(delivery == null)
        {
            return new ResponseEntity<>(new ApiResponse(
                    "error",
                    "No delivery for drone with serial number " + param.getSerialNumber() + "!",
                    null
            ), HttpStatus.NOT_FOUND);
        }
        else
        {
            DeliveryResponse deliveryResponse = dispatchService.dispatch(delivery);
            ApiResponse response = new ApiResponse(
                    "success",
                    "Drone dispatched successfully",
                    deliveryResponse
            );

            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @PostMapping("delivered")
    public ResponseEntity<ApiResponse> delivered(@Valid @RequestBody DroneRequest param)
    {
        Delivery delivery = dispatchService.get(param.getSerialNumber());
        if(delivery == null)
        {
            return new ResponseEntity<>(new ApiResponse(
                    "error",
                    "No delivery for drone with serial number " + param.getSerialNumber() + "!",
                    null
            ), HttpStatus.NOT_FOUND);
        }
        else
        {

            DeliveryResponse deliveryResponse = dispatchService.delivered(delivery);
            ApiResponse response = new ApiResponse(
                    "success",
                    "Drone delivered successfully",
                    deliveryResponse
            );

            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
}
