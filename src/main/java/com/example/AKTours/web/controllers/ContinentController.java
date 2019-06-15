package com.example.AKTours.web.controllers;

import com.example.AKTours.model.entity.Continent;
import com.example.AKTours.web.service.ContinentService;
import io.swagger.annotations.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
@Api(value = "Continent Management System")
@CrossOrigin(origins = "http://localhost:4200")
@Log4j2
@Controller
@RequestMapping(value = {"/continents"})
public class ContinentController {

    private final ContinentService continentService;

    public ContinentController(ContinentService continentService) {

        this.continentService = continentService;
    }

    @ApiOperation(value = "Shows all continents", response = List.class)
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Object> continents() {
        return new ResponseEntity<>(continentService.findAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Displays continents by name", response = Continent.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully found continents")})
    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public String findByContinentName(Model model,
                                      @ApiParam(value = "Name of the continent", required = true)
                                      @PathVariable("name") String name) throws Exception {
        log.info("Invoke findByContinentName method");
        Model continentName = model.addAttribute("continentName", continentService.findContinentByName(name));
        return "continentName";
    }
}
