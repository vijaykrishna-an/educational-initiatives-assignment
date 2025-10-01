package com.smartfarm.model;

import java.util.Random;

/**
 * Concrete Product: Represents a pest detection sensor (e.g., an insect trap with a camera).
 */
public class PestDetectionSensor extends Sensor {
    public PestDetectionSensor(String sensorId) {
        super(sensorId, "Pest Detection");
    }

    @Override
    public void collectData() {
        boolean pestsDetected = new Random().nextBoolean();
        System.out.printf(">>> Collecting data from [%s]: Pests detected status: %s\n", this.sensorId, pestsDetected ? "ALERT" : "Normal");
    }
}