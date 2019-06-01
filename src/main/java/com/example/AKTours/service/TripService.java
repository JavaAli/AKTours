package com.example.AKTours.service;

import com.example.AKTours.repository.TripRepository;
import org.springframework.stereotype.Service;

@Service
public class TripService {

    private final TripRepository tripRepository;

    public TripService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }
}
