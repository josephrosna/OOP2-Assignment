package com.carpool;

import com.carpool.UserRole;

// Sealed Interfaces
public final class Driver implements UserRole {
    private final int id;
    private final String name;
    private final String car;

    public Driver(int id, String name, String car) {
        this.id = id;
        this.name = name;
        this.car = car;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getCar() { return car; }

    @Override
    public String toString() {
        return "Driver : " + id + ", " + name + ", " + car + "";
    }
}
