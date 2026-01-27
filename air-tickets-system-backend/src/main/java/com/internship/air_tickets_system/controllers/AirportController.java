package com.internship.air_tickets_system.controllers;

import com.internship.air_tickets_system.models.Airport;
import com.internship.air_tickets_system.repositories.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/")
public class AirportController {


    @Autowired
    private AirportRepository airportRepository;

    @GetMapping("/airports")
    public ResponseEntity<List<Airport>> createSeats() {
        System.out.println("Looking for all airports");
        
        return ResponseEntity.ok().body(airportRepository.findAll()); 
    }
}
