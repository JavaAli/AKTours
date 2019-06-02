package com.example.AKTours.web.service;

import com.example.AKTours.repository.ContinentRepository;
import org.springframework.stereotype.Service;

@Service
public class ContinentService  {

    private final ContinentRepository continentRepository;

    public ContinentService(ContinentRepository continentRepository) {
        this.continentRepository = continentRepository;
    }
}
