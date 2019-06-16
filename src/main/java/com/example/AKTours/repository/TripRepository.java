package com.example.AKTours.repository;


import com.example.AKTours.model.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {

    @Query(value = "SELECT id, depart_date, return_date, adult_price, adult_vacancy, board_type, children_price, children_vacancy, number_days, promo_price, home_airport_id_pk, destin_airport_id_pk, hotel_id_pk from trips where hotel_id_pk = 1", nativeQuery = true)
    List<Trip> findTripByHilton(String name);

    @Query(value = "SELECT id, depart_date, return_date, adult_price, adult_vacancy, board_type, children_price, children_vacancy, number_days, promo_price, home_airport_id_pk, destin_airport_id_pk, hotel_id_pk from trips where hotel_id_pk = 3", nativeQuery = true)
    List<Trip> findTripByMariott(String name);

    @Query(value = "SELECT id, depart_date, return_date, adult_price, adult_vacancy, board_type, children_price, children_vacancy, number_days, promo_price, home_airport_id_pk, destin_airport_id_pk, hotel_id_pk from trips where hotel_id_pk = 2", nativeQuery = true)
    List<Trip> findTripByZacisze(String name);

    @Query(value = "SELECT id, depart_date, return_date, adult_price, adult_vacancy, board_type, children_price, children_vacancy, number_days, promo_price, home_airport_id_pk, destin_airport_id_pk, hotel_id_pk from trips where hotel_id_pk = 4", nativeQuery = true)
    List<Trip> findTripByLeCorbusiere(String name);

    @Query(value = "SELECT *FROM trips t JOIN hotels h ON h.id=t.hotel_id_pk WHERE h.hotel_name=?1", nativeQuery = true)
    List<Trip> findTripsByHotelName(String name);

    @Query(value = "SELECT *FROM trips t JOIN hotels h ON h.id=t.hotel_id_pk JOIN cities c on h.city_id = c.id WHERE c.city_name=?1", nativeQuery = true)
    List<Trip> findTripsByCityName(String name);
}
