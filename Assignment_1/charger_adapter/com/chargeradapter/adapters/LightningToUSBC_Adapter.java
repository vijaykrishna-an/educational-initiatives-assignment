package com.chargeradapter.adapters;

import com.chargeradapter.adaptees.LightningPort;
import com.chargeradapter.ports.ChargingPort;

/**
 * A concrete Adapter.
 * It implements our target `ChargingPort` interface and holds an instance
 * of the incompatible `LightningPort` class.
 */
public class LightningToUSBC_Adapter implements ChargingPort {
    private final LightningPort lightningPort;

    public LightningToUSBC_Adapter(LightningPort lightningPort) {
        this.lightningPort = lightningPort;
    }

    /**
     * The adapter's core logic: translate the target `charge()` call
     * into the adaptee's specific `chargeWithLightning()` call.
     */
    @Override
    public void charge() {
        System.out.print("Adapter converting signal... ");
        lightningPort.chargeWithLightning();
    }
}
