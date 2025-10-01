package com.manufacturing.system.builder;

import com.manufacturing.system.factories.ComputerFactory;

/**
 * Concrete Builder for a Gaming computer configuration.
 */
public class GamingComputerBuilder implements ComputerBuilder {
    private Computer computer = new Computer();
    private ComputerFactory factory;

    public GamingComputerBuilder(ComputerFactory factory) {
        this.factory = factory;
        this.computer.setConfigurationType("Gaming PC (" + factory.createCPU().getManufacturer() + ")");
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
    public void buildHardDrive() { /* Gaming PCs often prioritize SSDs */ }
    @Override
    public void buildSSD() { computer.addPart(factory.createSSD()); }
    @Override
    public Computer getResult() { return computer; }
}

