package com.internship.air_tickets_system.controllers;

import com.internship.air_tickets_system.models.Baggage;
import com.internship.air_tickets_system.repositories.BaggageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/baggage")
public class BaggageController {
    @Autowired
    private BaggageRepository baggageRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Baggage>> createSeats() {
        System.out.println("Looking for all baggage");
        
        return ResponseEntity.ok().body(baggageRepository.findAll()); 
    }
}
