package com.smartfarm.factory;

import com.smartfarm.model.Sensor;
import com.smartfarm.model.SoilMoistureSensor;

/**
 * Concrete Factory: Implements the operations to create concrete product objects.
 * This factory is responsible for creating SoilMoistureSensor instances.
 */
public class SoilMoistureSensorFactory implements SensorFactory {
    @Override
    public Sensor createSensor(String sensorId) {
        return new SoilMoistureSensor(sensorId);
    }
}