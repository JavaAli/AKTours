package com.example.AKTours.web.controllers;

import com.example.AKTours.web.service.HotelService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value = {"/", "/hotels"})
public class HotelController {

    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String hotels(Model model) {
        model.addAttribute("hotel", hotelService.findAll());
        return "hotel";
    }

    @RequestMapping(value = "/show/{standard}", method = RequestMethod.GET)
    public String hotelStandard (Model model, @PathVariable("standard") String standard )throws Exception{

        Model hotels2 = model.addAttribute("standardHotel", hotelService.findHotelByStandard(standard));

        return "standardHotel1";
    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public String findByHotelName(Model model, @PathVariable("name") String name)throws  Exception{
        Model hotelName = model.addAttribute("hotelName", hotelService.findByHotelName(name));
        return "hotelName";
    }
}

