package com.example.AKTours.web.controllers;

import com.example.AKTours.web.service.HotelService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = {"/", "/homepage"})
public class HomeController {


    @RequestMapping(method = RequestMethod.GET)
    public String home() {
        return "home";
    }
}