package com.example.AKTours.web.config;


import com.example.AKTours.model.entity.Hotel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
@Slf4j
public class LoadDataBase {
    @Bean
    CommandLineRunner initDataBase(HotelRepository hotels, ) {
        return args -> {
            log.info("inserting hotel to database " +hotels.save(new Hotel(1,"Zacisze", "3 STARS","Small quiet hotel at English country")));
            log.info("inserting hotel to database " +hotels.save(new Hotel(2,"Tropicana", "4 STARS","Big luxury hotel at the Jamayca Island")));
            log.info("inserting hotel to database " +hotels.save(new Hotel(3,"Hilton", "5 STARS","Exclusive hotel in New York")));

        };
}
