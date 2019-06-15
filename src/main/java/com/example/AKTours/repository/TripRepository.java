package com.example.AKTours.repository;

import com.example.AKTours.model.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends CrudRepository<Trip, Long> {

    //    @Query(value = "SELECT trip FROM Trip trip JOIN Hotel hotel ON hotel.id=trip.hotel WHERE hotel.name= ?1")
//    List<Trip> findTripByHotelName(String name);
}
