package com.learning.cabbooking.strategies;

import com.learning.cabbooking.model.Cab;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DefaultCabMatchingStrategy implements CabMatchingStrategy {

    @Override
    public Cab findCab(List<Cab> candidateCabs) {
        if (candidateCabs.size() == 0) {
            return null;
        }
        return candidateCabs.get(0);
    }
}
