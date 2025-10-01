package com.chargeradapter.ports;

/**
 * The Target Interface.
 * This is the modern, standard port that our client device (e.g., a new laptop) expects.
 * All our adapters will conform to this interface.
 */
public interface ChargingPort {
    void charge();
}