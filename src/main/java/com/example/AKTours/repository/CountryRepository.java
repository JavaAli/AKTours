package com.example.AKTours.repository;


import com.example.AKTours.model.entity.Country;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CountryRepository extends CrudRepository<Country, Long> {

     Country findCountryByName(String name);

     List<Country> findCountryByContinent_Name(String name);
}
