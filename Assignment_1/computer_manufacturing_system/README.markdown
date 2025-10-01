Computer Manufacturing System

This Java console application demonstrates creational design patterns (Factory, Abstract Factory, Builder) for designing and manufacturing computer systems. It allows users to build computers with components from different manufacturers (Lenovo, Dell) or create custom configurations with modular component selection.

Project Structure

The project is organized into the following package structure:

```
.
├── pom.xml
└── src
    └── com
        └── manufacturing
            └── system
                ├── builder
                │   ├── Computer.java
                │   ├── ComputerBuilder.java
                │   ├── ComputerDirector.java
                │   ├── CustomComputerBuilder.java
                │   ├── GamingComputerBuilder.java
                │   └── WorkstationComputerBuilder.java
                ├── factories
                │   ├── ComputerFactory.java
                │   ├── ComputerPartFactory.java
                │   ├── DellComputerFactory.java
                │   └── LenovoComputerFactory.java
                ├── parts
                │   ├── impl
                │   │   ├── StandardCPU.java
                │   │   ├── StandardGPU.java
                │   │   ├── StandardHardDrive.java
                │   │   ├── StandardMotherboard.java
                │   │   ├── StandardRAM.java
                │   │   └── StandardSSD.java
                │   ├── ComputerPart.java
                │   ├── CPU.java
                │   ├── GPU.java
                │   ├── HardDrive.java
                │   ├── Motherboard.java
                │   ├── RAM.java
                │   └── SSD.java
                └── ComputerManufacturingSystem.java
```

How to Compile and Run

There are two ways to build and run this project: manually using javac or with Apache Maven.

Option 1: Manual Compilation

1. Navigate to the src directory in your terminal.

2. Compile all .java files. The -d . flag places the compiled .class files into their respective package directories within the current directory (src).

   ```
   javac -d . com/manufacturing/system/ComputerManufacturingSystem.java com/manufacturing/system/parts/impl/*.java com/manufacturing/system/factories/*.java com/manufacturing/system/builder/*.java com/manufacturing/system/parts/*.java
   ```

3. Run the application from the src directory, specifying the fully qualified name of the main class.

   ```
   java com.manufacturing.system.ComputerManufacturingSystem
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

Example 1: Building a Pre-configured PC

```
=============================================
  Welcome to the Computer Manufacturing System
=============================================

Please choose an option:
1. Build a Pre-configured Computer (Lenovo/Dell)
2. Build a Custom Computer
3. Exit
Enter your choice: 1

--- Build a Pre-configured Computer ---
Select a manufacturer:
1. Lenovo
2. Dell
Enter your choice: 2

Select a computer type:
1. Gaming PC
2. Workstation PC
Enter your choice: 2

Successfully manufactured the following computer:
------------------------------------------
Computer Configuration: Workstation PC (Dell)
------------------------------------------
  Motherboard : Dell Precision 5820 Motherboard              | Manufacturer: Dell     | Price: $300.00
  CPU         : Intel Xeon W-2123 (Dell OEM)                 | Manufacturer: Dell     | Price: $450.00
  RAM         : 32GB DDR4 2666MHz ECC (Dell)                 | Manufacturer: Dell     | Price: $200.00
  GPU         : NVIDIA Quadro RTX 4000 (Dell)                | Manufacturer: Dell     | Price: $900.00
  HardDrive   : 2TB 7200rpm HDD (Dell)                       | Manufacturer: Dell     | Price: $100.00
  SSD         : 1TB PCIe NVMe M.2 SSD (Dell)                 | Manufacturer: Dell     | Price: $180.00
------------------------------------------
Total System Price: $2130.00
------------------------------------------
```

Example 2: Building a Custom PC

```
Please choose an option:
1. Build a Pre-configured Computer (Lenovo/Dell)
2. Build a Custom Computer
3. Exit
Enter your choice: 2

--- Build a Custom Computer ---
You must select a manufacturer for the following compulsory parts:
CPU, Motherboard, RAM, SSD

Select Motherboard manufacturer:
1. Lenovo
2. Dell
Enter your choice: 1

Select CPU manufacturer:
1. Lenovo
2. Dell
Enter your choice: 2

*** WARNING: CPU manufacturer (Dell) does not match Motherboard manufacturer (Lenovo). This may cause compatibility issues.

Select RAM manufacturer:
1. Lenovo
2. Dell
Enter your choice: 1

Select SSD manufacturer:
1. Lenovo
2. Dell
Enter your choice: 1

--- Optional Parts (can be skipped) ---

Select GPU manufacturer:
1. Lenovo
2. Dell
3. Skip this part
Enter your choice: 3
Skipping GPU.

Select Hard Drive manufacturer:
1. Lenovo
2. Dell
3. Skip this part
Enter your choice: 2

Successfully configured your custom computer:
------------------------------------------
Computer Configuration: Custom Build
------------------------------------------
  Motherboard : Lenovo ThinkStation P330 Motherboard         | Manufacturer: Lenovo   | Price: $250.00
  CPU         : Intel Xeon W-2123 (Dell OEM)                 | Manufacturer: Dell     | Price: $450.00
  RAM         : 16GB DDR4 2666MHz (Lenovo)                   | Manufacturer: Lenovo   | Price: $150.00
  SSD         : 512GB PCIe NVMe M.2 SSD (Lenovo)             | Manufacturer: Lenovo   | Price: $120.00
  HardDrive   : 2TB 7200rpm HDD (Dell)                       | Manufacturer: Dell     | Price: $100.00
------------------------------------------
Total System Price: $1070.00
------------------------------------------
```