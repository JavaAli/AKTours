package com.example.AKTours.repository;

import com.example.AKTours.model.entity.City;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {

    Optional<City> findCityByName(String name);

    List<City> findCitiesByCountryName(String name);

    @Query(value = "SELECT *FROM cities c JOIN countries count ON c.country_id = count.id JOIN continents cont ON count.continent_id = cont.continent_id WHERE cont.continent_name=?1", nativeQuery = true)
    List<City> findCitiesByContinentName(String name);
}
