package com.example.AKTours.web.config;


import com.example.AKTours.model.entity.*;
import com.example.AKTours.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import static java.util.Arrays.asList;

@Configuration
@Slf4j
public class LoadDataBase {
    Hotel hotel1 = new Hotel(1000L, "Zacisze", "3 STARS", "Small quiet hotel at English country");
    Hotel hotel4 = new Hotel(1003L, "Mormont", "4 STARS", "Big hotel at English country");
    Hotel hotel2 = new Hotel(1002L, "Hilton", "5 STARS", "Exclusive hotel in New York");
    Hotel hotel3 = new Hotel(1001L, "Tropicana", "4 STARS", "Big luxury hotel at the Jamayca Island");
    Hotel hotel5 = new Hotel(1004L, "Metropol", "5 STARS", "Big luxury hotel in The Centre of City");

    Airport airport1 = new Airport(1000L, "Sarbinowo");
    Airport airport2 = new Airport(1001L, "Heathrow");
    Airport airport3 = new Airport(1003L, "Orly");

    City brighton = new City(1002L, "Brighton", new HashSet<Hotel>(asList(hotel1, hotel4)),
            new HashSet<Airport>(asList(airport1, airport2)));
    City london = new City(1003L, "London", new HashSet<Hotel>(asList(hotel3, hotel2)),
            new HashSet<Airport>(asList(airport3, airport2)));
    City paris = new City(1004L, "Paris", new HashSet<Hotel>(asList(hotel2, hotel5)),
            new HashSet<Airport>(asList(airport3)));
    Country england=new Country(1000L, "Great Britan",new HashSet<City>(asList(brighton,london)));
    Country france=new Country(1000L, "France",new HashSet<City>(asList(paris)));

    Continent europe= new Continent(1000L,"Europe", new HashSet<Country>(asList(england,france)));
    @Bean
    CommandLineRunner initDataBase(HotelRepository hotels, CityRepository cities, AirportRepository airports,
                                   CountryRepository countries, ContinentRepository continents) {
        return args -> {
            log.info("inserting hotel to database " + hotels.save(hotel1));
            log.info("inserting hotel to database " + hotels.save(hotel2));
            log.info("inserting hotel to database " + hotels.save(hotel3));
            log.info("inserting city to database " + cities.save(brighton));
            log.info("inserting city to database " + cities.save(london));
            log.info("inserting airport to database " + airports.save(airport1));
            log.info("inserting airport to database " + airports.save(airport2));
            log.info("inserting airport to database " + airports.save(airport3));
            log.info("inserting country to database " + countries.save(england));
            log.info("inserting country to database " + countries.save(france));
            log.info("inserting continent to database " + continents.save(europe));


        };
    }
}