package com.smartfarm.factory;

import com.smartfarm.model.Sensor;

/**
 * Abstract Factory Interface: Declares an interface for operations that create
 * abstract product objects.
 * This decouples the client from the concrete implementation of the sensors.
 */
public interface SensorFactory {
    Sensor createSensor(String sensorId);
}