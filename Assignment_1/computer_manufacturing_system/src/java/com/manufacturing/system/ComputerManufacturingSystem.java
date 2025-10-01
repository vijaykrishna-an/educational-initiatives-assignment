package com.manufacturing.system;

import com.manufacturing.system.builder.*;
import com.manufacturing.system.factories.ComputerFactory;
import com.manufacturing.system.factories.ComputerPartFactory;
import com.manufacturing.system.factories.DellComputerFactory;
import com.manufacturing.system.factories.LenovoComputerFactory;
import com.manufacturing.system.parts.*;

import java.util.Scanner;

public class ComputerManufacturingSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static ComputerDirector director = new ComputerDirector();

    public static void main(String[] args) {
        System.out.println("=============================================");
        System.out.println("  Welcome to the Computer Manufacturing System  ");
        System.out.println("=============================================");

        while (true) {
            System.out.println("\nPlease choose an option:");
            System.out.println("1. Build a Pre-configured Computer (Lenovo/Dell)");
            System.out.println("2. Build a Custom Computer");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = getIntegerInput();

            switch (choice) {
                case 1:
                    buildPreconfiguredComputer();
                    break;
                case 2:
                    buildCustomComputer();
                    break;
                case 3:
                    System.out.println("Thank you for using the system. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void buildPreconfiguredComputer() {
        System.out.println("\n--- Build a Pre-configured Computer ---");
        System.out.println("Select a manufacturer:");
        System.out.println("1. Lenovo");
        System.out.println("2. Dell");
        System.out.print("Enter your choice: ");
        int manuChoice = getIntegerInput();

        ComputerFactory factory;
        if (manuChoice == 1) {
            factory = new LenovoComputerFactory();
        } else if (manuChoice == 2) {
            factory = new DellComputerFactory();
        } else {
            System.out.println("Invalid manufacturer choice.");
            return;
        }

        System.out.println("\nSelect a computer type:");
        System.out.println("1. Gaming PC");
        System.out.println("2. Workstation PC");
        System.out.print("Enter your choice: ");
        int typeChoice = getIntegerInput();

        ComputerBuilder builder;
        if (typeChoice == 1) {
            builder = new GamingComputerBuilder(factory);
        } else if (typeChoice == 2) {
            builder = new WorkstationComputerBuilder(factory);
        } else {
            System.out.println("Invalid computer type choice.");
            return;
        }

        director.construct(builder);
        Computer computer = builder.getResult();
        System.out.println("\nSuccessfully manufactured the following computer:");
        computer.showConfiguration();
    }

    private static void buildCustomComputer() {
        System.out.println("\n--- Build a Custom Computer ---");
        System.out.println("You must select a manufacturer for the following compulsory parts:");
        System.out.println("CPU, Motherboard, RAM, SSD");

        CustomComputerBuilder builder = new CustomComputerBuilder();

        // --- Compulsory Parts ---
        Motherboard motherboard = selectPart("Motherboard", ComputerPartFactory::createMotherboard, true);
        builder.addMotherboard(motherboard);

        CPU cpu = selectPart("CPU", ComputerPartFactory::createCPU,true);
        if (!cpu.getManufacturer().equals(motherboard.getManufacturer())) {
            System.out.println("\n*** WARNING: CPU manufacturer (" + cpu.getManufacturer()
                    + ") does not match Motherboard manufacturer (" + motherboard.getManufacturer() + "). This may cause compatibility issues.");
        }
        builder.addCPU(cpu);

        builder.addRAM(selectPart("RAM", ComputerPartFactory::createRAM, true));
        builder.addSSD(selectPart("SSD", ComputerPartFactory::createSSD,true));

        // --- Optional Parts ---
        System.out.println("\n--- Optional Parts (can be skipped) ---");
        builder.addGPU(selectPart("GPU", ComputerPartFactory::createGPU,false));
        builder.addHardDrive(selectPart("Hard Drive", ComputerPartFactory::createHardDrive,false));

        Computer computer = builder.getResult();
        System.out.println("\nSuccessfully configured your custom computer:");
        computer.showConfiguration();
    }

    private static <T extends ComputerPart> T selectPart(String partName, PartCreator<T> creator,boolean isCompulsory) {
        System.out.printf("%nSelect %s manufacturer:%n", partName);
        System.out.println("1. Lenovo");
        System.out.println("2. Dell");
        if(!isCompulsory) {
            System.out.println("3. Skip this part");
        }
        System.out.print("Enter your choice: ");
        int choice = getIntegerInput();
        String manufacturer = null;
        if (choice == 1) manufacturer = "Lenovo";
        else if (choice == 2) manufacturer = "Dell";

        if (manufacturer != null) {
            return creator.create(manufacturer);
        } else {
            System.out.print("Invalid choice. Please select a valid manufacturer (1 or 2): ");
        }
        if(!isCompulsory) {
            System.out.println("Skipping " + partName + ".");
        }
        return null;
    }

    @FunctionalInterface
    interface PartCreator<T extends ComputerPart> {
        T create(String manufacturer);
    }

    private static int getIntegerInput() {
        while (true) {
            try {
                String line = scanner.nextLine();
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }
}

