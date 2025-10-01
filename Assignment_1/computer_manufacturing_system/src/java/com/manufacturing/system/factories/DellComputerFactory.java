package com.manufacturing.system.factories;

import com.manufacturing.system.parts.*;
import com.manufacturing.system.parts.impl.*;

/**
 * Concrete Factory for creating Dell-specific components using standard parts.
 */
public class DellComputerFactory implements ComputerFactory {
    @Override
    public Motherboard createMotherboard() {
        return new StandardMotherboard("Dell Precision 5820 Motherboard", "Dell", 300.00);
    }

    @Override
    public CPU createCPU() {
        return new StandardCPU("Intel Xeon W-2123 (Dell OEM)", "Dell", 450.00);
    }

    @Override
    public RAM createRAM() {
        return new StandardRAM("32GB DDR4 2666MHz ECC (Dell)", "Dell", 200.00);
    }

    @Override
    public GPU createGPU() {
        return new StandardGPU("NVIDIA Quadro RTX 4000 (Dell)", "Dell", 900.00);
    }

    @Override
    public HardDrive createHardDrive() {
        return new StandardHardDrive("2TB 7200rpm HDD (Dell)", "Dell", 100.00);
    }

    @Override
    public SSD createSSD() {
        return new StandardSSD("1TB PCIe NVMe M.2 SSD (Dell)", "Dell", 180.00);
    }
}

