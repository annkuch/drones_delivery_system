package com.kucheruk.drone.drone_devirery_system.data.response;

import java.time.Instant;

public class BatteryResponse {
    private String serialNumber;
    private String battery;
    private Instant timestamp;

    public BatteryResponse(String serialNumber, String battery) {
        this.serialNumber = serialNumber;
        this.battery = battery;
        this.timestamp = Instant.now();

    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getBattery() {
        return battery;
    }

    public void setBattery(String battery) {
        this.battery = battery;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public BatteryResponse(String serialNumber, String battery, Instant timestamp) {
        this.serialNumber = serialNumber;
        this.battery = battery;
        this.timestamp = timestamp;
    }

    public BatteryResponse() {
    }
}
