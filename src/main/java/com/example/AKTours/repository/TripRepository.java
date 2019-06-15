package com.example.AKTours.repository;


import com.example.AKTours.model.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {

    @Query(value = "SELECT id, depart_date, return_date, adult_price, adult_vacancy, board_type, children_price, children_vacancy, number_days, promo_price, home_airport_id_pk, destin_airport_id_pk, hotel_id_pk from trips where hotel_id_pk = 1", nativeQuery = true)
    List<Trip> findTripByHotelName(String name);
}
