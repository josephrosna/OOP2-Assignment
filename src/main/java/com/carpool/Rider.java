package com.carpool;

import com.carpool.UserRole;

//Records, Sealed Interfaces
public record Rider(int id, String name, Location from, Location to) implements UserRole { }
