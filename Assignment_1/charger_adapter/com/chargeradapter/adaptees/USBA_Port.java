package com.chargeradapter.adaptees;

/**
 * Another Adaptee (Incompatible Class).
 * This represents an older USB-A charger.
 * Its method `chargeViaUSBA()` is also incompatible with the `charge()` method.
 */
public class USBA_Port {
    public void chargeViaUSBA() {
        System.out.println("◧ Charging with a USB-A connection. 5V signal activated.");
    }
}
