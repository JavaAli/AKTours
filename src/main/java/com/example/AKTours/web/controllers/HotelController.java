package com.example.AKTours.web.controllers;

import com.example.AKTours.model.entity.Hotel;
import com.example.AKTours.web.exceptions.EntityNotFoundException;
import com.example.AKTours.web.service.HotelService;
import io.swagger.annotations.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Log4j2
@RestController
@Api(value="Hotel Management System")
@CrossOrigin(origins = "http://localhost:4200")

public class HotelController {

    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @ApiOperation(value = "Shows all hotels",response = Hotel.class)
    @RequestMapping(value = "/hotels", method = RequestMethod.GET)
    public ResponseEntity<Object> hotels() {
        return new ResponseEntity<>(hotelService.findAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Displays hotels of a given standard", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list of hotels of given standard")})
    @RequestMapping(value = "/show/{standard}", method = RequestMethod.GET)
    public ResponseEntity<List> hotelStandard(
                                @ApiParam(value = "Number of stars", required = true)
                                @PathVariable("standard") String standard) throws EntityNotFoundException {
        log.info("Invoke findHotelByStandard method");
        return new ResponseEntity<>(hotelService.findHotelByStandard(standard), HttpStatus.OK);
    }

    @ApiOperation(value = "Displays hotels by name", response = Hotel.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully found hotel")})
    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public ResponseEntity<Hotel> findByHotelName(
                                  @ApiParam(value = "Name of the hotel", required = true)
                                  @PathVariable("name") String name) throws EntityNotFoundException {
        log.info("Invoke findByHotelName method");
        return new ResponseEntity<>(hotelService.findByHotelName(name),HttpStatus.OK);
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

