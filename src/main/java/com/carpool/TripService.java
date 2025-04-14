package com.carpool;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;                     
import java.util.function.Supplier;     
import java.util.stream.Collectors;   
import java.nio.file.*;
import java.util.concurrent.*; 

public class TripService {
    // Collectitons
    private final List<Rider> riders = new ArrayList<>();  
    private final List<Driver> drivers = new ArrayList<>(); 
    private final List<Trip> trips = new ArrayList<>();     
    private int nextTripId = 1;

    public void addRider(Rider rider) {
        riders.add(rider);  
    }

    public void addDriver(Driver driver) {
        drivers.add(driver); 
    }

    // Streams , Lambda
    public List<Driver> getAvailableDrivers(Location from, Location to) {
        return drivers.stream()
                .filter(d -> true) // placeholder
                .collect(Collectors.toList());
    }

    // Functional Interface - Supplier , Lambda
    public double calculateFare(Location from, Location to) {
        Supplier<Double> fareSupplier = () -> 5 + Math.random() * 45;
        return fareSupplier.get();
    }

    public Trip bookTrip(Rider rider, Driver driver) {
        Trip trip = new Trip(nextTripId++, rider, driver, LocalDateTime.now().plusHours(1));
        trips.add(trip); 
        return trip;
    }

    // Streams , Comparator , Lambda
    public void printTrips() {
        trips.stream()
             .sorted(Comparator.comparing(Trip::time))
             .forEach(System.out::println);
    }

    // Streams , groupingBy
    public Map<String, List<Rider>> groupRidersByCity() {
        return riders.stream()
                     .collect(Collectors.groupingBy(r -> r.from().city()));
    }

    

    // NIO2
    public void saveTrips(Path path) throws IOException {
        try (var writer = Files.newBufferedWriter(path)) {
            DateTimeFormatter fmt = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
            for (Trip t : trips) {
                writer.write(t.id() + "," + t.rider().name() + "," + t.driver().getName() + "," + t.time().format(fmt));
                writer.newLine();
            }
        }
    }

    // Concurrency ,Streams , Lambda
    public void notifyRidersAsync() throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        List<Callable<String>> tasks = trips.stream()
            .map(t -> (Callable<String>) () -> {
                Thread.sleep(100);
                return "Notified " + t.rider().name();
            })
            .collect(Collectors.toList());

        List<Future<String>> results = executor.invokeAll(tasks);
        for (Future<String> r : results) {
            System.out.println(r.get());
        }
        executor.shutdown();
    }
}