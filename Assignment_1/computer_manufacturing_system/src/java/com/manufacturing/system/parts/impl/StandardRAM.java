package com.manufacturing.system.parts.impl;

import com.manufacturing.system.parts.RAM;

public class StandardRAM implements RAM {
    private String name;
    private String manufacturer;
    private double price;

    public StandardRAM(String name, String manufacturer, double price) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.price = price;
    }

    @Override
    public String getName() { return this.name; }
    @Override
    public String getManufacturer() { return this.manufacturer; }
    @Override
    public double getPrice() { return this.price; }
}

