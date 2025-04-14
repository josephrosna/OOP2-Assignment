package com.carpool;

import java.nio.file.Path;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

//Switch
public class Main {
    public static void main(String[] args) {
        TripService service = new TripService();
        Scanner scanner = new Scanner(System.in);

        // Add drivers
        service.addDriver(new Driver(1, "Rosna", "Toyota"));
        service.addDriver(new Driver(2, "Anitta", "Honda"));
        service.addDriver(new Driver(3, "Little", "Tesla"));

        boolean exit = false;
        while (!exit) {
            System.out.println("\n--- Car Pooling Menu ---");
            System.out.println("1. Request a Ride");
            System.out.println("2. View Scheduled Trips");
            System.out.println("3. Save Trips to File");
            System.out.println("4. Notify Riders");
            System.out.println("5. Exit");
            System.out.print("Select option: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 5 -> { System.out.println("Goodbye!"); exit = true; }
                default -> System.out.println("Invalid option!");
            }
        }
        scanner.close();
    }
}
