package com.example.AKTours.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@Builder
@Data
@Entity
@JsonIgnoreProperties({"hotels", "airports"})
@NoArgsConstructor
@Table(name = "Cities")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "city_name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "city_id")
    private Set<Hotel> hotels;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "city_id")
    private Set<Airport> airports;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "country_id")
    private Country country;


    public City(String name, Country country) {
        this.name = name;
        this.country = country;
    }
}
