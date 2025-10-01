package com.smartfarm;

import com.smartfarm.config.FarmConfigurationManager;
import com.smartfarm.factory.PestDetectionSensorFactory;
import com.smartfarm.factory.SensorFactory;
import com.smartfarm.factory.SoilMoistureSensorFactory;
import com.smartfarm.factory.TemperatureSensorFactory;
import com.smartfarm.model.Sensor;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

/**
 * Client: The main application class that uses the Singleton and Abstract Factory patterns.
 */
public class SmartFarmManagementSystem {

    private final Map<String, Sensor> activeSensors = new HashMap<>();
    private final Scanner scanner = new Scanner(System.in);

    public void run() {
        // 1. Demonstrate Singleton Pattern
        FarmConfigurationManager config = FarmConfigurationManager.getInstance();
        System.out.println("==================================================");
        System.out.println("Welcome to the " + config.getFarmName() + " Management System");
        System.out.println("Location: " + config.getLocation());
        System.out.println("==================================================");

        // Attempting to get the instance again to show it's the same object
        FarmConfigurationManager anotherConfigInstance = FarmConfigurationManager.getInstance();
        if (config == anotherConfigInstance) {
            System.out.println("Singleton confirmed: Both configuration instances are the same.");
        }
        System.out.println("--------------------------------------------------");

        boolean running = true;
        while (running) {
            printMenu();
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        registerSensor();
                        break;
                    case 2:
                        viewActiveSensors();
                        break;
                    case 3:
                        collectSensorData();
                        break;
                    case 4:
                        running = false;
                        System.out.println("Exiting system. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.err.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the invalid input
            }
            System.out.println(); // Add a newline for better readability
        }
        scanner.close();
    }

    private void printMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. Register New Sensor");
        System.out.println("2. View Active Sensors");
        System.out.println("3. Collect Sensor Data");
        System.out.println("4. Exit");
        System.out.print("Select Option: ");
    }

    private void registerSensor() {
        System.out.println("\n--- Register New Sensor ---");
        System.out.println("Select Sensor Type:");
        System.out.println("1. Soil Moisture");
        System.out.println("2. Temperature");
        System.out.println("3. Pest Detection");
        System.out.print("Enter type: ");

        int typeChoice;
        try {
            typeChoice = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.err.println("Invalid sensor type. Please enter a number.");
            scanner.nextLine(); // Clear buffer
            return;
        }

        // 2. Demonstrate Abstract Factory Pattern
        SensorFactory factory;
        switch (typeChoice) {
            case 1:
                factory = new SoilMoistureSensorFactory();
                break;
            case 2:
                factory = new TemperatureSensorFactory();
                break;
            case 3:
                factory = new PestDetectionSensorFactory();
                break;
            default:
                System.err.println("Invalid sensor type selected. Registration cancelled.");
                return;
        }

        System.out.print("Enter a unique Sensor ID (e.g., SM-001): ");
        String sensorId = scanner.nextLine();

        // Validation
        if (sensorId.trim().isEmpty()) {
            System.err.println("Sensor ID cannot be empty. Registration cancelled.");
            return;
        }
        if (activeSensors.containsKey(sensorId)) {
            System.err.println("Error: Sensor with ID '" + sensorId + "' already exists.");
            return;
        }

        // Use the factory to create the sensor without knowing its concrete class
        Sensor newSensor = factory.createSensor(sensorId);
        activeSensors.put(sensorId, newSensor);
        System.out.println("Successfully registered sensor: " + newSensor);
    }

    private void viewActiveSensors() {
        System.out.println("\n--- Active Sensors ---");
        if (activeSensors.isEmpty()) {
            System.out.println("No sensors have been registered yet.");
        } else {
            for (Sensor sensor : activeSensors.values()) {
                System.out.println(sensor);
            }
        }
    }

    private void collectSensorData() {
        System.out.println("\n--- Collect Sensor Data ---");
        if (activeSensors.isEmpty()) {
            System.out.println("No sensors registered to collect data from.");
            return;
        }
        System.out.print("Enter the Sensor ID to collect data: ");
        String sensorId = scanner.nextLine();

        Sensor sensor = activeSensors.get(sensorId);
        if (sensor != null) {
            sensor.collectData();
        } else {
            System.err.println("Error: Sensor with ID '" + sensorId + "' not found.");
        }
    }

    public static void main(String[] args) {
        SmartFarmManagementSystem system = new SmartFarmManagementSystem();
        system.run();
    }
}