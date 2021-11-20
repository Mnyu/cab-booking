package com.learning.cabbooking.strategies;

import com.learning.cabbooking.model.Location;

public interface PricingStrategy {

    double findPrice(Location from, Location to);
}
