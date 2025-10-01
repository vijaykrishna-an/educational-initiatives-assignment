package com.manufacturing.system.factories;

import com.manufacturing.system.parts.*;
import com.manufacturing.system.parts.impl.*;

/**
 * Concrete Factory for creating Lenovo-specific components using standard parts.
 */
public class LenovoComputerFactory implements ComputerFactory {
    @Override
    public Motherboard createMotherboard() {
        return new StandardMotherboard("Lenovo ThinkStation P330 Motherboard", "Lenovo", 250.00);
    }

    @Override
    public CPU createCPU() {
        return new StandardCPU("Intel Core i7-8700 (Lenovo OEM)", "Lenovo", 400.00);
    }

    @Override
    public RAM createRAM() {
        return new StandardRAM("16GB DDR4 2666MHz (Lenovo)", "Lenovo", 150.00);
    }

    @Override
    public GPU createGPU() {
        return new StandardGPU("NVIDIA Quadro P1000 (Lenovo)", "Lenovo", 350.00);
    }

    @Override
    public HardDrive createHardDrive() {
        return new StandardHardDrive("1TB 7200rpm HDD (Lenovo)", "Lenovo", 80.00);
    }

    @Override
    public SSD createSSD() {
        return new StandardSSD("512GB PCIe NVMe M.2 SSD (Lenovo)", "Lenovo", 120.00);
    }
}

