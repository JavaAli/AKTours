package com.example.AKTours.web.service;

import com.example.AKTours.model.entity.Continent;

import com.example.AKTours.repository.ContinentRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Log4j2
@Service
public class ContinentService {

    private final ContinentRepository continentRepository;

    public ContinentService(ContinentRepository continentRepository) {
        this.continentRepository = continentRepository;
    }


    public List<Continent> findAll() {
        log.info("Invoke continent repisitory findAll ");
        List<Continent> collect = StreamSupport.stream(continentRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return collect;
    }

    public Continent findContinentByName(String name) {
        log.info("Invoke continent repository using" + name);
        return continentRepository.findContinentByName(name);
    }


}
