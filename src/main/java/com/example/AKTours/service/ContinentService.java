package com.example.AKTours.service;

import com.example.AKTours.repository.ContinentRepository;
import org.springframework.stereotype.Service;

@Service
public class ContinentService  {

    private final ContinentRepository continentRepository;

    public ContinentService(ContinentRepository continentRepository) {
        this.continentRepository = continentRepository;
    }
}
