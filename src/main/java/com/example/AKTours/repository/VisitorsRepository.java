package com.example.AKTours.repository;

import com.example.AKTours.model.entity.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VisitorsRepository extends JpaRepository<Visitor, Long> {

    @Query(value = "SELECT *FROM visitors v JOIN trips t ON v.trip_id_pk=t.id WHERE t.id=?1 AND v.first_name=?2 " +
            "AND v.last_name=?3 AND v.city=?4 AND v.street=?5 AND v.street_nr=?6", nativeQuery = true)
    Optional<Visitor> findVisitorByDataAndTripId(Long id, String firstName, String lastName, String city,
                                                 String street, String streetNr);
}
