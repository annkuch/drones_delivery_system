package com.kucheruk.drone.drone_devirery_system.model;

import jakarta.persistence.*;

import java.time.Instant;

@Table(name = "battery_levels")
@Entity
public class BatteryLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "drone_serial_number", referencedColumnName = "serialNumber")
    private Drone drone;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private double batteryLevel;

    @Column(nullable = false)
    private Instant timestamp;
    public BatteryLevel() {}

    public BatteryLevel(Drone drone, String state, double batteryLevel, Instant timestamp) {
        this.drone = drone;
        this.state = state;
        this.batteryLevel = batteryLevel;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Drone getDrone() {
        return drone;
    }

    public void setDrone(Drone drone) {
        this.drone = drone;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(double batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}
