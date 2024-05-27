package com.kucheruk.drone.drone_devirery_system.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Table(name = "delivery_items")
@Entity
public class DeliveryItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "delivery_id", referencedColumnName = "id")
    private Delivery delivery;
    @ManyToOne
    @JoinColumn(name = "item_code", referencedColumnName = "code")
    private Item item;

    public DeliveryItem(Long id, String code) {
    }
}
