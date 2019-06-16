package com.example.AKTours.repository;

import com.example.AKTours.model.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

    public List<Hotel> findHotelByStandard(String standard);

    public List<Hotel> findHotelByName(String name);

    @Query(value = "SELECT id,hotel_name,description,standard from hotels where city_id = 1", nativeQuery = true)
    List<Hotel> findHotelsInLondon(String name);

    @Query(value = "SELECT id,hotel_name,description,standard from hotels where city_id = 2", nativeQuery = true)
    List<Hotel> findHotelsInParis(String name);

    @Query(value = "SELECT id,hotel_name,description,standard from hotels where city_id = 3", nativeQuery = true)
    List<Hotel> findHotelsInWachock(String name);

    @Query(value = "SELECT id,hotel_name,description,standard from hotels where city_id = 4", nativeQuery = true)
    List<Hotel> findHotelsInTeheran(String name);


}
