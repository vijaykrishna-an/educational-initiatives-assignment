package com.smartfarm.model;

import java.util.Random;

/**
 * Concrete Product: Represents a soil moisture sensor.
 */
public class SoilMoistureSensor extends Sensor {
    public SoilMoistureSensor(String sensorId) {
        super(sensorId, "Soil Moisture");
    }

    @Override
    public void collectData() {
        double moisture = 20.0 + (50.0 - 20.0) * new Random().nextDouble();
        System.out.printf(">>> Collecting data from [%s]: Soil moisture is %.2f%%\n", this.sensorId, moisture);
    }
}