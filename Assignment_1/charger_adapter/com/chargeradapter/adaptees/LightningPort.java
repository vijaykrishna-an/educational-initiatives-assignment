package com.chargeradapter.adaptees;

/**
 * An Adaptee (Incompatible Class).
 * This represents a legacy Apple charger with its own proprietary connection method.
 * Its method `chargeWithLightning()` is incompatible with the `charge()` method in our Target Interface.
 */
public class LightningPort {
    public void chargeWithLightning() {
        System.out.println("⚡ Charging with a Lightning connection. Special proprietary signal sent.");
    }
}
