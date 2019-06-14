package com.example.AKTours.web.controllers;

import com.example.AKTours.web.service.CountryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/country")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String hotels(Model model) {
        model.addAttribute("countries", countryService.findAll());
        return "countries";
    }

    @RequestMapping(value = "/show/{name}", method = RequestMethod.GET)
    public String reviews1(Model model, @PathVariable String name) throws Exception {

        Model country = model.addAttribute("oneCountry", countryService.findByCountryName(name));

        return "oneCountry";

    }
}
