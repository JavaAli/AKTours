package com.example.AKTours.repository;

import com.example.AKTours.model.entity.City;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {

    public City findCityByName(String name);

    public List<City> findCitiesByCountryName(String name);

    @Query(value = "SELECT *FROM cities c JOIN countries count ON c.country_id = count.id JOIN continents cont ON count.continent_id = cont.continent_id WHERE cont.continent_name=?1", nativeQuery = true)
    public List<City> findCitiesByContinentName(String name);
}
