package com.example.AKTours.repository;

import com.example.AKTours.model.entity.Trip;
import org.springframework.data.repository.CrudRepository;

public interface TripRepository extends CrudRepository<Trip, Long> {
}
