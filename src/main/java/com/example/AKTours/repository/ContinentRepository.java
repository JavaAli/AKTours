package com.example.AKTours.repository;

import com.example.AKTours.model.entity.Continent;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ContinentRepository extends CrudRepository<Continent, Long> {

     Continent findContinentByName(String name);
}
