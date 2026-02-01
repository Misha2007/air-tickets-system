package com.internship.air_tickets_system.controllers;

import com.internship.air_tickets_system.models.Airport;
import com.internship.air_tickets_system.repositories.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/airports")
@Tag(name = "Airports", description = "Airport listing")
public class AirportController {


    @Autowired
    private AirportRepository airportRepository;

    @Operation(summary = "Get all airports")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "List of all airports")
    })
    @GetMapping("/airports")
    public ResponseEntity<List<Airport>> createSeats() {
        System.out.println("Looking for all airports");
        
        return ResponseEntity.ok().body(airportRepository.findAll()); 
    }
}
