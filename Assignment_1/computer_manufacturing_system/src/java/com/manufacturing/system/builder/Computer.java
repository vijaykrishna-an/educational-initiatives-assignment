package com.manufacturing.system.builder;

import com.manufacturing.system.parts.ComputerPart;
import java.util.ArrayList;
import java.util.List;

/**
 * The complex object being built.
 */
public class Computer {
    private List<ComputerPart> parts = new ArrayList<>();
    private String configurationType;

    public void setConfigurationType(String type) {
        this.configurationType = type;
    }

    public void addPart(ComputerPart part) {
        if (part != null) {
            this.parts.add(part);
        }
    }

    public double getTotalPrice() {
        return parts.stream().mapToDouble(ComputerPart::getPrice).sum();
    }

    public void showConfiguration() {
        System.out.println("------------------------------------------");
        System.out.println("Computer Configuration: " + (configurationType != null ? configurationType : "Custom Build"));
        System.out.println("------------------------------------------");
        if (parts.isEmpty()) {
            System.out.println("No parts have been added to this computer.");
            return;
        }

        for (ComputerPart part : parts) {
            System.out.printf("  %-12s: %-40s | Manufacturer: %-8s | Price: $%.2f%n",
                    part.getClass().getInterfaces()[0].getSimpleName(),
                    part.getName(),
                    part.getManufacturer(),
                    part.getPrice());
        }

        System.out.println("------------------------------------------");
        System.out.printf("Total System Price: $%.2f%n", getTotalPrice());
        System.out.println("------------------------------------------");
    }
}

