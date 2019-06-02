package com.example.AKTours.repository;


import com.example.AKTours.model.entity.Hotel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HotelRepository extends CrudRepository<Hotel, Long> {

    public List<Hotel> findHotelByStandard(String standard);

    public Hotel findHotelByName(String name);
}
