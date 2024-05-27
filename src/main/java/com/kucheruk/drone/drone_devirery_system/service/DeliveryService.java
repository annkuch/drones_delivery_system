package com.kucheruk.drone.drone_devirery_system.service;

import com.kucheruk.drone.drone_devirery_system.data.request.DeliveryRequest;
import com.kucheruk.drone.drone_devirery_system.data.response.DeliveryResponse;
import com.kucheruk.drone.drone_devirery_system.data.response.DroneItemResponse;
import com.kucheruk.drone.drone_devirery_system.model.Delivery;
import com.kucheruk.drone.drone_devirery_system.model.DeliveryItem;
import com.kucheruk.drone.drone_devirery_system.model.Drone;
import com.kucheruk.drone.drone_devirery_system.model.Item;
import com.kucheruk.drone.drone_devirery_system.repository.DeliveryItemRepository;
import com.kucheruk.drone.drone_devirery_system.repository.DeliveryRepository;
import com.kucheruk.drone.drone_devirery_system.repository.DroneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    private DeliveryItemRepository deliveryItemRepository;

    @Autowired
    private DroneRepository droneRepository;

    public DeliveryResponse load(DeliveryRequest request) {
        // Update Drone state to LOADING
        droneRepository.setState("LOADING", request.getSerialNumber());

        List<DeliveryItem> items = new ArrayList<>();

        // Find the drone by serial number
        Optional<Drone> optionalDrone = droneRepository.findBySerialNumber(request.getSerialNumber());
        if (optionalDrone.isPresent()) {
            Drone drone = optionalDrone.get();

            // Create Delivery
            Delivery delivery = deliveryRepository.save(new Delivery(drone, request.getSource(), request.getDestination()));

            // Save Items
            for (String code : request.getItems()) {
                DeliveryItem deliveryItem = deliveryItemRepository.save(new DeliveryItem(delivery.getId(), code));
                items.add(deliveryItem);
            }

            // Update Drone state to LOADED
            droneRepository.setState("LOADED", request.getSerialNumber());

            return new DeliveryResponse(delivery, items);
        } else {
            // Handle case where drone is not found
            return null;
        }
    }
    public DroneItemResponse items(String serialNumber) {
        List<Item> items = new ArrayList<>();

        // Find the drone by serial number
        Optional<Drone> optionalDrone = droneRepository.findBySerialNumber(serialNumber);
        if (optionalDrone.isPresent()) {
            Drone drone = optionalDrone.get();

            Delivery delivery = deliveryRepository.findByDrone(drone);
            if (delivery != null) {
                items = deliveryItemRepository.findAllItemsByDeliveryId(delivery.getId());
                return new DroneItemResponse(delivery, items);
            } else {
                // Handle case where delivery is not found
                return new DroneItemResponse(null, items);
            }
        } else {
            // Handle case where drone is not found
            return new DroneItemResponse(null, items);
        }
    }

    public Delivery get(String serialNumber) {
        // Find the delivery by drone serial number
        Optional<Drone> optionalDrone = droneRepository.findBySerialNumber(serialNumber);
        if (optionalDrone.isPresent()) {
            Drone drone = optionalDrone.get();
            return deliveryRepository.findByDrone(drone);
        } else {
            // Handle case where drone is not found
            return null;
        }
    }

    public DeliveryResponse dispatch(Delivery delivery) {
        // Update Delivery and Drone
        deliveryRepository.setDispatched(Instant.now(), delivery.getId());
        droneRepository.setState("DELIVERING", delivery.getDrone().getSerialNumber());

        // Get Data
        delivery.setDispatchedAt(Instant.now());
        List<DeliveryItem> items = deliveryItemRepository.findAllByDeliveryId(delivery.getId());

        return new DeliveryResponse(delivery, items);
    }

    public DeliveryResponse delivered(Delivery delivery) {
        // Update Delivery and Drone
        deliveryRepository.setDelivered(Instant.now(), delivery.getId());
        droneRepository.setState("IDLE", delivery.getDrone().getSerialNumber());

        // Get Data
        delivery.setDeliveredAt(Instant.now());
        List<DeliveryItem> items = deliveryItemRepository.findAllByDeliveryId(delivery.getId());

        return new DeliveryResponse(delivery, items);
    }
}
