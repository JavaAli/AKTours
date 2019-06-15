package com.example.AKTours.web.controllers;

import com.example.AKTours.web.service.TripService;
import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Log4j2
@Controller
@Api(value = "Trips Management System")
@CrossOrigin(origins = "http://localhost:4200")
public class TripController {

    private final TripService tripService;

    @Autowired
    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @RequestMapping(value = "/trips", method = RequestMethod.GET)
    public ResponseEntity<Object> allTrips() {
        return new ResponseEntity<>(tripService.findAllTrips(), HttpStatus.OK);
    }
}
