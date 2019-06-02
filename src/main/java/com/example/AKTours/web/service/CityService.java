package com.example.AKTours.web.service;

import com.example.AKTours.repository.CityRepository;
import org.springframework.stereotype.Service;

@Service
public class CityService  {

    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }
}
