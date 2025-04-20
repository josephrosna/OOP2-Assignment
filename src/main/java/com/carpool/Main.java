package com.carpool;

import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.Deque;
import java.util.Iterator;


//Switch, Pattern Matching, Records
public class Main {
    public static void main(String[] args) {
        TripService service = new TripService();
        Scanner scanner = new Scanner(System.in);

        // Preload drivers
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
            System.out.println("6. Java 22");
            System.out.println("7. Java 23");
            System.out.println("5. Exit");
            System.out.print("Select option: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> {
                    // Records & Scanner
                    System.out.print("Enter your name: ");
                    String name = scanner.nextLine();
                    System.out.print("From city: ");
                    String fromCity = scanner.nextLine();
                    System.out.print("From area: ");
                    String fromArea = scanner.nextLine();
                    System.out.print("To city: ");
                    String toCity = scanner.nextLine();
                    System.out.print("To area: ");
                    String toArea = scanner.nextLine();

                    Rider rider = new Rider(
                        service.groupRidersByCity().size() + 1,
                        name,
                        new Location(fromCity, fromArea),
                        new Location(toCity, toArea)
                    );

                    List<Driver> available = service.getAvailableDrivers(rider.from(), rider.to());
                    System.out.println("\nAvailable Drivers and Fares (€):");
                    for (int i = 0; i < available.size(); i++) {
                        Driver d = available.get(i);
                        double fare = service.calculateFare(rider.from(), rider.to());
                        System.out.printf("%d. %s (%s) - €%.2f%n", i+1, d.getName(), d.getCar(), fare);
                    }
                    System.out.print("Select driver: ");
                    int dChoice = Integer.parseInt(scanner.nextLine()) - 1;
                    Driver chosen = available.get(dChoice);
                    Trip trip = service.bookTrip(rider, chosen);
                    System.out.println("Trip booked: " + trip);
                    // Pattern Matching
                    if (chosen instanceof Driver d) {
                        System.out.println("Pattern matched driver: " + d.getName());
                    }
                }
                case 2 -> service.printTrips();
                case 3 -> {
                    try { service.saveTrips(Path.of("trips.csv")); System.out.println("Trips saved to trips.csv"); }
                    catch (Exception e) { e.printStackTrace(); }
                }
                case 4 -> {
                    try { service.notifyRidersAsync(); }
                    catch (InterruptedException | ExecutionException e) { e.printStackTrace(); }
                }
                case 5 -> { System.out.println("Goodbye!"); exit = true; }
                case 6 -> {
                    System.out.println("Java 22 Unnamed Variable");
                    record Driver(String name, int age, String city) {}
                    Driver p = new Driver("Alice", 30, "Dublin");
                    if (p instanceof Driver(_, int age, _)) {
                        System.out.println("Extracted age only using unnamed pattern " + age);
                    }
                    System.out.print("Looping with unnamed variable");
                    for (int _ : new int[]{1, 2, 3}) {
                        System.out.print("# ");
                    }
                    System.out.println();
                }
                case 7 -> {
                    System.out.println("Java 23: Sequenced Collections");
                    Deque<String> deque = new ArrayDeque<>();
                    deque.addFirst("First");
                    deque.addLast("Last");
                    System.out.println("First Element: " + deque.getFirst());
                    System.out.println("Last Element: " + deque.getLast());
                    System.out.println("Elements in reverse order:");
                    Iterator<String> reverseIterator = deque.descendingIterator();
                    while (reverseIterator.hasNext()) {
                        System.out.println(reverseIterator.next());
                    }
                }

                default -> System.out.println("Invalid option!");
            }
        }
        scanner.close();
    }
}
