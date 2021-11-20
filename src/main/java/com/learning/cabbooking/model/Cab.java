package com.learning.cabbooking.model;


import lombok.Getter;
import lombok.Setter;

public class Cab {
    @Getter
    private String id;

    @Getter
    private String driver;

    @Getter @Setter
    private Location currentLocation;

    @Getter @Setter
    private boolean isAvailable;

    @Getter @Setter
    private Trip currentTrip;

    public Cab(String id, String driver) {
        this.id = id;
        this.driver = driver;
        this.isAvailable = true;
    }
}
