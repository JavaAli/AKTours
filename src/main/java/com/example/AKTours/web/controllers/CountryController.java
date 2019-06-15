package com.example.AKTours.web.controllers;

import com.example.AKTours.web.service.CountryService;
import io.swagger.annotations.Api;
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

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Object> countiesAll(Model model) {
        return new ResponseEntity<>(countryService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/show/{name}", method = RequestMethod.GET)
    public String reviews1(Model model, @PathVariable String name) throws Exception {

        Model country = model.addAttribute("oneCountry", countryService.findByCountryName(name));

        return "oneCountry";

    }
}
