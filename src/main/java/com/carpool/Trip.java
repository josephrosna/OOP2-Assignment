package com.carpool;

import java.time.LocalDateTime;

//Records, Date/Time API
public record Trip(int id, Rider rider, Driver driver, LocalDateTime time) { }
