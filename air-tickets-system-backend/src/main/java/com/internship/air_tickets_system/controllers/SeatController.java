package com.internship.air_tickets_system.controllers;

import com.internship.air_tickets_system.models.Flight;
import com.internship.air_tickets_system.models.Seat;
import com.internship.air_tickets_system.repositories.FlightRepository;
import com.internship.air_tickets_system.repositories.SeatRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/seats")
@Tag(name = "Seats", description = "Seat management for flights")
public class SeatController {

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Operation(
        summary = "Create seats for a flight",
        description = "Creates 20 seats for the given flight ID"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Seats successfully created"),
        @ApiResponse(responseCode = "404", description = "Flight not found")
    })
    @PostMapping("/create/{id}")
    public ResponseEntity<List<Seat>> createSeats(
            @Parameter(description = "ID of the flight", example = "1")
            @PathVariable Long id) {

        Flight flight = flightRepository.findById(id).orElse(null);

        if (flight == null) {
            return ResponseEntity.notFound().build();
        }

        for (int i = 1; i <= 20; i++) {
            Seat seat = new Seat(i, false, false, 1, flight, true);
            seatRepository.save(seat);
        }

        return ResponseEntity.ok(seatRepository.findByFlight(flight));
    }

    @Operation(
        summary = "Get seats by flight ID",
        description = "Returns all seats belonging to the specified flight"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Seats retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "Flight not found")
    })
    @GetMapping("/flight/{id}")
    public ResponseEntity<List<Seat>> getSeatsByFlight(
            @Parameter(description = "ID of the flight", example = "1")
            @PathVariable Long id) {

        Flight flight = flightRepository.findById(id).orElse(null);

        if (flight == null) {
            return ResponseEntity.notFound().build();
        }

        List<Seat> seats = seatRepository.findByFlight(flight);
        return ResponseEntity.ok(seats);
    }
}
