package com.learning.cabbooking.service;

import com.learning.cabbooking.model.Cab;
import com.learning.cabbooking.model.Location;
import com.learning.cabbooking.repository.CabRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CabServiceImpl implements CabService {

    private final double MAX_DIST_TO_CHECK = 10.0;

    private CabRepository cabRepository;

    @Autowired
    public CabServiceImpl(CabRepository cabRepository) {
        this.cabRepository = cabRepository;
    }

    @Override
    public void register(String id, String driver) {
        cabRepository.createCab(new Cab(id, driver));
    }

    @Override
    public Cab getCabById(String id) {
        return cabRepository.getCab(id);
    }

    @Override
    public void updateCabLocation(String id, Location newLocation) {
        Cab cab = cabRepository.getCab(id);
        cab.setCurrentLocation(newLocation);
        cabRepository.updateCab(cab);
    }

    @Override
    public void updateCabAvailability(String id, boolean isAvailable) {
        Cab cab = cabRepository.getCab(id);
        cab.setAvailable(isAvailable);
        cabRepository.updateCab(cab);
    }

    @Override
    public List<Cab> searchAvailableCabs(Location fromPoint) {
        return cabRepository.getAllCabs().stream()
                .filter(cab -> cab.isAvailable() && cab.getCurrentTrip() == null)
                .filter(cab -> isCabInVicinity(cab, fromPoint))
                .collect(Collectors.toList());
    }

    private boolean isCabInVicinity(Cab cab, Location from) {
        Location curr = cab.getCurrentLocation();
        if (curr == null)
            return false;
        double xComp = Math.pow(from.getX() - curr.getX(), 2);
        double yComp = Math.pow(from.getY() - curr.getY(), 2);
        return Math.sqrt(xComp + yComp) <=  MAX_DIST_TO_CHECK;
    }
}
