package com.example.AKTours.repository;

import com.example.AKTours.model.entity.Airport;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface AirportRepository extends CrudRepository<Airport, Long> {

     Optional<Airport> findAirportByName(String name);

    @Query(value = "SELECT airport_name,id from airports where city_id = 1", nativeQuery = true)
    public List<Airport> findAirportsInLondon(String name);

    @Query(value = "SELECT airport_name,id from airports where city_id = 2", nativeQuery = true)
    public List<Airport> findAirportsInParis(String name);

    @Query(value = "SELECT airport_name,id from airports where city_id = 5", nativeQuery = true)
    public List<Airport> findAirportsInKatowice(String name);

}
