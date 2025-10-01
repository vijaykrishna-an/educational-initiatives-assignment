package com.chargeradapter.adapters;

import com.chargeradapter.adaptees.USBA_Port;
import com.chargeradapter.ports.ChargingPort;

/**
 * Another concrete Adapter.
 * This one adapts the old `USBA_Port` to our modern `ChargingPort` interface.
 */
public class USBA_ToUSBC_Adapter implements ChargingPort {
    private final USBA_Port usbaPort;

    public USBA_ToUSBC_Adapter(USBA_Port usbaPort) {
        this.usbaPort = usbaPort;
    }

    /**
     * The adapter's core logic: translate the target `charge()` call
     * into the adaptee's specific `chargeViaUSBA()` call.
     */
    @Override
    public void charge() {
        System.out.print("Adapter converting voltage... ");
        usbaPort.chargeViaUSBA();
    }
}
