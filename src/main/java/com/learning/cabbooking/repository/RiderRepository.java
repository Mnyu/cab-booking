package com.learning.cabbooking.repository;

import com.learning.cabbooking.model.Rider;
import lombok.NonNull;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class RiderRepository {

    private Map<String, Rider> riders = new HashMap<>();

    public void createRider(@NonNull Rider rider) {
        if (riders.containsKey(rider.getId())) {
            throw new RuntimeException("Rider already exists");
        }
        riders.put(rider.getId(), rider);
    }

    public Rider getRider(@NonNull String id) {
        if (!riders.containsKey(id)) {
            throw new RuntimeException("No rider exists with id : " + id);
        }
        return riders.get(id);
    }

    public void updateRider(@NonNull Rider rider) {
        if (!riders.containsKey(rider.getId())) {
            throw new RuntimeException("No rider exists with id : " + rider.getId());
        }
        riders.put(rider.getId(), rider);
    }
}
