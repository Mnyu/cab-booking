package com.learning.cabbooking.strategies;

import com.learning.cabbooking.model.Location;
import org.springframework.stereotype.Component;

@Component
public class DefaultPricingStrategy implements PricingStrategy {

    private final double COST_PER_KM = 10.0;

    @Override
    public double findPrice(Location from, Location to) {
        double xComp = Math.pow(from.getX() - to.getX(), 2);
        double yComp = Math.pow(from.getY() - to.getY(), 2);
        return Math.sqrt(xComp + yComp) * COST_PER_KM;
    }
}
