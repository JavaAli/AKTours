package com.example.AKTours.web.service;

import com.example.AKTours.model.entity.Country;
import com.example.AKTours.model.entity.Hotel;
import com.example.AKTours.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> findAll(){
        List<Country> collect = StreamSupport.stream(countryRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return collect;
    }

    public Country findByCountryName(String name){
        return countryRepository.findCountryByName(name);
    }
}
