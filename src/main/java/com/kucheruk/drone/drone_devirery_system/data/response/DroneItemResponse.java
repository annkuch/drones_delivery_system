package com.kucheruk.drone.drone_devirery_system.data.response;

import com.kucheruk.drone.drone_devirery_system.model.Delivery;
import com.kucheruk.drone.drone_devirery_system.model.Item;

import java.util.List;

public class DroneItemResponse {
    private Delivery delivery;
    private List<Item> items;

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public DroneItemResponse(Delivery delivery, List<Item> items) {
        this.delivery = delivery;
        this.items = items;
    }

    public DroneItemResponse() {
    }
}
