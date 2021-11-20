package com.learning.cabbooking.service;

import com.learning.cabbooking.model.Location;
import com.learning.cabbooking.model.Trip;

import java.util.List;

public interface TripService {

    Trip createTrip(String riderId, Location fromPoint, Location toPoint);

    List<Trip> getTripsForRider(String riderId);

    List<Trip> getTripsForCab(String cabId);

    void startTrip(String cabId);

    void endTrip(String cabId);
}
