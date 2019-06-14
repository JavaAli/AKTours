package com.example.AKTours.web.controllers;

import com.example.AKTours.model.entity.City;
import com.example.AKTours.web.service.CityService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Log4j2
@Controller
@RequestMapping(value = {"/", "/cities"})
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @ApiOperation(value = "Shows all cities", response = List.class)
    @RequestMapping(method = RequestMethod.GET)
    public String cities(Model model) {
        model.addAttribute("cities", cityService.findAll());
        return "cities";
    }

    @ApiOperation(value = "Displays cities by name", response = City.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully found cities")})
    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public String findByCityName(Model model,
                                 @ApiParam(value = "Name of the city", required = true)
                                 @PathVariable("name") String name) throws Exception {
        log.info("Invoke findByCityName method");
        Model cityName = model.addAttribute("cityName", cityService.findCityByName(name));
        return "cityName";
    }
}
