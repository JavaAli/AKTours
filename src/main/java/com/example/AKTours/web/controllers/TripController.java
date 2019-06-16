package com.example.AKTours.web.controllers;

import com.example.AKTours.model.entity.Trip;
import com.example.AKTours.web.exceptions.EntityNotFoundException;
import com.example.AKTours.web.service.TripService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@Api(value = "Trips Management System")
@CrossOrigin(origins = "http://localhost:4200")

public class TripController {
    private final TripService tripService;

    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @ApiOperation(value = "Displays all trips ", response = Trip.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully found trips")})
    @RequestMapping(value = "/trips", method = RequestMethod.GET)
    public ResponseEntity<Object> findAllTrips() throws EntityNotFoundException {
        log.info("Invoke findAllTrips method");
        return new ResponseEntity<>(tripService.findAllTrips(), HttpStatus.OK);

    }

    @ApiOperation(value = "Displays trips by hotel name", response = Trip.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully found trips")})
    @RequestMapping(value = "/tripsByHotel/{name}", method = RequestMethod.GET)
    public ResponseEntity<List> findTripsByHotelName(
                                           @ApiParam(value = "Name of the hotel", required = true)
                                           @PathVariable("name") String name) throws EntityNotFoundException {
        log.info("Invoke findTripsByHotelName method");
        return new ResponseEntity<>(tripService.findTripByHotelNameNew(name), HttpStatus.OK);

    }
    @ApiOperation(value = "Displays trips by city name", response = Trip.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully found trips")})
    @RequestMapping(value = "/tripsByCity/{name}", method = RequestMethod.GET)
    public ResponseEntity<List> findTripsByCityName(
            @ApiParam(value = "Name of the city", required = true)
            @PathVariable("name") String name) throws EntityNotFoundException {
        log.info("Invoke findTripsByCityName method");
        return new ResponseEntity<>(tripService.findTripByCityName(name), HttpStatus.OK);

    }
}
