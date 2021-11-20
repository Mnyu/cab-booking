package com.learning.cabbooking.strategies;

import com.learning.cabbooking.model.Cab;

import java.util.List;

public interface CabMatchingStrategy {

    Cab findCab(List<Cab> candidateCabs);
}
