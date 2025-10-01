package com.manufacturing.system.builder;

import com.manufacturing.system.factories.ComputerFactory;

/**
 * Concrete Builder for a Workstation computer configuration.
 */
public class WorkstationComputerBuilder implements ComputerBuilder {
    private Computer computer = new Computer();
    private ComputerFactory factory;

    public WorkstationComputerBuilder(ComputerFactory factory) {
        this.factory = factory;
        this.computer.setConfigurationType("Workstation PC (" + factory.createCPU().getManufacturer() + ")");
    }

    @Override
    public void buildMotherboard() { computer.addPart(factory.createMotherboard()); }
    @Override
    public void buildCPU() { computer.addPart(factory.createCPU()); }
    @Override
    public void buildRAM() { computer.addPart(factory.createRAM()); }
    @Override
    public void buildGPU() { computer.addPart(factory.createGPU()); }
    @Override
    public void buildHardDrive() { computer.addPart(factory.createHardDrive()); }
    @Override
    public void buildSSD() { computer.addPart(factory.createSSD()); }
    @Override
    public Computer getResult() { return computer; }
}

