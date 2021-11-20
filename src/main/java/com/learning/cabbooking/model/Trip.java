package com.learning.cabbooking.model;

import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Getter
@ToString
public class Trip {
    private UUID id;
    private Cab cab;
    private Rider rider;
    private Location fromPoint;
    private Location toPoint;
    private double price;
    private TripStatus tripStatus;

    public Trip(Cab cab, Rider rider, Location from, Location to, double price) {
        this.id = UUID.randomUUID();
        this.cab = cab;
        this.rider = rider;
        this.fromPoint = from;
        this.toPoint = to;
        this.price = price;
        this.tripStatus = TripStatus.BOOKED;
    }

    public void start() {
        this.tripStatus = TripStatus.INPROGRESS;
    }

    public void end() {
        this.tripStatus = TripStatus.FINISHED;
    }
}
