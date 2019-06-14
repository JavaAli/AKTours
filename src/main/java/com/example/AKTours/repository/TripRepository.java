package com.example.AKTours.repository;

import com.example.AKTours.model.entity.Hotel;
import com.example.AKTours.model.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
    @Query(value = "SELECT trip FROM Trip trip JOIN Hotel hotel ON hotel.id=trip.hotel_id_pk WHERE h.hotel_name= 1")
    List<Hotel> findTripByHotelName(String name);
}
