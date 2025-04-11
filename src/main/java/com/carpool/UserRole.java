package com.carpool;

//Sealed Interfaces
public sealed interface UserRole permits Admin, Driver, Rider { }
