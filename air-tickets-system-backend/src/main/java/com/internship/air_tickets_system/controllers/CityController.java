package com.internship.air_tickets_system.controllers;

import com.internship.air_tickets_system.models.City;
import com.internship.air_tickets_system.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/cities")
public class CityController {


    @Autowired
    private CityRepository cityRepository;


    @GetMapping("/all")
    public ResponseEntity<List<City>> createSeats() {
        System.out.println("Looking for all cities");
        
        return ResponseEntity.ok().body(cityRepository.findAll()); 
    }



    @GetMapping("/city/text")
    @ResponseBody
    public ResponseEntity<List<City>> getCitybyText( @PathVariable String text ) {
        System.out.println("Looking for all of the matching cities by text");
        List<City> match = cityRepository.findBylinnaNimiContainingIgnoreCase(text);   
        return ResponseEntity.ok(match);
    }

 

}
