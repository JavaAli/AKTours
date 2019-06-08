package com.example.AKTours.web.controllers;

import com.example.AKTours.model.entity.Hotel;
import com.example.AKTours.web.service.HotelService;
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
@RequestMapping(value = {"/", "/hotels"})
public class HotelController {

    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @ApiOperation(value = "Shows all hotels",response = List.class)
    @RequestMapping(method = RequestMethod.GET)
    public String hotels(Model model) {
        model.addAttribute("hotel", hotelService.findAll());
        return "hotel";
    }

    @ApiOperation(value = "Displays hotels of a given standard", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list of hotels of given standard")})
    @RequestMapping(value = "/show/{standard}", method = RequestMethod.GET)
    public String hotelStandard(Model model,
                                @ApiParam(value = "Number of stars", required = true)
                                @PathVariable("standard") String standard) throws Exception {
        log.info("Invoke findHotelByStandard method");
        Model hotels2 = model.addAttribute("standardHotel", hotelService.findHotelByStandard(standard));
        return "standardHotel1";
    }

    @ApiOperation(value = "Displays hotels by name", response = Hotel.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully found hotel")})
    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public String findByHotelName(Model model,
                                  @ApiParam(value = "Name of the hotel", required = true)
                                  @PathVariable("name") String name) throws Exception {
        log.info("Invoke findByHotelName method");
        Model hotelName = model.addAttribute("hotelName", hotelService.findByHotelName(name));
        return "hotelName";
    }

    @ApiOperation(value = "Displays hotels in given city", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list of hotels")})
    @RequestMapping(value = "/byCity/{name}", method = RequestMethod.GET)
    public String findHotelByCityName(Model model,
                                      @ApiParam(value = "Name of the city", required = true)
                                      @PathVariable("name") String name) throws Exception {
        log.info("Invoke findHotelByCityName method");
        Model hotelName1 = model.addAttribute("hotelName1", hotelService.findHotelByCityName(name));
        return "hotelByCityName";
    }

}

