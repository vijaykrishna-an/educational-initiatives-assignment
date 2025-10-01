package com.manufacturing.system.builder;

/**
 * The Director class is responsible for managing the construction process.
 * It works with a builder object to create a computer in a specific sequence.
 */
public class ComputerDirector {
    public void construct(ComputerBuilder builder) {
        builder.buildMotherboard();
        builder.buildCPU();
        builder.buildRAM();
        builder.buildGPU();
        builder.buildHardDrive();
        builder.buildSSD();
    }
}

