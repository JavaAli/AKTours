package com.example.AKTours.repository;


import com.example.AKTours.model.entity.Hotel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface HotelRepository extends CrudRepository<Hotel, Long> {

    public List<Hotel> findHotelByStandard(String standard);

    public Hotel findHotelByName(String name);
}
