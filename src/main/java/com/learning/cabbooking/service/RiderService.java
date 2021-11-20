package com.learning.cabbooking.service;

import com.learning.cabbooking.model.Rider;

public interface RiderService {

    void register(String name, String email);

    Rider getRiderById(String id);
}
