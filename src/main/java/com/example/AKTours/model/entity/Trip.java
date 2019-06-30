package com.example.AKTours.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private LocalDate departureDate;

    @Column(name = "return_date")
    private LocalDate returnDate;

    @Column(name = "number_days")
    private long numberOfDays;

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

    public Trip(LocalDate departureDate, LocalDate returnDate, long numberOfDays, String boardType, BigDecimal adultPrice,
                BigDecimal childrenPrice, BigDecimal promoPrice, int adultVacancy, int childrenVacancy) {
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.numberOfDays = numberOfDays;
        this.boardType = boardType;
        this.adultPrice = adultPrice;
        this.childrenPrice = childrenPrice;
        this.promoPrice = promoPrice;
        this.adultVacancy = adultVacancy;
        this.childrenVacancy = childrenVacancy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trip trip = (Trip) o;
        return numberOfDays == trip.numberOfDays &&
                id.equals(trip.id) &&
                departureDate.equals(trip.departureDate) &&
                returnDate.equals(trip.returnDate) &&
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
        return Objects.hash(id, departureDate, returnDate, numberOfDays, boardType, adultPrice, childrenPrice,
                promoPrice, hotel, homeAirport, destinAirport);
    }

    @Override
    public String toString() {
        return "Trip{" +
                "id=" + id +
                ", departureDate=" + departureDate +
                ", returnDate=" + returnDate +
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
