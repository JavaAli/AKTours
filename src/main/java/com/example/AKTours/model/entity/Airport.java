package com.example.AKTours.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Airports")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="airport_name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "home_airport_id_pk")
    private Set<Trip> tripsHome;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "destin_airport_id_pk")
    private Set<Trip> tripsDestina;
}
