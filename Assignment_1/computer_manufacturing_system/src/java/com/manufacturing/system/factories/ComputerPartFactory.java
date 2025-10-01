package com.manufacturing.system.factories;

import com.manufacturing.system.parts.*;
import com.manufacturing.system.parts.impl.*;

/**
 * A simple factory for creating individual computer parts for custom builds.
 */
public class ComputerPartFactory {
    public static Motherboard createMotherboard(String manufacturer) {
        if ("Lenovo".equalsIgnoreCase(manufacturer)) {
            return new StandardMotherboard("Lenovo ThinkStation P330 Motherboard", "Lenovo", 250.00);
        }
        if ("Dell".equalsIgnoreCase(manufacturer)) {
            return new StandardMotherboard("Dell Precision 5820 Motherboard", "Dell", 300.00);
        }
        return null;
    }

    public static CPU createCPU(String manufacturer) {
        if ("Lenovo".equalsIgnoreCase(manufacturer)) {
            return new StandardCPU("Intel Core i7-8700 (Lenovo OEM)", "Lenovo", 400.00);
        }
        if ("Dell".equalsIgnoreCase(manufacturer)) {
            return new StandardCPU("Intel Xeon W-2123 (Dell OEM)", "Dell", 450.00);
        }
        return null;
    }

    public static RAM createRAM(String manufacturer) {
        if ("Lenovo".equalsIgnoreCase(manufacturer)) {
            return new StandardRAM("16GB DDR4 2666MHz (Lenovo)", "Lenovo", 150.00);
        }
        if ("Dell".equalsIgnoreCase(manufacturer)) {
            return new StandardRAM("32GB DDR4 2666MHz ECC (Dell)", "Dell", 200.00);
        }
        return null;
    }

    public static GPU createGPU(String manufacturer) {
        if ("Lenovo".equalsIgnoreCase(manufacturer)) {
            return new StandardGPU("NVIDIA Quadro P1000 (Lenovo)", "Lenovo", 350.00);
        }
        if ("Dell".equalsIgnoreCase(manufacturer)) {
            return new StandardGPU("NVIDIA Quadro RTX 4000 (Dell)", "Dell", 900.00);
        }
        return null;
    }

    public static HardDrive createHardDrive(String manufacturer) {
        if ("Lenovo".equalsIgnoreCase(manufacturer)) {
            return new StandardHardDrive("1TB 7200rpm HDD (Lenovo)", "Lenovo", 80.00);
        }
        if ("Dell".equalsIgnoreCase(manufacturer)) {
            return new StandardHardDrive("2TB 7200rpm HDD (Dell)", "Dell", 100.00);
        }
        return null;
    }

    public static SSD createSSD(String manufacturer) {
        if ("Lenovo".equalsIgnoreCase(manufacturer)) {
            return new StandardSSD("512GB PCIe NVMe M.2 SSD (Lenovo)", "Lenovo", 120.00);
        }
        if ("Dell".equalsIgnoreCase(manufacturer)) {
            return new StandardSSD("1TB PCIe NVMe M.2 SSD (Dell)", "Dell", 180.00);
        }
        return null;
    }
}

