package com.internship.air_tickets_system.controllers;

import com.internship.air_tickets_system.models.City;
import com.internship.air_tickets_system.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/cities")
@Tag(name = "Cities", description = "City search and listing")
public class CityController {


    @Autowired
    private CityRepository cityRepository;


    @Operation(summary = "Get all cities")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "List of all cities")
    })
    @GetMapping("/all")
    public ResponseEntity<List<City>> createSeats() {
        System.out.println("Looking for all cities");
        
        return ResponseEntity.ok().body(cityRepository.findAll()); 
    }



    @Operation(
        summary = "Search cities by name",
        description = "Returns cities whose name contains the given text (case-insensitive)"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Matching cities returned")
    })
    @GetMapping("/search")
    public ResponseEntity<List<City>> getCitybyText( @PathVariable String text ) {
        System.out.println("Looking for all of the matching cities by text");
        List<City> match = cityRepository.findBylinnaNimiContainingIgnoreCase(text);   
        return ResponseEntity.ok(match);
    }

 

}
