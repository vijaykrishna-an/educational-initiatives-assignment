package com.manufacturing.system.factories;

import com.manufacturing.system.parts.*;

/**
 * The Abstract Factory interface.
 */
public interface ComputerFactory {
    Motherboard createMotherboard();
    CPU createCPU();
    RAM createRAM();
    GPU createGPU();
    HardDrive createHardDrive();
    SSD createSSD();
}

