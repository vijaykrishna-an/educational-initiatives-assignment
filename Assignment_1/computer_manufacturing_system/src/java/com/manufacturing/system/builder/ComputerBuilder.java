package com.manufacturing.system.builder;

/**
 * The Builder interface specifies methods for creating the different parts of the Computer object.
 */
public interface ComputerBuilder {
    void buildMotherboard();
    void buildCPU();
    void buildRAM();
    void buildGPU();
    void buildHardDrive();
    void buildSSD();
    Computer getResult();
}

