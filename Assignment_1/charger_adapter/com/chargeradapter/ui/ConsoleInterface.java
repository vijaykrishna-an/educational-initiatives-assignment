package com.chargeradapter.ui;

import com.chargeradapter.adaptees.LightningPort;
import com.chargeradapter.adaptees.USBA_Port;
import com.chargeradapter.adapters.LightningToUSBC_Adapter;
import com.chargeradapter.adapters.USBA_ToUSBC_Adapter;
import com.chargeradapter.ports.ChargingPort;

import java.util.Scanner;

/**
 * Provides a user-driven console interface to demonstrate the Adapter pattern.
 * Users can choose which legacy device to connect to a modern port.
 */
public class ConsoleInterface {
    private final Scanner scanner;

    public ConsoleInterface() {
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;
        while (running) {
            displayMainMenu();
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    handleChargingScenario();
                    break;
                case "0":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println(); // Add a blank line for readability
        }
        System.out.println("Shutting down charging station. Goodbye!");
    }

    private void displayMainMenu() {
        System.out.println("===== Universal Charging Station =====");
        System.out.println("Your modern device only accepts standard charging ports.");
        System.out.println("----------------------------------------");
        System.out.println("1. Connect a legacy charger via an adapter");
        System.out.println("0. Exit");
        System.out.println("========================================");
    }

    private void handleChargingScenario() {
        System.out.println("\n--- Available Legacy Chargers ---");
        System.out.println("1. Old USB-A Charger (◧)");
        System.out.println("2. Apple Lightning Charger (⚡)");
        System.out.println("0. Cancel");
        System.out.print("Which charger would you like to adapt? ");
        String choice = scanner.nextLine().trim();

        ChargingPort adapter = null;

        switch (choice) {
            case "1":
                // The client creates the incompatible object...
                USBA_Port oldUSBACharger = new USBA_Port();
                // ...and wraps it in an adapter.
                adapter = new USBA_ToUSBC_Adapter(oldUSBACharger);
                System.out.println("\n[Plugging in USB-A to Standard Adapter...]");
                break;
            case "2":
                // The client creates the incompatible object...
                LightningPort appleLightningCharger = new LightningPort();
                // ...and wraps it in a different adapter.
                adapter = new LightningToUSBC_Adapter(appleLightningCharger);
                System.out.println("\n[Plugging in Lightning to Standard Adapter...]");
                break;
            case "0":
                System.out.println("Charging cancelled.");
                return;
            default:
                System.out.println("Invalid charger selection.");
                return;
        }

        // The beautiful part: the client code from this point forward
        // is identical regardless of which charger was chosen.
        // It only knows how to work with the `ChargingPort` interface.
        chargeDevice(adapter);
    }

    /**
     * This client method only understands the modern ChargingPort interface.
     * It does not know or care about the complexity of the legacy chargers.
     * @param port The charging port that conforms to the standard interface.
     */
    public static void chargeDevice(ChargingPort port) {
        System.out.println("Device connected. Attempting to charge...");
        port.charge(); // The adapter does the translation behind the scenes.
        System.out.println("✅ Success! Device is now charging.");
    }
}
