package com.smartfarm.model;

/**
 * Abstract Product: Represents the base for all sensor types.
 * Defines the common interface for all sensors in the farm.
 */
public abstract class Sensor {
    protected String sensorId;
    protected String sensorType;

    public Sensor(String sensorId, String sensorType) {
        this.sensorId = sensorId;
        this.sensorType = sensorType;
    }

    /**
     * Abstract method to be implemented by concrete sensor classes.
     * Simulates the process of collecting data from the physical sensor.
     */
    public abstract void collectData();

    public String getSensorId() {
        return sensorId;
    }

    @Override
    public String toString() {
        return "Sensor [ID=" + sensorId + ", Type=" + sensorType + "]";
    }
}