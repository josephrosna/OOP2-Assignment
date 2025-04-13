package com.carpool;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;                     
import java.util.function.Supplier;     
import java.util.stream.Collectors;    

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

    
}
