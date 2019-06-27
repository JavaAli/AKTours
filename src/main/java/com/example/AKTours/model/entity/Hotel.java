package com.example.AKTours.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Log4j2
@JsonIgnoreProperties({"trips"})
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Hotels")
@Data
@NoArgsConstructor
public class Hotel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "hotel_name")
    private String name;

    @Column(name = "standard")
    private String standard;

    @Column(name = "description")
    private String description;

    @OneToMany(targetEntity = Trip.class, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "hotel_id_pk")
    private Set<Trip> trips;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id")
    private City city;

    public void addTripToHotels(Trip trip) {
        log.info("Setting hotel to new trip");
        trip.setHotel(this);
        log.info("Add new trip to hotel");
        this.trips.add(trip);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hotel hotel = (Hotel) o;
        return id.equals(hotel.id) &&
                name.equals(hotel.name) &&
                Objects.equals(standard, hotel.standard) &&
                Objects.equals(description, hotel.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, standard, description);
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", standard='" + standard + '\'' +
                ", description='" + description + '\'' +
                ", city=" + city +
                '}';
    }
}
