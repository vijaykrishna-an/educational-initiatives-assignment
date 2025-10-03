    

# Universal Charger Adapter Simulation

This is a console-based Java application that demonstrates the **Adapter** design pattern. It simulates a common real-world scenario: connecting legacy devices with incompatible interfaces (like USB-A or Apple's Lightning) to a modern system that expects a single, standard charging port.

The application allows a user to dynamically select a legacy charger and "plug it in" using a corresponding adapter, all while the client code interacts with a simple, unified interface.

## Architectural Design & The Adapter Pattern

**The core of this project is to show how to make two incompatible interfaces work together. The Adapter pattern is used to wrap an existing class (the **`Adaptee`) with a new interface (the `Target`) that the client code understands.

* **Target Interface (**`ChargingPort`**):** This is the modern, unified interface that our client system expects. It has a single method, `charge()`.
* **Adaptees (**`USBA_Port`, `LightningPort`**):** These are the legacy classes with their own unique, incompatible methods (e.g., `chargeWithUSBA()`, `chargeWithLightning()`). The client cannot use them directly.
* **Adapters (**`USBA_ToUSBC_Adapter`, `LightningToUSBC_Adapter`**):** These are the crucial bridge classes. Each adapter implements the `ChargingPort` interface and holds an instance of an `Adaptee`. When the adapter's `charge()` method is called, it translates that call into the specific method of the legacy port it holds (e.g., calling `chargeWithUSBA()`).

This approach avoids messy conditional logic (like `instanceof` checks) and allows the system to be easily extended with new legacy port types without changing any client code.

## Project Structure

```
charger_adapter/
├── com/
│   └── chargeradapter/
│       ├── adaptees/
│       │   ├── LightningPort.java
│       │   └── USBA_Port.java
│       ├── adapters/
│       │   ├── LightningToUSBC_Adapter.java
│       │   └── USBA_ToUSBC_Adapter.java
│       ├── ports/
│       │   └── ChargingPort.java
│       ├── ui/
│       │   └── ConsoleInterface.java
│       └── Main.java
├── pom.xml
└── README.markdown
```

## Getting Started

### Prerequisites

* **Java Development Kit (JDK)** **:** Version 8 or higher.
* **Apache Maven** **:** For dependency management and building.

### Compilation & Execution

1. **Clone the repository** to your local machine.
2. **Open a terminal** and navigate to the project's root directory (`charger_adapter/`).
3. **Clean and Compile** the project using Maven:
   ```
   mvn clean compile
   ```
4. **Run** the application:
   ```
   mvn exec:java -Dexec.mainClass="com.chargeradapter.Main"
   ```

The application will start, presenting you with the charging station menu.

## Usage Scenario

1. Run the application to see the main menu.
2. Select option `1` to connect a legacy charger.
3. You will be prompted to choose between a `USB-A` and a `Lightning` charger.
4. Choose `1` for USB-A. The application will create a `USBA_ToUSBC_Adapter` and plug it into the client system. The client calls `charge()` and the legacy device begins charging.
5. Run the scenario again, but this time choose `2` for Lightning. The application creates a `LightningToUSBC_Adapter`. The exact same client code now successfully charges the Lightning device, demonstrating the power of the pattern.