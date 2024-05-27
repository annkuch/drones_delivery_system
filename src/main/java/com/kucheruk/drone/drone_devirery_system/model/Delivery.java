package com.kucheruk.drone.drone_devirery_system.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;

@Table(name = "deliveries")
@Entity
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "drone_serial_number", referencedColumnName = "serialNumber")
    private Drone drone;

    @NotEmpty
    @Column(nullable = false)
    private String source;

    @NotEmpty
    @Column(nullable = false)
    private String destination;

    @CreatedDate
    @Column(nullable = false)
    private Instant createdAt;

    @Column(nullable = true)
    private Instant dispatchedAt;

    @Column(nullable = true)
    private Instant deliveredAt;

    public Delivery(Long id, Drone drone, String source, String destination, Instant createdAt, Instant dispatchedAt, Instant deliveredAt) {
        this.id = id;
        this.drone = drone;
        this.source = source;
        this.destination = destination;
        this.createdAt = createdAt;
        this.dispatchedAt = dispatchedAt;
        this.deliveredAt = deliveredAt;
    }

    public Delivery() {

    }
    public Delivery(Drone drone, String source, String destination) {
        this.drone = drone;
        this.source = source;
        this.destination = destination;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Drone getDrone() {
        return drone;
    }

    public void setDrone(Drone drone) {
        this.drone = drone;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getDispatchedAt() {
        return dispatchedAt;
    }

    public void setDispatchedAt(Instant dispatchedAt) {
        this.dispatchedAt = dispatchedAt;
    }

    public Instant getDeliveredAt() {
        return deliveredAt;
    }

    public void setDeliveredAt(Instant deliveredAt) {
        this.deliveredAt = deliveredAt;
    }
}
