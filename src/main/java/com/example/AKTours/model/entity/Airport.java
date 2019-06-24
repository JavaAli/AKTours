package com.example.AKTours.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
@JsonIgnoreProperties({"tripsDestina", "tripsHome"})
@Entity
@Table(name = "Airports")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Airport implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="airport_name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "home_airport_id_pk")
    private Set<Trip> tripsHome;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "destin_airport_id_pk")
    private Set<Trip> tripsDestina;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id")
    private City city;

    public void addTripToTripsHome (Trip trip){
       trip.setHomeAirport(this);
       this.tripsHome.add(trip);
    }
    public void addTripToTripsDest (Trip trip){
        trip.setDestinAirport(this);
        this.tripsDestina.add(trip);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airport airport = (Airport) o;
        return id.equals(airport.id) &&
                name.equals(airport.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Airport{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city=" + city +
                '}';
    }
}
