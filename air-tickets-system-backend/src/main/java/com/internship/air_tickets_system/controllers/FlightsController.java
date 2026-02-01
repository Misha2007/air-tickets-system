package com.internship.air_tickets_system.controllers;

import com.internship.air_tickets_system.models.Flight;
import com.internship.air_tickets_system.repositories.FlightRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin()
@RestController
@RequestMapping("/flights")
@Tag(name = "Flights", description = "Flight search and management")
public class FlightsController {

    private final FlightRepository flightsRepository;

    @Autowired
    public FlightsController(FlightRepository flightsRepository) {
        this.flightsRepository = flightsRepository;
    }

    @Operation(summary = "Get all flights")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "List of all flights")
    })
    @GetMapping
    public @ResponseBody List<Flight> getFlights() {
        return StreamSupport.stream(flightsRepository.findAll().spliterator(), false)
                            .collect(Collectors.toList());
    }

    @Operation(
        summary = "Search flights with filters",
        description = "Filter flights by departure, destination, date, seat count and optional max price"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Filtered flights returned")
    })
    @GetMapping("/filtered")
    @ResponseBody
    public List<Flight> getFilteredFlights(@RequestParam String Saabumiskoht, 
                                        @RequestParam String Sihtkoht, 
                                        @RequestParam String LahkumiseAeg, 
                                        @RequestParam int Arv, 
                                        @RequestParam(required = false) Double Hind) {
    LocalDate lahkumiseKuupaev = LocalDate.parse(LahkumiseAeg, DateTimeFormatter.ISO_DATE);

    List<Flight> flightsList = StreamSupport.stream(flightsRepository.findAll().spliterator(), false)
                                             .collect(Collectors.toList());


    return flightsList.stream()
        .filter(s -> s.getSaabumiskoht().equalsIgnoreCase(Saabumiskoht)
                && s.getSihtkoht().equalsIgnoreCase(Sihtkoht)
                && s.getLahkumiseaeg().toLocalDate().isEqual(lahkumiseKuupaev) 
                && s.getIstmed().size() >= Arv
                && (Hind == null || s.getHind() <= Hind)) 
        .collect(Collectors.toList());
}

    @Operation(summary = "Get flight by ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Flight found"),
        @ApiResponse(responseCode = "404", description = "Flight not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Flight> getFlightByNumber(@PathVariable Long id) {
        
        return ResponseEntity.ok().body(flightsRepository.findById(id).orElse(null));
    }


    // @GetMapping("/flights/date") 
    // public ResponseEntity<List<Flight>> getFlightsDates(@PathVariable String datetimeStr) {
    //     // return ResponseEntity.ok().body(flightsRepository.findAllByAttribute(LocalDateTime datetime).orElse(null));
    //     LocalDateTime datetime = LocalDateTime.parse(datetimeStr);
    //     List<Flight> flights = flightsRepository.findByDepartureTime(datetime);
    //     return ResponseEntity.ok(flights);
    //     // @Query("SELECT ")
    //     // return ResponseEntity.ok().body(flightsRepository.findAllByAttribute(LocalDateTime datetime).orElse(null));
    // }

    @Operation(summary = "Get all departure locations")
    @GetMapping("/from") 
    public ResponseEntity<List<String>> getDistinctSaabumiskohad() {
        // return ResponseEntity.ok().body(flightsRepository.findAllByAttribute(LocalDateTime datetime).orElse(null));
        System.out.println("Looking for the going flights");
        List<String> flights = flightsRepository.findDistinctSaabumiskoht();
        return ResponseEntity.ok(flights);
    }

    @Operation(summary = "Get destinations from a departure location")
    @GetMapping("/to") 
    public ResponseEntity<List<String>> GetAllSihtkohad(@RequestParam String Saabumiskoht) {
        System.out.println("Looking for where the spcific input flight is going");
        List<String> flights = flightsRepository.findBySihtkoht(Saabumiskoht);
        return ResponseEntity.ok(flights);
    }

    @Operation(summary = "Get available flight dates for route")
    @GetMapping("/destination") 
    public ResponseEntity<List<String>> GetAllDates(@RequestParam String Saabumiskoht, @RequestParam String Sihtkoht) {
        System.out.println("Find all of the dates that to/from combo has");
        List<String> flights = flightsRepository.findBySihtkohtAndSaabumiskoht(Saabumiskoht, Sihtkoht);
        return ResponseEntity.ok(flights);
    }




    // @GetMapping("/from")
    // public ResponseEntity<List<City>> createSeats() {
    //     System.out.println("Looking for all cities");
        
    //     return ResponseEntity.ok().body(cityRepository.findAll()); 
    // }

    //  @GetMapping("/flights/airport/{saabumislennujid}") 
    // public ResponseEntity<List<Flight>> getFlightsByAirport(@PathVariable Long saabumislennujid) {
    //     System.out.println("Looking for flight with saabumislennujid: " + saabumislennujid);
    //     List<Flight> flights = flightsRepository.findBysaabumislennuJId_Id(saabumislennujid);
    //     return ResponseEntity.ok(flights);
    // }

    @PostMapping("/add")
    public @ResponseBody String addNewFlight(
        @RequestParam String saabumiskoht,
        @RequestParam String sihtkoht,
        @RequestParam String saabumiskohtcode,
        @RequestParam String sihtkohtcode,
        @RequestParam LocalDateTime lahkumiseaeg,
        @RequestParam LocalDateTime saabumiseaeg,
        @RequestParam double hind
    ) {
        String flightNumber = generateFlightNumber();

        Flight flight = new Flight();
        flight.setFlightnumber(flightNumber);
        flight.setSaabumiskoht(saabumiskoht);
        flight.setSihtkoht(sihtkoht);
        flight.setSaabumiskohtcode(saabumiskohtcode);
        flight.setSihtkohtcode(sihtkohtcode);
        flight.setLahkumiseaeg(lahkumiseaeg);
        flight.setSaabumiseaeg(saabumiseaeg);
        flight.setHind(hind);

        flightsRepository.save(flight);
        return "Saved";
    }

    private String generateFlightNumber() {
        Random random = new Random();
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder flightCode = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            flightCode.append(letters.charAt(random.nextInt(letters.length())));
        }

        for (int i = 0; i < 3; i++) {
            flightCode.append(random.nextInt(10));
        }

        return flightCode.toString();
    }
}
