package com.learning.cabbooking;

import com.learning.cabbooking.model.Location;
import com.learning.cabbooking.model.Trip;
import com.learning.cabbooking.repository.CabRepository;
import com.learning.cabbooking.repository.RiderRepository;
import com.learning.cabbooking.repository.TripRepository;
import com.learning.cabbooking.service.CabService;
import com.learning.cabbooking.service.CabServiceImpl;
import com.learning.cabbooking.service.RiderService;
import com.learning.cabbooking.service.RiderServiceImpl;
import com.learning.cabbooking.service.TripService;
import com.learning.cabbooking.service.TripServiceImpl;
import com.learning.cabbooking.strategies.CabMatchingStrategy;
import com.learning.cabbooking.strategies.DefaultCabMatchingStrategy;
import com.learning.cabbooking.strategies.DefaultPricingStrategy;
import com.learning.cabbooking.strategies.PricingStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CabBookingTests {

    CabService cabService;
    RiderService riderService;
    TripService tripService;

    @BeforeEach
    public void setup() {
        CabRepository cabRepository = new CabRepository();
        RiderRepository riderRepository = new RiderRepository();
        TripRepository tripRepository = new TripRepository();

        CabMatchingStrategy cabMatchingStrategy = new DefaultCabMatchingStrategy();
        PricingStrategy pricingStrategy = new DefaultPricingStrategy();

        cabService = new CabServiceImpl(cabRepository);
        riderService = new RiderServiceImpl(riderRepository);
        tripService = new TripServiceImpl(tripRepository, cabService, riderService, pricingStrategy, cabMatchingStrategy);
    }

    @Test
    public void testFlows() {

        // 1. Register riders
        riderService.register("rider1", "r1@cabservice.com");
        riderService.register("rider2", "r2@cabservice.com");
        riderService.register("rider3", "r3@cabservice.com");
        riderService.register("rider4", "r4@cabservice.com");

        // 2. Register cabs
        cabService.register("TS07001", "driver1");
        cabService.register("TS07002", "driver2");
        cabService.register("TS07003", "driver3");
        cabService.register("TS07004", "driver4");
        cabService.register("TS07005", "driver5");

        // 3. Update Cab Location
        cabService.updateCabLocation("TS07001", new Location(1.0, 1.0));
        cabService.updateCabLocation("TS07002", new Location(2.0, 2.0));
        cabService.updateCabLocation("TS07003", new Location(100.0, 100.0));
        cabService.updateCabLocation("TS07004", new Location(110.0, 110.0));
        cabService.updateCabLocation("TS07005", new Location(4.0, 4.0));

        // 4. Update Cab Availability
        cabService.updateCabAvailability("TS07002", false);
        cabService.updateCabAvailability("TS07004", false);

        // 5. Book cab/trip
        Trip trip1 = tripService.createTrip("rider1", new Location(0.0, 0.0), new Location(500, 500));
        Trip trip2 = tripService.createTrip("rider2", new Location(0.0, 0.0), new Location(500, 500));

        System.out.println("****** Booked Trip for rider 1 ******");
        System.out.println(trip1);
        System.out.println("****** Booked Trip for rider 2 ******");
        System.out.println(trip2);

        // 6. Start trip
        tripService.startTrip(trip1.getCab().getId());
        tripService.startTrip(trip2.getCab().getId());
        System.out.println("****** In progress Trip for rider 1 ******");
        System.out.println(trip1);
        System.out.println("****** In progress Trip for rider 2 ******");
        System.out.println(trip2);

        // 7. End trip
        tripService.endTrip(trip2.getCab().getId());

        System.out.println("***** Trips for Rider 1 *****");
        System.out.println(tripService.getTripsForRider("rider1"));
        System.out.println("***** Trips for Rider 2 *****");
        System.out.println(tripService.getTripsForRider("rider2"));

        tripService.endTrip(trip1.getCab().getId());
    }
}
