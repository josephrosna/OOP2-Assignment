# OOP2 Assignment – Carpool Management System

## Overview

This project implements a **Carpool Management System** using **Java 21** features as part of the OOP2 module. It illustrates Java 21 features and object-oriented design principles within a simple interactive console application. Users can request rides, view trips, save trip data, and receive asynchronous notifications.

---

## Project Structure

All source files are under `src/main/java/com/carpool/`:

```
Admin.java
Driver.java
Rider.java
UserRole.java
Location.java
Trip.java
TripService.java
LocalizationUtil.java
Main.java
```

---

## Java Features Demonstrated

### Core Java 21 Features

- **Records**  
  - `Location`, `Trip`, and `Rider` are implemented as records for concise immutable data carriers.

- **Sealed Interfaces**  
  - `UserRole` is a sealed interface permitting only `Admin`, `Driver`, and `Rider`.

- **Switch Expressions**  
  - Arrow syntax used in `Main.java` for the menu-driven switch on user choice.

- **Pattern Matching (`instanceof`)**  
  - In `Main.java`, demos matching a `Driver` reference after `instanceof`.

- **Date/Time API**  
  - `java.time.LocalDateTime` used in `Trip` to schedule trips.

### Streams and Functional Interfaces

- **Streams API** in `TripService`  
  - **Intermediate operations**: `filter()`, `map()`, `sorted()`, `limit()`.  
  - **Terminal operations**: `forEach()`, `count()`, `anyMatch()`, `findFirst()`.
- **Collectors**  
  - `groupingBy()` to group riders by city.  
  - `toMap()` or custom mappings as placeholders for extension.
- **Functional Interfaces**  
  - `Supplier<Double>` in `calculateFare()` to generate random fare values.

### Advanced Features

- **Comparator with Lambdas**  
  - `Comparator.comparing(Trip::time)` to sort trips by scheduled time.

- **NIO.2 File API**  
  - Writing trip data to CSV using `Files.newBufferedWriter(Path)`.

- **Concurrency**  
  - `ExecutorService` in `notifyRidersAsync()` to notify riders in parallel using `Callable`.

- **Localization**  
  - `ResourceBundle` in `LocalizationUtil` to support multi-locale message retrieval.

---

## Usage

When the application starts, you can:

1. **Request a Ride** – Enter your details and select from available drivers.  
2. **View Scheduled Trips** – Display all booked trips sorted by time.  
3. **Save Trips to File** – Export current trips to `trips.csv`.  
4. **Notify Riders** – Send asynchronous notifications to all riders.  
5. **Exit** – Close the application.

---
