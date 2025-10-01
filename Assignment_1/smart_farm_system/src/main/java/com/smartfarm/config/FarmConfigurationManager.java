package com.smartfarm.config;

/**
 * Singleton Pattern: Manages global configuration for the smart farm.
 * This class ensures that only one instance of the configuration manager exists
 * throughout the application, providing a single point of access to configuration settings.
 * It is implemented with double-checked locking for thread-safety.
 */
public class FarmConfigurationManager {

    // The single, volatile instance of the class.
    // 'volatile' ensures that multiple threads handle the instance variable correctly.
    private static volatile FarmConfigurationManager instance;

    private final String farmName;
    private final String location;

    /**
     * Private constructor to prevent instantiation from outside the class.
     */
    private FarmConfigurationManager() {
        // Default configuration values
        this.farmName = "ANVK Smart Fields";
        this.location = "Madurai, Tamil Nadu, India";

        // This message demonstrates that the constructor is only called once.
        System.out.println("FarmConfigurationManager initialized...");
    }

    /**
     * Provides the global point of access to the Singleton instance.
     * Uses double-checked locking for thread-safe lazy initialization.
     *
     * @return The single instance of FarmConfigurationManager.
     */
    public static FarmConfigurationManager getInstance() {
        if (instance == null) { // First check (not synchronized)
            synchronized (FarmConfigurationManager.class) {
                if (instance == null) { // Second check (synchronized)
                    instance = new FarmConfigurationManager();
                }
            }
        }
        return instance;
    }

    // --- Getter methods for configuration properties ---

    public String getFarmName() {
        return farmName;
    }

    public String getLocation() {
        return location;
    }

}