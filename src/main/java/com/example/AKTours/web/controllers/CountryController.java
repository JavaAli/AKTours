package com.example.AKTours.web.controllers;

import com.example.AKTours.model.entity.Country;
import com.example.AKTours.model.entity.Trip;
import com.example.AKTours.web.exceptions.EntityNotFoundException;
import com.example.AKTours.web.service.CountryService;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Api(value = "Countries Management System")
@CrossOrigin(origins = "http://localhost:4200")
@Controller
@RequestMapping(value = "/country")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @ApiOperation(value = "Displays all countries ", response = Country.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully found countries")})
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Object> countiesAll() {
        return new ResponseEntity<>(countryService.findAll(), HttpStatus.OK);
    }
    @ApiOperation(value = "Find countries by its name", response = Country.class)
    @RequestMapping(value = "/show/{name}", method = RequestMethod.GET)
    public ResponseEntity<Object> countryByName(
            @ApiParam(value = "Name of the country", required = true)
            @PathVariable String name) {
        return new ResponseEntity<>(countryService.findByCountryName(name),HttpStatus.OK);
    }
    @ApiOperation(value = "Find countries by Continent name", response = Country.class)
    @RequestMapping(value = "/byContinent/{name}", method = RequestMethod.GET)
    public ResponseEntity<Object> countryByContinentName(
            @ApiParam(value = "Name of the continent", required = true)
            @PathVariable String name) {
        return new ResponseEntity<>(countryService.findCountryByContinentName(name),HttpStatus.OK);
    }
}
