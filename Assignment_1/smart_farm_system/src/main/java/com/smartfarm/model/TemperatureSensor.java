package com.smartfarm.model;

import java.util.Random;

/**
 * Concrete Product: Represents a temperature sensor.
 */
public class TemperatureSensor extends Sensor {
    public TemperatureSensor(String sensorId) {
        super(sensorId, "Temperature");
    }

    @Override
    public void collectData() {
        double temperature = 15.0 + (40.0 - 15.0) * new Random().nextDouble();
        System.out.printf(">>> Collecting data from [%s]: Temperature is %.2f°C\n", this.sensorId, temperature);
    }
}