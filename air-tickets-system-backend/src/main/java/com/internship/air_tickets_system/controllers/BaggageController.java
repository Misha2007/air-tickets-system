package com.internship.air_tickets_system.controllers;

import com.internship.air_tickets_system.models.Baggage;
import com.internship.air_tickets_system.repositories.BaggageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/baggage")
@Tag(name = "Baggage", description = "Baggage options and pricing")
public class BaggageController {
    @Autowired
    private BaggageRepository baggageRepository;

    @Operation(summary = "Get all baggage options")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "List of baggage options")
    })
    @GetMapping("/all")
    public ResponseEntity<List<Baggage>> createSeats() {
        System.out.println("Looking for all baggage");
        
        return ResponseEntity.ok().body(baggageRepository.findAll()); 
    }
}
