package com.learning.cabbooking.repository;

import com.learning.cabbooking.model.Cab;
import lombok.NonNull;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CabRepository {

    private Map<String, Cab> cabs = new HashMap<>();

    public void createCab(@NonNull Cab cab) {
        if (cabs.containsKey(cab.getId())) {
            throw new RuntimeException("Cab already exists");
        }
        cabs.put(cab.getId(), cab);
    }

    public Cab getCab(@NonNull String id) {
        if (!cabs.containsKey(id)) {
            throw new RuntimeException("No cab exists with id : " + id);
        }
        return cabs.get(id);
    }

    public void updateCab(@NonNull Cab cab) {
        if (!cabs.containsKey(cab.getId())) {
            throw new RuntimeException("No cab exists with id : " + cab.getId());
        }
        cabs.put(cab.getId(), cab);
    }

    public List<Cab> getAllCabs() {
        return new ArrayList<>(cabs.values());
    }
}
