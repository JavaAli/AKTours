package com.example.AKTours.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@JsonIgnoreProperties({"trips"})
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Hotels")
@Data
@NoArgsConstructor
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "hotel_name")
    private String name;

    @Column(name = "standard")
    private String standard;

    @Column(name = "description")
    private String description;

    @OneToMany(targetEntity = Trip.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "hotel_id_pk")
    private Set<Trip> trips;


}
