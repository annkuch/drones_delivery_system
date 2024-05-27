package com.kucheruk.drone.drone_devirery_system.data.response;

import com.kucheruk.drone.drone_devirery_system.model.Delivery;
import com.kucheruk.drone.drone_devirery_system.model.DeliveryItem;

import java.time.Instant;
import java.util.List;

public class DeliveryResponse {
    private Delivery delivery;
    private List<DeliveryItem> deliveryItems;
    private Instant timestamp;

    public DeliveryResponse() {
    }

    public DeliveryResponse(Delivery delivery, List<DeliveryItem> deliveryItems, Instant timestamp) {
        this.delivery = delivery;
        this.deliveryItems = deliveryItems;
        this.timestamp = timestamp;
    }

    public DeliveryResponse(Delivery delivery, List<DeliveryItem> items) {
    }
}
