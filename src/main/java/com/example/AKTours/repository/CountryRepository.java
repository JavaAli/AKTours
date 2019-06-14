package com.example.AKTours.repository;


import com.example.AKTours.model.entity.Country;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<Country, Long> {

     Country findCountryByName(String name);
}
