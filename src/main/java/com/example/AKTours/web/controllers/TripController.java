package com.example.AKTours.web.controllers;

import com.example.AKTours.model.dtos.TripDto;
import com.example.AKTours.model.entity.Trip;
import com.example.AKTours.model.entity.Visitor;
import com.example.AKTours.web.exceptions.DuplicateEntityException;
import com.example.AKTours.web.exceptions.EntityNotFoundException;
import com.example.AKTours.web.exceptions.NoVacancysException;
import com.example.AKTours.web.service.TripService;
import io.swagger.annotations.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import javax.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return new ResponseEntity<>(tripService.findTripByHotelName(name), HttpStatus.OK);

    }

    @ApiOperation(value = "Displays trips by city name", response = Trip.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully found trips")})
    @RequestMapping(value = "/tripsByCity/{name}", method = RequestMethod.GET)
    public ResponseEntity<List> findTripsByCityName(
            @ApiParam(value = "Name of the city", required = true)
            @PathVariable("name") String name) throws EntityNotFoundException {
        log.info("Invoke findTripsByCityName method");
        if (name.equals("Select")) {
            return new ResponseEntity<>(tripService.findAllTrips(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(tripService.findTripByCityName(name), HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Displays trips by country name", response = Trip.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully found trips")})
    @RequestMapping(value = "/tripsByCountry/{name}", method = RequestMethod.GET)
    public ResponseEntity<List> findTripsByCountryName(
            @ApiParam(value = "Name of the country", required = true)
            @PathVariable("name") String name) throws EntityNotFoundException {
        log.info("Invoke findTripsByCountryName method");
        if (name.equals("Select")) {
            return new ResponseEntity<>(tripService.findAllTrips(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(tripService.findTripByCountryName(name), HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Displays trips by continent name", response = Trip.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully found trips")})
    @RequestMapping(value = "/tripsByContinent/{name}", method = RequestMethod.GET)
    public ResponseEntity<List> findTripsByContinentName(
            @ApiParam(value = "Name of the continent", required = true)
            @PathVariable("name") String name) throws EntityNotFoundException {
        log.info("Invoke findTripsByContinentName method");
        if (name.equals("Select")) {
            return new ResponseEntity<>(tripService.findAllTrips(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(tripService.findTripByContinentName(name), HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Displays trips by date", response = Trip.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully found trips")})
    @RequestMapping(value = "/tripsByDate/{date}", method = RequestMethod.GET)
    public ResponseEntity<List> findTripsByDate(
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @ApiParam(value = "Date of the trip", required = true)
            @PathVariable("date") LocalDate date) throws EntityNotFoundException {
        log.info("Invoke findTripsByContinentName method");
        return new ResponseEntity<>(tripService.findTripByDate(date), HttpStatus.OK);
    }

    @ApiOperation(value = "Displays trips by price", response = Trip.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully found trips")})
    @RequestMapping(value = "/tripsByPrice/{price}", method = RequestMethod.GET)
    public ResponseEntity<List> findTripsByPrice(
            @ApiParam(value = "Price of the trip", required = true)
            @PathVariable("price") BigDecimal price) throws EntityNotFoundException {
        log.info("Invoke findTripsByContinentName method");
        return new ResponseEntity<>(tripService.findTripByPrice(price), HttpStatus.OK);
    }

    @ApiOperation(value = "Add new trip to base", response = TripDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully saved trip"),
            @ApiResponse(code = 400, message = "Validation errors or JSON Parse error"),
            @ApiResponse(code = 404, message = "Given hotel or airport not exist in base"),
            @ApiResponse(code = 409, message = "Trip with given data already exists in database")})
    @RequestMapping(value = "/trips", method = RequestMethod.POST)
    public ResponseEntity<Object> saveTrips(
            @ApiParam(value = "Trip to add", required = true)
            @Valid @RequestBody TripDto tripDto) throws EntityNotFoundException, DuplicateEntityException {
        log.info("Invoke addTrip method");
        return new ResponseEntity<>(tripService.addTrip(tripDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Displays trips by id", response = Trip.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully found trips")})
    @RequestMapping(value = "/trips/{id}", method = RequestMethod.GET)
    public ResponseEntity<Trip> findTripsById(
            @ApiParam(value = "Id of Trip", required = true)
            @PathVariable("id") Long tripId){
        log.info("Invoke findTripsByContinentName method");
        return new ResponseEntity<>(tripService.displayTripById(tripId), HttpStatus.OK);
    }

    @ApiOperation(value = "Add visitors to trip", response = Trip.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully saved new version of Trip"),
            @ApiResponse(code = 400, message = "Validation errors or JSON Parse error"),
            @ApiResponse(code = 404, message = "Given trip not exist in base"),
            @ApiResponse(code = 406, message = "No vacancies in this trip"),
            @ApiResponse(code = 409, message = "Visitor is already enlisted on this trip")})
    @RequestMapping(value = "/trips/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> buyTrip(
            @ApiParam(value = "Id of Trip", required = true)
            @PathVariable("id") Long tripId,
            @ApiParam(value = "Visitor buying trip", required = true)
            @RequestBody Visitor visitor) throws NoVacancysException, EntityNotFoundException {
        log.info("Invoke addVisitorsToTrip method");
        return new ResponseEntity<>(tripService.addVisitorsToTrip(tripId,visitor), HttpStatus.OK);
    }
}

