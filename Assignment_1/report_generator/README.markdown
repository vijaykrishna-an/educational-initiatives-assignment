# Dynamic Report Generator

This console-based Java application demonstrates the **Bridge** design pattern. It solves the common problem of needing to generate various types of reports (e.g., Sales Report, Performance Report) in multiple output formats (e.g., HTML, Plain Text) without creating a combinatorial explosion of classes.

The application allows a user to dynamically pair any report type with any format type at runtime, showcasing the flexibility of decoupling an abstraction from its implementation.

## Architectural Design & The Bridge Pattern

The goal of the Bridge pattern is to split a large class or a set of closely related classes into two separate hierarchies—**abstraction** and  **implementation** **—which can be developed independently of each other.

* **Abstraction (**`Report`**):** This is the high-level control hierarchy. It consists of the abstract `Report` class and its concrete subclasses (`SalesReport`, `PerformanceReport`). These classes define the logic for what data goes into a report but delegate the actual formatting to the implementation.
* **Implementor (**`ReportFormatter`**):** This is the implementation hierarchy. It consists of the `ReportFormatter` interface and its concrete subclasses (`HtmlFormatter`, `PlainTextFormatter`). These classes know how to format data but have no knowledge of what kind of report they are formatting.

The "bridge" is the composition link between the `Report` abstraction and the `ReportFormatter` implementation. Each `Report` object holds a reference to a `ReportFormatter` object, allowing you to mix and match them at runtime. This prevents a rigid inheritance structure and makes the system highly extensible.

## Project Structure

```
report_generator/
├── com/
│   └── reportgenerator/
│       ├── reports/
│       │   ├── Report.java
│       │   ├── SalesReport.java
│       │   └── PerformanceReport.java
│       ├── formatters/
│       │   ├── ReportFormatter.java
│       │   ├── HtmlFormatter.java
│       │   └── PlainTextFormatter.java
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
2. **Open a terminal** and navigate to the project's root directory (`report_generator/`).
3. **Clean and Compile** the project using Maven:
   ```
   mvn clean compile
   ```
4. **Run** the application:
   ```
   mvn exec:java -Dexec.mainClass="com.reportgenerator.Main"
   ```

The application will start, presenting you with the report generation menu.

## Usage Scenario

1. Run the application to see the main menu.
2. Select a report type to generate, for example, `1` for "Sales Report".
3. Next, select an export format, for example, `1` for "HTML".
4. The application will generate the Sales Report formatted as HTML.
5. Run the scenario again. This time, select `1` for "Sales Report" again, but choose `2` for "Plain Text" format.
6. The exact same `SalesReport` object will now generate its content in a completely different format, demonstrating the power of the bridge. You can do this for any combination of report and format.
