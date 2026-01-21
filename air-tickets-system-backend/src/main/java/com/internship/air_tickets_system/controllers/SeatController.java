package com.internship.air_tickets_system.controllers;

import com.internship.air_tickets_system.models.Flight;
import com.internship.air_tickets_system.models.Seat;
import com.internship.air_tickets_system.repositories.FlightRepository;
import com.internship.air_tickets_system.repositories.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/seats")
public class SeatController {

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private FlightRepository flightRepository;

    @PostMapping("/create/{id}")
    public ResponseEntity<List<Seat>> createSeats(@PathVariable Long id) {
        System.out.println("Looking for flight with ID: " + id);
        
        Flight flight = flightRepository.findById(id).orElse(null);
        
        if (flight == null) {
            throw new RuntimeException("Flight not found!");
        }

        for (int i = 1; i <= 20; i++) { 
            Seat seat = new Seat(i, false, false, 1, flight, true); 
            seatRepository.save(seat);
        }
        
        return ResponseEntity.ok().body(seatRepository.findAll()); 
    }

    @GetMapping("/flight/{id}")
    public ResponseEntity<List<Seat>>  getSeatsByFlight(@PathVariable Long id) {
        System.out.println("Looking for flight with ID: " + id);
        System.out.println("Found flight: " + flightRepository.findById(id).get());

        Flight flight = flightRepository.findById(id).get();
        System.out.println(seatRepository.findByFlight(flight));
        System.out.println("Found flight: " + flight);
        if (flight == null) {
            throw new RuntimeException("Flight not found!");
        }
        List<Seat> seats = seatRepository.findByFlight(flight);
        System.out.println(seats);
        return ResponseEntity.ok().body(seats);

    }
}
