<DOCUMENT filename="README.markdown">
Smart Farm IoT Management System

This Java console application demonstrates the use of creational design patterns (Singleton and Abstract Factory) to manage IoT devices in a smart agricultural ecosystem. It provides a scalable and flexible architecture for registering, validating, and collecting data from various sensor types (e.g., Soil Moisture, Temperature, Pest Detection) through an interactive console menu.

Project Structure

The project is organized into the following package structure:

```
.
├── pom.xml
└── src
    └── main
        └── java
            └── com
                └── smartfarm
                    ├── config
                    │   └── FarmConfigurationManager.java
                    ├── factory
                    │   ├── SensorFactory.java
                    │   ├── SoilMoistureSensorFactory.java
                    │   ├── TemperatureSensorFactory.java
                    │   └── PestDetectionSensorFactory.java
                    ├── model
                    │   ├── Sensor.java
                    │   ├── SoilMoistureSensor.java
                    │   ├── TemperatureSensor.java
                    │   └── PestDetectionSensor.java
                    └── SmartFarmManagementSystem.java
```

How to Compile and Run

There are two ways to build and run this project: manually using javac or with Apache Maven.

Option 1: Manual Compilation

1. Navigate to the src directory in your terminal.

2. Compile all .java files. The -d . flag places the compiled .class files into their respective package directories within the current directory (src).

   ```
   javac -d . com/smartfarm/SmartFarmManagementSystem.java com/smartfarm/config/*.java com/smartfarm/factory/*.java com/smartfarm/model/*.java
   ```

3. Run the application from the src directory, specifying the fully qualified name of the main class.

   ```
   java com.smartfarm.SmartFarmManagementSystem
   ```

Option 2: Building with Maven

This is the recommended approach for managing Java projects.

1. Prerequisites: Make sure you have Apache Maven installed.

2. Setup: Place the pom.xml file in the root directory of the project, alongside the src folder.

3. Navigate to the project's root directory (the one containing pom.xml and src).

4. Compile the project: This command will compile the source code and put the .class files in the target/classes directory.

   ```
   mvn compile
   ```

5. Run the application: This command will execute the main class specified in the pom.xml.

   ```
   mvn exec:java
   ```

Sample Interaction

Below is an example of what a user session might look like.

Example 1: Registering and Viewing Sensors

```
FarmConfigurationManager initialized...
==================================================
Welcome to the Gemini Smart Fields Management System
Location: Madurai, Tamil Nadu, India
==================================================
Singleton confirmed: Both configuration instances are the same.
--------------------------------------------------

--- Main Menu ---
1. Register New Sensor
2. View Active Sensors
3. Collect Sensor Data
4. Exit
Select Option: 2

--- Active Sensors ---
No sensors have been registered yet.

--- Main Menu ---
1. Register New Sensor
2. View Active Sensors
3. Collect Sensor Data
4. Exit
Select Option: 1

--- Register New Sensor ---
Select Sensor Type:
1. Soil Moisture
2. Temperature
3. Pest Detection
Enter type: 1
Enter a unique Sensor ID (e.g., SM-001): SM-01
Successfully registered sensor: Sensor [ID=SM-01, Type=Soil Moisture]

--- Main Menu ---
1. Register New Sensor
2. View Active Sensors
3. Collect Sensor Data
4. Exit
Select Option: 2

--- Active Sensors ---
Sensor [ID=SM-01, Type=Soil Moisture]
```

Example 2: Collecting Sensor Data

```
--- Main Menu ---
1. Register New Sensor
2. View Active Sensors
3. Collect Sensor Data
4. Exit
Select Option: 1

--- Register New Sensor ---
Select Sensor Type:
1. Soil Moisture
2. Temperature
3. Pest Detection
Enter type: 2
Enter a unique Sensor ID (e.g., SM-001): TEMP-A
Successfully registered sensor: Sensor [ID=TEMP-A, Type=Temperature]

--- Main Menu ---
1. Register New Sensor
2. View Active Sensors
3. Collect Sensor Data
4. Exit
Select Option: 3

--- Collect Sensor Data ---
Enter the Sensor ID to collect data: TEMP-A
>>> Collecting data from [TEMP-A]: Temperature is 23.45°C

--- Main Menu ---
1. Register New Sensor
2. View Active Sensors
3. Collect Sensor Data
4. Exit
Select Option: 3

--- Collect Sensor Data ---
Enter the Sensor ID to collect data: NON-EXISTENT-ID
Error: Sensor with ID 'NON-EXISTENT-ID' not found.

--- Main Menu ---
1. Register New Sensor
2. View Active Sensors
3. Collect Sensor Data
4. Exit
Select Option: 4
Exiting system. Goodbye!
```
