package com.learning.cabbooking.repository;

import com.learning.cabbooking.model.Cab;
import com.learning.cabbooking.model.Rider;
import com.learning.cabbooking.model.Trip;
import lombok.NonNull;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TripRepository {

    private Map<String, List<Trip>> riderTrips = new HashMap<>();
    private Map<String, List<Trip>> cabTrips = new HashMap<>();

    public void createTrip(@NonNull Trip trip) {
        Rider tripRider = trip.getRider();
        Cab tripCab = trip.getCab();
        riderTrips.computeIfAbsent(tripRider.getId(), key -> new ArrayList<>());
        riderTrips.get(tripRider.getId()).add(trip);
        cabTrips.computeIfAbsent(tripCab.getId(), key -> new ArrayList<>());
        cabTrips.get(tripCab.getId()).add(trip);
    }

    public List<Trip> getTripsForRider(String riderId) {
        return riderTrips.getOrDefault(riderId, Collections.emptyList());
    }

    public List<Trip> getTripsForCab(String cabId) {
        return cabTrips.getOrDefault(cabId, Collections.emptyList());
    }
}
