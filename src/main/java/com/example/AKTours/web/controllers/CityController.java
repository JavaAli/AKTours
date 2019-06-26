package com.example.AKTours.web.controllers;

import com.example.AKTours.model.entity.City;
import com.example.AKTours.web.exceptions.EntityNotFoundException;
import com.example.AKTours.web.service.CityService;
import io.swagger.annotations.*;

import java.util.List;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Api(value = "City Management System")
@Controller
@CrossOrigin(origins = "http://localhost:4200")
@Log4j2
@RequestMapping(value = {"/cities"})
public class CityController {

    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @ApiOperation(value = "Shows all cities", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully found cities")})
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Object> allCities() {
        return new ResponseEntity<>(cityService.findAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Displays cities by name", response = City.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully found cities")})
    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public ResponseEntity<Object> findByCityName(
            @ApiParam(value = "Name of the city", required = true)
            @PathVariable("name") String name) throws Exception {
        log.info("Invoke findByCityName method");
        return new ResponseEntity<>(cityService.findCityByName(name), HttpStatus.OK);
    }

    @ApiOperation(value = "Displays cities by continent name", response = City.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully found cities")})
    @RequestMapping(value = "/byContinentName/{name}", method = RequestMethod.GET)
    public ResponseEntity<Object> findCitiesByContinentName(
            @ApiParam(value = "Name of the continent", required = true)
            @PathVariable("name") String name) throws EntityNotFoundException {
        log.info("Invoke findCitiesByContinentName method");
        return new ResponseEntity<>(cityService.findCityByContinentName(name), HttpStatus.OK);
    }

    @ApiOperation(value = "Displays cities by country name", response = City.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully found cities")})
    @RequestMapping(value = "/byCountryName/{name}", method = RequestMethod.GET)
    public ResponseEntity<Object> findCitiesByCountryName(
            @ApiParam(value = "Name of the continent", required = true)
            @PathVariable("name") String name) throws EntityNotFoundException {
        log.info("Invoke findCitiesByCountryName method");
        return new ResponseEntity<>(cityService.findCityByCountryName(name), HttpStatus.OK);
    }
}
