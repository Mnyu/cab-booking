package com.learning.cabbooking.service;

import com.learning.cabbooking.model.Cab;
import com.learning.cabbooking.model.Location;
import com.learning.cabbooking.model.Trip;
import com.learning.cabbooking.model.TripStatus;
import com.learning.cabbooking.repository.TripRepository;
import com.learning.cabbooking.strategies.CabMatchingStrategy;
import com.learning.cabbooking.strategies.PricingStrategy;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripServiceImpl implements TripService {

    private TripRepository tripRepository;
    private CabService cabService;
    private RiderService riderService;

    @Getter @Setter
    private PricingStrategy pricingStrategy;

    @Getter @Setter
    private CabMatchingStrategy cabMatchingStrategy;

    @Autowired
    public TripServiceImpl(TripRepository tripRepository,
                           CabService cabService,
                           RiderService riderService,
                           PricingStrategy pricingStrategy,
                           CabMatchingStrategy cabMatchingStrategy) {
        this.tripRepository = tripRepository;
        this.cabService = cabService;
        this.riderService = riderService;
        this.pricingStrategy = pricingStrategy;
        this.cabMatchingStrategy = cabMatchingStrategy;
    }

    @Override
    public Trip createTrip(String riderId, Location fromPoint, Location toPoint) {
        // Steps
        // 1. Find list of candidate cabs - check distance and availability
        // 2. Perform CabMatchingStrategy to find matchedCab out of candidate cabs
        // 3. Perform PricingStrategy to find price
        // 4. Create trip using Rider,Cab,from,to,price.
        // 5. Save trip
        // 6. Set currentTrip for matchedCab

        List<Cab> candidateCabs = cabService.searchAvailableCabs(fromPoint); // 1
        Cab matchedCab = cabMatchingStrategy.findCab(candidateCabs); // 2
        double price = pricingStrategy.findPrice(fromPoint, toPoint); // 3
        Trip trip = new Trip(matchedCab, riderService.getRiderById(riderId), fromPoint, toPoint, price); // 4
        tripRepository.createTrip(trip); // 5
        matchedCab.setCurrentTrip(trip); // 6
        return trip;
        // Note : Multi-threading has to be taken care of here
    }

    @Override
    public List<Trip> getTripsForRider(String riderId) {
        return tripRepository.getTripsForRider(riderId);
    }

    @Override
    public List<Trip> getTripsForCab(String cabId) {
        return tripRepository.getTripsForCab(cabId);
    }

    @Override
    public void startTrip(String cabId) {
        Cab cab = cabService.getCabById(cabId);
        if(cab.getCurrentTrip() == null || cab.getCurrentTrip().getTripStatus() != TripStatus.BOOKED) {
            throw new RuntimeException("Unable to start trip.");
        }
        cab.getCurrentTrip().start();
        cab.setCurrentLocation(cab.getCurrentTrip().getFromPoint());
    }

    @Override
    public void endTrip(String cabId) {
        Cab cab = cabService.getCabById(cabId);
        if(cab.getCurrentTrip() == null || cab.getCurrentTrip().getTripStatus() != TripStatus.INPROGRESS) {
            throw new RuntimeException("Unable to end trip.");
        }
        cab.getCurrentTrip().end();
        cab.setCurrentLocation(cab.getCurrentTrip().getToPoint());
        cab.setCurrentTrip(null);
    }
}
