package com.learning.cabbooking.service;

import com.learning.cabbooking.model.Cab;
import com.learning.cabbooking.model.Location;

import java.util.List;

public interface CabService {

    void register(String id, String driver);

    Cab getCabById(String id);

    void updateCabLocation(String id, Location newLocation);

    void updateCabAvailability(String id, boolean isAvailable);

    List<Cab> searchAvailableCabs(Location fromPoint);
}
