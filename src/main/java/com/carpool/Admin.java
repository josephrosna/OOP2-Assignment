package com.carpool;

//Sealed Interfaces
public final class Admin implements UserRole {
    private final String username;
    public Admin(String username) { this.username = username; }
    public String getUsername() { return username; }
}
