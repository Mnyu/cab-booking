package com.learning.cabbooking.service;


import com.learning.cabbooking.model.Rider;
import com.learning.cabbooking.repository.RiderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RiderServiceImpl implements RiderService {

    private RiderRepository riderRepository;

    @Autowired
    public RiderServiceImpl(RiderRepository riderRepository) {
        this.riderRepository = riderRepository;
    }

    @Override
    public void register(String name, String email) {
        riderRepository.createRider(new Rider(name, name, email));
    }

    @Override
    public Rider getRiderById(String id) {
        return riderRepository.getRider(id);
    }
}
