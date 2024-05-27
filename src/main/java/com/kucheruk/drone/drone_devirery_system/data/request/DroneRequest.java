package com.kucheruk.drone.drone_devirery_system.data.request;

public class DroneRequest {
    public DroneRequest() {
    }

    public DroneRequest(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    private String serialNumber;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}

