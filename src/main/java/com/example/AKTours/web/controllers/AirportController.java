package com.example.AKTours.web.controllers;

import com.example.AKTours.model.entity.Airport;
import com.example.AKTours.web.service.AirportService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Log4j2
@Controller
@RequestMapping(value = {"/airports"})
public class AirportController {

    private final AirportService airportService;

    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @ApiOperation(value = "Shows all airports", response = List.class)
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Object> airports() {
        return new ResponseEntity<>(airportService.findAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Displays airports by name", response = Airport.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully found airports")})
    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public ResponseEntity<Object> findByAirportName(
            @ApiParam(value = "Name of the airport", required = true)
            @PathVariable("name") String name) throws Exception {
        log.info("Invoke findByAirportName method");
        return new ResponseEntity<>(airportService.findAirportByName(name), HttpStatus.OK);
    }

    @ApiOperation(value = "Displays airports in given city", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list of airports")})
    @RequestMapping(value = "/byCity/{name}", method = RequestMethod.GET)
    public ResponseEntity<Object> findAirportByCityName(
            @ApiParam(value = "Name of the airport", required = true)
            @PathVariable("name") String name) throws Exception {
        log.info("Invoke findAirportByCityName method");
        return new ResponseEntity<>(airportService.findAirportByCityName(name), HttpStatus.OK);
    }
}
