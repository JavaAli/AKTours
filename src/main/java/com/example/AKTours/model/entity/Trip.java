package com.example.AKTours.model.entity;

import com.example.AKTours.web.exceptions.EntityNotFoundException;
import com.example.AKTours.web.service.AirportService;
import com.example.AKTours.web.service.HotelService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@JsonIgnoreProperties({"hotelService", "airportService"})
@Builder
@Entity
@Table(name = "Trips")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Trip implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "depart_date")
    private LocalDate DepartureDate;

    @Column(name = "return_date")
    private LocalDate ReturnDate;

    @Column(name = "number_days")
    private int numberOfDays;

    @Column(name = "board_type")
    private String boardType;

    @Column(name = "adult_price")
    private BigDecimal adultPrice;

    @Column(name = "children_price")
    private BigDecimal childrenPrice;

    @Column(name = "promo_price")
    private BigDecimal promoPrice;

    @Column(name = "adult_vacancy")
    private int adultVacancy;

    @Column(name = "children_vacancy")
    private int childrenVacancy;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hotel_id_pk")
    private Hotel hotel;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "home_airport_id_pk")
    private Airport homeAirport;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "destin_airport_id_pk")
    private Airport destinAirport;

    public Trip(LocalDate departureDate, LocalDate returnDate, int numberOfDays, String boardType, BigDecimal adultPrice,
                BigDecimal childrenPrice, BigDecimal promoPrice, int adultVacancy, int childrenVacancy ) {
        DepartureDate = departureDate;
        ReturnDate = returnDate;
        this.numberOfDays = numberOfDays;
        this.boardType = boardType;
        this.adultPrice = adultPrice;
        this.childrenPrice = childrenPrice;
        this.promoPrice = promoPrice;
        this.adultVacancy = adultVacancy;
        this.childrenVacancy = childrenVacancy;

//        this.homeAirport = homeAirport;
//        this.destinAirport = destinAirport;, Hotel hotel,
//                Airport homeAirport, Airport destinAirport
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trip trip = (Trip) o;
        return numberOfDays == trip.numberOfDays &&
                id.equals(trip.id) &&
                DepartureDate.equals(trip.DepartureDate) &&
                ReturnDate.equals(trip.ReturnDate) &&
                boardType.equals(trip.boardType) &&
                adultPrice.equals(trip.adultPrice) &&
                childrenPrice.equals(trip.childrenPrice) &&
                promoPrice.equals(trip.promoPrice) &&
                Objects.equals(hotel, trip.hotel) &&
                Objects.equals(homeAirport, trip.homeAirport) &&
                Objects.equals(destinAirport, trip.destinAirport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, DepartureDate, ReturnDate, numberOfDays, boardType, adultPrice, childrenPrice, promoPrice, hotel, homeAirport, destinAirport);
    }

    @Override
    public String toString() {
        return "Trip{" +
                "id=" + id +
                ", DepartureDate=" + DepartureDate +
                ", ReturnDate=" + ReturnDate +
                ", numberOfDays=" + numberOfDays +
                ", boardType='" + boardType + '\'' +
                ", adultPrice=" + adultPrice +
                ", childrenPrice=" + childrenPrice +
                ", promoPrice=" + promoPrice +
                ", adultVacancy=" + adultVacancy +
                ", childrenVacancy=" + childrenVacancy +
                ", hotel=" + hotel.getName() +
                ", homeAirport=" + homeAirport.getName() +
                ", destinAirport=" + destinAirport.getName() +
                '}';
    }
}
