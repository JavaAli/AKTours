package com.example.AKTours.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;
@JsonIgnoreProperties({"countries"})
@Entity
@Table(name = "Continents")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Continent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long continent_id;

    @Column(name = "continent_name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "continent_id")
    private Set<Country> countries;

    public Continent(String name) {
        this.name = name;
    }
}
