package com.manufacturing.system.builder;

import com.manufacturing.system.parts.*;

/**
 * Concrete builder for custom configurations, allowing mixing and matching parts.
 */
public class CustomComputerBuilder implements ComputerBuilder {
    private Computer computer = new Computer();

    public CustomComputerBuilder() {
        this.computer.setConfigurationType("Custom Build");
    }

    public void addMotherboard(Motherboard motherboard) { computer.addPart(motherboard); }
    public void addCPU(CPU cpu) { computer.addPart(cpu); }
    public void addRAM(RAM ram) { computer.addPart(ram); }
    public void addGPU(GPU gpu) { computer.addPart(gpu); }
    public void addHardDrive(HardDrive hdd) { computer.addPart(hdd); }
    public void addSSD(SSD ssd) { computer.addPart(ssd); }

    @Override public void buildMotherboard() {}
    @Override public void buildCPU() {}
    @Override public void buildRAM() {}
    @Override public void buildGPU() {}
    @Override public void buildHardDrive() {}
    @Override public void buildSSD() {}

    @Override
    public Computer getResult() { return computer; }
}

