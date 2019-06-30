package com.example.AKTours.repository;

import com.example.AKTours.model.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {

    @Query(value = "SELECT *FROM trips t JOIN hotels h ON h.id=t.hotel_id_pk WHERE h.hotel_name=?1", nativeQuery = true)
    List<Trip> findTripsByHotelName(String name);

    @Query(value = "SELECT *FROM trips t JOIN hotels h ON h.id=t.hotel_id_pk JOIN cities c on h.city_id = c.id WHERE c.city_name=?1", nativeQuery = true)
    List<Trip> findTripsByCityName(String name);

    @Query(value = "SELECT *FROM trips t JOIN hotels h ON h.id=t.hotel_id_pk JOIN cities c on h.city_id = c.id JOIN countries count on c.country_id = count.id WHERE count.country_name=?1", nativeQuery = true)
    List<Trip> findTripsByCountryName(String name);

    @Query(value = "SELECT *FROM trips t JOIN hotels h ON h.id=t.hotel_id_pk JOIN cities c on h.city_id = c.id JOIN countries count on c.country_id = count.id JOIN continents cont on count.continent_id = cont.continent_id WHERE cont.continent_name=?1", nativeQuery = true)
    List<Trip> findTripsByContinentName(String name);

    @Query(value = "SELECT * FROM trips WHERE NOT (depart_date >@RangeTill or return_date < @RangeForm)", nativeQuery = true)
    List<Trip> findTripByDate(LocalDate date);

    List<Trip> findAllByAdultPriceLessThanEqual(BigDecimal price);

    Optional<Trip> findTripByBoardTypeAndDepartureDateAndHomeAirport_NameAndHotel_NameAndReturnDateAndDestinAirport_Name(String boardType, LocalDate departureDate, String homeAirport_name, String hotel_name, LocalDate returnDate, String destinAirport_name);
}
